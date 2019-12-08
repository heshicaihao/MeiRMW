package com.heshicai.meirmw.weibo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.component.Authorize;
import com.tencent.weibo.sdk.android.component.sso.AuthHelper;
import com.tencent.weibo.sdk.android.component.sso.OnAuthListener;
import com.tencent.weibo.sdk.android.component.sso.WeiboToken;

public class ShareQQ {
	private final Context mContext;

	public ShareQQ(Context context) {
		mContext = context;
	}

	/**
	 * 腾讯微博
	 */

	public void auth(long appid, String app_secket) {
		final Context context = mContext.getApplicationContext();
		// 注册当前应用的appid和appkeysec，并指定一个OnAuthListener
		// OnAuthListener在授权过程中实施监听
		AuthHelper.register(mContext, appid, app_secket, new OnAuthListener() {

			// 如果当前设备没有安装腾讯微博客户端，走这里
			@SuppressLint("WrongConstant")
			@Override
			public void onWeiBoNotInstalled() {
				Toast.makeText(mContext, "onWeiBoNotInstalled", 1000).show();
				AuthHelper.unregister(mContext);
				Intent i = new Intent(mContext, Authorize.class);
				mContext.startActivity(i);
			}

			// 如果当前设备没安装指定版本的微博客户端，走这里
			@SuppressLint("WrongConstant")
			@Override
			public void onWeiboVersionMisMatch() {
				Toast.makeText(mContext, "onWeiboVersionMisMatch", 1000).show();
				AuthHelper.unregister(mContext);
				Intent i = new Intent(mContext, Authorize.class);
				mContext.startActivity(i);
			}

			// 如果授权失败，走这里
			@SuppressLint("WrongConstant")
			@Override
			public void onAuthFail(int result, String err) {
				Toast.makeText(mContext, "result : " + result, 1000).show();
				AuthHelper.unregister(mContext);
			}

			// 授权成功，走这里
			// 授权成功后，所有的授权信息是存放在WeiboToken对象里面的，可以根据具体的使用场景，将授权信息存放到自己期望的位置，
			// 在这里，存放到了applicationcontext中
			@SuppressLint("WrongConstant")
			@Override
			public void onAuthPassed(String name, WeiboToken token) {
				Toast.makeText(mContext, "passed", 1000).show();
				//
				Util.saveSharePersistent(context, "ACCESS_TOKEN",
						token.accessToken);
				Util.saveSharePersistent(context, "EXPIRES_IN",
						String.valueOf(token.expiresIn));
				Util.saveSharePersistent(context, "OPEN_ID", token.openID);
				// Util.saveSharePersistent(context, "OPEN_KEY", token.omasKey);
				Util.saveSharePersistent(context, "REFRESH_TOKEN", "");
				// Util.saveSharePersistent(context, "NAME", name);
				// Util.saveSharePersistent(context, "NICK", name);
				Util.saveSharePersistent(context, "CLIENT_ID", Util.getConfig()
						.getProperty("APP_KEY"));
				Util.saveSharePersistent(context, "AUTHORIZETIME",
						String.valueOf(System.currentTimeMillis() / 1000l));
				AuthHelper.unregister(mContext);
			}
		});

		AuthHelper.auth(mContext, "");
	}

}

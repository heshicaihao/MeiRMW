package com.heshicai.meirmw.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.heshicai.meirmw.weibo.Constants;
import com.heshicai.meirmw.weibo.OnLoginSuccessListener;
import com.heshicai.meirmw.weibo.WeiboListener;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseRequest;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

public class ShareActivity extends Activity implements OnLoginSuccessListener {
	private SsoHandler mSsoHandler;
	private IWeiboShareAPI mWeiboShareApi;
	private AuthInfo mAuthInfo;
	private Oauth2AccessToken mOauth2AccessToken;
	private IWeiboShareAPI mWeiboShareAPI;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mWeiboShareApi = WeiboShareSDK.createWeiboAPI(this, Constants.APP_KEY);
		mWeiboShareApi.registerApp(); // 将应用注册到微博客户端

		mAuthInfo = new AuthInfo(this, Constants.APP_KEY,
				Constants.REDIRECT_URL, Constants.SCOPE);

		mSsoHandler = new SsoHandler(this, mAuthInfo);
		shareWidthOauth();
	}

	public void shareWidthOauth() {
		Oauth2AccessToken accessToken = getAccessToken();
		if (accessToken != null) {
			String token = accessToken.getToken();
			System.out.println(mWeiboShareApi == null);
			System.out.println(getRequest() == null);
			System.out.println(mAuthInfo == null);
			System.out.println(token == null);
			mWeiboShareApi.sendRequest(this, getRequest(), mAuthInfo, token,
					new WeiboListener(mOauth2AccessToken, this));
			finish();
		} else {
			mSsoHandler.authorize(new WeiboListener(this, mOauth2AccessToken,
					this));
		}
	}

	public BaseRequest getRequest() {
		WeiboMultiMessage message = new WeiboMultiMessage();
		TextObject obj = new TextObject();
		obj.text = "@逸灬明";
		message.textObject = obj;

		// 需要一个发送消息请求
		SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
		request.multiMessage = message;
		request.transaction = String.valueOf(System.currentTimeMillis());

		return request;

	}

	public Oauth2AccessToken getAccessToken() {

		if (mOauth2AccessToken == null) {
			SharedPreferences sp = getSharedPreferences("weiboToken",
					Context.MODE_PRIVATE);
			String token = sp.getString("token", null);
			String uid = sp.getString("uid", null);
			long time = sp.getLong("time", 0);

			if (token != null) {
				mOauth2AccessToken = new Oauth2AccessToken();
				mOauth2AccessToken.setToken(token);
				mOauth2AccessToken.setUid(uid);
				mOauth2AccessToken.setExpiresTime(time);
			}
		}

		return mOauth2AccessToken;
	}

	@Override
	public void onSuccess() {
		mWeiboShareApi.sendRequest(ShareActivity.this, getRequest(), mAuthInfo,
				getAccessToken().getToken(), new WeiboListener(
						mOauth2AccessToken, this));
		finish();
	}

}

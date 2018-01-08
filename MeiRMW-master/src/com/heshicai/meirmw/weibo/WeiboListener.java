package com.heshicai.meirmw.weibo;

import java.sql.Date;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;

public class WeiboListener implements WeiboAuthListener {

	private OnLoginSuccessListener mListener;
	private Oauth2AccessToken mAccessToken;
	private final Activity mActivity;

	public WeiboListener(OnLoginSuccessListener l,
			Oauth2AccessToken accessToken, Activity activity) {
		mListener = l;
		mAccessToken = accessToken;
		mActivity = activity;
	}

	public WeiboListener(Oauth2AccessToken accessToken, Activity activity) {
		mAccessToken = accessToken;
		mActivity = activity;
	}

	@Override
	public void onComplete(Bundle arg0) {
		mAccessToken = Oauth2AccessToken.parseAccessToken(arg0);

		if (mAccessToken != null) {
			saveToken(mAccessToken);
		}

		if (mListener != null) {
			mListener.onSuccess();
		}

		String token = mAccessToken.getToken();
		// 获取用户的唯一标示符，同一个用在无论在哪里登入或者使用什么第三方程序登入
		// uid都是一样的，但不是用户名
		String uid = mAccessToken.getUid();
		// token的超时时间
		long time = mAccessToken.getExpiresTime();

		Log.i("bm", "token : " + token);
		Log.i("bm", "uid : " + uid);
		Log.i("bm", "time : " + new Date(time).toLocaleString());
	}

	@Override
	public void onCancel() {
	}

	@Override
	public void onWeiboException(WeiboException arg0) {
	}

	public void saveToken(Oauth2AccessToken token) {
		SharedPreferences sp = mActivity.getSharedPreferences("weiboToken",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();

		editor.putInt("login", 1);
		editor.putString("token", token.getToken());
		editor.putString("uid", token.getUid());
		editor.putLong("time", token.getExpiresTime());
		editor.commit();
	}
}

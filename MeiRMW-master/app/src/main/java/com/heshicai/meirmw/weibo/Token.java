package com.heshicai.meirmw.weibo;

import android.content.Context;
import android.content.SharedPreferences;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

public class Token {
	private final Context mContext;
	private Oauth2AccessToken mAccessToken;

	public Token(Context context, Oauth2AccessToken token) {
		mAccessToken = token;
		mContext = context;
	}

	public Oauth2AccessToken getAccessToken() {

		if (mAccessToken == null) {
			SharedPreferences sp = mContext.getSharedPreferences("weiboToken",
					Context.MODE_PRIVATE);

			String token = sp.getString("token", null);
			String uid = sp.getString("uid", null);
			long time = sp.getLong("time", 0);

			if (token != null) {
				mAccessToken = new Oauth2AccessToken();
				mAccessToken.setToken(token);
				mAccessToken.setUid(uid);
				mAccessToken.setExpiresTime(time);
			}
		}

		return mAccessToken;
	}
}

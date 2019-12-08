package com.heshicai.meirmw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.heshicai.meirmw.weibo.Constants;
import com.heshicai.meirmw.weibo.ConstantsQQ;
import com.heshicai.meirmw.weibo.ShareQQ;
import com.heshicai.meirmw.weibo.WeiboListener;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

public class LoginActivity extends Activity implements OnClickListener {
	private final Intent intent = new Intent();

	private AuthInfo mAuthInfo;

	private IWeiboShareAPI mWeiboShareApi;

	private SsoHandler mSsoHandler;

	private Oauth2AccessToken mOauth2AccessToken;

	private Context context = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.login);
		// 新浪微博
		mWeiboShareApi = WeiboShareSDK.createWeiboAPI(this, Constants.APP_KEY);
		mWeiboShareApi.registerApp();

		mAuthInfo = new AuthInfo(this, Constants.APP_KEY,
				Constants.REDIRECT_URL, Constants.SCOPE);

		mSsoHandler = new SsoHandler(this, mAuthInfo);
		// 登陆界面的返回按钮
		ImageButton headerLeftBtn = (ImageButton) findViewById(R.id.header_left_btn);
		headerLeftBtn.setOnClickListener(this);
		// 腾讯微博
		context = this.getApplicationContext();

		init();
	}

	private void initqq() {
		ShareQQ qq = new ShareQQ(this);
		qq.auth(ConstantsQQ.appid, ConstantsQQ.app_secket);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (mSsoHandler != null) {
			mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
		}
	}

	@Override
	protected void onResume() {
		SharedPreferences sp = getSharedPreferences("weiboToken",
				Context.MODE_PRIVATE);
		int login = sp.getInt("login", 0);
		if (login != 0) {
			Intent intent = new Intent(this, LoginSuccessActivity.class);
			startActivity(intent);
			finish();
		}
		super.onResume();
	}

	private void init() {

		// Login界面头文件中的返回功能的ImageButton
		ImageButton returnBtn = (ImageButton) findViewById(R.id.header_left_btn);
		// login界面中新浪微博的Button
		Button sinaBtn = (Button) findViewById(R.id.sina_login_btn);
		// login界面中腾讯微博的Button
		Button qqBtn = (Button) findViewById(R.id.qq_login_btn);
		// login界面中邮箱的EditText
		EditText email = (EditText) findViewById(R.id.email);
		// login界面中密码的EditText
		EditText password = (EditText) findViewById(R.id.password);
		// login界面中登录按钮
		Button loginBtn = (Button) findViewById(R.id.login_button);
		// login界面中注册新账号的按钮
		Button registerBtn = (Button) findViewById(R.id.register_button);
		returnBtn.setOnClickListener(this);
		sinaBtn.setOnClickListener(this);
		qqBtn.setOnClickListener(this);
		email.setOnClickListener(this);
		password.setOnClickListener(this);
		loginBtn.setOnClickListener(this);
		registerBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		switch (id) {
		case R.id.header_left_btn:
			finish();
			break;
		case R.id.sina_login_btn:
			loginWidthOauth();
			break;
		case R.id.qq_login_btn:
			initqq();
			break;
		case R.id.email:

			break;
		case R.id.password:

			break;
		case R.id.login_button:

			break;
		case R.id.register_button:
			intent.setClass(this, LoginRegisterActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	/**
	 * 通过该方法可以唤起Sso的登入
	 * 
	 * @param view
	 */
	public void loginWidthOauth() {
		mSsoHandler.authorize(new WeiboListener(mOauth2AccessToken, this));
	}

}

package com.heshicai.meirmw;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class LoginRegisterActivity extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_new_account);
		ImageButton registerReturnBtn = (ImageButton) findViewById(R.id.register_header_left_btn);
		registerReturnBtn.setOnClickListener(this);
		init();
	}

	private void init() {
		// 注册新账号界面中头部左边的返回按钮
		ImageButton returnRegisterBtn = (ImageButton) findViewById(R.id.register_header_left_btn);
		// 注册新账号界面中常用昵称的EditText
		EditText commonEdit = (EditText) findViewById(R.id.common_name);
		// 注册新账号界面中登录邮箱的EditText
		EditText loginEmailEdit = (EditText) findViewById(R.id.login_email);
		// 注册新账号界面中登录密码的EditText
		EditText loginPasswordEdit = (EditText) findViewById(R.id.login_password);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		finish();

	}
}

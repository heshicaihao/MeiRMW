package com.heshicai.meirmw.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.heshicai.meirmw.R;

public class LoginSuccessActivity extends Activity implements OnClickListener {
	private TextView name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_success);
		View btn1 = findViewById(R.id.btn1);
		View btn2 = findViewById(R.id.btn2);
		View btn3 = findViewById(R.id.btn3);
		View btn4 = findViewById(R.id.btn4);
		name = (TextView) findViewById(R.id.name);

		View rebtn = findViewById(R.id.frg_homepage_header_left_btn);
		useruID();
		rebtn.setOnClickListener(this);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
	}

	public void useruID() {
		SharedPreferences sp = getSharedPreferences("weiboToken",
				Context.MODE_PRIVATE);

		String uid = sp.getString("uid", null);
		name.setText(uid);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.frg_homepage_header_left_btn:
			finish();

			break;
		default:
			break;
		}
	}
}

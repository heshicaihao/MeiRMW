package com.heshicai.meirmw.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;

import com.igexin.sdk.PushManager;

public class NotificationSettingActivity extends Activity implements
		OnCheckedChangeListener, OnClickListener {
	private Switch mSwitch;
	private final PushManager pushManager = new PushManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.notification_setting);

		mSwitch = (Switch) findViewById(R.id.open);

		View rebtn = findViewById(R.id.header_left_btn);

		rebtn.setOnClickListener(this);

		mSwitch.setOnCheckedChangeListener(this);

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			isChecked = !isChecked;
			pushManager.turnOnPush(getApplicationContext());
		} else {
			isChecked = !isChecked;
			pushManager.turnOffPush(getApplicationContext());
		}

	}

	@Override
	public void onClick(View v) {
		finish();
	}
}

package com.heshicai.meirmw.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.heshicai.meirmw.R;

public class AboutUsSettingActivity extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.about_us_setting);

		ImageButton aboutUsBtn = (ImageButton) findViewById(R.id.about_us_return_btn);
		aboutUsBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		finish();

	}

}

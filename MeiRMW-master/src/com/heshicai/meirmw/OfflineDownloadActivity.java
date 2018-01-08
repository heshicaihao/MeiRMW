package com.heshicai.meirmw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class OfflineDownloadActivity extends Activity implements
		OnClickListener {
	private Button offDownSetBtn;
	private Button offDownDownBtn;
	private Button homeImg;
	private Button articleImg;
	private Button novelImg;
	private Button writerImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.offline_download_setting);

		// 离线下载界面中返回按钮
		ImageButton offDownReturnBtn = (ImageButton) findViewById(R.id.offline_download_header_left_btn);
		// 离线下载界面中设置按钮
		offDownSetBtn = (Button) findViewById(R.id.offline_download_setting);

		// 离线下载界面中开始下载按钮
		offDownDownBtn = (Button) findViewById(R.id.offline_download_download);
		// 离线下载界面中的首页的的ImageView
		homeImg = (Button) findViewById(R.id.offline_download_tv1);
		// 离线下载界面中的杂志文章的ImageView
		articleImg = (Button) findViewById(R.id.offline_download_tv2);
		// 离线下载界面中的连载小说的ImageView
		novelImg = (Button) findViewById(R.id.offline_download_tv3);
		// 离线下载界面中的专栏作家的ImageView
		writerImg = (Button) findViewById(R.id.offline_download_tv4);

		offDownReturnBtn.setOnClickListener(this);
		offDownSetBtn.setOnClickListener(this);
		offDownDownBtn.setOnClickListener(this);
	}

	boolean tag = true;

	@Override
	public void onClick(View v) {
		int id = v.getId();

		Intent intent = new Intent();
		switch (id) {
		case R.id.offline_download_header_left_btn:
			finish();

			return;
		case R.id.offline_download_setting:
			intent.setClass(this, OfflineDownloadSetActivity.class);
			startActivity(intent);

			break;
		case R.id.offline_download_download:

			if (tag) {
				tag = !tag;
				offDownDownBtn
						.setBackgroundResource(R.drawable.ic_btn_state_2_bg_normal);
				homeImg.setBackgroundResource(R.drawable.ic_tick_normal);
				articleImg.setBackgroundResource(R.drawable.ic_tick_normal);
				novelImg.setBackgroundResource(R.drawable.ic_tick_normal);
				writerImg.setBackgroundResource(R.drawable.ic_tick_normal);
			} else {
				tag = !tag;
				offDownDownBtn
						.setBackgroundResource(R.drawable.ic_btn_state_1_bg_normal);
				homeImg.setBackgroundResource(R.drawable.ic_tick_pressed);
				articleImg.setBackgroundResource(R.drawable.ic_tick_pressed);
				novelImg.setBackgroundResource(R.drawable.ic_tick_pressed);
				writerImg.setBackgroundResource(R.drawable.ic_tick_pressed);
			}
			break;

		default:
			break;
		}

	}

}

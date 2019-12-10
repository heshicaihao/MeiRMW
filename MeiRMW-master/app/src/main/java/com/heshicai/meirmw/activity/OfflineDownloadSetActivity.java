package com.heshicai.meirmw.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.heshicai.meirmw.R;

public class OfflineDownloadSetActivity extends Activity implements
		OnClickListener {
	private View loadTog;
	private View homeTog;
	private View articleTog;
	private View novelTog;
	private View writerTog;
	private TextView modelSetTv;
	private TextView loadTimeSetTv;
	private RelativeLayout loadTimeLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.offline_download_setting_set);

		init();
	}

	private void init() {
		// 离线设置头文件左边的返回按钮
		ImageButton returnBtn = (ImageButton) findViewById(R.id.offline_download_setting_set_header_left_btn);
		// 离线设置下载模式的布局文件
		RelativeLayout loadModelLayout = (RelativeLayout) findViewById(R.id.offline_download_model_layout);
		// 离线设置下载模式的TextView
		modelSetTv = (TextView) findViewById(R.id.offline_download_setting_set_tv1);
		// 离线设置定时下载的按鈕
		loadTog = findViewById(R.id.offline_download_setting_set_tog1);

		// 离线设置下载时间的布局文件

		loadTimeLayout = (RelativeLayout) findViewById(R.id.offline_download_time_layout);

		// 离线设置下载时间的TextView
		loadTimeSetTv = (TextView) findViewById(R.id.offline_download_setting_set_tv2);
		// 离线设置页面中全部关闭的按钮
		Button startAllBtn = (Button) findViewById(R.id.offline_download_setting_set_btn3);
		// 离线设置页面中全部开启的按钮
		Button endAllBtn = (Button) findViewById(R.id.offline_download_setting_set_btn4);

		// 离线设置页面首页的View
		homeTog = findViewById(R.id.offline_download_setting_set_tog2);
		// 离线设置页面杂志文章View
		articleTog = findViewById(R.id.offline_download_setting_set_tog3);
		// 离线设置页面连载小说的View
		novelTog = findViewById(R.id.offline_download_setting_set_tog4);
		// 离线设置专栏作家的View
		writerTog = findViewById(R.id.offline_download_setting_set_tog5);

		returnBtn.setOnClickListener(this);
		loadModelLayout.setOnClickListener(this);
		loadTog.setOnClickListener(this);
		loadTimeLayout.setOnClickListener(this);

		startAllBtn.setOnClickListener(this);
		endAllBtn.setOnClickListener(this);
		homeTog.setOnClickListener(this);
		articleTog.setOnClickListener(this);
		novelTog.setOnClickListener(this);
		writerTog.setOnClickListener(this);

	}

	private boolean tog1tag = true;
	private boolean tog2tag = true;
	private boolean tog3tag = true;
	private boolean tog4tag = true;
	private boolean tog5tag = true;

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.offline_download_setting_set_header_left_btn:
			finish();

			return;
		case R.id.offline_download_model_layout:

			showLineDownModelDialog();

			break;
		case R.id.offline_download_time_layout:
			showDownloadTimeDialog();
			break;
		case R.id.offline_download_setting_set_tog1:
			if (tog1tag) {
				tog1tag = false;
				loadTog.setBackgroundResource(R.drawable.setting_checkbox_normal);
				loadTimeLayout.setVisibility(View.GONE);
			} else {
				tog1tag = true;
				loadTog.setBackgroundResource(R.drawable.setting_checkbox_checked);
				loadTimeLayout.setVisibility(View.VISIBLE);
				showDownloadTimeDialog();
			}
			break;
		case R.id.offline_download_setting_set_btn2:

			break;
		case R.id.offline_download_setting_set_btn3:

			tog2tag = false;
			tog3tag = false;
			tog4tag = false;
			tog5tag = false;

			homeTog.setBackgroundResource(R.drawable.setting_checkbox_normal);
			articleTog
					.setBackgroundResource(R.drawable.setting_checkbox_normal);
			novelTog.setBackgroundResource(R.drawable.setting_checkbox_normal);
			writerTog.setBackgroundResource(R.drawable.setting_checkbox_normal);

			break;
		case R.id.offline_download_setting_set_btn4:

			tog2tag = true;
			tog3tag = true;
			tog4tag = true;
			tog5tag = true;
			homeTog.setBackgroundResource(R.drawable.setting_checkbox_checked);
			articleTog
					.setBackgroundResource(R.drawable.setting_checkbox_checked);
			novelTog.setBackgroundResource(R.drawable.setting_checkbox_checked);
			writerTog
					.setBackgroundResource(R.drawable.setting_checkbox_checked);

			break;
		case R.id.offline_download_setting_set_tog2:
			if (tog2tag) {
				tog2tag = false;
				homeTog.setBackgroundResource(R.drawable.setting_checkbox_normal);
			} else {
				tog2tag = true;
				homeTog.setBackgroundResource(R.drawable.setting_checkbox_checked);
			}

			break;
		case R.id.offline_download_setting_set_tog3:
			if (tog3tag) {
				tog3tag = false;
				articleTog
						.setBackgroundResource(R.drawable.setting_checkbox_normal);
			} else {
				tog3tag = true;
				articleTog
						.setBackgroundResource(R.drawable.setting_checkbox_checked);
			}

			break;
		case R.id.offline_download_setting_set_tog4:
			if (tog4tag) {
				tog4tag = false;
				novelTog.setBackgroundResource(R.drawable.setting_checkbox_normal);
			} else {
				tog4tag = true;
				novelTog.setBackgroundResource(R.drawable.setting_checkbox_checked);
			}

			break;
		case R.id.offline_download_setting_set_tog5:

			if (tog5tag) {
				tog5tag = false;
				writerTog
						.setBackgroundResource(R.drawable.setting_checkbox_normal);
			} else {
				tog5tag = true;
				writerTog
						.setBackgroundResource(R.drawable.setting_checkbox_checked);
			}

			break;
		case R.id.time_button:
			loadTimeSetTv.setText(h + " : " + min);
			timeDialog.dismiss();
			break;

		default:
			break;
		}
	}

	private Dialog timeDialog;

	private void showDownloadTimeDialog() {
		timeDialog = new Dialog(this);
		// timeDialog.setTitle("设置时间");
		// 设置窗口没有标题栏,必须放在setContentView之前
		timeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		View view = View.inflate(this, R.layout.download_time_dialog, null);
		timeDialog.setContentView(view);

		TimePicker timePicker = (TimePicker) view.findViewById(R.id.timePicker);
		Button timeBtn = (Button) view.findViewById(R.id.time_button);

		// 是否使用24小时制
		timePicker.setIs24HourView(true);
		timeDialog.show();
		// 设置Dialog的宽度为屏幕宽度
		setWidthForDialog(timeDialog);
		MyOnTimeChangedListener listener = new MyOnTimeChangedListener();
		timePicker.setOnTimeChangedListener(listener);
		timeBtn.setOnClickListener(this);

	}

	private int h;
	private int min;

	class MyOnTimeChangedListener implements OnTimeChangedListener {

		/**
		 * view 当前选中TimePicker控件 hourOfDay 当前控件选中TimePicker 的小时 minute
		 * 当前选中控件TimePicker 的分钟
		 */
		@Override
		public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
			h = hourOfDay;
			min = minute;
		}

	}

	private Dialog modelDialog;

	private void showLineDownModelDialog() {
		// 显示自己定义的对话框
		modelDialog = new Dialog(this);
		// 设置窗口标题栏
		// modelDialog.setTitle("离线下载模式");
		modelDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 给对话框设置内容
		View view = View.inflate(this, R.layout.offline_download_model_dialog,
				null);
		modelDialog.setContentView(view);
		// 根据ID找到RadioGroup实例
		RadioGroup group = (RadioGroup) view
				.findViewById(R.id.line_down_radioGroup);

		modelDialog.show();

		// 设置Dialog的宽度为屏幕宽度
		setWidthForDialog(modelDialog);

		// 绑定一个group监听器
		MyOnCheckedChangeListener listener = new MyOnCheckedChangeListener();

		group.setOnCheckedChangeListener(listener);

	}

	/**
	 * 获取屏幕宽度并设置给Dialog
	 * 
	 * @param dialog
	 */

	private void setWidthForDialog(Dialog dialog) {
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		lp.width = (display.getWidth()); // 设置宽度
		dialog.getWindow().setAttributes(lp);
	}

	/**
	 * group的监听者 用于获取用户选的，哪一项选项
	 * 
	 * @author shicai
	 * 
	 */

	class MyOnCheckedChangeListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {

			switch (checkedId) {
			case R.id.line_down_radioButton1:
				modelSetTv.setText("包括图片");
				break;
			case R.id.line_down_radioButton2:
				modelSetTv.setText("仅文字");
				break;

			default:
				break;
			}
			modelDialog.dismiss();

		}

	}
}

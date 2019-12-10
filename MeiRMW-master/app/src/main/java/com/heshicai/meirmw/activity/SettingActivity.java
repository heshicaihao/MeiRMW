package com.heshicai.meirmw.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.heshicai.meirmw.R;
import com.heshicai.meirmw.consts.NetConst;
import com.heshicai.meirmw.util.DataCleanManager;
import com.heshicai.meirmw.util.IsWIFI;

public class SettingActivity extends Activity implements OnClickListener,
		OnSeekBarChangeListener {

	private TextView imageload;
	private static Boolean isWifi = false;
	private TextView textshow;
	private SharedPreferences sharedPreferences;
	private Editor editor;
	private TextView textsizeshow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.setting);

		sharedPreferences = getSharedPreferences("wujay", Context.MODE_PRIVATE); // 私有数据
		editor = sharedPreferences.edit();// 获取编辑器

		init();

	}

	private void init() {
		imageload = (TextView) findViewById(R.id.tv022);
		textsizeshow = (TextView) findViewById(R.id.tv044);

		// 检查radiogroup状态
		checkData();
		View rl01 = findViewById(R.id.rl01);
		View rl02 = findViewById(R.id.rl02);
		View rl03 = findViewById(R.id.rl03);
		View rl04 = findViewById(R.id.rl04);
		View rl05 = findViewById(R.id.rl05);
		View rl06 = findViewById(R.id.rl06);
		View rl07 = findViewById(R.id.rl07);
		View rl08 = findViewById(R.id.rl08);
		View rebtn = findViewById(R.id.setting_return_btn);

		rl01.setOnClickListener(this);
		rl02.setOnClickListener(this);
		rl03.setOnClickListener(this);
		rl04.setOnClickListener(this);
		rl05.setOnClickListener(this);
		rl06.setOnClickListener(this);
		rl07.setOnClickListener(this);
		rl08.setOnClickListener(this);
		rebtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent = new Intent();
		switch (id) {

		case R.id.rl01:
			intent.setClass(this, NotificationSettingActivity.class);
			break;
		case R.id.rl02:
			showImageLoaderDialog();
			return;
		case R.id.rl03:
			intent.setClass(this, OfflineDownloadActivity.class);

			break;
		case R.id.rl04:
			showArticleFontSizeDialog();
			return;
		case R.id.rl05:
			showClearCacheDialog();
			return;
		case R.id.rl06:
			intent.setClass(this, AboutUsSettingActivity.class);

			break;
		case R.id.rl07:
			intent.setClass(this, FeedbackSettingActivity.class);

			break;
		case R.id.rl08:
			intent.setClass(this, LoginActivity.class);

			break;
		case R.id.button1:
			articleFontSizeDialog.cancel();
			return;
		case R.id.button2:
			articleFontSizeDialog.cancel();
			return;
		case R.id.button3:
			DataCleanManager.all(SettingActivity.this);
			clearCacheDialog.cancel();
			return;
		case R.id.button4:
			clearCacheDialog.cancel();
			return;
		case R.id.setting_return_btn:
			finish();
			return;
		}
		startActivity(intent);
	}

	private Dialog clearCacheDialog;
	private Button button3, button4;

	/**
	 * 清理缓存显示Dialog的方法
	 */
	private void showClearCacheDialog() {
		// 显示自己定义的对话框
		clearCacheDialog = new Dialog(this);
		// 设置窗口没有标题栏,必须放在setContentView之前
		clearCacheDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// 给对话框设置内容
		View view = View.inflate(this, R.layout.clear_cache_dialog, null);
		clearCacheDialog.setContentView(view);

		// 根据ID找到对象
		button3 = (Button) view.findViewById(R.id.button3);
		button4 = (Button) view.findViewById(R.id.button4);

		clearCacheDialog.show();

		// 设置Dialog的宽度为屏幕宽度
		setWidthForDialog(clearCacheDialog);

		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
	}

	private Dialog articleFontSizeDialog;
	private SeekBar seekBar;
	private Button button1, button2;

	/**
	 * 文章字体大小显示Dialog的方法
	 */
	private void showArticleFontSizeDialog() {
		// 显示自己定义的对话框
		articleFontSizeDialog = new Dialog(this);
		// 设置窗口没有标题栏,必须放在setContentView之前
		articleFontSizeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// 给对话框设置内容
		View view = View.inflate(this, R.layout.article_font_size_dialog, null);
		articleFontSizeDialog.setContentView(view);

		// 根据ID找到对象
		seekBar = (SeekBar) view.findViewById(R.id.seekBar1);
		button1 = (Button) view.findViewById(R.id.button1);
		button2 = (Button) view.findViewById(R.id.button2);
		textshow = (TextView) view.findViewById(R.id.textView1);

		articleFontSizeDialog.show();

		checkTextSize();
		// 设置Dialog的宽度为屏幕宽度
		setWidthForDialog(articleFontSizeDialog);

		seekBar.setOnSeekBarChangeListener(this);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);

	}

	private Dialog imageLoaderDialog;

	/**
	 * 图片加载显示Dialog的方法
	 */
	private void showImageLoaderDialog() {
		// 显示自己定义的对话框
		imageLoaderDialog = new Dialog(this);

		// 设置窗口没有标题栏,必须放在setContentView之前
		imageLoaderDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// 给对话框设置内容
		View view = View.inflate(this, R.layout.imageloader_dialog, null);
		imageLoaderDialog.setContentView(view);

		// 根据ID找到RadioGroup实例
		RadioGroup group = (RadioGroup) view.findViewById(R.id.radioGroup);

		imageLoaderDialog.show();

		// 设置Dialog的宽度为屏幕宽度
		setWidthForDialog(imageLoaderDialog);

		// 绑定一个group监听器
		MyOnCheckedChangeListener listener = new MyOnCheckedChangeListener();

		group.setOnCheckedChangeListener(listener);

	}

	private RadioGroup radioGroup;

	/**
	 * group的监听者 用于获取用户选的，哪一项选项
	 * 
	 * @author shicai
	 * 
	 */
	private RadioButton radioButton;
	private int NetState;

	class MyOnCheckedChangeListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {

			RadioButton rb = (RadioButton) findViewById(checkedId);
			boolean iswifi = IsWIFI.isWifi(SettingActivity.this);

			switch (checkedId) {
			case R.id.radioButton1:
				imageload.setText("仅WIFI");
				NetState = NetConst.WIFI;
				break;
			case R.id.radioButton2:
				imageload.setText("所有网络");
				NetState = NetConst.ALL;
				break;
			case R.id.radioButton3:
				imageload.setText("都不加载");
				NetState = NetConst.NOPIC;
				break;
			}
			saveState();
			imageLoaderDialog.dismiss();
		}

		private void saveState() {
			editor.putInt("NetState", NetState);
			editor.commit();// 提交修改
		}
	}

	/**
	 * 获取屏幕宽度并设置给Dialog
	 * 
	 * @param dialog
	 */
	public void setWidthForDialog(Dialog dialog) {
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		lp.width = (display.getWidth()); // 设置宽度
		dialog.getWindow().setAttributes(lp);
	}

	/**
	 * 查询radiobutton 状态
	 */
	private void checkData() {
		SharedPreferences share = getSharedPreferences("wujay",
				Context.MODE_PRIVATE); // 私有数据
		int i = share.getInt("NetState", 0);
		if (i != 0) {
			switch (i) {
			case NetConst.WIFI:
				imageload.setText("仅WIFI");
				break;
			case NetConst.ALL:
				imageload.setText("所有网络");
				break;
			case NetConst.NOPIC:
				imageload.setText("都不加载");
				break;

			default:
				break;
			}
		}

	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		int textsize = 15 + progress / 10;
		textshow.setText("设置文章字号：" + textsize);
		textshow.setTextSize(textsize);
		textsizeshow.setText(textsize + "");
		editor.putInt("progress", progress);
		editor.commit();// 提交修改

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}

	public void checkTextSize() {
		int i = sharedPreferences.getInt("progress", 0);
		if (i != 0) {
			seekBar.setProgress(i);
			int textsize = 15 + i / 10;
			textshow.setText("设置文章字号：" + textsize);
			textshow.setTextSize(textsize);

		}
	}
}

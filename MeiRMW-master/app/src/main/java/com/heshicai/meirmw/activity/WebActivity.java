package com.heshicai.meirmw.activity;

import java.io.File;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.heshicai.meirmw.R;
import com.tencent.weibo.sdk.android.component.ReAddActivity;

public class WebActivity extends Activity implements OnClickListener,
		OnSeekBarChangeListener {

	// // private WebView myWebView;
	// private static String url;

	/**
	 * Manages settings state for a WebView
	 */
	WebSettings settings;
	/**
	 * 用来控制字体大小
	 */
	int fontSize = 1;
	private boolean like = true;
	private Context mContext;
	private ImageButton likeBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_web_view);
		mContext = this.getApplicationContext();

		WebView myWebView = (WebView) findViewById(R.id.webView);
		ImageButton fontsizeButton = (ImageButton) findViewById(R.id.articleMore);
		ImageButton shareBtn = (ImageButton) findViewById(R.id.icShare);
		ImageButton commentBtn = (ImageButton) findViewById(R.id.articleComment);

		likeBtn = (ImageButton) findViewById(R.id.articleDislike);
		// webView界面中返回按钮
		ImageButton returnBtn = (ImageButton) findViewById(R.id.bottombarBack);

		// 用Intent从其他Activity传入一个数据
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		// 在WebView中加入网页
		myWebView.loadUrl(url);
		// 在WebView中使用网页的JavaScript
		WebSettings webSettings = myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		// 在WebView中使用网页的超链接，并本框架大开
		myWebView.setWebViewClient(new WebViewClient());

		fontsizeButton.setOnClickListener(this);
		shareBtn.setOnClickListener(this);
		likeBtn.setOnClickListener(this);
		settings = myWebView.getSettings();
		settings.setSupportZoom(true);

		commentBtn.setOnClickListener(this);

		CookieManager.getInstance().setAcceptCookie(true);

	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		int id = v.getId();
		switch (id) {
		case R.id.articleMore:
			fontSizeDialog();
			return;

		case R.id.articleDislike:
			if (like) {
				Toast.makeText(this, "喜欢了这篇文章", Toast.LENGTH_SHORT).show();
				likeBtn.setBackgroundResource(R.drawable.article_like_normal);
			} else {
				likeBtn.setBackgroundResource(R.drawable.article_dislike_normal);
			}
			like = !like;
			return;
		case R.id.icShare:
			shareDialog();
			return;
		case R.id.articleComment:
			System.out.println("intent");
			intent.setClass(this, CommentsActivity.class);
			break;
		case R.id.tab_1:
			intent.setClass(this, ShareActivity.class);
			break;
		case R.id.tab_2:
			intent.setClass(this, ShareActivity.class);
			break;
		case R.id.tab_3:
			Sms();
			break;
		case R.id.tab_4:
			Sms();
			break;
		default:
			break;
		}
		startActivity(intent);
	}

	private void Sms() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		// intent.setType("text/plain"); //纯文本
		// 图片分享
		intent.setType("image/png");
		// 添加图片
		File f = new File("/sdcard/sd.png");
		Uri u = Uri.fromFile(f);
		intent.putExtra(Intent.EXTRA_STREAM, u); // 添加图片
		intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
		intent.putExtra(Intent.EXTRA_TEXT,
				"I would like to share this with you...");
		startActivity(Intent.createChooser(intent, getTitle()));
	}

	private Dialog fontSizeDialog;
	private Dialog shareDialog;
	private SeekBar seekBar;

	public void fontSizeDialog() {
		// 显示自己定义的对话框
		fontSizeDialog = new Dialog(this);
		System.out.println(fontSizeDialog == null);

		// 设置窗口没有标题栏,必须放在setContentView之前
		fontSizeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 给对话框设置内容
		View view = View.inflate(this, R.layout.font_size_dialog, null);

		seekBar = (SeekBar) view.findViewById(R.id.seekBar1);

		fontSizeDialog.setContentView(view);
		fontSizeDialog.getWindow().setGravity(Gravity.BOTTOM);
		fontSizeDialog.show();

		seekBar.setOnSeekBarChangeListener(this);

	}

	private RadioButton sinaButton, qqButton, SMSButton, EmailButton;

	public void shareDialog() {
		shareDialog = new Dialog(this);

		shareDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 给对话框设置内容
		View view = View.inflate(this, R.layout.share_dialog, null);

		shareDialog.setContentView(view);
		shareDialog.getWindow().setGravity(Gravity.BOTTOM);

		setWidthForDialog(shareDialog);

		shareDialog.show();

		sinaButton = (RadioButton) view.findViewById(R.id.tab_1);
		qqButton = (RadioButton) view.findViewById(R.id.tab_2);
		SMSButton = (RadioButton) view.findViewById(R.id.tab_3);
		EmailButton = (RadioButton) view.findViewById(R.id.tab_4);

		sinaButton.setOnClickListener(this);
		qqButton.setOnClickListener(this);
		SMSButton.setOnClickListener(this);
		EmailButton.setOnClickListener(this);

	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		if (progress >= 0 && progress <= 20) {
			settings.setTextSize(WebSettings.TextSize.SMALLEST);
		} else if (progress <= 40) {

			settings.setTextSize(WebSettings.TextSize.SMALLER);
		} else if (progress <= 60) {

			settings.setTextSize(WebSettings.TextSize.NORMAL);
		} else if (progress <= 80) {

			settings.setTextSize(WebSettings.TextSize.LARGER);
		} else {
			settings.setTextSize(WebSettings.TextSize.LARGEST);
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}

	public void setWidthForDialog(Dialog dialog) {
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		lp.width = (display.getWidth()); // 设置宽度
		dialog.getWindow().setAttributes(lp);
	}

	public void shareqq() {
		Intent i = new Intent(this, ReAddActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("content", "Make U happy");
		bundle.putString("video_url",
				"http://www.tudou.com/programs/view/b-4VQLxwoX4/");
		bundle.putString("pic_url",
				"http://t2.qpic.cn/mblogpic/9c7e34358608bb61a696/2000");
		i.putExtras(bundle);
		startActivity(i);
	}

}

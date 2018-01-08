package com.heshicai.meirmw;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class FeedbackSettingActivity extends Activity implements
		OnClickListener {
	private Button feedbackSendBtn;
	private ImageButton feedbackReturnBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.feedback_setting);

		// 意见反馈中的返回按钮
		feedbackReturnBtn = (ImageButton) findViewById(R.id.feedback_return_btn);

		// 意见反馈中的editText
		EditText feedbackEdit = (EditText) findViewById(R.id.feedback_edit);
		// 意见反馈中的发送按钮
		feedbackSendBtn = (Button) findViewById(R.id.feedback_send_btn);

		feedbackReturnBtn.setOnClickListener(this);
		feedbackSendBtn.setOnClickListener(this);

		feedbackEdit.addTextChangedListener(watcher);

	}

	@Override
	public View onCreateView(View parent, String name, Context context,
			AttributeSet attrs) {
		return super.onCreateView(parent, name, context, attrs);
	}

	private CharSequence temp;

	TextWatcher watcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			temp = s;
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			if (temp.length() > 0) {
				// editText不为空，改变发送按钮的背景
				feedbackSendBtn
						.setBackgroundResource(R.drawable.feedback_setting_send_content);

			} else {
				feedbackSendBtn
						.setBackgroundResource(R.drawable.feedback_setting_send_bg);
			}
		}
	};

	@Override
	public void onClick(View v) {
		int id = v.getId();

		switch (id) {
		case R.id.feedback_return_btn:
			// 结束当前页面
			finish();
			break;
		case R.id.feedback_send_btn:

			Toast.makeText(this, "意见反馈成功 ", Toast.LENGTH_SHORT).show();
			finish();
			break;

		default:
			break;
		}

	}

}

package com.heshicai.meirmw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.heshicai.meirmw.adapter.CommentAdapter;

public class CommentsActivity extends Activity implements OnClickListener {

	private String id;
	private EditText container;
	private CommentAdapter mAdapter;
	private ListView mList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comments);
		View sendbtn = findViewById(R.id.msg_time_btn);
		container = (EditText) findViewById(R.id.msg_item_container);
		mList = (ListView) findViewById(R.id.frg_homepage_list);
		// 获取用户信息
		getUser();
		mAdapter = new CommentAdapter();
		mList.setAdapter(mAdapter);

		sendbtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		String content = container.getText().toString();
		mAdapter.addData(id, content);
		container.setText("");
	}

	public void getUser() {
		SharedPreferences sp = getSharedPreferences("weiboToken",
				Context.MODE_PRIVATE);
		int login = sp.getInt("login", 0);
		String token = sp.getString("token", null);
		String uid = sp.getString("uid", null);
		long time = sp.getLong("time", 0);

		if (login == 0) {
			Intent intent = new Intent(this, LoginActivity.class);
			Toast.makeText(this, "请先登陆", Toast.LENGTH_SHORT).show();
			startActivity(intent);
			finish();
		}
	}

}

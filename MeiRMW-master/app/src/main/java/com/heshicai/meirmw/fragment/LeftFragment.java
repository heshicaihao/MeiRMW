package com.heshicai.meirmw.fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.heshicai.meirmw.LoginActivity;
import com.heshicai.meirmw.R;
import com.heshicai.meirmw.SettingActivity;
import com.heshicai.meirmw.adapter.LeftAdapter;
import com.heshicai.meirmw.bean.LeftOptions;
import com.heshicai.meirmw.consts.Option;

public class LeftFragment extends Fragment implements OnClickListener,
		OnItemClickListener {

	private ListView mListView;
	private LeftAdapter mAdapter;
	private ArrayList<LeftOptions> mData;
	private int optionKey;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		View view = getView();

		View login_btn = view.findViewById(R.id.login);
		View setting_btn = view.findViewById(R.id.setting);
		mListView = (ListView) view.findViewById(R.id.list_menu);

		initListview();

		login_btn.setOnClickListener(this);
		setting_btn.setOnClickListener(this);

	}

	/**
	 * 添加listview的数据
	 */
	private void addListData() {
		mData = new ArrayList<LeftOptions>();
		mData.add(new LeftOptions(R.drawable.ic_main_normal, "首页",
				Option.HOMEPAGE));
		mData.add(new LeftOptions(R.drawable.ic_today_hot_normal, "每日热门",
				Option.HOT));
		mData.add(new LeftOptions(R.drawable.ic_update_normal, "动态",
				Option.DYNAMIC));
		mData.add(new LeftOptions(R.drawable.ic_notification_normal, "提醒",
				Option.REMIND));
		mData.add(new LeftOptions(R.drawable.default_channel_icon_normal,
				"杂志文章", Option.MAGAZINE));
		mData.add(new LeftOptions(R.drawable.default_channel_icon_normal,
				"连载小说", Option.NOVEL));
		mData.add(new LeftOptions(R.drawable.default_channel_icon_normal,
				"专栏作家", Option.WRITER));
	}

	/**
	 * 初始化listview
	 */
	public void initListview() {
		mAdapter = new LeftAdapter();
		addListData();
		mAdapter.setData(mData);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fgm_left, container, false);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent = new Intent();
		switch (id) {
		case R.id.login:
			intent.setClass(getActivity(), LoginActivity.class);

			break;
		case R.id.setting:
			intent.setClass(getActivity(), SettingActivity.class);

			break;

		default:
			break;
		}

		startActivity(intent);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		LeftOptions option = mData.get(position);
		System.out.println(option.key);
		mListener.key(option.key);

	}

	// 监听key的值 接口回调到main
	public static interface OnOptionKeyListener {
		void key(int options);
	}

	private OnOptionKeyListener mListener;

	public void setOnOptionKeyListener(OnOptionKeyListener listener) {
		mListener = listener;
	}

}

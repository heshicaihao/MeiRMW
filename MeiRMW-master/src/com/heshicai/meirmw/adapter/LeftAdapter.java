package com.heshicai.meirmw.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.heshicai.meirmw.R;
import com.heshicai.meirmw.bean.LeftOptions;

public class LeftAdapter extends BaseAdapter {

	private final ArrayList<LeftOptions> mList = new ArrayList<LeftOptions>();

	public void setData(List<LeftOptions> data) {
		mList.addAll(data);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public LeftOptions getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		convertView = inflater.inflate(R.layout.left_menu_item, parent, false);
		ImageView imgLogin = (ImageView) convertView
				.findViewById(R.id.img_logo);
		TextView textOption = (TextView) convertView
				.findViewById(R.id.text_options);
		LeftOptions option = mList.get(position);

		imgLogin.setBackgroundResource(option.Res);
		textOption.setText(option.options);

		return convertView;
	}
}

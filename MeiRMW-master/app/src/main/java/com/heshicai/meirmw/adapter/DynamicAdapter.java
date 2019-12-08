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
import com.heshicai.meirmw.bean.DynamicItem;

public class DynamicAdapter extends BaseAdapter {

	// private BitmapUtils mBitmapUtils;
	private final ArrayList<DynamicItem> mList = new ArrayList<DynamicItem>();

	public void setData(List<DynamicItem> data) {
		mList.clear();
		mList.addAll(data);
		notifyDataSetChanged();
	}

	public void addData(List<DynamicItem> data) {
		mList.addAll(data);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public DynamicItem getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater
					.inflate(R.layout.dynamic_item, parent, false);
			ViewHolder holder = new ViewHolder();
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.image_user);
			holder.metadata = (TextView) convertView
					.findViewById(R.id.text_metadata);
			holder.comment = (TextView) convertView
					.findViewById(R.id.text_comment);
			holder.username = (TextView) convertView
					.findViewById(R.id.text_username);

			convertView.setTag(holder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		DynamicItem dynamicItem = mList.get(position);
		// mBitmapUtils = new BitmapUtils(parent.getContext());

		holder.comment.setText(dynamicItem.content);
		holder.metadata.setText(dynamicItem.item.metadata);
		holder.username.setText(dynamicItem.user.name);

		return convertView;
	}

	class ViewHolder {
		ImageView imageView;
		TextView metadata;
		TextView username;
		TextView comment;
	}

}

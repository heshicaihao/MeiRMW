package com.heshicai.meirmw.adapter;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.heshicai.meirmw.R;
import com.heshicai.meirmw.bean.Loginuser;
import com.heshicai.meirmw.util.FormatTime;

public class CommentAdapter extends BaseAdapter {

	private final ArrayList<Loginuser> mList = new ArrayList<Loginuser>();

	public void addData(String id, String content) {
		mList.add(new Loginuser(id, content));
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Loginuser getItem(int position) {
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
			convertView = inflater.inflate(R.layout.comments_item, parent,
					false);
			ViewHolder holder = new ViewHolder();

			holder.usercontent = (TextView) convertView
					.findViewById(R.id.user_content);
			holder.username = (TextView) convertView.findViewById(R.id.user_id);
			holder.usertime = (TextView) convertView
					.findViewById(R.id.user_time);

			convertView.setTag(holder);

		}

		ViewHolder holder = (ViewHolder) convertView.getTag();
		Loginuser loginuser = mList.get(position);

		holder.usercontent.setText(loginuser.userContent);
		holder.usertime.setText(FormatTime.getTime(null));
		holder.username.setText(loginuser.username);

		return convertView;
	}

	class ViewHolder {
		ImageView imageView;
		TextView username;
		TextView usertime;
		TextView usercontent;
	}

}

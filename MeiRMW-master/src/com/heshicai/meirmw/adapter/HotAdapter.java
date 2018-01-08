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
import com.heshicai.meirmw.bean.HotItem;
import com.heshicai.meirmw.util.FormatTime;
import com.lidroid.xutils.BitmapUtils;

public class HotAdapter extends BaseAdapter {

	private BitmapUtils mBitmapUtils;
	private final ArrayList<HotItem> mList = new ArrayList<HotItem>();

	public void setData(List<HotItem> data) {
		mList.clear();
		mList.addAll(data);
		notifyDataSetChanged();
	}

	public void addData(List<HotItem> data) {
		mList.addAll(data);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
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
			convertView = inflater.inflate(R.layout.hot_item, parent, false);
			ViewHolder holder = new ViewHolder();
			holder.creationTime = (TextView) convertView
					.findViewById(R.id.creationTime);
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.homepage_item_imageview);
			holder.metadata = (TextView) convertView
					.findViewById(R.id.metadata);
			holder.numUsersCommentIt = (TextView) convertView
					.findViewById(R.id.numUsersCommentIt);
			holder.numUsersWithoutNameLikeIt = (TextView) convertView
					.findViewById(R.id.numUsersWithoutNameLikeIt);

			convertView.setTag(holder);

		}

		ViewHolder holder = (ViewHolder) convertView.getTag();
		HotItem hotitem = mList.get(position);
		mBitmapUtils = new BitmapUtils(parent.getContext());

		System.out.println(hotitem.item.thumbnailUrl);
		mBitmapUtils.display(holder.imageView, hotitem.item.thumbnailUrl);
		holder.metadata.setText(hotitem.item.metadata + "");
		holder.creationTime.setText(FormatTime
				.getTime(hotitem.item.creationTime));
		holder.numUsersCommentIt.setText(hotitem.numUsersCommentIt + "");
		holder.numUsersWithoutNameLikeIt
				.setText(hotitem.numUsersWithoutNameLikeIt + "");

		return convertView;
	}

	class ViewHolder {
		ImageView imageView;
		TextView metadata;
		TextView creationTime;
		TextView numUsersWithoutNameLikeIt;
		TextView numUsersCommentIt;
	}

}

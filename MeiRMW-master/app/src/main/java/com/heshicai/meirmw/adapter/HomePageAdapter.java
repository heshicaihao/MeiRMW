package com.heshicai.meirmw.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.heshicai.meirmw.R;
import com.heshicai.meirmw.bean.HomePageItem;
import com.heshicai.meirmw.consts.NetConst;
import com.heshicai.meirmw.util.FormatTime;
import com.lidroid.xutils.BitmapUtils;

public class HomePageAdapter extends BaseAdapter {
	private final boolean wifi = true;
	private BitmapUtils mBitmapUtils;
	private Context mContext;
	private final ArrayList<HomePageItem> mList = new ArrayList<HomePageItem>();

	public void setData(List<HomePageItem> data) {
		mList.clear();
		mList.addAll(data);
		notifyDataSetChanged();
	}

	public void addData(List<HomePageItem> data) {
		mList.addAll(data);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public HomePageItem getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (mContext == null) {
			mContext = parent.getContext();
		}
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.homepage_item, parent,
					false);
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
		HomePageItem homePageitem = mList.get(position);
		mBitmapUtils = new BitmapUtils(parent.getContext());

		System.out.println("1111111111111");

		if (State()) {

			mBitmapUtils.display(holder.imageView,
					homePageitem.item.thumbnailUrl);

		}
		holder.metadata.setText(homePageitem.item.metadata + "");
		holder.creationTime.setText(FormatTime
				.getTime(homePageitem.item.creationTime));
		holder.numUsersCommentIt.setText(homePageitem.numUsersCommentIt + "");
		holder.numUsersWithoutNameLikeIt
				.setText(homePageitem.numUsersWithoutNameLikeIt + "");

		return convertView;
	}

	public boolean State() {
		SharedPreferences share = mContext.getSharedPreferences("wujay",
				Context.MODE_PRIVATE); // 私有数据
		int i = share.getInt("NetState", 0);
		System.out.println(i);
		if (i == NetConst.WIFI || i == NetConst.ALL || i == 0)
			if (isWifi(mContext))
				return true;
		return false;

	}

	class ViewHolder {
		ImageView imageView;
		TextView metadata;
		TextView creationTime;
		TextView numUsersWithoutNameLikeIt;
		TextView numUsersCommentIt;
	}

	public static boolean isWifi(Context mContext) {
		ConnectivityManager connectivityManager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

}

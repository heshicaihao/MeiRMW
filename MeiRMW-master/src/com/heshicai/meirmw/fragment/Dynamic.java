package com.heshicai.meirmw.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.heshicai.meirmw.R;
import com.heshicai.meirmw.adapter.DynamicAdapter;
import com.heshicai.meirmw.bean.DynamicItem;
import com.heshicai.meirmw.consts.URL;
import com.heshicai.meirmw.lintener.OnLayoutOpenListener;
import com.heshicai.meirmw.util.DynamicJson;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class Dynamic extends Fragment implements OnClickListener,
		OnRefreshListener {
	private ListView mListView;
	private OnLayoutOpenListener mListener;
	private HttpUtils mHttpUtils;
	private DbUtils mDbUtils;
	private SwipeRefreshLayout mRefreshLayout;
	private DynamicAdapter mAdapter;
	private ArrayList<DynamicItem> mArrayList;
	private final boolean isFirst = true;
	private String url = URL.DYNAMIC;
	private final ArrayList<String> homePageUrl = new ArrayList<String>();
	private final ArrayList<String> hotUrl = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHttpUtils = new HttpUtils();
		mDbUtils = DbUtils.create(getActivity());

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		requestData();
		View view = inflater.inflate(R.layout.fragment_homepage, container,
				false);
		TextView title = (TextView) view.findViewById(R.id.text_title);
		title.setText("动态");
		return view;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		System.out.println("onactivitycreated");
		View view = getView();

		// 设置初始化下拉刷新
		mRefreshLayout = (SwipeRefreshLayout) view
				.findViewById(R.id.refreshLayout);
		// 设置下拉刷新的监听器
		mRefreshLayout.setOnRefreshListener(this);

		// 当Fragment复用的时候，若发现Adapter已经存在，则使用之前的Adapter就可以了
		if (mAdapter == null) {
			mAdapter = new DynamicAdapter();
		}

		ImageButton openMenuBtn = (ImageButton) view
				.findViewById(R.id.frg_homepage_header_left_btn);
		openMenuBtn.setOnClickListener(this);

		mListView = (ListView) view.findViewById(R.id.frg_homepage_list);
		mListView.setAdapter(mAdapter);

		requestData();
		mRefreshLayout.setRefreshing(true);
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void requestData() {
		mHttpUtils.send(HttpMethod.GET, url, mCallBack);

	}

	/**
	 * 请求网络数据
	 */
	RequestCallBack<String> mCallBack = new RequestCallBack<String>() {

		@Override
		public void onFailure(HttpException arg0, String arg1) {
			System.out.println("onFailure");
			mRefreshLayout.setRefreshing(false);
		}

		@Override
		public void onSuccess(ResponseInfo<String> responseInfo) {
			DynamicJson json = new DynamicJson();
			String jsonString = responseInfo.result;

			mArrayList = json.json(jsonString);

			System.out.println("dynamic" + mAdapter);
			mAdapter.setData(mArrayList);
			mRefreshLayout.setRefreshing(false);
		}
	};

	@Override
	public void onClick(View v) {
		if (mListener != null) {
			mListener.openLayout();

		}
	}

	public void setOnLayoutOpenListener(OnLayoutOpenListener l) {
		mListener = l;
	}

	@Override
	public void onRefresh() {
		requestData();
	}

}

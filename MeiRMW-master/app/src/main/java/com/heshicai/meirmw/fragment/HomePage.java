package com.heshicai.meirmw.fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.heshicai.meirmw.R;
import com.heshicai.meirmw.activity.WebActivity;
import com.heshicai.meirmw.adapter.HomePageAdapter;
import com.heshicai.meirmw.bean.HomePageItem;
import com.heshicai.meirmw.consts.URL;
import com.heshicai.meirmw.lintener.OnLayoutOpenListener;
import com.heshicai.meirmw.util.HomePageJson;
import com.heshicai.meirmw.util.HttpCallbackListener;
import com.heshicai.meirmw.util.HttpUtil;
import com.lidroid.xutils.DbUtils;

public class HomePage extends Fragment implements OnClickListener,
        SwipeRefreshLayout.OnRefreshListener, OnScrollListener, OnItemClickListener {
    private ListView mListView;
    private OnLayoutOpenListener mListener;
    //private HttpUtils mHttpUtils;
    private DbUtils mDbUtils;
    private SwipeRefreshLayout mRefreshLayout;
    private HomePageAdapter mAdapter;
    private ArrayList<HomePageItem> mArrayList;
    private final boolean isFirst = true;
    private String url = URL.HOMEPAGE;
    private final ArrayList<String> homePageUrl = new ArrayList<String>();
    private final ArrayList<String> hotUrl = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		mHttpUtils = new HttpUtils();
        mDbUtils = DbUtils.create(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        requestData();

        return inflater.inflate(R.layout.fragment_homepage, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("onactivitycreated");

        View view = getView();

        homePageUrl.add(URL.HOMEPAGE);
        homePageUrl.add(URL.HOMEPAGEPAGE2);
        homePageUrl.add(URL.HOMEPAGEPAGE3);
        homePageUrl.add(URL.HOMEPAGEPAGE4);
        homePageUrl.add(URL.HOMEPAGEPAGE5);
        homePageUrl.add(URL.HOMEPAGEPAGE6);
        homePageUrl.add(URL.HOMEPAGEPAGE7);

        hotUrl.add(URL.HOT);
        hotUrl.add(URL.HOTPAGE2);
        hotUrl.add(URL.HOTPAGE3);

        // 设置初始化下拉刷新
        mRefreshLayout = (SwipeRefreshLayout) view
                .findViewById(R.id.refreshLayout);
        // 设置下拉刷新的监听器
        mRefreshLayout.setOnRefreshListener(this);

        // 当Fragment复用的时候，若发现Adapter已经存在，则使用之前的Adapter就可以了
        if (mAdapter == null) {
            mAdapter = new HomePageAdapter();
        }

        ImageButton openMenuBtn = (ImageButton) view
                .findViewById(R.id.frg_homepage_header_left_btn);
        openMenuBtn.setOnClickListener(this);

        mListView = (ListView) view.findViewById(R.id.frg_homepage_list);
        // 解析FooterView
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View footerView = inflater.inflate(R.layout.listview_footer, mListView,
                false);
        // addFooterView方法必须在setAdapter方法之前调用
        mListView.addFooterView(footerView);

        mListView.setAdapter(mAdapter);
        mListView.setOnScrollListener(this);
        mListView.setOnItemClickListener(this);

        // if (isFirst) {
        // try {
        // List<HomePageItem> list = mDbUtils.findAll(HomePageItem.class);
        // if (list != null) {
        // mAdapter.setData(list);
        // mAdapter.notifyDataSetChanged();
        // }
        // } catch (DbException e) {
        // e.printStackTrace();
        // }
        // }
        requestData();
        mRefreshLayout.setRefreshing(true);
        // isFirst = false;

    }

    public void setUrl(String url) {
        this.url = url;
    }


    /**
     * 请求网络数据
     */
    HttpCallbackListener mCallBack = new HttpCallbackListener() {

        @Override
        public void onSuccess(String response) {
            // TODO Auto-generated method stub
            System.out.println(response);
            HomePageJson json = new HomePageJson();

            mArrayList = json.json(response);
            if (n == 2) {
                mAdapter.setData(mArrayList);
            } else {
                mAdapter.addData(mArrayList);
            }
            mRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onFailure(Exception e) {
            // TODO Auto-generated method stub
            System.out.println("onFailure");
            mRefreshLayout.setRefreshing(false);
        }


    };

    public void requestData() {
//		mHttpUtils.send(HttpMethod.GET, url, mCallBack);
        HttpUtil.sendHttpRequest(url, mCallBack);

    }

//	RequestCallBack<String> mCallBack = new RequestCallBack<String>() {
//
//		@Override
//		public void onFailure(HttpException arg0, String arg1) {
//			System.out.println("onFailure");
//			mRefreshLayout.setRefreshing(false);
//		}
//
//		@Override
//		public void onSuccess(ResponseInfo<String> responseInfo) {
//			HomePageJson json = new HomePageJson();
//			String jsonString = responseInfo.result;
//			// System.out.println(jsonString);
//
//			mArrayList = json.json(jsonString);
//			if (n == 2) {
//				mAdapter.setData(mArrayList);
//			} else {
//				mAdapter.addData(mArrayList);
//			}
//			mRefreshLayout.setRefreshing(false);
//		}
//	};

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

    // 分页页数
    private int n = 2;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case OnScrollListener.SCROLL_STATE_FLING:// 手指离开屏幕后，ListView自己滚动时触发
                System.out.println("SCROLL_STATE_FLING");
                break;
            case OnScrollListener.SCROLL_STATE_IDLE:// ListView滚动停止时触发
                System.out.println("SCROLL_STATE_IDLE");
                if ((firstVisibleItem + visibleItemCount) == totalItemCount) {// 说名ListView已经滚动到了底部
                    if (url == URL.HOMEPAGE && n <= 7) {
                        String url = homePageUrl.get(n);
                        n++;
                        setUrl(url);
                        requestData();
                        return;
                    } else if (url == URL.HOT && n <= 3) {
                        String url = hotUrl.get(n);
                        n++;
                        setUrl(url);
                        requestData();
                        return;
                    }
                    for (int i = 0; i < homePageUrl.size(); i++) {
                        if (url == homePageUrl.get(i)
                                && i != homePageUrl.size() - 1) {
                            url = homePageUrl.get(i + 1);
                            setUrl(url);
                            requestData();
                            return;
                        }
                    }
                    for (int i = 0; i < hotUrl.size(); i++) {
                        System.out.println(hotUrl.size());
                        if (url == hotUrl.get(i) && i != hotUrl.size() - 1) {
                            url = hotUrl.get(i + 1);
                            setUrl(url);
                            requestData();
                            return;
                        }
                    }

                }

                break;
            case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:// 手指按住屏幕并且拖动ListView滚动时触发
                System.out.println("SCROLL_STATE_TOUCH_SCROLL");
                break;

            default:
                break;
        }

    }

    private int firstVisibleItem;
    private int visibleItemCount;
    private int totalItemCount;

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        // TODO Auto-generated method stub
        this.firstVisibleItem = firstVisibleItem;
        this.visibleItemCount = visibleItemCount;
        this.totalItemCount = totalItemCount;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        HomePageItem homepageitem = mArrayList.get(position);
        String url = homepageitem.item.name;
        // String url = "http://www.wowenxue.com/2449.html";
        Intent intent = new Intent();
        intent.setClass(getActivity(), WebActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);

    }

}

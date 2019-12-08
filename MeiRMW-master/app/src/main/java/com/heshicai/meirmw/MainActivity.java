package com.heshicai.meirmw;

import java.util.HashMap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.heshicai.meirmw.consts.Option;
import com.heshicai.meirmw.fragment.Dynamic;
import com.heshicai.meirmw.fragment.HomePage;
import com.heshicai.meirmw.fragment.Hot;
import com.heshicai.meirmw.fragment.LeftFragment;
import com.heshicai.meirmw.fragment.LeftFragment.OnOptionKeyListener;
import com.heshicai.meirmw.fragment.Magazine;
import com.heshicai.meirmw.fragment.Novel;
import com.heshicai.meirmw.fragment.Writer;
import com.heshicai.meirmw.lintener.OnLayoutOpenListener;
import com.igexin.sdk.PushManager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends FragmentActivity implements
        OnOptionKeyListener, OnLayoutOpenListener {

    private SlidingMenu mSlidingMenu;
    private LeftFragment mLeftFragment;
    private Fragment f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 消息推送

        PushManager.getInstance().initialize(this.getApplicationContext());

        setContentView(R.layout.activity_main);

        // 初始化SlidingMen
        initSlidingMenu();
        mLeftFragment = new LeftFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.left_fgm, mLeftFragment).commit();

        // 初始化加载fragment
        HomePage mHomePage = new HomePage();
        mHomePage.setOnLayoutOpenListener(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, mHomePage).commit();

        mLeftFragment.setOnOptionKeyListener(this);

    }

    private void initSlidingMenu() {

        mSlidingMenu = new SlidingMenu(this);
        // 设置背景图片
        // mSlidingMenu
        // .setBackgroundResource(R.drawable.notification_tab_indicator_left_pressed);
        // 设置背景菜单
        mSlidingMenu.setMenu(R.layout.left_empty_layout);

        // 设置打开关闭的模式
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        mSlidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_MARGIN);
        // 获取屏幕宽度
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        // 设置menu的大小
        mSlidingMenu.setBehindWidth((int) (screenWidth * 0.7f));
        // 设置打开特效
        mSlidingMenu.setMode(SlidingMenu.LEFT);

        mSlidingMenu.attachToActivity(this, 0);
    }

    // 对Fragment进行缓存
    private final HashMap<Integer, Fragment> mFragmentCache = new HashMap<Integer, Fragment>();

    @Override
    public void key(int options) {
        Fragment fgm = mFragmentCache.get(options);
        if (fgm == null) {
            switch (options) {
                case Option.HOMEPAGE:

                    HomePage mHomePage = new HomePage();
                    mHomePage.setOnLayoutOpenListener(this);
                    fgm = mHomePage;
                    break;
                case Option.HOT:
                    Hot mHot = new Hot();
                    mHot.setOnLayoutOpenListener(this);
                    fgm = mHot;
                    break;
                case Option.DYNAMIC:
                    Dynamic mDynamic = new Dynamic();
                    mDynamic.setOnLayoutOpenListener(this);
                    fgm = mDynamic;
                    break;
                case Option.REMIND:
//				MyNotification noti = new MyNotification();
//				noti.setOnLayoutOpenListener(this);
//				fgm = noti;
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    break;
                case Option.MAGAZINE:

                    Magazine mMagazine = new Magazine();
                    mMagazine.setOnLayoutOpenListener(this);
                    fgm = mMagazine;

                    break;
                case Option.NOVEL:
                    Novel mNovel = new Novel();
                    mNovel.setOnLayoutOpenListener(this);
                    fgm = mNovel;

                    break;
                case Option.WRITER:
                    Writer mWriter = new Writer();
                    mWriter.setOnLayoutOpenListener(this);
                    fgm = mWriter;

                    break;

                default:
                    break;
            }
            mFragmentCache.put(options, fgm);
        }
        mSlidingMenu.toggle();

        getSupportFragmentManager().beginTransaction().replace(R.id.main, fgm)
                .commit();
    }

    @Override
    public void openLayout() {
        mSlidingMenu.showMenu();
    }

    @Override
    protected void onDestroy() {
        SharedPreferences sp = getSharedPreferences("weiboToken",
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putInt("login", 0);
        editor.commit();

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("注意");
        builder.setMessage("你真的想退出么？");
        // 设置确定按钮的文案和监听器
        builder.setPositiveButton("确定", listener);
        // 设置取消按钮的文案和监听器
        builder.setNegativeButton("取消", listener);

        builder.show();
    }

    private final MyDialogClickListener listener = new MyDialogClickListener();

    /**
     * 对话框上面的按钮的监听器
     *
     * @author Administrator
     *
     */
    private class MyDialogClickListener implements
            DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == AlertDialog.BUTTON_NEGATIVE) {// 点击的是"不要"按钮
//				Intent intent = new Intent();
//				intent.setClass(getApplicationContext(), MainActivity.class);
//				startActivity(intent);

            } else if (which == AlertDialog.BUTTON_POSITIVE) {// 点击的是确定
                finish();
            }
        }

    }

}

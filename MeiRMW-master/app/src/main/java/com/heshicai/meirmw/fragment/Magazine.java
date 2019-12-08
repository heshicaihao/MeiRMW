package com.heshicai.meirmw.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heshicai.meirmw.R;
import com.heshicai.meirmw.consts.URL;

public class Magazine extends HomePage {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_homepage, container,
				false);
		TextView title = (TextView) view.findViewById(R.id.text_title);
		title.setText("杂志文章");
		super.setUrl(URL.MAGAZINE);
		return view;

	}

}

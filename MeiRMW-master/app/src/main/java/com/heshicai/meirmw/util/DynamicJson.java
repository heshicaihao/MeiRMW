package com.heshicai.meirmw.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.heshicai.meirmw.bean.DynamicItem;

public class DynamicJson {

	ArrayList<DynamicItem> mArrayList = new ArrayList<DynamicItem>();

	public ArrayList<DynamicItem> json(String json) {
		try {

			JSONObject object = new JSONObject(json);

			JSONArray updateEntries = object.getJSONArray("updateEntries");

			for (int i = 0; i < updateEntries.length(); i++) {
				JSONObject pageItemJson = updateEntries.getJSONObject(i);
				JSONObject commentDetail = pageItemJson
						.getJSONObject("commentDetail");

				JSONObject comment = commentDetail.getJSONObject("comment");
				JSONObject user = comment.getJSONObject("user");
				JSONObject item = comment.getJSONObject("item");

				DynamicItem dynamicItem = new DynamicItem();

				dynamicItem.content = comment.getString("content");
				dynamicItem.id = comment.getString("id");
				dynamicItem.user.id = user.getString("id");
				dynamicItem.user.name = user.getString("name");
				dynamicItem.item.id = item.getString("id");
				dynamicItem.item.metadata = item.getString("metadata");
				dynamicItem.item.name = item.getString("name");
				dynamicItem.item.thumbnailUrl = item.getString("thumbnailUrl");

				mArrayList.add(dynamicItem);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mArrayList;

	}

}

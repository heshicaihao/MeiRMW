package com.heshicai.meirmw.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.heshicai.meirmw.bean.HomePageItem;

public class HomePageJson {
	ArrayList<HomePageItem> mArrayList = new ArrayList<HomePageItem>();

	public ArrayList<HomePageItem> json(String json) {
		try {

			JSONObject object = new JSONObject(json);

			JSONObject readerModule = object.getJSONObject("readerModule");

			JSONArray Array = readerModule.getJSONArray("itemInfos");

			for (int i = 0; i < Array.length(); i++) {
				JSONObject pageItemJson = Array.getJSONObject(i);
				HomePageItem pageItem = new HomePageItem();

				JSONObject itemJson = pageItemJson.getJSONObject("item");
				// item.creationTime = itemJson.getLong("creationTime");
				pageItem.item.thumbnailUrl = itemJson.getString("thumbnailUrl");
				pageItem.item.metadata = itemJson.getString("metadata");
				pageItem.item.name = itemJson.getString("name");
				pageItem.item.id = itemJson.getString("id");

				// pageItem.displayTime = pageItemJson.getLong("displayTime");
				pageItem.likedByLoginUser = pageItemJson
						.getBoolean("likedByLoginUser");
				pageItem.numUsersCommentIt = pageItemJson
						.getString("numUsersCommentIt");
				pageItem.numUsersLikeIt = pageItemJson
						.getString("numUsersLikeIt");
				pageItem.numUsersWithoutNameLikeIt = pageItemJson
						.getString("numUsersWithoutNameLikeIt");
				pageItem.numWeiboCommentIt = pageItemJson
						.getString("numWeiboCommentIt");
				pageItem.websiteName = pageItemJson.getString("websiteName");

				JSONArray ArrayThumbnailUrls = pageItemJson
						.getJSONArray("thumbnailUrls");
				JSONArray ArrayObBigImageIds = pageItemJson
						.getJSONArray("obBigImageIds");

				for (int j = 0; j < ArrayThumbnailUrls.length(); j++) {
					pageItem.thumbnailUrls.add(ArrayThumbnailUrls.get(j) + "");
				}
				for (int j = 0; j < ArrayObBigImageIds.length(); j++) {
					pageItem.obBigImageIds.add((String) ArrayObBigImageIds
							.get(j));
				}
				mArrayList.add(pageItem);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mArrayList;

	}
}

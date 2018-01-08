package com.heshicai.meirmw.bean;

import java.util.ArrayList;

public class HotItem {
	public Long displayTime;
	public Item item = new Item();
	public Boolean likedByLoginUser;
	public String numUsersCommentIt;
	public String numUsersLikeIt;
	public String numUsersWithoutNameLikeIt;
	public String numWeiboCommentIt;
	public ArrayList<String> obBigImageIds = new ArrayList<String>();
	public ArrayList<String> thumbnailUrls = new ArrayList<String>();
	public String websiteName;

	public static class Item {
		public Long creationTime;
		public String id;
		public String metadata;
		public String name;
		public String thumbnailUrl;

	}

}

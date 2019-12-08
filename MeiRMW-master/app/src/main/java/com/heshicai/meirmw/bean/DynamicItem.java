package com.heshicai.meirmw.bean;

public class DynamicItem {

	public String id;
	public final User user = new User();
	public final Item item = new Item();
	public String content;

	public static class User {

		public String id;
		public String name;
		public String screenName;
		public String imageName;
		public String description;
	}

	public static class Item {
		public String id;
		public String name;
		public String metadata;
		public String thumbnailUrl;
	}
}

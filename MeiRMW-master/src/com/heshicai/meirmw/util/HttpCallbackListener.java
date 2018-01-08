package com.heshicai.meirmw.util;

public interface HttpCallbackListener {

	void onSuccess(String response);
	
	void onFailure(Exception e);
}

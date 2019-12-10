package com.heshicai.meirmw.util;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @author shicai
 *
 */
public class HttpUtil {

	public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
					URL url = new URL(address);
					
					connection = (HttpURLConnection) url.openConnection();
					
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					connection.setDoInput(true);
					connection.setDoOutput(true);
					
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					
					StringBuilder response = new StringBuilder();
					String line;
					while((line =reader.readLine())!=null){
						response.append(line);
					}
					if(listener!=null){
						runUIcallback(listener,response.toString(),true);
//						listener.onSuccess(response.toString());
					}
					
				} catch (Exception e) {
					if(listener!=null){
						runUIcallback(listener, e.toString(),false);
//						listener.onFailure(e);
					}
				}finally{
					if(connection!=null){
						connection.disconnect();
					}
				}
			}
		}).start();
		
	}

	/**
	 * 转回主线程
	 * @param callback
	 * @param result
	 * @param isOK
	 */
	public  static void runUIcallback(final HttpCallbackListener callback,final String result,final boolean isOK)
	{
		AsyncTask asyncTask=new AsyncTask() {
			@Override
			protected Object doInBackground(Object[] params) {
				return null;
			}

			@Override
			protected void onPostExecute(Object o) {
				super.onPostExecute(o);
				if (isOK){
					callback.onSuccess(result);
				}else{
					callback.onFailure(new Exception());
				}

			}

			@Override
			protected void onProgressUpdate(Object[] values) {
				super.onProgressUpdate(values);
			}
		};
		asyncTask.execute();
	}
}

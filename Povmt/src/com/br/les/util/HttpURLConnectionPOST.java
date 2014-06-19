package com.br.les.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

import com.google.gson.GsonBuilder;

public class HttpURLConnectionPOST extends AsyncTask<String, Void, Void> {

	public void sendPostJson(String json, String email) {// String json, String
															// mail){
		try {
			new HttpURLConnectionPOST().execute(json, email).get();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static HttpResponse makeRequest(String uri, String json) {
		try {
			HttpPost httpPost = new HttpPost(uri);
			httpPost.setEntity(new StringEntity(json));
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			return new DefaultHttpClient().execute(httpPost);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected Void doInBackground(String... params) {
		String URL = "http://les-timeitup.appspot.com/put_user";
		String JSON = params[0];
		String EMAIL = params[1];

		Map<String, String> comment = new HashMap<String, String>();
		comment.put("data", JSON);
		comment.put("mail", EMAIL);
		String json = new GsonBuilder().create().toJson(comment, Map.class);
		makeRequest(URL, json);

		return null;
	}

}

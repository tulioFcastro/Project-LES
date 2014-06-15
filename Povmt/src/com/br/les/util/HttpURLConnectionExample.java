package com.br.les.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;

public class HttpURLConnectionExample extends AsyncTask<String, Void, String> {

	private final String USER_AGENT = "Mozilla/5.0";

	public String requestJson(String mail) {
		try {
			return new HttpURLConnectionExample().execute(mail).get();
		} catch (Exception ex) {
			return null;
		}

	}

	public void sendPostJson() {// String json, String mail){
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost("http://localhost:8080/put_user");

			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("data", "mail"));
			pairs.add(new BasicNameValuePair("mail", "reh.nally@gmail.com"));
			pairs.add(new BasicNameValuePair("id", "3"));
			post.setEntity(new UrlEncodedFormEntity(pairs));

			// post.setHeader("Accept", "Application/json");
			// post.setHeader("Content-type", "application/json");

			HttpResponse response = httpClient.execute(post);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * // HTTP POST request private void sendPost() throws Exception {
	 * 
	 * 
	 * 
	 * //add reuqest header con.setRequestMethod("POST");
	 * con.setRequestProperty("User-Agent", USER_AGENT);
	 * con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	 * 
	 * String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
	 * 
	 * // Send post request con.setDoOutput(true); DataOutputStream wr = new
	 * DataOutputStream(con.getOutputStream()); wr.writeBytes(urlParameters);
	 * wr.flush(); wr.close();
	 * 
	 * int responseCode = con.getResponseCode();
	 * System.out.println("\nSending 'POST' request to URL : " + url);
	 * System.out.println("Post parameters : " + urlParameters);
	 * System.out.println("Response Code : " + responseCode);
	 * 
	 * BufferedReader in = new BufferedReader( new
	 * InputStreamReader(con.getInputStream())); String inputLine; StringBuffer
	 * response = new StringBuffer();
	 * 
	 * while ((inputLine = in.readLine()) != null) { response.append(inputLine);
	 * } in.close();
	 * 
	 * //print result System.out.println(response.toString());
	 * 
	 * }
	 **/

	@Override
	protected String doInBackground(String... params) {
		String URL = "http://les-timeitup.appspot.com/get_user?mail="
				+ params[0];
		String linha = "";
		try {

			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			request.setHeader("Content-Type", "application/json");
			request.setURI(new URI(URL));
			HttpResponse resposta = client.execute(request);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					resposta.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");

			while ((linha = br.readLine()) != null) {
				sb.append(linha);
			}

			br.close();

			linha = sb.toString();
		} catch (Exception e) {
			return null;
		}

		return linha;
	}

}

package com.br.les.timeitup;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HttpURLConnectionExample {

	private final String USER_AGENT = "Mozilla/5.0";

	// public static void main(String[] args) throws Exception {
	//
	// HttpURLConnectionExample http = new HttpURLConnectionExample();
	//
	// System.out.println("Testing 1 - Send Http GET request");
	// System.out.println(http.sendGet("raif@gmail.com"));
	//
	// System.out.println("\nTesting 2 - Send Http POST request");
	// // http.sendPost();
	//
	// }

	// HTTP GET request
	public String sendGet(String mail) throws Exception {
		
		HttpURLConnection con = (HttpURLConnection) ( new URL("http://les-timeitup.appspot.com/get_user")).openConnection(); 
		con.setRequestMethod("GET");
		con.setDoInput(true); 
		con.setDoOutput(true); 
		con.connect(); 

		con.getOutputStream().write( ("mail=" + "raif@gmail.com").getBytes()); 
		InputStream is = con.getInputStream(); 
		byte[] b = new byte[1024]; 
		StringBuffer buffer = new StringBuffer();
		while ( is.read(b) != -1) 
			buffer.append(new String(b)); 
		con.disconnect() ;
		
		return buffer.toString();
		/**
		String url = "http://les-timeitup.appspot.com/get_user?mail=" + mail;

		URL obj = new URL(url);
		System.out.println("###### " + obj.toString());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		System.out.println("#####CON: " + con);
		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		System.setProperty("http.keepAlive", "false");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		System.out.println("###RESPONSE1: " + response);

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println("###RESPONSE2: " + response);

		// print result
		return response.toString();**/

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

}

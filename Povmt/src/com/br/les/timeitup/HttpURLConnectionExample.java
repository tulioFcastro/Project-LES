package com.br.les.timeitup;

<<<<<<< HEAD

=======
>>>>>>> f0f4a831b240bf3e0f1c1803055640839f07d056
import java.io.BufferedReader;
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
<<<<<<< HEAD
 
public class HttpURLConnectionExample {
 
	private final String USER_AGENT = "Mozilla/5.0";
 
	public static void main(String[] args) throws Exception {
 
		HttpURLConnectionExample http = new HttpURLConnectionExample();
 
		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();
 
		System.out.println("\nTesting 2 - Send Http POST request");
		//http.sendPost();
 
	}
 
	// HTTP GET request
	private void sendGet() throws Exception {
 
		String url = "http://localhost:8080/get_ti?mail='test'&id='5'";
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
=======

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

>>>>>>> f0f4a831b240bf3e0f1c1803055640839f07d056
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
<<<<<<< HEAD
 
		//print result
		System.out.println(response.toString());
 
	}
	
	public void sendPostJson(){//String json, String mail){ 
        try {  
            DefaultHttpClient httpClient = new DefaultHttpClient();  
            HttpPost post = new HttpPost("http://localhost:8080/put_user");  
            
            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("data", "mail"));
            pairs.add(new BasicNameValuePair("mail", "reh.nally@gmail.com"));
            post.setEntity(new UrlEncodedFormEntity(pairs));  
  
            //post.setHeader("Accept", "Application/json");  
            //post.setHeader("Content-type", "application/json");  
  
            HttpResponse response = httpClient.execute(post);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
	    
	}
 /**
	// HTTP POST request
	private void sendPost() throws Exception {
		
		
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
 
	}**/
 
=======
		System.out.println("###RESPONSE2: " + response);

		// print result
		return response.toString();

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

>>>>>>> f0f4a831b240bf3e0f1c1803055640839f07d056
}

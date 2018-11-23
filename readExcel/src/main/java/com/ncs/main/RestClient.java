package com.ncs.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.core.HttpHeaders;

import sun.misc.BASE64Encoder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestClient {

//	public static void main(String[] args) throws MalformedURLException, IOException {
//
//		String input = "{\"firstName\": \"Deepak\",\"lastName\": \"Guta\",\"country\": \"SG\",\"cellPhone\": \"99990001\",\"primaryEmail\":\"deepakg1@gmail.com\"}";
//		createCandidate(input);
//		//restCall(input);
//		//rest();
//	}

public  void createCandidate(String json) throws MalformedURLException, IOException {

		URL myurl = new URL("https://apisalesdemo4.successfactors.com/odata/v2/Candidate");
		String name = "sfadmin@SFPART031347";
	    String password = "part1802DC4";
	    String authString = name + ":" + password;
	    String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
		
		HttpURLConnection con = (HttpURLConnection) myurl.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);

		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Method", "POST");
		con.setRequestProperty("Authorization", "Basic " + authStringEnc);
			
		OutputStream os = con.getOutputStream();
		os.write(json.toString().getBytes("UTF-8"));
		os.close();
		StringBuilder sb = new StringBuilder();
		int HttpResult = con.getResponseCode();
		if (HttpResult == HttpURLConnection.HTTP_OK) {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
			System.out.println("" + sb.toString());

		} else {
			System.out.println(con.getResponseCode());
			System.out.println(con.getResponseMessage());
		}

	}
}

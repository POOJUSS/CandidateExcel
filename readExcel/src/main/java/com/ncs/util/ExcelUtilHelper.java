package com.ncs.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ncs.main.RestClient;
import com.ncs.model.Candidate;
import com.ncs.model.Response;

import sun.misc.BASE64Encoder;


public class ExcelUtilHelper {
	
	public static JsonObject prepareJson(final List<Candidate> candidateList) {

		final JSONArray candidateArray = new JSONArray();
		RestClient restClient = new RestClient();
		Gson userGson=new GsonBuilder().create();
		String jo=userGson.toJson(candidateList);
        
		for (Candidate candidate : candidateList) {
			JSONObject json = new JSONObject();
			json.put("firstName", candidate.getFirstName());
			json.put("lastName", candidate.getLastName());
			json.put("country", candidate.getCountry());
			json.put("contactEmail", candidate.getContactEmail());
			json.put("cellPhone", candidate.getCellPhone());
			candidateArray.put(json);
		}

		try {
			restClient.createCandidate(jo);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		final JSONObject candidates = new JSONObject();

		candidates.put("Candidates", candidateArray);

		final JsonObject jsonResult = new JsonParser().parse(candidates.toString()).getAsJsonObject();

		return jsonResult;
	}
	

	public static String prepareResult(final String message, final String statusCcde, final JsonObject candidateList) {

		final Response response = new Response();
		response.setMessage(message);
		response.setStatusCode(statusCcde);
		response.setData(candidateList);

		return new Gson().toJson(response);
	}
	
}

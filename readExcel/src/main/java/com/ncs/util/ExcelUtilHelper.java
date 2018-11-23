package com.ncs.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ncs.model.Candidate;
import com.ncs.model.Response;

public class ExcelUtilHelper {
	
	public static JsonObject prepareJson(final List<Candidate> candidateList) {

		final JSONArray candidateArray = new JSONArray();

		for (Candidate candidate : candidateList) {
			JSONObject json = new JSONObject();
			json.put("firstName", candidate.getFirstName());
			json.put("lastName", candidate.getLastName());
			json.put("country", candidate.getCountry());
			json.put("contactEmail", candidate.getContactEmail());
			json.put("cellPhone", candidate.getCellPhone());
			candidateArray.put(json);
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

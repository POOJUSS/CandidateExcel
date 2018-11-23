package com.ncs.model;

import com.google.gson.JsonObject;

public class Response {

	@Override
	public String toString() {
		return "Response [message=" + message + ", statusCode=" + statusCode + ", data=" + data + "]";
	}

	private String message;
	private String statusCode;
	private JsonObject data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public JsonObject getData() {
		return data;
	}

	public void setData(JsonObject data) {
		this.data = data;
	}

}

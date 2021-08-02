package de.sixbits.squeakyjava.feature.checkout.response;

import com.squareup.moshi.Json;

public class Status{

	@Json(name = "reason")
	private String reason;

	@Json(name = "code")
	private String code;

	public String getReason(){
		return reason;
	}

	public String getCode(){
		return code;
	}
}
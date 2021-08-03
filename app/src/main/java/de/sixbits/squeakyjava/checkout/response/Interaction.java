package de.sixbits.squeakyjava.checkout.response;

import com.squareup.moshi.Json;

public class Interaction{

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
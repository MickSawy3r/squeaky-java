package de.sixbits.squeakyjava.checkout.response;

import com.squareup.moshi.Json;

public class ReturnCode{

	@Json(name = "name")
	private String name;

	@Json(name = "source")
	private String source;

	public String getName(){
		return name;
	}

	public String getSource(){
		return source;
	}
}
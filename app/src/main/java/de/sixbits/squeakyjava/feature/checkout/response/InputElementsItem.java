package de.sixbits.squeakyjava.feature.checkout.response;

import com.squareup.moshi.Json;

public class InputElementsItem{

	@Json(name = "name")
	private String name;

	@Json(name = "type")
	private String type;

	public String getName(){
		return name;
	}

	public String getType(){
		return type;
	}
}
package de.sixbits.squeakyjava.checkout.response;

import com.squareup.moshi.Json;

public class Style{

	@Json(name = "language")
	private String language;

	public String getLanguage(){
		return language;
	}
}
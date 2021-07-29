package de.sixbits.squeakyjava.feature.checkout.datasource.network.response;

import com.squareup.moshi.Json;

public class Style{

	@Json(name = "language")
	private String language;

	public String getLanguage(){
		return language;
	}
}
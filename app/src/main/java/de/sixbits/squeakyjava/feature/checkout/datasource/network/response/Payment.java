package de.sixbits.squeakyjava.feature.checkout.datasource.network.response;

import com.squareup.moshi.Json;

public class Payment{

	@Json(name = "reference")
	private String reference;

	@Json(name = "amount")
	private int amount;

	@Json(name = "currency")
	private String currency;

	public String getReference(){
		return reference;
	}

	public int getAmount(){
		return amount;
	}

	public String getCurrency(){
		return currency;
	}
}
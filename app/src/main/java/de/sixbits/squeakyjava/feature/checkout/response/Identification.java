package de.sixbits.squeakyjava.feature.checkout.response;

import com.squareup.moshi.Json;

public class Identification{

	@Json(name = "shortId")
	private String shortId;

	@Json(name = "longId")
	private String longId;

	@Json(name = "transactionId")
	private String transactionId;

	public String getShortId(){
		return shortId;
	}

	public String getLongId(){
		return longId;
	}

	public String getTransactionId(){
		return transactionId;
	}
}
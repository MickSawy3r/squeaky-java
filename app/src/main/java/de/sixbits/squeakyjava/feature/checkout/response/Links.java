package de.sixbits.squeakyjava.feature.checkout.response;

import com.squareup.moshi.Json;

public class Links{

	@Json(name = "self")
	private String self;

	@Json(name = "lang")
	private String lang;

	@Json(name = "logo")
	private String logo;

	@Json(name = "operation")
	private String operation;

	@Json(name = "validation")
	private String validation;

	public String getSelf(){
		return self;
	}

	public String getLang(){
		return lang;
	}

	public String getLogo(){
		return logo;
	}

	public String getOperation(){
		return operation;
	}

	public String getValidation(){
		return validation;
	}
}
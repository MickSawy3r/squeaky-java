package de.sixbits.squeakyjava.checkout.response;

import com.squareup.moshi.Json;

public class ContractData{

	@Json(name = "PAGE_ENVIRONMENT")
	private String pAGEENVIRONMENT;

	@Json(name = "JAVASCRIPT_INTEGRATION")
	private String jAVASCRIPTINTEGRATION;

	@Json(name = "PAGE_BUTTON_LOCALE")
	private String pAGEBUTTONLOCALE;

	public String getPAGEENVIRONMENT(){
		return pAGEENVIRONMENT;
	}

	public String getJAVASCRIPTINTEGRATION(){
		return jAVASCRIPTINTEGRATION;
	}

	public String getPAGEBUTTONLOCALE(){
		return pAGEBUTTONLOCALE;
	}
}
package de.sixbits.squeakyjava.feature.checkout.response;

import com.squareup.moshi.Json;

public class PaymentMethodsResponse{

	@Json(name = "resultCode")
	private String resultCode;

	@Json(name = "networks")
	private Networks networks;

	@Json(name = "resultInfo")
	private String resultInfo;

	@Json(name = "returnCode")
	private ReturnCode returnCode;

	@Json(name = "identification")
	private Identification identification;

	@Json(name = "integrationType")
	private String integrationType;

	@Json(name = "interaction")
	private Interaction interaction;

	@Json(name = "links")
	private Links links;

	@Json(name = "operationType")
	private String operationType;

	@Json(name = "style")
	private Style style;

	@Json(name = "payment")
	private Payment payment;

	@Json(name = "operation")
	private String operation;

	@Json(name = "timestamp")
	private String timestamp;

	@Json(name = "status")
	private Status status;

	public String getResultCode(){
		return resultCode;
	}

	public Networks getNetworks(){
		return networks;
	}

	public String getResultInfo(){
		return resultInfo;
	}

	public ReturnCode getReturnCode(){
		return returnCode;
	}

	public Identification getIdentification(){
		return identification;
	}

	public String getIntegrationType(){
		return integrationType;
	}

	public Interaction getInteraction(){
		return interaction;
	}

	public Links getLinks(){
		return links;
	}

	public String getOperationType(){
		return operationType;
	}

	public Style getStyle(){
		return style;
	}

	public Payment getPayment(){
		return payment;
	}

	public String getOperation(){
		return operation;
	}

	public String getTimestamp(){
		return timestamp;
	}

	public Status getStatus(){
		return status;
	}
}

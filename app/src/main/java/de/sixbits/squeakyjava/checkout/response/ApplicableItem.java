package de.sixbits.squeakyjava.checkout.response;

import java.util.List;
import com.squareup.moshi.Json;

public class ApplicableItem{

	@Json(name = "recurrence")
	private String recurrence;

	@Json(name = "redirect")
	private boolean redirect;

	@Json(name = "code")
	private String code;

	@Json(name = "method")
	private String method;

	@Json(name = "registration")
	private String registration;

	@Json(name = "links")
	private Links links;

	@Json(name = "operationType")
	private String operationType;

	@Json(name = "label")
	private String label;

	@Json(name = "grouping")
	private String grouping;

	@Json(name = "selected")
	private boolean selected;

	@Json(name = "inputElements")
	private List<InputElementsItem> inputElements;

	@Json(name = "contractData")
	private ContractData contractData;

	public String getRecurrence(){
		return recurrence;
	}

	public boolean isRedirect(){
		return redirect;
	}

	public String getCode(){
		return code;
	}

	public String getMethod(){
		return method;
	}

	public String getRegistration(){
		return registration;
	}

	public Links getLinks(){
		return links;
	}

	public String getOperationType(){
		return operationType;
	}

	public String getLabel(){
		return label;
	}

	public String getGrouping(){
		return grouping;
	}

	public boolean isSelected(){
		return selected;
	}

	public List<InputElementsItem> getInputElements(){
		return inputElements;
	}

	public ContractData getContractData(){
		return contractData;
	}
}
package de.sixbits.squeakyjava.feature.checkout.response;

import java.util.List;
import com.squareup.moshi.Json;

public class Networks{

	@Json(name = "applicable")
	private List<ApplicableItem> applicable;

	public List<ApplicableItem> getApplicable(){
		return applicable;
	}
}
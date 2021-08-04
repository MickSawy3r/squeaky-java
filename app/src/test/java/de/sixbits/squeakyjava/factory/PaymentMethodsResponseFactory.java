package de.sixbits.squeakyjava.factory;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import de.sixbits.squeakyjava.checkout.PaymentMethodMapper;
import de.sixbits.squeakyjava.checkout.response.PaymentMethodsResponse;

public class PaymentMethodsResponseFactory {

    public static PaymentMethodsResponse getPaymentResponse(String jsonPayload) throws IOException {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<PaymentMethodsResponse> adapter = moshi.adapter(PaymentMethodsResponse.class);
        return adapter.fromJson(jsonPayload);
    }
}

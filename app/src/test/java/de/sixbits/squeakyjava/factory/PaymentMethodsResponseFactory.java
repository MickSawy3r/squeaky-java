package de.sixbits.squeakyjava.factory;

import androidx.annotation.NonNull;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.sixbits.squeakyjava.feature.checkout.PaymentMethodDataModel;
import de.sixbits.squeakyjava.feature.checkout.response.PaymentMethodsResponse;

public class PaymentMethodsResponseFactory {

    public static PaymentMethodsResponse getPaymentResponse(String jsonPayload) throws IOException {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<PaymentMethodsResponse> adapter = moshi.adapter(PaymentMethodsResponse.class);
        return adapter.fromJson(jsonPayload);
    }

    @NonNull
    public static List<PaymentMethodDataModel> getPaymentMethodList() {
        List<PaymentMethodDataModel> paymentMethods = new ArrayList<>();

        paymentMethods.add(new PaymentMethodDataModel(
                "1",
                "name",
                "url"
        ));

        return paymentMethods;
    }
}

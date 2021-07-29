package de.sixbits.squeakyjava.feature.checkout.datasource.network;

import de.sixbits.squeakyjava.feature.checkout.datasource.network.response.PaymentMethodsResponse;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface PayoneerApi {
    @GET("")
    Single<PaymentMethodsResponse> getPaymentMethods();
}

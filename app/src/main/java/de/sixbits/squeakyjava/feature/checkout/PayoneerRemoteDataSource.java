package de.sixbits.squeakyjava.feature.checkout;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class PayoneerRemoteDataSource {
    private final PayoneerApi mPayoneerApi;

    @Inject
    public PayoneerRemoteDataSource(PayoneerApi payoneerApi) {
        mPayoneerApi = payoneerApi;
    }

    public Single<List<PaymentMethodDataModel>> getPaymentMethods() {
        return mPayoneerApi.getPaymentMethods().map(PaymentMethodMapper::toDomainModel);
    }
}

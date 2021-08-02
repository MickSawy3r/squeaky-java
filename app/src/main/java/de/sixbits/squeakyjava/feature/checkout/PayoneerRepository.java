package de.sixbits.squeakyjava.feature.checkout;

import java.util.List;

import javax.inject.Inject;

import de.sixbits.squeakyjava.feature.checkout.PayoneerRemoteDataSource;
import de.sixbits.squeakyjava.feature.checkout.PaymentMethodDataModel;
import io.reactivex.rxjava3.core.Single;

public class PayoneerRepository {
    private final PayoneerRemoteDataSource mPayoneerRemoteDataSource;

    @Inject
    public PayoneerRepository(PayoneerRemoteDataSource payoneerRemoteDataSource) {
        mPayoneerRemoteDataSource = payoneerRemoteDataSource;
    }

    public Single<List<PaymentMethodDataModel>> getAvailablePaymentMethods() {
        return mPayoneerRemoteDataSource.getPaymentMethods();
    }
}

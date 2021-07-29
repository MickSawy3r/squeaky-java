package de.sixbits.squeakyjava.feature.checkout.data;

import java.util.List;

import javax.inject.Inject;

import de.sixbits.squeakyjava.feature.checkout.datasource.network.PayoneerRemoteDataSource;
import de.sixbits.squeakyjava.feature.checkout.domain.datamodel.PaymentMethodDataModel;
import io.reactivex.rxjava3.core.Single;

public class PayoneerRepository {
    private final PayoneerRemoteDataSource mPayoneerRemoteDataSource;

    @Inject
    PayoneerRepository(PayoneerRemoteDataSource payoneerRemoteDataSource) {
        mPayoneerRemoteDataSource = payoneerRemoteDataSource;
    }

    public Single<List<PaymentMethodDataModel>> getAvailablePaymentMethods() {
        return mPayoneerRemoteDataSource.getPaymentMethods();
    }
}

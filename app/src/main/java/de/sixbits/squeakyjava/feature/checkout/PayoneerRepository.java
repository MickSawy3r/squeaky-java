package de.sixbits.squeakyjava.feature.checkout;

import androidx.annotation.NonNull;

import java.net.HttpURLConnection;
import java.util.List;

import javax.inject.Inject;

import de.sixbits.platform.core.NetworkHandler;
import de.sixbits.platform.core.Failure;
import io.reactivex.rxjava3.core.Single;
import retrofit2.HttpException;

public class PayoneerRepository {
    private final PayoneerRemoteDataSource mPayoneerRemoteDataSource;
    private final NetworkHandler mNetworkHandler;

    @Inject
    public PayoneerRepository(
            PayoneerRemoteDataSource payoneerRemoteDataSource,
            NetworkHandler networkHandler
    ) {
        mPayoneerRemoteDataSource = payoneerRemoteDataSource;
        mNetworkHandler = networkHandler;
    }

    public Single<List<PaymentMethodDataModel>> getAvailablePaymentMethods() {
        if (mNetworkHandler.isNetworkAvailable()) {
            return requestCall(mPayoneerRemoteDataSource.getPaymentMethods());
        } else {
            return Single.error(new Failure.ConnectivityError());
        }
    }

    @NonNull
    private <T> Single<T> requestCall(@NonNull Single<T> request) {
        return request.onErrorResumeNext((err) -> {
            if (err instanceof HttpException) {
                HttpException httpException = (HttpException) err;
                if (httpException.code() == HttpURLConnection.HTTP_FORBIDDEN ||
                        httpException.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    return Single.error(new Failure.UnauthorizedError());
                }
                if (httpException.code() >= HttpURLConnection.HTTP_MULT_CHOICE &&
                        httpException.code() < HttpURLConnection.HTTP_INTERNAL_ERROR) {
                    return Single.error(new Failure.BadRequestError());
                }
                if (httpException.code() == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                    return Single.error(new Failure.ServerError());
                }
            }
            return Single.error(new Failure.UnknownFailure());
        });
    }
}

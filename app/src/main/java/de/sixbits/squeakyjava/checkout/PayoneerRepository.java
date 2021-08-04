package de.sixbits.squeakyjava.checkout;

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

    private <Request> Single<Request> requestCall(Single<Request> request) {
        return request.onErrorReturn((err) -> {
            if (err instanceof HttpException) {
                HttpException httpException = (HttpException) err;
                if (httpException.code() == HttpURLConnection.HTTP_FORBIDDEN ||
                        httpException.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    throw new Failure.UnauthorizedError();
                }
                if (httpException.code() >= HttpURLConnection.HTTP_MULT_CHOICE &&
                        httpException.code() < HttpURLConnection.HTTP_INTERNAL_ERROR) {
                    throw new Failure.BadRequestError();
                }
                if (httpException.code() == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                    throw new Failure.ServerError();
                }
            }

            throw new Failure.UnknownFailure();
        });
    }
}

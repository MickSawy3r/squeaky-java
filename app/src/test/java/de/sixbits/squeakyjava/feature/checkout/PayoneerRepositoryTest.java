package de.sixbits.squeakyjava.feature.checkout;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import de.sixbits.platform.core.Failure;
import de.sixbits.platform.core.NetworkHandler;
import de.sixbits.squeakyjava.feature.checkout.response.PaymentMethodsResponse;
import de.sixbits.squeakyjava.utils.PaymentMethodsResponseFactory;
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

@RunWith(MockitoJUnitRunner.class)
public class PayoneerRepositoryTest {

    @Mock
    PayoneerRemoteDataSource payoneerRemoteDataSource;

    @Mock
    NetworkHandler networkHandler;


    @Before
    public void setup() {
        RxJavaPlugins.setIoSchedulerHandler((v) -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler((v) -> Schedulers.trampoline());
    }

    @Test
    public void testOkResponse() {
        when(payoneerRemoteDataSource.getPaymentMethods())
                .thenReturn(Single.just(PaymentMethodsResponseFactory.getPaymentMethodList()));
        when(networkHandler.isNetworkAvailable())
                .thenReturn(true);

        PayoneerRepository payoneerRepository = new PayoneerRepository(
                payoneerRemoteDataSource,
                networkHandler
        );

        List<PaymentMethodDataModel> methods =
                payoneerRepository.getAvailablePaymentMethods().test().values().get(0);

        assertThat(methods.size()).isEqualTo(
                PaymentMethodsResponseFactory.getPaymentMethodList().size());
    }

    @Test
    public void testConnectivityError() {
        when(networkHandler.isNetworkAvailable())
                .thenReturn(false);

        PayoneerRepository payoneerRepository = new PayoneerRepository(
                payoneerRemoteDataSource,
                networkHandler
        );

        payoneerRepository.getAvailablePaymentMethods().test()
                .assertError((err) -> err instanceof Failure.ConnectivityError);
    }

    @Test
    public void testUnauthorizedError() {
        Response<List<PaymentMethodsResponse>> response = Response.error(
                403,
                ResponseBody.create(
                        "{\"key\":[\"somestuff\"]}",
                        MediaType.parse("application/json")
                )
        );

        when(payoneerRemoteDataSource.getPaymentMethods())
                .thenReturn(Single.error(new HttpException(response)));
        when(networkHandler.isNetworkAvailable())
                .thenReturn(true);

        PayoneerRepository payoneerRepository = new PayoneerRepository(
                payoneerRemoteDataSource,
                networkHandler
        );

        payoneerRepository.getAvailablePaymentMethods().test()
                .assertError((err) -> err instanceof Failure.UnauthorizedError);
    }

    @Test
    public void testRequestError() {
        Response<List<PaymentMethodsResponse>> response = Response.error(
                400,
                ResponseBody.create(
                        "{\"key\":[\"somestuff\"]}",
                        MediaType.parse("application/json")
                )
        );

        when(payoneerRemoteDataSource.getPaymentMethods())
                .thenReturn(Single.error(new HttpException(response)));
        when(networkHandler.isNetworkAvailable())
                .thenReturn(true);

        PayoneerRepository payoneerRepository = new PayoneerRepository(
                payoneerRemoteDataSource,
                networkHandler
        );

        payoneerRepository.getAvailablePaymentMethods().test()
                .assertError((err) -> err instanceof Failure.BadRequestError);
    }

    @Test
    public void testServerError() {
        Response<List<PaymentMethodsResponse>> response = Response.error(
                500,
                ResponseBody.create(
                        "{\"key\":[\"somestuff\"]}",
                        MediaType.parse("application/json")
                )
        );

        when(payoneerRemoteDataSource.getPaymentMethods())
                .thenReturn(Single.error(new HttpException(response)));
        when(networkHandler.isNetworkAvailable())
                .thenReturn(true);

        PayoneerRepository payoneerRepository = new PayoneerRepository(
                payoneerRemoteDataSource,
                networkHandler
        );

        payoneerRepository.getAvailablePaymentMethods().test()
                .assertError((err) -> err instanceof Failure.ServerError);
    }
}

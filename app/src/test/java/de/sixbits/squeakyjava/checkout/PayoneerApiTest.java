package de.sixbits.squeakyjava.checkout;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import de.sixbits.squeakyjava.helper.FileTestHelpers;
import de.sixbits.squeakyjava.checkout.response.PaymentMethodsResponse;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.observers.TestObserver;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class PayoneerApiTest {
    MockWebServer mockWebServer;

    @Before
    public void setup() {
        mockWebServer = new MockWebServer();
        try {
            mockWebServer.start(8765);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPayoneerApi() throws IOException {
        MockResponse response = new MockResponse();

        String jsonData = FileTestHelpers.loadJson("/listresult.json");
        response.setBody(jsonData);
        response.setResponseCode(200);

        mockWebServer.enqueue(response);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        PayoneerApi api = retrofit.create(PayoneerApi.class);
        TestObserver<PaymentMethodsResponse> testObserver = api.getPaymentMethods().test();
        testObserver.assertComplete();

        testObserver.assertValue((v) -> v.getNetworks().getApplicable().size() == 17);

        testObserver.assertValue((v) ->
                v.getNetworks()
                        .getApplicable()
                        .get(0)
                        .getLabel()
                        .equals("American Express"));

        testObserver.assertValue((v) ->
                v.getNetworks()
                        .getApplicable()
                        .get(0)
                        .getLinks()
                        .getLogo()
                        .equals("https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/amex.png"));
    }
}

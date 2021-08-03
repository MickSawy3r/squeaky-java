package de.sixbits.squeakyjava.checkout;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import de.sixbits.squeakyjava.checkout.response.PaymentMethodsResponse;
import de.sixbits.squeakyjava.factory.PaymentMethodsResponseFactory;

public class PaymentMethodMapperTest {

    @Test
    public void testMapper_shouldMapDataCorrectly() throws IOException {
        PaymentMethodsResponse response = PaymentMethodsResponseFactory.getPaymentResponse(
                PaymentMethodsResponseFactory.VisaDankortResponse
        );

        List<PaymentMethodDataModel> paymentMethod = PaymentMethodMapper.toDomainModel(response);

        assert paymentMethod.size() == 1;
        assert paymentMethod.get(0).getId().equals("0");
        assert paymentMethod.get(0).getLogoUrl().equals("https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/visa_dankort.png");
        assert paymentMethod.get(0).getName().equals("Visa Dankort");
    }
}

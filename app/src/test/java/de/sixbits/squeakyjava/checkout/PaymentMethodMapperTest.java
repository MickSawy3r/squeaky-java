package de.sixbits.squeakyjava.checkout;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import de.sixbits.squeakyjava.TestHelpers;
import de.sixbits.squeakyjava.checkout.response.PaymentMethodsResponse;
import de.sixbits.squeakyjava.factory.PaymentMethodsResponseFactory;

public class PaymentMethodMapperTest {

    @Test
    public void testMapper_shouldMapDataCorrectly() throws IOException {
        String jsonData = TestHelpers.loadJson("/listresult.json");
        PaymentMethodsResponse response = PaymentMethodsResponseFactory.getPaymentResponse(
                jsonData
        );

        List<PaymentMethodDataModel> paymentMethod = PaymentMethodMapper.toDomainModel(response);

        assertThat(paymentMethod.size()).isEqualTo(17);
        assertThat(paymentMethod.get(0).getId()).isEqualTo("0");
        assertThat(paymentMethod.get(0).getLogoUrl()).isEqualTo(
                "https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/amex.png"
        );
        assertThat(paymentMethod.get(0).getName()).isEqualTo(
                "American Express"
        );
    }
}

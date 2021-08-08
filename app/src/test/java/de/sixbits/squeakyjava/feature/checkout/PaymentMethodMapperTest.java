package de.sixbits.squeakyjava.feature.checkout;

import static com.google.common.truth.Truth.assertThat;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import de.sixbits.squeakyjava.utils.FileTestHelpers;
import de.sixbits.squeakyjava.feature.checkout.response.PaymentMethodsResponse;
import de.sixbits.squeakyjava.utils.PaymentMethodsResponseFactory;

public class PaymentMethodMapperTest {

    @Test
    public void testMapper_shouldMapDataCorrectly() throws IOException {
        String jsonData = FileTestHelpers.loadJson("/listresult.json");
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

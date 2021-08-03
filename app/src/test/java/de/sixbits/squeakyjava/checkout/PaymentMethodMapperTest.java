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
                "{\"networks\":{\"applicable\":[{\"code\":\"VISA_DANKORT\",\"label\":\"Visa Dankort\",\"method\":\"DEBIT_CARD\",\"grouping\":\"DEBIT_CARD\",\"registration\":\"OPTIONAL\",\"recurrence\":\"NONE\",\"redirect\":false,\"links\":{\"logo\":\"https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/visa_dankort.png\",\"self\":\"https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/VISA_DANKORT\",\"lang\":\"https://resources.integration.oscato.com/resource/lang/MOBILETEAM/en_US/VISA_DANKORT.json\",\"operation\":\"https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/VISA_DANKORT/charge\",\"validation\":\"https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/MOBILETEAM/en_US/VISA_DANKORT/standard/validate\"},\"selected\":false,\"inputElements\":[{\"name\":\"number\",\"type\":\"numeric\"},{\"name\":\"expiryMonth\",\"type\":\"integer\"},{\"name\":\"expiryYear\",\"type\":\"integer\"},{\"name\":\"verificationCode\",\"type\":\"integer\"},{\"name\":\"holderName\",\"type\":\"string\"}],\"operationType\":\"CHARGE\"}]}}"
        );

        List<PaymentMethodDataModel> paymentMethod = PaymentMethodMapper.toDomainModel(response);

        assert paymentMethod.size() == 1;
        assert paymentMethod.get(0).getId().equals("0");
        assert paymentMethod.get(0).getLogoUrl().equals("https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/visa_dankort.png");
        assert paymentMethod.get(0).getName().equals("Visa Dankort");
    }
}

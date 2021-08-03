package de.sixbits.squeakyjava.factory;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import de.sixbits.squeakyjava.checkout.PaymentMethodMapper;
import de.sixbits.squeakyjava.checkout.response.PaymentMethodsResponse;

public class PaymentMethodsResponseFactory {

    public static PaymentMethodsResponse getPaymentResponse(String jsonPayload) throws IOException {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<PaymentMethodsResponse> adapter = moshi.adapter(PaymentMethodsResponse.class);
        return adapter.fromJson(jsonPayload);
    }

    /**
     * Contains:
     * code: VISA_DANKORT
     * label: Visa Dankort
     * logo: https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/visa_dankort.png
     */
    public static String VisaDankortResponse = "{\"networks\":{\"applicable\":[{\"code\":\"VISA_DANKORT\",\"label\":\"Visa Dankort\",\"method\":\"DEBIT_CARD\",\"grouping\":\"DEBIT_CARD\",\"registration\":\"OPTIONAL\",\"recurrence\":\"NONE\",\"redirect\":false,\"links\":{\"logo\":\"https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/visa_dankort.png\",\"self\":\"https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/VISA_DANKORT\",\"lang\":\"https://resources.integration.oscato.com/resource/lang/MOBILETEAM/en_US/VISA_DANKORT.json\",\"operation\":\"https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/VISA_DANKORT/charge\",\"validation\":\"https://api.integration.oscato.com/pci/v1/6076b2feabe8170009d56de4l7ab1tlvai852jekk4s2h2b7it/MOBILETEAM/en_US/VISA_DANKORT/standard/validate\"},\"selected\":false,\"inputElements\":[{\"name\":\"number\",\"type\":\"numeric\"},{\"name\":\"expiryMonth\",\"type\":\"integer\"},{\"name\":\"expiryYear\",\"type\":\"integer\"},{\"name\":\"verificationCode\",\"type\":\"integer\"},{\"name\":\"holderName\",\"type\":\"string\"}],\"operationType\":\"CHARGE\"}]}}";
}

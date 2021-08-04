package de.sixbits.squeakyjava.feature.checkout;

import java.util.ArrayList;
import java.util.List;

import de.sixbits.squeakyjava.feature.checkout.response.ApplicableItem;
import de.sixbits.squeakyjava.feature.checkout.response.PaymentMethodsResponse;

public class PaymentMethodMapper {
    public static List<PaymentMethodDataModel> toDomainModel(PaymentMethodsResponse response) {
        List<PaymentMethodDataModel> result = new ArrayList<>();
        List<ApplicableItem> networks = response.getNetworks().getApplicable();
        for (int i = 0; i < networks.size(); i++) {
            result.add(new PaymentMethodDataModel(
                    String.valueOf(i),
                    networks.get(i).getLabel(),
                    networks.get(i).getLinks().getLogo()
            ));
        }
        return result;
    }
}

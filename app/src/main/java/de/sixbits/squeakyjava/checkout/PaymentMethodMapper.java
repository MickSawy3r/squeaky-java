package de.sixbits.squeakyjava.checkout;

import java.util.ArrayList;
import java.util.List;

import de.sixbits.squeakyjava.checkout.response.ApplicableItem;
import de.sixbits.squeakyjava.checkout.response.PaymentMethodsResponse;

public class PaymentMethodMapper {
    static List<PaymentMethodDataModel> toDomainModel(PaymentMethodsResponse response) {
        List<PaymentMethodDataModel> result = new ArrayList<>();
        List<ApplicableItem> networks = response.getNetworks().getApplicable();
        for (int i = 0; i < networks.size(); i++) {
            result.add(new PaymentMethodDataModel(
                    networks.get(i).getLabel(),
                    networks.get(i).getLinks().getLogo()
            ));
        }
        return result;
    }
}

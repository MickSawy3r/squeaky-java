package de.sixbits.squeakyjava.feature.checkout.datasource.network;

import java.util.ArrayList;
import java.util.List;

import de.sixbits.squeakyjava.feature.checkout.datasource.network.response.ApplicableItem;
import de.sixbits.squeakyjava.feature.checkout.datasource.network.response.PaymentMethodsResponse;
import de.sixbits.squeakyjava.feature.checkout.domain.datamodel.PaymentMethodDataModel;

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

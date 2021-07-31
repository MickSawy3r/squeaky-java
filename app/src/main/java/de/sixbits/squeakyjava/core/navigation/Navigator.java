package de.sixbits.squeakyjava.core.navigation;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.sixbits.squeakyjava.feature.checkout.domain.datamodel.PaymentMethodDataModel;
import de.sixbits.squeakyjava.feature.checkout.presentation.CheckoutActivity;
import de.sixbits.squeakyjava.feature.checkout.presentation.PaymentFormActivity;

@Singleton
public class Navigator {

    @Inject
    Navigator() {
    }

    public void showCheckout(@NotNull Context context) {
        context.startActivity(CheckoutActivity.callingIntent(context));
    }

    public void showPaymentForm(@NotNull Context context, PaymentMethodDataModel paymentMethod) {
        context.startActivity(PaymentFormActivity.callingIntent(context, paymentMethod));
    }
}

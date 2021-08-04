package de.sixbits.squeakyjava.core.navigation;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.sixbits.squeakyjava.feature.checkout.PaymentMethodDataModel;
import de.sixbits.squeakyjava.feature.checkout.PaymentMethodActivity;
import de.sixbits.squeakyjava.feature.checkout.PaymentFormActivity;

@Singleton
public class Navigator {

    @Inject
    Navigator() {
    }

    public void showPaymentMethod(@NotNull Context context) {
        context.startActivity(PaymentMethodActivity.callingIntent(context));
    }

    public void showPaymentForm(@NotNull Context context, PaymentMethodDataModel paymentMethod) {
        context.startActivity(PaymentFormActivity.callingIntent(context, paymentMethod));
    }
}

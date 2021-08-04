package de.sixbits.squeakyjava.checkout;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static com.google.common.truth.Truth.assertThat;

import android.content.Context;
import android.widget.TextView;

import org.junit.Test;

import dagger.hilt.android.testing.HiltAndroidTest;
import de.sixbits.squeakyjava.R;
import de.sixbits.squeakyjava.RobolectricTest;
import de.sixbits.squeakyjava.helper.HiltTestHelpers;

@HiltAndroidTest
public class PaymentFormFragmentTest extends RobolectricTest {

    @Test
    public void testPaymentFormFragment_shouldShowPaymentMethodName() {
        // Given Payment Method Fragment is requested for payment method "CC"
        PaymentMethodDataModel method = new PaymentMethodDataModel(
                "id",
                "CC",
                "image"
        );
        PaymentFormFragment fragment = PaymentFormFragment.getInstance(method);
        HiltTestHelpers.launchFragmentInHiltContainer(fragment);

        // Then a text with the name "CC" Must show up in the screen.
        assertThat(fragment.getView()).isNotNull();
        TextView tvPayment = fragment.getView().findViewById(R.id.tv_payment);
        assertThat(tvPayment.getText().toString()).isEqualTo(method.getName());
    }

    @Test
    public void testPaymentFormFragment_shouldShowError() {
        // Given Payment Method Fragment is requested for no payment method
        PaymentFormFragment fragment = new PaymentFormFragment();
        HiltTestHelpers.launchFragmentInHiltContainer(fragment);

        // Then a text with the error should show in the screen
        Context context = getApplicationContext();
        assertThat(fragment.getView()).isNotNull();
        TextView tvPayment = fragment.getView().findViewById(R.id.tv_payment);
        assertThat(tvPayment.getText().toString()).isEqualTo(
                context.getString(R.string.err_empty_payment_method)
        );
    }
}

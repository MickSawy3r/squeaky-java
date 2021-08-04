package de.sixbits.squeakyjava.checkout;

import static com.google.common.truth.Truth.assertThat;

import android.os.Build;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import dagger.hilt.android.testing.HiltTestApplication;
import de.sixbits.squeakyjava.R;
import de.sixbits.squeakyjava.RoboCustomRunner;
import de.sixbits.squeakyjava.RobolectricTest;
import de.sixbits.squeakyjava.TestHelpers;

@HiltAndroidTest
public class PaymentFormFragmentTest extends RobolectricTest {

    @Test
    public void testPaymentFormFragment() {
        // Given Payment Method Fragment is requested for payment method "CC"
        PaymentMethodDataModel method = new PaymentMethodDataModel(
                "id",
                "CC",
                "image"
        );
        PaymentFormFragment fragment = PaymentFormFragment.getInstance(method);
        TestHelpers.launchFragmentInHiltContainer(fragment);

        // Then a text with the name "CC" Must show up in the screen.
        assertThat(fragment.getView()).isNotNull();
        TextView tvPayment = fragment.getView().findViewById(R.id.tv_payment);
        assertThat(tvPayment.getText().toString()).isEqualTo(method.getName());
    }
}
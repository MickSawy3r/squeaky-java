package de.sixbits.squeakyjava.checkout;

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
        PaymentMethodDataModel method = new PaymentMethodDataModel(
                "id",
                "CC",
                "image"
        );
        PaymentFormFragment fragment = PaymentFormFragment.getInstance(method);
        TestHelpers.launchFragmentInHiltContainer(fragment);

        assert fragment.getView() != null;
        TextView tvPayment = fragment.getView().findViewById(R.id.tv_payment);

        assert tvPayment.getText().toString().equals(method.getName());

        System.out.println(tvPayment.getText().toString());
    }
}
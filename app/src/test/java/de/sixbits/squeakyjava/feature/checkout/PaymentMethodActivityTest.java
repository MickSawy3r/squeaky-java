package de.sixbits.squeakyjava.feature.checkout;

import static androidx.test.core.app.ActivityScenario.launch;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static com.google.common.truth.Truth.assertThat;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.test.core.app.ActivityScenario;

import org.junit.Before;
import org.junit.Test;

import dagger.hilt.android.testing.HiltAndroidTest;
import de.sixbits.squeakyjava.R;
import de.sixbits.squeakyjava.RobolectricTest;

@HiltAndroidTest
public class PaymentMethodActivityTest extends RobolectricTest {

    Context context;

    @Before
    public void setup() {
        context = getApplicationContext();
    }

    @Test
    public void testPaymentMethods() {
        Intent intent = PaymentMethodActivity.callingIntent(context);

        ActivityScenario<PaymentMethodActivity> scenario = launch(intent);
        scenario.onActivity((activity -> {
            Fragment fragment = activity.getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_container);

            assertThat(fragment).isNotNull();
            assertThat(fragment).isInstanceOf(PaymentMethodFragment.class);
        }));
    }
}

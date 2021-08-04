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
public class PaymentFormActivityTest extends RobolectricTest {

    Context context;

    @Before
    public void setup() {
        context = getApplicationContext();
    }

    @Test
    public void testPaymentForm() {
        PaymentMethodDataModel dataModel = new PaymentMethodDataModel(
                "1",
                "name",
                "url"
        );
        Intent intent = PaymentFormActivity.callingIntent(context, dataModel);

        ActivityScenario<PaymentFormActivity> scenario = launch(intent);
        scenario.onActivity((activity -> {
            Fragment fragment = activity.getSupportFragmentManager().findFragmentById(R.id.fragment_container);

            assertThat(fragment).isNotNull();
            assertThat(fragment).isInstanceOf(PaymentFormFragment.class);
        }));
    }
}

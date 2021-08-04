package de.sixbits.squeakyjava;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static com.google.common.truth.Truth.assertThat;
import static de.sixbits.squeakyjava.EspressoIdlingResource.countingIdlingResource;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import de.sixbits.squeakyjava.feature.checkout.PaymentFormActivity;
import de.sixbits.squeakyjava.feature.checkout.PaymentMethodDataModel;

@HiltAndroidTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class PaymentFormAcceptanceTest {

    @Rule
    public ActivityTestRule<PaymentFormActivity> activityTestRule =
            new ActivityTestRule<>(PaymentFormActivity.class, false, false);

    @Rule
    public HiltAndroidRule hiltAndroidRule = new HiltAndroidRule(this);

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(countingIdlingResource);
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(countingIdlingResource);
    }

    @Test
    public void testPaymentForm() {
        Intent intent = PaymentFormActivity.callingIntent(
                getApplicationContext(),
                new PaymentMethodDataModel("1", "CC", "Url")
        );
        PaymentFormActivity activity = activityTestRule.launchActivity(intent);

        TextView tvPayment = activity.findViewById(R.id.tv_payment);
        assertThat(tvPayment.getVisibility())
                .isEqualTo(View.VISIBLE);
        assertThat(tvPayment.getText().toString())
                .isEqualTo("CC");
    }
}

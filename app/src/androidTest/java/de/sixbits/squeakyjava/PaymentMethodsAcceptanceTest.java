package de.sixbits.squeakyjava;

import static de.sixbits.squeakyjava.EspressoIdlingResource.countingIdlingResource;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import de.sixbits.squeakyjava.feature.checkout.PaymentMethodActivity;
import de.sixbits.squeakyjava.feature.checkout.PaymentMethodFragment;
import de.sixbits.squeakyjava.helper.HiltTestHelpers;

@HiltAndroidTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class PaymentMethodsAcceptanceTest {

    @Rule
    public ActivityTestRule<PaymentMethodActivity> activityTestRule =
            new ActivityTestRule<>(PaymentMethodActivity.class, false, false);

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

    public void testPaymentMethod() {
        PaymentMethodFragment fragment = PaymentMethodFragment.getInstance();

        HiltTestHelpers.launchFragmentInHiltContainer(fragment);
    }
}

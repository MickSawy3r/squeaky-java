package de.sixbits.squeakyjava;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static de.sixbits.squeakyjava.EspressoIdlingResource.countingIdlingResource;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import dagger.hilt.android.testing.BindValue;
import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import dagger.hilt.android.testing.UninstallModules;
import de.sixbits.squeakyjava.core.di.NetworkModule;
import de.sixbits.squeakyjava.feature.checkout.PaymentMethodActivity;
import de.sixbits.squeakyjava.feature.checkout.PaymentMethodFragment;
import de.sixbits.squeakyjava.utils.HiltTestHelpers;
import de.sixbits.squeakyjava.utils.NetworkTestHelper;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@HiltAndroidTest
@UninstallModules(NetworkModule.class)
@RunWith(AndroidJUnit4ClassRunner.class)
public class PaymentMethodsServerError {

    @Rule
    public HiltAndroidRule hiltAndroidRule = new HiltAndroidRule(this);

    @Rule
    public ActivityTestRule<PaymentMethodActivity> activityTestRule =
            new ActivityTestRule<>(PaymentMethodActivity.class, false, false);

    @BindValue
    Retrofit retrofit = createRetrofitClient();

    @Before
    public void setup() {
        hiltAndroidRule.inject();
        IdlingRegistry.getInstance().register(countingIdlingResource);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(countingIdlingResource);
    }

    @Test
    public void testPaymentMethod() {
        PaymentMethodFragment fragment = PaymentMethodFragment.getInstance();

        HiltTestHelpers.launchFragmentInHiltContainer(fragment);

        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.failure_server_error)));
    }


    private Retrofit createRetrofitClient() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(
                        new OkHttpClient.Builder()
                                .addInterceptor(new HttpLoggingInterceptor()
                                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                                .addInterceptor(NetworkTestHelper.provideMockInterceptor(
                                        "error500.json", 500))
                                .build()
                )
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }
}

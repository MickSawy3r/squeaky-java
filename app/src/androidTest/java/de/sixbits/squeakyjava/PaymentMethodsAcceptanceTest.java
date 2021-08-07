package de.sixbits.squeakyjava;

import static java.lang.Thread.sleep;
import static de.sixbits.squeakyjava.EspressoIdlingResource.countingIdlingResource;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.testing.BindValue;
import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import dagger.hilt.android.testing.UninstallModules;
import dagger.hilt.components.SingletonComponent;
import de.sixbits.squeakyjava.core.di.BaseUrl;
import de.sixbits.squeakyjava.core.di.BaseUrlModule;
import de.sixbits.squeakyjava.feature.checkout.PaymentMethodActivity;
import de.sixbits.squeakyjava.feature.checkout.PaymentMethodFragment;
import de.sixbits.squeakyjava.helper.FileTestHelpers;
import de.sixbits.squeakyjava.helper.HiltTestHelpers;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

@HiltAndroidTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class PaymentMethodsAcceptanceTest {

    @Rule
    public HiltAndroidRule hiltAndroidRule = new HiltAndroidRule(this);

    @Rule
    public ActivityTestRule<PaymentMethodActivity> activityTestRule =
            new ActivityTestRule<>(PaymentMethodActivity.class, false, false);

    MockWebServer webServer;

    @Before
    public void setup() {
        IdlingRegistry.getInstance().register(countingIdlingResource);
        webServer = new MockWebServer();
        try {
            webServer.start(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(countingIdlingResource);
        try {
            webServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPaymentMethod() throws IOException {
        MockResponse response = new MockResponse();
        response.setResponseCode(200);
        response.setBody(FileTestHelpers.loadJson("listresult.json"));

        webServer.enqueue(response);

        PaymentMethodFragment fragment = PaymentMethodFragment.getInstance();

        HiltTestHelpers.launchFragmentInHiltContainer(fragment);
        try {
            sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package de.sixbits.squeakyjava;

import android.os.Build;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import dagger.hilt.android.testing.HiltTestApplication;

@HiltAndroidTest
@RunWith(RoboCustomRunner.class)
@Config(
        manifest = Config.NONE,
        sdk = {Build.VERSION_CODES.O_MR1},
        application = HiltTestApplication.class
)
public abstract class RobolectricTest {
    @Rule
    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);
}

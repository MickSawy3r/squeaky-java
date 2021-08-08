package de.sixbits.squeakyjava.utils;

import static com.google.common.truth.Truth.assertThat;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import org.robolectric.Robolectric;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;

import de.sixbits.platform.core.ContainerActivity;
import de.sixbits.squeakyjava.core.navigation.RouterActivity;

public class RobolectricAssertions {

    public static <T extends ContainerActivity> void shouldNavigateTo(
            Class<RouterActivity> currentActivity,
            Class<T> nextActivity
    ) {
        AppCompatActivity originActivity = Robolectric.buildActivity(currentActivity).get();
        ShadowActivity shadowActivity = Shadows.shadowOf(originActivity);
        Intent nextIntent = shadowActivity.peekNextStartedActivity();

        assertThat(nextIntent.getComponent().getClassName()).isEqualTo(nextActivity.getCanonicalName());
    }
}

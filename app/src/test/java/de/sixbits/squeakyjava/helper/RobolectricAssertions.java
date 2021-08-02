package de.sixbits.squeakyjava.helper;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import org.robolectric.Robolectric;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowActivity;

import de.sixbits.platform.core.ContainerActivity;
import de.sixbits.squeakyjava.feature.routing.RouterActivity;

public class RobolectricAssertions {

    public static <T extends ContainerActivity> void shouldNavigateTo(
            Class<RouterActivity> currentActivity,
            Class<T> nextActivity
    ) {
        AppCompatActivity originActivity = Robolectric.buildActivity(currentActivity).get();
        ShadowActivity shadowActivity = Shadows.shadowOf(originActivity);
        Intent nextIntent = shadowActivity.peekNextStartedActivity();

        assert nextIntent.getComponent().getClassName().equals(nextActivity.getCanonicalName());
    }
}

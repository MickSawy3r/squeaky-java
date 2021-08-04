package de.sixbits.squeakyjava;

import android.content.ComponentName;
import android.content.Intent;

import androidx.annotation.StyleRes;
import androidx.fragment.app.Fragment;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;

public class TestHelpers {

    public static void launchFragmentInHiltContainer(
            Fragment fragment,
            @StyleRes Integer themeResId
    ) {
        Intent startActivityIntent = Intent.makeMainActivity(
                new ComponentName(
                        ApplicationProvider.getApplicationContext(),
                        HiltTestActivity.class
                )
        ).putExtra(
                "androidx.fragment.app.testing.FragmentScenario.EmptyFragmentActivity.THEME_EXTRAS_BUNDLE_KEY",
                themeResId
        );

        ActivityScenario<HiltTestActivity> startActivityScenario = ActivityScenario.launch(startActivityIntent);

        startActivityScenario.onActivity((activity -> activity.getSupportFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, fragment, "")
                .commitNow()));
    }

    public static void launchFragmentInHiltContainer(
            Fragment fragment
    ) {
        launchFragmentInHiltContainer(fragment, R.style.FragmentScenarioEmptyFragmentActivityTheme);
    }
}

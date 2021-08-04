package de.sixbits.squeakyjava;

import android.content.ComponentName;
import android.content.Intent;

import androidx.annotation.StyleRes;
import androidx.fragment.app.Fragment;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

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

    public static String loadJson(String fileName) throws IOException {
        if (fileName.toCharArray()[0] != '/') {
            fileName = "/" + fileName;
        }

        URL url = TestHelpers.class.getResource(fileName);
        if (url == null) {
            throw new FileNotFoundException();
        }
        File file = new File(url.getFile());

        StringBuilder jsonString = new StringBuilder();

        try (Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
            stream.forEach(jsonString::append);
        }

        return jsonString.toString();
    }
}

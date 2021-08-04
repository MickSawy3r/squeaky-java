package de.sixbits.squeakyjava;

import androidx.test.espresso.idling.CountingIdlingResource;

public class EspressoIdlingResource {
    static final String RESOURCE = "GLOBAL";
    static final CountingIdlingResource countingIdlingResource = new CountingIdlingResource(RESOURCE);

    public static void increment() {
        countingIdlingResource.increment();
    }

    public static void decrement() {
        if (!countingIdlingResource.isIdleNow()) {
            countingIdlingResource.decrement();
        }
    }
}

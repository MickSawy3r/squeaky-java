package de.sixbits.squeakyjava.feature.checkout;

import androidx.annotation.NonNull;

import de.sixbits.platform.core.Failure;

public class CheckoutFailure extends Failure.FeatureFailure {

    public static class BadRequestError extends CheckoutFailure {
        @NonNull
        @Override
        public String toString() {
            return "Bad Network Request";
        }
    }

    public static class ConnectivityError extends CheckoutFailure {
        @NonNull
        @Override
        public String toString() {
            return "Connectivity Error";
        }
    }

    public static class ServerError extends CheckoutFailure {
        @NonNull
        @Override
        public String toString() {
            return "Server Error";
        }
    }
}

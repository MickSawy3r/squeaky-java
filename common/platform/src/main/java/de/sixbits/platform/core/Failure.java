package de.sixbits.platform.core;

import androidx.annotation.NonNull;

public class Failure extends Exception {

    private Failure() {
    }

    public static class NetworkConnection extends Failure {
        @NonNull
        @Override
        public String toString() {
            return "Network Connection";
        }
    }

    public static class UnauthorizedError extends Failure {
        @NonNull
        @Override
        public String toString() {
            return "Unauthorized Error";
        }
    }

    public static class UnknownFailure extends Failure {
        @NonNull
        @Override
        public String toString() {
            return "Unknown Failure";
        }
    }

    /**
     * Extend this class for feature specific failures.
     */
    abstract static public class FeatureFailure extends Failure {
    }
}

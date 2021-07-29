package de.sixbits.platform.core;

public class Failure extends Exception {
    public static class NetworkConnection extends Failure {
    }

    public static class ServerError extends Failure {
    }

    public static class UnauthorizedError extends Failure {
    }

    /**
     * Extend this class for feature specific failures.
     */
    abstract static class FeatureFailure extends Failure {
    }
}

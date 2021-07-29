package de.sixbits.platform.core;

public class Failure extends Exception {
    static class NetworkConnection extends Failure {
    }

    static class ServerError extends Failure {
    }

    static class UnauthorizedError extends Failure {
    }

    /**
     * Extend this class for feature specific failures.
     */
    abstract static class FeatureFailure extends Failure {
    }
}

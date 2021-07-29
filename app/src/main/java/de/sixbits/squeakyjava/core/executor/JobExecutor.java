package de.sixbits.squeakyjava.core.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.sixbits.reactive.executor.ThreadExecutor;

@Singleton
public class JobExecutor extends ThreadExecutor {

    @Inject
    JobExecutor() {
    }

    private static final int THREAD_POOL = 3;

    Executor getThreadPoolExecutor() {
        return Executors.newFixedThreadPool(THREAD_POOL);
    }

    @Override
    public void execute(Runnable runnable) {
        getThreadPoolExecutor().execute(runnable);
    }
}

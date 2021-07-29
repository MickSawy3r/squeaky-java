package de.sixbits.squeakyjava.core.executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.sixbits.reactive.executor.PostExecutionThread;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;

@Singleton
public class UIThread implements PostExecutionThread {
    @Inject
    UIThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}

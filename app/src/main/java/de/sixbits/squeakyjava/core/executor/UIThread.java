package de.sixbits.squeakyjava.core.executor;

import javax.inject.Singleton;

import de.sixbits.reactive.executor.PostExecutionThread;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;

@Singleton
public class UIThread extends PostExecutionThread {
    Scheduler scheduler = AndroidSchedulers.mainThread();
}

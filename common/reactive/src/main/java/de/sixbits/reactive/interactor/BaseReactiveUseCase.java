package de.sixbits.reactive.interactor;

import de.sixbits.reactive.executor.PostExecutionThread;
import de.sixbits.reactive.executor.ThreadExecutor;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public abstract class BaseReactiveUseCase {
    private final CompositeDisposable disposables = new CompositeDisposable();

    private final ThreadExecutor mThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;

    BaseReactiveUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        mThreadExecutor = threadExecutor;
        mPostExecutionThread = postExecutionThread;
    }

    protected Scheduler getThreadExecutorScheduler() {
        return Schedulers.from(mThreadExecutor);
    }


    protected Scheduler getPostExecutionThreadScheduler() {
        return mPostExecutionThread.scheduler;
    }

    void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }
}

package de.sixbits.reactive.interactor;

import de.sixbits.reactive.executor.PostExecutionThread;
import de.sixbits.reactive.executor.ThreadExecutor;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public abstract class ObservableUseCase<Results, Params> extends BaseReactiveUseCase {

    ObservableUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    /**
     * Builds an [Observable] which will be used when executing the current [ObservableUseCase].
     */
    abstract Observable<Results> buildUseCaseObservable(Params params);

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableObserver] which will be listening to the observer build
     *                 by [buildUseCaseObservable] method.
     * @param params   Parameters (Optional) used to build/execute this use case.
     */
    void execute(DisposableObserver<Results> observer, Params params) {
        if (observer == null) {
            observer = new EmptyObserver<>();
        }
        Observable<Results> observable = buildUseCaseObservableWithSchedulers(params);
        addDisposable(observable.subscribeWith(observer));
    }

    /**
     * Builds an [Observable] which will be used when executing the current [ObservableUseCase].
     * With provided Schedulers
     */
    private Observable<Results> buildUseCaseObservableWithSchedulers(Params params) {
        return buildUseCaseObservable(params)
                .subscribeOn(getThreadExecutorScheduler())
                .observeOn(getPostExecutionThreadScheduler());
    }
}

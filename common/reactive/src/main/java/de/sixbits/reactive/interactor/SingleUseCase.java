package de.sixbits.reactive.interactor;

import de.sixbits.reactive.executor.PostExecutionThread;
import de.sixbits.reactive.executor.ThreadExecutor;
import io.reactivex.rxjava3.core.Single;

public abstract class SingleUseCase<Results, Params> extends BaseReactiveUseCase {

    SingleUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    /**
     * Builds an [Observable] which will be used when executing the current [ObservableUseCase].
     */
    abstract Single<Results> buildSingleUseCaseObservable(Params params);

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableObserver] which will be listening to the observer build
     *                 by [buildUseCaseObservable] method.
     * @param params   Parameters (Optional) used to build/execute this use case.
     */
    void execute(EmptySingleObserver<Results> observer, Params params) {
        if (observer == null) {
            observer = new EmptySingleObserver<>();
        }
        Single<Results> single = buildUseCaseSingleWithSchedulers(params);
        addDisposable(single.subscribeWith(observer));
    }

    /**
     * Builds an [Observable] which will be used when executing the current [ObservableUseCase].
     * With provided Schedulers
     */
    private Single<Results> buildUseCaseSingleWithSchedulers(Params params) {
        return buildSingleUseCaseObservable(params)
                .subscribeOn(getThreadExecutorScheduler())
                .observeOn(getPostExecutionThreadScheduler());
    }
}

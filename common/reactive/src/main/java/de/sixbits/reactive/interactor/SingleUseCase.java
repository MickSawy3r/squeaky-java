package de.sixbits.reactive.interactor;

import de.sixbits.reactive.executor.PostExecutionThread;
import de.sixbits.reactive.executor.ThreadExecutor;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public abstract class SingleUseCase<Results, Params> extends BaseReactiveUseCase {

    public SingleUseCase(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread
    ) {
        super(threadExecutor, postExecutionThread);
    }

    /**
     * Builds an [Single] which will be used when executing the current [SingleUseCase].
     */
    public abstract Single<Results> buildUseCaseSingle(Params params);

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableSingleObserver] which will be listening to the observer build
     *                 by [buildUseCaseSingle] method.
     * @param params   Parameters (Optional) used to build/execute this use case.
     */
    public void execute(DisposableSingleObserver<Results> observer, Params params) {
        if (observer == null) {
            observer = new EmptySingleObserver<>();
        }
        Single<Results> single = buildUseCaseSingleWithSchedulers(params);
        addDisposable(single.subscribeWith(observer));
    }

    /**
     * Builds a [Single] which will be used when executing the current [SingleUseCase].
     * With provided Schedulers
     */
    private Single<Results> buildUseCaseSingleWithSchedulers(Params params) {
        return buildUseCaseSingle(params)
                .subscribeOn(getThreadExecutorScheduler())
                .observeOn(getPostExecutionThreadScheduler());
    }
}

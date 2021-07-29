package de.sixbits.reactive.interactor;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class EmptySingleObserver<T> extends DisposableSingleObserver<T> {
    @Override
    public void onSuccess(@NonNull T t) {
    }

    @Override
    public void onError(@NonNull Throwable e) {
    }
}

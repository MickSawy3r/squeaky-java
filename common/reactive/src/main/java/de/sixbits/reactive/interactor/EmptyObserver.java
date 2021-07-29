package de.sixbits.reactive.interactor;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class EmptyObserver<T> extends DisposableObserver<T> {
    public void onNext(@NonNull T t) {
    }

    public void onError(@NonNull Throwable t) {
    }

    public void onComplete() {
    }
}

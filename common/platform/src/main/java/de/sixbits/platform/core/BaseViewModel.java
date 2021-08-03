package de.sixbits.platform.core;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {

    private final MutableLiveData<Boolean> _loading = new MutableLiveData<>(false);
    public LiveData<Boolean> loading = _loading;

    private final MutableLiveData<Failure> _failure = new MutableLiveData<>();
    public LiveData<Failure> failure = _failure;

    protected void handleFailure(Failure failure) {
        _failure.setValue(failure);
    }

    protected void setLoading(Boolean isLoading) {
        _loading.postValue(isLoading);
    }
}


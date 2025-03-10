package com.felfeit.readly.presentation.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.felfeit.readly.domain.model.Work;
import com.felfeit.readly.domain.usecase.WorkUseCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();

    private final MutableLiveData<List<Work>> works = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    private final PublishSubject<String> querySubject = PublishSubject.create(); // Untuk search real-time

    @Inject
    MainViewModel(WorkUseCase useCase) {
        disposables.add(
                querySubject
                        .debounce(750, TimeUnit.MILLISECONDS)
                        .distinctUntilChanged()
                        .switchMapSingle(query -> useCase.searchWorks(query)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .doOnSubscribe(disposable -> {
                                    isLoading.postValue(true);
                                    Log.d("DEBUG", "Mulai fetch data untuk query: " + query);
                                })
                                .doAfterTerminate(() -> isLoading.postValue(false))
                                .onErrorReturnItem(Collections.emptyList())
                                .map(ArrayList::new))
                        .subscribe(
                                result -> {
                                    Log.d("DEBUG", "Data diterima di ViewModel, total items: " + result.size());
                                    works.postValue(result);
                                },
                                throwable -> {
                                    Log.e("ERROR", "Gagal fetch data: " + throwable.getMessage());
                                    errorMessage.postValue(throwable.getMessage());
                                }
                        )
        );
    }

    public void searchWorks(String query) {
        querySubject.onNext(query);
    }

    public LiveData<List<Work>> getWorks() {
        return works;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
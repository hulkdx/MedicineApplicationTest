package com.hulkdx.medicine.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hulkdx.medicine.di.ConfigPersistent
import javax.inject.Inject

import hulkdx.com.domain.interactor.MedicineUseCase
import hulkdx.com.domain.models.MedicineCollection
import io.reactivex.functions.Consumer

/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/11/2018.
 */
@ConfigPersistent
class MainViewModel @Inject constructor(
        private val mGetGithubRepositoryList: MedicineUseCase
): ViewModel() {

    private var medicineLiveData = MutableLiveData<MainContract>()

    override fun onCleared() {
        super.onCleared()
        mGetGithubRepositoryList.dispose()
    }

    fun loadData() {
        medicineLiveData.value = MainContract.Loading
        mGetGithubRepositoryList.execute(
                Consumer { medicineCollection ->
                    medicineLiveData.value = MainContract.LoadDataSuccess(medicineCollection)
                },
                Consumer { throwable ->
                    medicineLiveData.value = MainContract.LoadDataError(throwable)
                })
    }

    fun getMedicineLiveData(): LiveData<MainContract> = medicineLiveData
}

sealed class MainContract {
    object Loading : MainContract()
    class LoadDataSuccess constructor(val value: MedicineCollection): MainContract()
    class LoadDataError constructor(val value: Throwable): MainContract()
}

package com.hulkdx.medicine.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hulkdx.medicine.di.ConfigPersistent
import javax.inject.Inject

import hulkdx.com.domain.interactor.MedicineUseCase
import hulkdx.com.domain.models.MedicineCollection
import io.reactivex.functions.Consumer
import timber.log.Timber

/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/11/2018.
 */
@ConfigPersistent
class MainViewModel @Inject constructor(
        private val mGetGithubRepositoryList: MedicineUseCase
): ViewModel() {

    private var medicineLiveData = MutableLiveData<MainContract>()

    //---------------------------------------------------------------
    // Extra functions
    //---------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
        mGetGithubRepositoryList.dispose()
    }

    fun getMedicineLiveData(): LiveData<MainContract> = medicineLiveData

    //---------------------------------------------------------------
    // Main functions
    //---------------------------------------------------------------

    /**
     * Load Medicines.
     *
     * @param searchText: if its null load all medicines and if its not null, search getMedicines
     *                    with it.
     */
    fun loadMedicines(searchText: String? = null) {
        medicineLiveData.value = MainContract.Loading
        mGetGithubRepositoryList.execute(
                searchText,
                Consumer { medicineCollection ->
                    medicineLiveData.value = MainContract.LoadDataSuccess(medicineCollection)
                },
                Consumer { throwable ->
                    medicineLiveData.value = MainContract.LoadDataError(throwable)
                })
    }

}

sealed class MainContract {
    object Loading : MainContract()
    class LoadDataSuccess constructor(val value: MedicineCollection): MainContract()
    class LoadDataError constructor(val value: Throwable): MainContract()
}

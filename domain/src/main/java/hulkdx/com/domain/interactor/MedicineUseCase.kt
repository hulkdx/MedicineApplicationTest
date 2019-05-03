package hulkdx.com.domain.interactor

import hulkdx.com.domain.executor.PostExecutionThread
import hulkdx.com.domain.executor.ThreadExecutor
import javax.inject.Inject
import javax.inject.Singleton

import hulkdx.com.domain.models.MedicineCollection
import hulkdx.com.domain.repository.MedicineRepository
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/11/2018.
 */
@Singleton
class MedicineUseCase @Inject constructor(

        private val mThreadExecutor: ThreadExecutor,
        private val mPostExecutionThread: PostExecutionThread,
        private val mRepository: MedicineRepository

) {

    private val mDisposables = CompositeDisposable()

    //---------------------------------------------------------------
    // Extra functions
    //---------------------------------------------------------------

    fun execute(searchText: String?,
                onNext: Consumer<in MedicineCollection>,
                onError: Consumer<in Throwable>) {

        val observable = this.create(searchText)
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.scheduler)

        val disposable: Disposable = observable.subscribe(onNext, onError)
        mDisposables.add(disposable)
    }

    fun dispose() {
        if (!mDisposables.isDisposed) {
            mDisposables.dispose()
        }
    }

    //---------------------------------------------------------------
    // Main Logic
    //---------------------------------------------------------------

    private fun create(searchText: String?): Single<MedicineCollection> {
        return if (searchText == null || searchText.isEmpty()) {
            mRepository.getMedicines()
        } else {
            mRepository.findMedicine(searchText)
        }
    }
}

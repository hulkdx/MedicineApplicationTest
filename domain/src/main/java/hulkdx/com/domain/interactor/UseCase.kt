package hulkdx.com.domain.interactor


import hulkdx.com.domain.executor.PostExecutionThread
import hulkdx.com.domain.executor.ThreadExecutor
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.annotations.Nullable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/11/2018.
 */
abstract class UseCase<T>(val mThreadExecutor: ThreadExecutor, val mPostExecutionThread: PostExecutionThread) {

    abstract class SingleUseCase<T>(mThreadExecutor: ThreadExecutor, mPostExecutionThread: PostExecutionThread) : UseCase<T>(mThreadExecutor, mPostExecutionThread) {

        private val mDisposables = CompositeDisposable()

        fun dispose() {
            if (!mDisposables.isDisposed) {
                mDisposables.dispose()
            }
        }

        internal abstract fun create(): io.reactivex.Single<T>

        fun execute(onNext: Consumer<in T>,
                    onError: Consumer<in Throwable>) {
            val flowable = this.create()
                    .subscribeOn(Schedulers.io())
                    .observeOn(mPostExecutionThread.scheduler)
            val disposable: Disposable = flowable.subscribe(onNext, onError)
            mDisposables.add(disposable)
        }
    }

    // Add Single and Completable:
}

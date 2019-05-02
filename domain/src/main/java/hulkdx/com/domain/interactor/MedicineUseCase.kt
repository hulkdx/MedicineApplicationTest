package hulkdx.com.domain.interactor

import hulkdx.com.domain.executor.PostExecutionThread
import hulkdx.com.domain.executor.ThreadExecutor
import javax.inject.Inject
import javax.inject.Singleton

import hulkdx.com.domain.models.Medicine
import hulkdx.com.domain.models.MedicineCollection
import hulkdx.com.domain.repository.MedicineRepository
import io.reactivex.Single

/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/11/2018.
 */
@Singleton
class MedicineUseCase @Inject constructor(
        mThreadExecutor: ThreadExecutor,
        mPostExecutionThread: PostExecutionThread,
        private val mRepository: MedicineRepository)
    : UseCase.SingleUseCase<MedicineCollection>(mThreadExecutor, mPostExecutionThread)
{
    override fun create(): Single<MedicineCollection> {
        return mRepository.medicine
    }
}

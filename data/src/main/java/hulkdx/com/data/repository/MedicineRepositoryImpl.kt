package hulkdx.com.data.repository

import hulkdx.com.data.mapper.MedicineListRemoteMapper
import javax.inject.Inject
import javax.inject.Singleton

import hulkdx.com.data.remote.RemoteService
import hulkdx.com.domain.models.MedicineCollection
import hulkdx.com.domain.repository.MedicineRepository
import io.reactivex.Single

/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/11/2018.
 */

@Singleton
class MedicineRepositoryImpl @Inject constructor(
        private val mRemoteService: RemoteService,
        private val mMedicineListRemoteMapper: MedicineListRemoteMapper
) : MedicineRepository {

    override fun getMedicine(): Single<MedicineCollection> {
        return mRemoteService
                .medicineList("medlist")
                .map(mMedicineListRemoteMapper)
    }

}

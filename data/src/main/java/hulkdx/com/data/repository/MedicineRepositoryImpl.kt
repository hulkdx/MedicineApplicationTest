package hulkdx.com.data.repository

import hulkdx.com.data.local.LocalMemoryCache
import hulkdx.com.data.mapper.MedicineListRemoteMapper
import javax.inject.Inject
import javax.inject.Singleton

import hulkdx.com.data.remote.RemoteService
import hulkdx.com.domain.models.Medicine
import hulkdx.com.domain.models.MedicineCollection
import hulkdx.com.domain.models.MedicineGroup
import hulkdx.com.domain.repository.MedicineRepository
import io.reactivex.Single

/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/11/2018.
 */

@Singleton
class MedicineRepositoryImpl @Inject constructor(
        private val mRemoteService: RemoteService,
        private val mLocalMemoryCache: LocalMemoryCache,
        private val mMedicineListRemoteMapper: MedicineListRemoteMapper
) : MedicineRepository {

    override fun getMedicines(): Single<MedicineCollection> {
        println("getMedicines")
        val medicineCollection = mLocalMemoryCache.medicineCollection
        if (medicineCollection != null) {
            return Single.just(medicineCollection)
        }

        return mRemoteService
                .medicineList("medlist")
                .map(mMedicineListRemoteMapper)
                .doAfterSuccess {
                    mLocalMemoryCache.medicineCollection = it
                }
    }

    override fun findMedicine(searchText: String): Single<MedicineCollection> {
        return getMedicines().map {
            val filteredMedicineList = mutableListOf<Medicine>()

            it.medicineList.forEach { medicine ->
                medicine.name?.contains(searchText, true)?.let { contains ->
                    if (contains) {
                        filteredMedicineList.add(medicine)
                    }
                }
            }

            return@map MedicineCollection(filteredMedicineList, it.medicineGroupList)
        }
    }

}

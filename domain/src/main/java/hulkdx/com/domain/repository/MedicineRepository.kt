package hulkdx.com.domain.repository

import hulkdx.com.domain.models.MedicineCollection
import io.reactivex.Single

/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/11/2018.
 */
interface MedicineRepository {
    fun getMedicines():                   Single<MedicineCollection>
    fun findMedicine(searchText: String): Single<MedicineCollection>
}

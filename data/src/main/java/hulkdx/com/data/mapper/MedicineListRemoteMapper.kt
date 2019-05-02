package hulkdx.com.data.mapper

import hulkdx.com.data.model.MedicineListRemoteEntity
import hulkdx.com.domain.models.Medicine
import hulkdx.com.domain.models.MedicineCollection
import hulkdx.com.domain.models.MedicineGroup
import io.reactivex.functions.Function
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/11/2018.
 */
@Singleton
class MedicineListRemoteMapper @Inject constructor()
    : Function<MedicineListRemoteEntity, MedicineCollection> {
    override fun apply(medicineListRemoteEntity: MedicineListRemoteEntity): MedicineCollection {

        val medicineGroupList = mutableMapOf<String, MedicineGroup>()

        medicineListRemoteEntity.medicineGroup?.get(0)?.forEach {
            val id = it.key
            val name = it.value
            val medicineGroup = MedicineGroup(name)
            medicineGroupList[id] = medicineGroup
        }

        val medicineList = mutableListOf<Medicine>()
        medicineListRemoteEntity.medicine?.forEach {
            val name: String?                 = it.medicineName
            val totalPillsInSheet: Int?       = it.totalPillsInSheet
            val atc: String?                  = it.atc
            val country: String?              = it.medicineCountry
            val safeMargin: Int?              = it.safeMargin
            val pillType: String?             = it.pillType
            val hormonalPills: Int?           = it.hormonalPills
            val placeboPills: Int?            = it.placeboPills

            var medicineGroup: MedicineGroup? = null
            it.medicineGroup?.let { medicineGroupId ->
                medicineGroup = medicineGroupList[medicineGroupId.toString()]
            }

            val medicine = Medicine(
                    name,
                    totalPillsInSheet,
                    medicineGroup,
                    atc,
                    country,
                    safeMargin,
                    pillType,
                    hormonalPills,
                    placeboPills
            )
            medicineList.add(medicine)
        }

        return MedicineCollection(medicineList, medicineGroupList)
    }
}

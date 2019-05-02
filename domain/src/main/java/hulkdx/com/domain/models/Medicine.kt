package hulkdx.com.domain.models

/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/11/2018.
 */
data class Medicine
(
        val name: String?,
        val totalPillsInSheet: Int?,
        val medicineGroup: MedicineGroup?,
        val atc: String?,
        val country: String?,
        val safeMargin: Int?,
        val pillType: String?,
        val hormonalPills: Int?,
        val placeboPills: Int?
)

data class MedicineGroup
(
        val name: String
)

data class MedicineCollection
(
        val medicineList: List<Medicine>,
        val medicineGroupList: Map<String, MedicineGroup>
)
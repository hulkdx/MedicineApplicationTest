package hulkdx.com.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/11/2018.
 */
class MedicineListRemoteEntity {

    @SerializedName("version")        @Expose var version: Int = 0
    @SerializedName("medicine_group") @Expose var medicineGroup: List<Map<String, String>>? = null
    @SerializedName("medicine")       @Expose var medicine: List<Medicine>? = null

    class Medicine {
        @SerializedName("medicine_name")
        @Expose
        var medicineName: String? = null
        @SerializedName("total_pills_in_sheet")
        @Expose
        var totalPillsInSheet: Int? = null
        @SerializedName("medicine_group")
        @Expose
        var medicineGroup: Int? = null
        @SerializedName("atc")
        @Expose
        var atc: String? = null
        @SerializedName("medicine_country")
        @Expose
        var medicineCountry: String? = null
        @SerializedName("safe_margin")
        @Expose
        var safeMargin: Int? = null
        @SerializedName("pill_type")
        @Expose
        var pillType: String? = null
        @SerializedName("hormonal_pills")
        @Expose
        var hormonalPills: Int? = null
        @SerializedName("placebo_pills")
        @Expose
        var placeboPills: Int? = null
    }
}

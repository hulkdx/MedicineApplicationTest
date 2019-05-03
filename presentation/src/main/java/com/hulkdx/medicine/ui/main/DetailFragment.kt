package com.hulkdx.medicine.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hulkdx.medicine.R
import com.hulkdx.medicine.ui.base.BaseFragment
import hulkdx.com.domain.models.Medicine
import kotlinx.android.synthetic.main.fragment_detail.*
import timber.log.Timber


/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/11/2018.
 *
 */
class DetailFragment : BaseFragment() {

    //---------------------------------------------------------------
    // Statics
    //---------------------------------------------------------------

    companion object {
        private const val ARG_MEDICINE_NAME = "ARG_MEDICINE_NAME"
        private const val ARG_MEDICINE_TOTALPILLSINSHEET = "ARG_MEDICINE_TOTALPILLSINSHEET"
        private const val ARG_MEDICINE_MEDICINEGROUP_NAME = "ARG_MEDICINE_MEDICINEGROUP_NAME"
        private const val ARG_MEDICINE_ATC = "ARG_MEDICINE_ATC"
        private const val ARG_MEDICINE_COUNTRY = "ARG_MEDICINE_COUNTRY"
        private const val ARG_MEDICINE_SAFEMARGIN = "ARG_MEDICINE_SAFEMARGIN"
        private const val ARG_MEDICINE_PILLTYPE = "ARG_MEDICINE_PILLTYPE"
        private const val ARG_MEDICINE_HORMONALPILLS = "ARG_MEDICINE_HORMONALPILLS"
        private const val ARG_MEDICINE_PLACEBOPILLS = "ARG_MEDICINE_PLACEBOPILLS"

        fun newInstance(medicine: Medicine): DetailFragment {
            val fragment = DetailFragment()

            val args = Bundle()
            args.putString(ARG_MEDICINE_NAME, medicine.name)
            args.putInt(ARG_MEDICINE_TOTALPILLSINSHEET, medicine.totalPillsInSheet ?: 0)
            args.putString(ARG_MEDICINE_ATC, medicine.atc)
            args.putString(ARG_MEDICINE_COUNTRY, medicine.country)
            args.putInt(ARG_MEDICINE_SAFEMARGIN, medicine.safeMargin ?: 0)
            args.putString(ARG_MEDICINE_PILLTYPE, medicine.pillType)
            args.putInt(ARG_MEDICINE_HORMONALPILLS, medicine.hormonalPills ?: 0)
            args.putInt(ARG_MEDICINE_PLACEBOPILLS, medicine.placeboPills ?: 0)
            medicine.medicineGroup?.let {
                args.putString(ARG_MEDICINE_MEDICINEGROUP_NAME, it.name)
            }

            fragment.arguments = args

            return fragment
        }
    }

    //---------------------------------------------------------------
    // Fields
    //---------------------------------------------------------------

    private lateinit var mMainViewModel: MainViewModel

    private val mainActivity: MainActivity
        get() = this.activity as MainActivity

    //---------------------------------------------------------------
    // Lifecycle
    //---------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.apply {
            getString(ARG_MEDICINE_NAME, null)?.apply {
                updateMedicineName(this)
            }
            getString(ARG_MEDICINE_ATC, null)?.apply {
                updateMedicineAtc(this)
            }
            getString(ARG_MEDICINE_COUNTRY, null)?.apply {
                updateMedicineCountry(this)
            }
            getString(ARG_MEDICINE_PILLTYPE, null)?.apply {
                updateMedicinePillType(this)
            }
            getString(ARG_MEDICINE_MEDICINEGROUP_NAME, null)?.apply {
                updateMedicineGroup(this)
            }
            updateMedicineTotalPillsInSheet(getInt(ARG_MEDICINE_TOTALPILLSINSHEET))
            updateMedicineSafeMargin(getInt(ARG_MEDICINE_SAFEMARGIN))
            updateMedicineHormonalPills(getInt(ARG_MEDICINE_HORMONALPILLS))
            updateMedicinePlaceboPills(getInt(ARG_MEDICINE_PLACEBOPILLS))
        }
    }

    //---------------------------------------------------------------
    // Views Update model:
    //---------------------------------------------------------------

    private fun updateMedicineName(name: String) {
        mNameTextView.text = name
    }

    private fun updateMedicinePillType(pillType: String) {
        mPillTypeTextView.text = pillType
    }

    private fun updateMedicineCountry(country: String) {
        mMedicineGroupTextView.text = country
    }

    private fun updateMedicineAtc(atc: String) {
        mAtcTextView.text = atc
    }

    private fun updateMedicineTotalPillsInSheet(totalPillsInSheet: Int) {
        mTotalPillsInSheetTextView.text = totalPillsInSheet.toString()
    }

    private fun updateMedicineSafeMargin(second: Int) {
        val hours = second / 3600
        val minutes = (second % (hours * 3600)) / 60
        mSafeMarginTextView.text = getString(R.string.safeMarginText, hours, minutes)
    }

    private fun updateMedicineHormonalPills(hormonalPills: Int) {
        mHormonalPillsTextView.text = hormonalPills.toString()
    }

    private fun updateMedicinePlaceboPills(placeboPills: Int) {
        mPlaceboPillsTextView.text = placeboPills.toString()
    }

    private fun updateMedicineGroup(group: String) {
        mMedicineGroupTextView.text = group
    }
}

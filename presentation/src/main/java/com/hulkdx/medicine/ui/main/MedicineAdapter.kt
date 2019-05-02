package com.hulkdx.medicine.ui.main

import android.animation.Animator
import android.graphics.Color
import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat

import javax.inject.Inject
import androidx.recyclerview.widget.RecyclerView
import com.hulkdx.medicine.R
import com.hulkdx.medicine.utils.addRippleEffect
import com.hulkdx.medicine.utils.changeColor
import hulkdx.com.domain.models.Medicine
import hulkdx.com.domain.models.MedicineCollection

/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/11/2018.
 */
class MedicineAdapter(private val clickListener: (Medicine) -> Unit): RecyclerView.Adapter<MedicineAdapter.ViewHolder>() {

    private var mModels: MedicineCollection? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_medicine, parent, false)
        itemView.addRippleEffect()
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mModels?.medicineList?.get(position)?.apply {
            holder.mTitle.text = name
            holder.mGroupName.text = medicineGroup?.name
            holder.mAtcCode.text = atc

            holder.mTitle.changeColor(
                    if (atc.equals("G03"))
                        R.color.red
                    else
                        R.color.black
            )
            holder.mRootView.setOnClickListener {
                clickListener(this)
            }
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        holder.mRootView.setOnClickListener(null)
        super.onViewRecycled(holder)
    }

    override fun getItemCount(): Int = mModels?.medicineList?.size ?: 0

    fun setData(githubRepositoryModels: MedicineCollection) {
        mModels = githubRepositoryModels
        notifyDataSetChanged()
    }

    //---------------------------------------------------------------
    // ViewHolder
    //---------------------------------------------------------------

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mRootView   = itemView
        val mTitle      = itemView.findViewById<TextView>(R.id.title)!!
        val mGroupName  = itemView.findViewById<TextView>(R.id.group_name)!!
        val mAtcCode    = itemView.findViewById<TextView>(R.id.atc_code)!!
    }

}


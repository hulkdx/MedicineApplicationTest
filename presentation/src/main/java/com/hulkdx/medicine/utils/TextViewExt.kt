package com.hulkdx.medicine.utils

import android.widget.TextView
import androidx.core.content.ContextCompat


/**
 * Created by Mohammad Jafarzadeh Rezvan on 06/02/2019.
 */
fun TextView.changeColor(colorResource: Int) {
    setTextColor(ContextCompat.getColor(context, colorResource))
}
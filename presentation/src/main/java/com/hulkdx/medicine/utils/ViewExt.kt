package com.hulkdx.medicine.utils

import android.os.Build
import android.view.View


/**
 * Created by Mohammad Jafarzadeh Rezvan on 06/02/2019.
 */
fun View.addRippleEffect() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val attrs = intArrayOf(android.R.attr.selectableItemBackground /* index 0 */)

        // Obtain the styled attributes. 'themedContext' is a context with a
        // theme, typically the current Activity (i.e. 'this')
        val ta = context.obtainStyledAttributes(attrs)

        // Now get the value of the 'listItemBackground' attribute that was
        // set in the theme used in 'themedContext'. The parameter is the index
        // of the attribute in the 'attrs' array. The returned Drawable
        // is what you are after
        val drawableFromTheme = ta.getDrawable(0 /* index */)

        // Finally free resources used by TypedArray
        ta.recycle()

        foreground = drawableFromTheme
    }
}
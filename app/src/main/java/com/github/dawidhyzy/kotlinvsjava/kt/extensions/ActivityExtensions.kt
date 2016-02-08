package com.github.dawidhyzy.kotlinvsjava.kt.extensions

import android.app.Activity
import android.view.View
import org.jetbrains.anko.find

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 08/02/16.
 */

fun Activity.contentView() : View = this.find<View>(android.R.id.content)
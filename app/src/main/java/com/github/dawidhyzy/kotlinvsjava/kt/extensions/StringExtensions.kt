package com.github.dawidhyzy.kotlinvsjava.kt.extensions

import java.util.*

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 06/02/16.
 */

fun String.format(vararg args: Any): String{
    return String.format(Locale.getDefault(), this, args);
}
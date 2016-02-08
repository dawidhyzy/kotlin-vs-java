package com.github.dawidhyzy.kotlinvsjava.kt.extensions

import timber.log.Timber

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 08/02/16.
 */

fun Any.v(msg: () -> String) {
    v(msg())
}

fun Any.d(msg: () -> String) {
    d(msg())
}

fun Any.i(msg: () -> String) {
    i(msg())
}

fun Any.e(msg: () -> String) {
    e(msg())
}
fun Any.wtf(msg: () -> String) {
    w(msg())
}

fun Any.v(msg: String, vararg args: Any) {
    Timber.v(msg, args)
}

fun Any.d(msg: String, vararg args: Any) {
    Timber.d(msg, args)
}

fun Any.i(msg: String, vararg args: Any) {
    Timber.i(msg, args)
}

fun Any.w(msg: String, vararg args: Any) {
    Timber.w(msg, args)
}

fun Any.e(msg: String, vararg args: Any) {
    Timber.e(msg, args)
}

fun Any.wtf(msg: String, vararg args: Any) {
    Timber.wtf(msg, args)
}
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

fun Any.w(msg: () -> String) {
    w(msg())
}

fun Any.e(msg: () -> String) {
    e(msg())
}
fun Any.wtf(msg: () -> String) {
    wtf(msg())
}

fun Any.v(msg: String) {
    Timber.tag(tag).v(msg)
}

fun Any.d(msg: String) {
    Timber.tag(tag).d(msg)
}

fun Any.i(msg: String) {
    Timber.tag(tag).i(msg)
}

fun Any.w(msg: String) {
    Timber.tag(tag).w(msg)
}

fun Any.e(msg: String) {
    Timber.tag(tag).e(msg)
}

fun Any.wtf(msg: String) {
    Timber.tag(tag).wtf(msg)
}

fun Any.v(msg: String, vararg args: Any) {
    Timber.tag(tag).v(msg, args)
}

fun Any.d(msg: String, vararg args: Any) {
    Timber.tag(tag).d(msg, args)
}

fun Any.i(msg: String, vararg args: Any) {
    Timber.tag(tag).i(msg, args)
}

fun Any.w(msg: String, vararg args: Any) {
    Timber.tag(tag).w(msg, args)
}

fun Any.e(msg: String, vararg args: Any) {
    Timber.tag(tag).e(msg, args)
}

fun Any.wtf(msg: String, vararg args: Any) {
    Timber.tag(tag).wtf(msg, args)
}

private val Any.tag: String
    get() = javaClass.simpleName
package com.github.dawidhyzy.kotlinvsjava.kt.extensions

import java.lang.ref.WeakReference

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 07/02/16.
 */

inline fun <T> WeakReference<T>.safe(body : T.() -> Unit) {
    this.get()?.body()
}
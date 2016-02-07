package com.github.dawidhyzy.kotlinvsjava.kt.util

import rx.Observer

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 06/02/16.
 */
open class SimpleObserver<T> : Observer<T> {
    override fun onCompleted() {

    }

    override fun onError(e: Throwable) {

    }

    override fun onNext(t: T) {

    }
}
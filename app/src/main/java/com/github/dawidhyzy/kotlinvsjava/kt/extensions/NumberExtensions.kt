package com.github.dawidhyzy.kotlinvsjava.kt.extensions

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 07/02/16.
 */

val KELVIN_ZERO = 273.15f;

fun Double.toCelcius(): Double{
    return this - KELVIN_ZERO
}
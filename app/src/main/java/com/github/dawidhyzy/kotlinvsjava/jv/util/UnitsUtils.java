package com.github.dawidhyzy.kotlinvsjava.jv.util;

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 03/02/16.
 */
public class UnitsUtils {

    private static final double KELVIN_ZERO = 273.15f;

    public static double kelvinToCelsius(double kelvin) {
        return kelvin - KELVIN_ZERO;
    }
}

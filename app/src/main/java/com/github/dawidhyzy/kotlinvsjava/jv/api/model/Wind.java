package com.github.dawidhyzy.kotlinvsjava.jv.api.model;

import com.google.gson.annotations.SerializedName;

public class Wind {

    private double speed;
    @SerializedName("deg")
    private float direction;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public float getDirection() {
        return direction;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }

}

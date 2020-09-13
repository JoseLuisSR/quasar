package com.rebell.alliance.intelligence.entities;

public class Position {

    private double x;

    private  double y;

    public Position(double[] positions){
        this.x = positions[0];
        this.y = positions[1];
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }
}

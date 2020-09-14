package com.rebell.alliance.intelligence.entities;

import java.util.List;

public class Satellite extends Spaceship {

    private double distance;

    private String name;

    private List<String> message;

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public double getDistance() {
        return distance;
    }

    public String getName() {
        return name;
    }

    public List<String> getMessage() {
        return message;
    }
}

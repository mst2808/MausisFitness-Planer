package com.example.marcus.mausisfitness_planer;

/**
 * Created by Marcus on 19.01.2016.
 */
public class Gewicht {

    private double gewicht;
    private long dateMillis;

    public Gewicht(double gewicht, long dateMillis) {
        this.gewicht = gewicht;
        this.dateMillis = dateMillis;
    }

    public double getGewicht() {
        return gewicht;
    }

    public long getDateMillis() {
        return dateMillis;
    }
}

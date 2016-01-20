package com.example.marcus.mausisfitness_planer;

/**
 * Created by Marcus on 20.01.2016.
 */
public class Sport {

    private double dauer;
    private String type;
    private long milis;

    public Sport(double dauer, long milis, String type) {
        this.dauer = dauer;
        this.milis = milis;
        this.type = type;
    }

    public double getDauer() {
        return dauer;
    }

    public long getDateMillis() {
        return milis;
    }

    public String getType() {
        return type;
    }
}

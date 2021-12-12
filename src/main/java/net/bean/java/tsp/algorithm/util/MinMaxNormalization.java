package net.bean.java.tsp.algorithm.util;

public class MinMaxNormalization {

    private final double min, max;

    private final long newMin, newMax;

    public MinMaxNormalization(double max, long newMax) {
        this(0.0, 0, max, newMax);
    }

    public MinMaxNormalization(double min, long newMin, double max, long newMax) {
        this.min = min;
        this.newMin = newMin;
        this.max = max;
        this.newMax = newMax;
    }

    public double normalize(double value) {
        return (((value - min) / (max - min)) * (newMax - newMin)) + newMin;
    }

}
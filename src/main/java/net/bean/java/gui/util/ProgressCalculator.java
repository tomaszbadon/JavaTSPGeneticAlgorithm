package net.bean.java.gui.util;

import net.bean.java.tsp.algorithm.genetic.generation.Generation;

public class ProgressCalculator {

    private ProgressCalculator() { }

    public static <T>double calculate(Generation<T> generation, int numberOfGenerations) {
        return generation.getNumberOfGeneration() / (double) numberOfGenerations;
    }

}
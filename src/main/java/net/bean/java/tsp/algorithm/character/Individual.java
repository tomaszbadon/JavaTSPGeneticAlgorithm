package net.bean.java.tsp.algorithm.character;

import java.util.List;

public interface Individual<T> extends Comparable<Individual<T>> {

    List<T> getChromosomes();

    double getValueOfFitnessFunction();

}
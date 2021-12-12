package net.bean.java.tsp.algorithm.selection;

import net.bean.java.tsp.algorithm.population.Population;

public interface Selection <T> {

    Population<T> select(int numberOfSelectedIndividuals, Population<T> population);

}
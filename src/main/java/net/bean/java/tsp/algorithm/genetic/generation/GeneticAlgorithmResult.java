package net.bean.java.tsp.algorithm.genetic.generation;

import net.bean.java.tsp.algorithm.population.Population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneticAlgorithmResult<T> {

    private final List<Generation<T>> listOfGenerations;

    private GeneticAlgorithmResult(List<Generation<T>> listOfGenerations) {
        this.listOfGenerations = Collections.unmodifiableList(listOfGenerations);
    }

    public Generation<T> getInitialPopulationInfo() { return listOfGenerations.get(0); }

    public Generation<T> getLastPopulationInfo() {
        return listOfGenerations.get(listOfGenerations.size() - 1);
    }

    public static class GeneticAlgorithmResultBuilder<T> {

        private int counter = 0;
        private final List<Generation<T>> listOfGenerations = new ArrayList<>();

        public static GeneticAlgorithmResultBuilder create() {
            return new GeneticAlgorithmResultBuilder();
        }

        public GeneticAlgorithmResultBuilder<T> addInformationAboutPopulation(Population<T>  population) {
            listOfGenerations.add(new Generation<>(counter++, population));
            return this;
        }

        public Generation<T> getLastInserted() {
            return listOfGenerations.get(listOfGenerations.size() - 1);
        }

        public GeneticAlgorithmResult<T> build() {
            return new GeneticAlgorithmResult<>(listOfGenerations);
        }

    }

}
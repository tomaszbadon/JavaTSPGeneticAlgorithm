package net.bean.java.tsp.algorithm.genetic.generation;

import net.bean.java.tsp.algorithm.character.Individual;
import net.bean.java.tsp.algorithm.population.Population;

public class Generation<T> {

    private final int numberOfGeneration;

    private final Individual<T> theBestIndividual;

    private final double averageValueOfFitnessFunction;

    public Generation(int numberOfGeneration, Population<T> population) {
        this.numberOfGeneration = numberOfGeneration;
        this.theBestIndividual = population.findTheBestIndividual();
        this.averageValueOfFitnessFunction = population.getAverageValueOfFitnessFunction();
    }

    public int getNumberOfGeneration() {
        return numberOfGeneration;
    }

    public Individual<T> getTheBestIndividual() {
        return theBestIndividual;
    }

    public double getAverageValueOfFitnessFunction() {
        return averageValueOfFitnessFunction;
    }

    @Override
    public String toString() {
        return "Generation{" +
                "numberOfGeneration=" + numberOfGeneration +
                ", theBestIndividual=" + theBestIndividual +
                ", averageValueOfFitnessFunction=" + averageValueOfFitnessFunction +
                '}';
    }
}
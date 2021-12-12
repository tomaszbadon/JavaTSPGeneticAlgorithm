package net.bean.java.tsp.algorithm.population;

import net.bean.java.tsp.algorithm.character.ChromosomeStrategyCreation;
import net.bean.java.tsp.algorithm.character.Individual;
import net.bean.java.tsp.algorithm.character.IndividualBuilder;

import java.util.ArrayList;
import java.util.List;

public class PopulationBuilder<T> {

    private PopulationBuilder() { }

    public static <T>PopulationBuilder<T> builder() {
        return new PopulationBuilder<>();
    }

    private int population;

    private ChromosomeStrategyCreation chromosomeStrategyCreation;

    private IndividualBuilder<T> individualBuilder;

    public PopulationBuilder<T> setPopulation(int population) {
        this.population = population;
        return this;
    }

    public PopulationBuilder<T> setChromosomeStrategyCreation(ChromosomeStrategyCreation chromosomeStrategyCreation) {
        this.chromosomeStrategyCreation = chromosomeStrategyCreation;
        return this;
    }

    public PopulationBuilder<T> setIndividualBuilder(IndividualBuilder<T> individualBuilder) {
        this.individualBuilder = individualBuilder;
        return this;
    }

    public Population<T> build() {
        List<Individual<T>> individuals = new ArrayList<>();
        for(int i=0 ; i<population ; i++) {
            individuals.add(individualBuilder.createNewIndividual(chromosomeStrategyCreation.getChromosome()));
        }
        return new Population(individuals);
    }

}
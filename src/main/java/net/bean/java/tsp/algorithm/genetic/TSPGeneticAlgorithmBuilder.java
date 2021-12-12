package net.bean.java.tsp.algorithm.genetic;

import net.bean.java.gui.callback.AfterEachIterationCallback;
import net.bean.java.tsp.algorithm.crossover.Crossover;
import net.bean.java.tsp.algorithm.crossover.CrossoverExecutor;
import net.bean.java.tsp.algorithm.genetic.generation.Generation;
import net.bean.java.tsp.algorithm.mutation.Mutation;
import net.bean.java.tsp.algorithm.population.Population;
import net.bean.java.tsp.algorithm.selection.Selection;

public class TSPGeneticAlgorithmBuilder<T> {

    private Population<T> initialPopulation;
    private Mutation<T> mutation;
    private Crossover<T> crossover;
    private Selection<T> selection;
    private int numberOfGenerations;
    private double probabilityOfMutation;
    private AfterEachIterationCallback<Generation<T>> executedAfterEachGenerationConsumer = (g -> { });
    private CrossoverExecutor<T> crossoverExecutor = new CrossoverExecutor() { };

    private TSPGeneticAlgorithmBuilder() { }

    public TSPGeneticAlgorithmBuilder<T> setInitialPopulation(Population<T> initialPopulation) {
        this.initialPopulation = initialPopulation;
        return this;
    }

    public TSPGeneticAlgorithmBuilder<T> setMutation(Mutation<T> mutation) {
        this.mutation = mutation;
        return this;
    }

    public TSPGeneticAlgorithmBuilder<T> setCrossover(Crossover<T> crossover) {
        this.crossover = crossover;
        return this;
    }

    public TSPGeneticAlgorithmBuilder<T> setSelection(Selection<T> selection) {
        this.selection = selection;
        return this;
    }

    public TSPGeneticAlgorithmBuilder<T> setNumberOfGenerations(int numberOfGenerations) {
        this.numberOfGenerations = numberOfGenerations;
        return this;
    }

    public TSPGeneticAlgorithmBuilder<T> setProbabilityOfMutation(double probabilityOfMutation) {
        this.probabilityOfMutation = probabilityOfMutation;
        return this;
    }

    public TSPGeneticAlgorithmBuilder<T> setExecuteAfterEachGenerationCallback(AfterEachIterationCallback<Generation<T>> executedAfterEachGenerationConsumer) {
        this.executedAfterEachGenerationConsumer = executedAfterEachGenerationConsumer;
        return this;
    }

    public TSPGeneticAlgorithmBuilder<T> setCrossoverExecutor(CrossoverExecutor<T> crossoverExecutor) {
        this.crossoverExecutor = crossoverExecutor;
        return this;
    }

    public TSPGeneticAlgorithm<T> build() {
        return new TSPGeneticAlgorithm<>(executedAfterEachGenerationConsumer, crossoverExecutor, initialPopulation, mutation, crossover, selection, numberOfGenerations, probabilityOfMutation);
    }

    public static <T> TSPGeneticAlgorithmBuilder<T> getBuilder() {
        return new TSPGeneticAlgorithmBuilder<>();
    }

}

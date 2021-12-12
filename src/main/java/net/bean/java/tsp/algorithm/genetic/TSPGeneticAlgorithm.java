package net.bean.java.tsp.algorithm.genetic;

import net.bean.java.gui.callback.AfterEachIterationCallback;
import net.bean.java.tsp.algorithm.crossover.Crossover;
import net.bean.java.tsp.algorithm.crossover.CrossoverExecutor;
import net.bean.java.tsp.algorithm.genetic.generation.Generation;
import net.bean.java.tsp.algorithm.genetic.generation.GeneticAlgorithmResult;
import net.bean.java.tsp.algorithm.mutation.Mutation;
import net.bean.java.tsp.algorithm.population.Population;
import net.bean.java.tsp.algorithm.selection.Selection;

public class TSPGeneticAlgorithm<T> {

    private final Population<T> initialPopulation;
    private final Mutation<T> mutation;
    private final Selection<T> selection;
    private final int numberOfGenerations;
    private final double probabilityOfMutation;
    private final AfterEachIterationCallback<Generation<T>> executedAfterEachGenerationConsumer;
    private final CrossoverExecutor<T> crossoverExecutor;
    private final Crossover<T> crossover;
    private GeneticAlgorithmResult<T> geneticAlgorithmResult;

    TSPGeneticAlgorithm(AfterEachIterationCallback<Generation<T>> executedAfterEachGenerationConsumer, CrossoverExecutor<T> crossoverExecutor, Population<T> initialPopulation, Mutation<T> mutation, Crossover<T> crossover, Selection<T> selection, int numberOfGenerations, double probabilityOfMutation) {
        this.initialPopulation = initialPopulation;
        this.mutation = mutation;
        this.selection = selection;
        this.numberOfGenerations = numberOfGenerations;
        this.probabilityOfMutation = probabilityOfMutation;
        this.executedAfterEachGenerationConsumer = executedAfterEachGenerationConsumer;
        this.crossover = crossover;
        this.crossoverExecutor = crossoverExecutor;
    }

    public GeneticAlgorithmResult<T> run() {
        GeneticAlgorithmResult.GeneticAlgorithmResultBuilder<T> builder = GeneticAlgorithmResult.GeneticAlgorithmResultBuilder.create();
        builder.addInformationAboutPopulation(initialPopulation);
        Population<T> population = new Population<>(initialPopulation);
        for (int i = 0; i < numberOfGenerations; i++) {
            Population<T> selectedPopulation = selection.select(population.getPopulation().size(), population);
            population = performCrossover(selectedPopulation);
            performMutation(probabilityOfMutation, mutation, population);
            builder.addInformationAboutPopulation(population);
            executedAfterEachGenerationConsumer.afterEachIteration(builder.getLastInserted());
        }
        geneticAlgorithmResult = builder.build();
        return geneticAlgorithmResult;
    }

    private void performMutation(double probabilityOfMutation, Mutation<T> mutation, Population<T> population) {
        population.getPopulation().stream().forEach(individual -> mutation.performMutation(probabilityOfMutation, individual));
    }

    private Population<T> performCrossover(Population<T> population) {
        return crossoverExecutor.performCrossover(crossover, population);
    }

    public GeneticAlgorithmResult<T> getGeneticAlgorithmResult() {
        return geneticAlgorithmResult;
    }
}
package net.bean.java.gui.executor;

import net.bean.java.gui.callback.AfterEachIterationCallback;
import net.bean.java.gui.callback.OnStartCallback;
import net.bean.java.tsp.algorithm.character.ChromosomeStrategyCreation;
import net.bean.java.tsp.algorithm.character.IndividualBuilder;
import net.bean.java.tsp.algorithm.character.route.RandomChromosomeStrategyCreationForCity;
import net.bean.java.tsp.algorithm.character.route.Route;
import net.bean.java.tsp.algorithm.city.City;
import net.bean.java.tsp.algorithm.crossover.Crossover;
import net.bean.java.tsp.algorithm.crossover.DualThreadCrossoverExecutor;
import net.bean.java.tsp.algorithm.genetic.TSPGeneticAlgorithm;
import net.bean.java.tsp.algorithm.genetic.TSPGeneticAlgorithmBuilder;
import net.bean.java.tsp.algorithm.genetic.generation.Generation;
import net.bean.java.tsp.algorithm.genetic.generation.GeneticAlgorithmResult;
import net.bean.java.tsp.algorithm.mutation.Mutation;
import net.bean.java.tsp.algorithm.mutation.SimpleMutation;
import net.bean.java.tsp.algorithm.population.Population;
import net.bean.java.tsp.algorithm.population.PopulationBuilder;
import net.bean.java.tsp.algorithm.selection.Selection;
import net.bean.java.gui.callback.OnCompleteCallback;

import java.util.Set;

public class GeneticAlgorithmExecutor {

    private final int numberOfIndividuals;
    private final int numberOfGenerations;
    private final double probability;
    private final Selection<City> selection;
    private final Set<City> cities;
    private AfterEachIterationCallback<Generation<City>> afterEachIterationCallback = (ge) -> { };
    private OnCompleteCallback<GeneticAlgorithmResult<City>> onFinishOnCompleteCallback = (gi) -> { };
    private OnStartCallback onStartCallback = () -> { };

    public GeneticAlgorithmExecutor(int numberOfIndividuals, int numberOfGenerations, double probability, Selection<City> selection, Set<City> cities) {
        this.numberOfIndividuals = numberOfIndividuals;
        this.numberOfGenerations = numberOfGenerations;
        this.probability = probability;
        this.selection = selection;
        this.cities = cities;
    }

    public void execute() {
        IndividualBuilder<City> individualBuilder = new Route.RouteBuilder();
        Population<City> population = createPopulation(individualBuilder, numberOfIndividuals);
        Mutation<City> mutation = new SimpleMutation<>();
        Crossover<City> crossover = new Crossover<>(individualBuilder);
        TSPGeneticAlgorithm<City> geneticAlgorithm = TSPGeneticAlgorithmBuilder.<City>getBuilder()
                .setSelection(selection)
                .setCrossover(crossover)
                .setInitialPopulation(population)
                .setMutation(mutation)
                .setNumberOfGenerations(numberOfGenerations)
                .setProbabilityOfMutation(probability)
                .setCrossoverExecutor(new DualThreadCrossoverExecutor<>())
                .setExecuteAfterEachGenerationCallback(afterEachIterationCallback)
               .build();
        onStartCallback.onStart();
        Thread thread = new Thread(() -> {
           GeneticAlgorithmResult<City> geneticAlgorithmResult = geneticAlgorithm.run();
           onFinishOnCompleteCallback.onComplete(geneticAlgorithmResult);
        });
        thread.start();
    }

    private Population<City> createPopulation(IndividualBuilder<City> individualBuilder, int numberOfIndividuals) {
        ChromosomeStrategyCreation<City> chromosomeStrategyCreation = new RandomChromosomeStrategyCreationForCity(cities);
        return PopulationBuilder.<City>builder()
                .setIndividualBuilder(individualBuilder)
                .setPopulation(numberOfIndividuals)
                .setChromosomeStrategyCreation(chromosomeStrategyCreation)
                .build();
    }

    public void setAfterEachIterationCallback(AfterEachIterationCallback<Generation<City>> afterEachIterationCallback) {
        this.afterEachIterationCallback = afterEachIterationCallback;
    }

    public void setOnFinishOnCompleteCallback(OnCompleteCallback<GeneticAlgorithmResult<City>> onFinishOnCompleteCallback) {
        this.onFinishOnCompleteCallback = onFinishOnCompleteCallback;
    }

    public void setOnStartCallback(OnStartCallback onStartCallback) {
        this.onStartCallback = onStartCallback;
    }
}

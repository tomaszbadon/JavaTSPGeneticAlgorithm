package net.bean.java.tsp.algorithm.crossover;

import com.google.common.base.Stopwatch;
import net.bean.java.tsp.algorithm.character.Individual;
import net.bean.java.tsp.algorithm.population.Population;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DualThreadCrossoverExecutor<T> implements CrossoverExecutor<T> {

    private final Logger logger = LogManager.getLogger(DualThreadCrossoverExecutor.class);

    private final static int THREADS = 2;

    private final ExecutorService executorService = Executors.newFixedThreadPool(THREADS);

    @Override
    public Population<T> performCrossover(Crossover<T> crossover, Population<T> population) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            List<Population<T>> subPopulations = population.partition(getPartitionSize(population));
            Future<Population<T>> populationFuture1 = submit(crossover, subPopulations.get(0));
            Future<Population<T>> populationFuture2 = submit(crossover, subPopulations.get(1));
            Population<T> nextGenPopulation = populationFuture1.get().concat(populationFuture2.get());
            logger.info("Dual Threads Crossover took: " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms for whole population");
            return nextGenPopulation;
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Callable<Population<T>> buildCallable(Crossover<T> crossover, Population<T> population) {
        return () -> {
            List<Individual<T>> nextGeneration = new ArrayList<>();
            for (int i = 0; i < population.getNumberOfIndividuals(); i = i + 2) {
                CrossoverInput<T> crossoverInput = CrossoverInput.of(population.getPopulation().get(i), population.getPopulation().get(i + 1));
                nextGeneration.addAll(crossover.makeCrossover(crossoverInput).getAsCollection());
            }
            return new Population<>(nextGeneration);
        };
    }

    private Future<Population<T>> submit(Crossover<T> crossover, Population<T> population) {
        return executorService.submit(buildCallable(crossover, population));
    }

    private int getPartitionSize(Population<T> population) {
        int partition = population.getNumberOfIndividuals() / 2;
        if(partition % 2 == 1) {
            partition++;
        }
        return partition;
    }
}
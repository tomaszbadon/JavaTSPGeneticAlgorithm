package net.bean.java.tsp.algorithm.crossover;

import com.google.common.base.Stopwatch;
import net.bean.java.tsp.algorithm.character.Individual;
import net.bean.java.tsp.algorithm.population.Population;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface CrossoverExecutor<T> {

    final Logger logger = LogManager.getLogger(CrossoverExecutor.class);

    default Population<T> performCrossover(Crossover<T> crossover, Population<T> population) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        List<Individual<T>> nextGeneration = new ArrayList<>();
        for (int i = 0; i < population.getNumberOfIndividuals(); i = i + 2) {
            CrossoverInput<T> crossoverInput = CrossoverInput.of(population.getPopulation().get(i), population.getPopulation().get(i + 1));
            nextGeneration.addAll(crossover.makeCrossover(crossoverInput).getAsCollection());
        }
        logger.info("Crossover took: " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + " ms for whole population");
        return new Population<>(nextGeneration);
    }

}
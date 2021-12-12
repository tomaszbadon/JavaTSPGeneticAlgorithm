package net.bean.java.gui;

import net.bean.java.tsp.algorithm.genetic.TSPGeneticAlgorithm;
import net.bean.java.tsp.algorithm.genetic.generation.GeneticAlgorithmResult;

import java.util.function.Consumer;

public class BackgroundGeneticAlgorithmCalculation<T> extends Thread {

    private final TSPGeneticAlgorithm<T> geneticAlgorithm;
    private final Consumer<GeneticAlgorithmResult<T>> callback;

    public BackgroundGeneticAlgorithmCalculation(TSPGeneticAlgorithm<T> geneticAlgorithm, Consumer<GeneticAlgorithmResult<T>> callback) {
        this.geneticAlgorithm = geneticAlgorithm;
        this.callback = callback;
    }

    @Override
    public void run() {
        GeneticAlgorithmResult<T> geneticAlgorithmResult = geneticAlgorithm.run();
        callback.accept(geneticAlgorithmResult);
    }
}
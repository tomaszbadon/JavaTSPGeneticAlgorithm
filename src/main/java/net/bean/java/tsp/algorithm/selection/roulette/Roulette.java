package net.bean.java.tsp.algorithm.selection.roulette;

import net.bean.java.tsp.algorithm.character.Individual;
import net.bean.java.tsp.algorithm.util.MinMaxNormalization;

import java.util.*;

public class Roulette<T> {

    private static int rouletteRange = Integer.MAX_VALUE;

    private final int calculatedRouletteRange;

    private final Map<Range, Individual<T>> rangeToIndividual = new HashMap<>();

    private final Random random = new Random();

    private final double sumOfFitnessFuncVal;

    private final double sumOfInvertedSumOfFitnessFuncVal;

    public Roulette(List<Individual<T>> individuals, int rouletteRange) {
        this.rouletteRange = rouletteRange;
        sumOfFitnessFuncVal = calculateSumOfFitnessFunctionValues(individuals);
        sumOfInvertedSumOfFitnessFuncVal = calculateInvertedSumOfFitnessFunctionValues(individuals);
        int startPoint = -1, to = 0;
        for(Individual<T> individual : individuals) {
            int segmentOfRoulette = segmentOfRoulette(individual.getValueOfFitnessFunction());
            int from = startPoint + 1;
            to = segmentOfRoulette + startPoint;
            Range range = new Range(from, to);
            rangeToIndividual.put(range, individual);
            startPoint = to;
        }
        calculatedRouletteRange = to;
    }

    public Roulette(List<Individual<T>> individuals) {
        this(individuals, Integer.MAX_VALUE);
    }

    private int segmentOfRoulette(double valueOfFitnessFunction) {
        MinMaxNormalization minMaxNormalization = new MinMaxNormalization(0.0, 0, sumOfInvertedSumOfFitnessFuncVal, rouletteRange);
        double normalized = minMaxNormalization.normalize(sumOfFitnessFuncVal - valueOfFitnessFunction);
        return (int) Math.floor(normalized);
    }

    private double calculateInvertedSumOfFitnessFunctionValues(List<Individual<T>> individuals) {
        return individuals.stream()
                .map(i -> sumOfFitnessFuncVal - i.getValueOfFitnessFunction())
                .reduce(0.00, (accumulator, fitnessValue) -> (accumulator + fitnessValue));
    }

    private double calculateSumOfFitnessFunctionValues(List<Individual<T>> individuals) {
        return individuals.stream()
                .map(i -> i.getValueOfFitnessFunction())
                .reduce(0.00, (accumulator, fitnessValue) -> (accumulator + fitnessValue));
    }

    public Individual<T> spin() {
        int randomLongValue = Math.abs(random.nextInt(calculatedRouletteRange + 1));
        Optional<Range> found = rangeToIndividual.keySet().stream().filter(range -> range.getFrom() <= randomLongValue && range.getTo() >= randomLongValue).findFirst();
        Range range = found.orElseThrow(() -> new RuntimeException("Cannot find appropriate Individual using random value: " + randomLongValue));
        return rangeToIndividual.get(range);

    }

    public double getSumOfFitnessFuncVal() {
        return sumOfFitnessFuncVal;
    }
}
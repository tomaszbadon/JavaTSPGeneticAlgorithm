package net.bean.java.tsp.algorithm.mutation;

import net.bean.java.tsp.algorithm.character.Individual;
import net.bean.java.tsp.algorithm.util.MinMaxNormalization;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimpleMutation<T> implements Mutation<T> {

    private final static int RANGE_OF_MAX_FOR_NORMALIZATION = 100;

    private final static double MAX_CERTAINTY = 1.0;

    private final Random random = new Random();

    private final MinMaxNormalization normalization = new MinMaxNormalization(MAX_CERTAINTY, RANGE_OF_MAX_FOR_NORMALIZATION);

    @Override
    public void performMutation(double probabilityOfMutation, Individual<T> individual) {
        if(isReadyForMutation(probabilityOfMutation)) {
            List<T> chromosome = individual.getChromosomes();
            Collections.swap(chromosome, random.nextInt(chromosome.size()), random.nextInt(chromosome.size()));
        }
    }

    private boolean isReadyForMutation(double probabilityOfMutation) {
        int normalizedProbOfMut = (int) Math.round(normalization.normalize(probabilityOfMutation));
        return random.nextInt(RANGE_OF_MAX_FOR_NORMALIZATION + 1) <= normalizedProbOfMut;
    }

}
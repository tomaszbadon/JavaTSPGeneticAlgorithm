package net.bean.java.tsp.algorithm.mutation;

import net.bean.java.tsp.algorithm.character.Individual;
import net.bean.java.tsp.algorithm.character.IndividualBuilder;
import net.bean.java.tsp.algorithm.util.MinMaxNormalization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ForcedMutation<T> implements Mutation<T> {

    private final static Logger logger = LogManager.getLogger(ForcedMutation.class);

    private final static int RANGE_OF_MAX_FOR_NORMALIZATION = 100;

    private final static double MAX_CERTAINTY = 1.0;

    private final IndividualBuilder<T> individualBuilder;

    private final Random random = new Random();

    private final MinMaxNormalization normalization = new MinMaxNormalization(MAX_CERTAINTY, RANGE_OF_MAX_FOR_NORMALIZATION);

    private final static int MAX_NUMBER_OF_TRIES = 1000;

    public ForcedMutation(IndividualBuilder<T> individualBuilder) {
        this.individualBuilder = individualBuilder;
    }

    @Override
    public void performMutation(double probabilityOfMutation, Individual<T> individual) {
        if(isReadyForMutation(probabilityOfMutation)) {
            double initialCost = individual.getValueOfFitnessFunction();
            Individual<T> newIndividual;
            int counter = 0;
            do {
                List<T> chromosome = new ArrayList(individual.getChromosomes());
                newIndividual = individualBuilder.createNewIndividual(chromosome);
                Collections.swap(chromosome, random.nextInt(chromosome.size()), random.nextInt(chromosome.size()));
                counter++;
            } while (individual.getValueOfFitnessFunction() > newIndividual.getValueOfFitnessFunction() || counter < MAX_NUMBER_OF_TRIES);
            individual.getChromosomes().clear();
            individual.getChromosomes().addAll(newIndividual.getChromosomes());
            if(individual.getValueOfFitnessFunction() < initialCost) {
                logger.info("ValueOfFitnessFunction was reduced from: " + initialCost + " to " + individual.getValueOfFitnessFunction());
            }
        }
    }

    private boolean isReadyForMutation(double probabilityOfMutation) {
        int normalizedProbOfMut = (int) Math.round(normalization.normalize(probabilityOfMutation));
        return random.nextInt(RANGE_OF_MAX_FOR_NORMALIZATION + 1) <= normalizedProbOfMut;
    }

}

package net.bean.java.tsp.algorithm.mutation;

import net.bean.java.tsp.algorithm.character.Individual;

public interface Mutation<T> {

    void performMutation(double probabilityOfMutation, Individual<T> individual);

}

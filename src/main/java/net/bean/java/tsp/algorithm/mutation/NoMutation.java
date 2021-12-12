package net.bean.java.tsp.algorithm.mutation;

import net.bean.java.tsp.algorithm.character.Individual;

public class NoMutation<T> implements Mutation<T> {

    @Override
    public void performMutation(double probabilityOfMutation, Individual<T> individual) { }

}
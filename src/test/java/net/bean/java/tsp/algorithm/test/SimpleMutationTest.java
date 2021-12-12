package net.bean.java.tsp.algorithm.test;

import net.bean.java.tsp.algorithm.character.Individual;
import net.bean.java.tsp.algorithm.mutation.SimpleMutation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SimpleMutationTest {

    @Test
    void simpleTestOfMutation() {
        Individual<Integer> individual = new MockIndividual(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        List<Integer> copyOfChromosomes = new ArrayList<>(individual.getChromosomes());
        SimpleMutation<Integer> simpleMutation = new SimpleMutation();
        simpleMutation.performMutation(1.0, individual);
        Assertions.assertFalse(copyOfChromosomes.equals(individual.getChromosomes()));
    }

}
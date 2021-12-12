package net.bean.java.tsp.algorithm.crossover;

import net.bean.java.tsp.algorithm.character.Individual;

public interface CrossoverInput<T> {

    Individual<T> getFirst();

    Individual<T> getSecond();

    static <T> CrossoverInput<T> of(Individual<T> first, Individual<T> second) {
        return new CrossoverInputOutput<>(first, second);
    }

}

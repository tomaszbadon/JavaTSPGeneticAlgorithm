package net.bean.java.tsp.algorithm.crossover;

import net.bean.java.tsp.algorithm.character.Individual;

import java.util.Collection;

public interface CrossoverOutput<T> {

    Individual<T> getFirst();

    Individual<T> getSecond();

    Collection<Individual<T>> getAsCollection();

    static <T> CrossoverOutput<T> of(Individual<T> first, Individual<T> second) {
        return new CrossoverInputOutput<>(first, second);
    }

}

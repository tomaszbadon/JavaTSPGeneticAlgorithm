package net.bean.java.tsp.algorithm.selection;

import net.bean.java.tsp.algorithm.character.Individual;
import net.bean.java.tsp.algorithm.population.Population;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public abstract class AbstractSelection<T> implements Selection<T> {

    @Override
    public Population<T> select(int numberOfSelectedIndividuals, Population<T> population) {
        List<Individual<T>> selectedIndividuals = new ArrayList<>();
        IntStream.range(0, numberOfSelectedIndividuals).forEach((i) -> selectedIndividuals.add(selectOne(population)));
        return new Population<>(selectedIndividuals);
    }

    protected abstract Individual<T> selectOne(Population<T> population);

}
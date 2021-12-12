package net.bean.java.tsp.algorithm.selection.roulette;

import net.bean.java.tsp.algorithm.character.Individual;
import net.bean.java.tsp.algorithm.population.Population;
import net.bean.java.tsp.algorithm.selection.AbstractSelection;

import java.util.Optional;

public class RouletteSelection<T> extends AbstractSelection<T> {

    private Optional<Roulette<T>> roulette = Optional.empty();

    @Override
    public Population<T> select(int numberOfSelectedIndividuals, Population<T> population) {
        roulette = Optional.of(new Roulette(population.getPopulation()));
        return super.select(numberOfSelectedIndividuals, population);
    }

    @Override
    protected Individual<T> selectOne(Population<T> population) {
        return roulette.orElse(new Roulette<>(population.getPopulation())).spin();
    }
}
package net.bean.java.tsp.algorithm.selection.ranking;

import net.bean.java.tsp.algorithm.character.Individual;
import net.bean.java.tsp.algorithm.population.Population;
import net.bean.java.tsp.algorithm.selection.AbstractSelection;

import java.util.Random;

public class RankingSelection<T> extends AbstractSelection<T> {

    private final static double FACTOR = 0.4;

    private final int range;

    private final Random random = new Random();

    public RankingSelection(int range) {
        this.range = range;
    }

    @Override
    public Population<T> select(int numberOfSelectedIndividuals, Population<T> population) {
        return super.select(numberOfSelectedIndividuals, population.sorted());
    }

    @Override
    protected Individual<T> selectOne(Population<T> population) {
        return population.getPopulation().get(random.nextInt(range));
    }

    public static long defaultRangeOfSelection(int numberOfIndividuals) {
        return Math.round(numberOfIndividuals * FACTOR);
    }
}
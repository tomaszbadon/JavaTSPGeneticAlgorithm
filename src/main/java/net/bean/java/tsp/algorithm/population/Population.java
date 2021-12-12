package net.bean.java.tsp.algorithm.population;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.bean.java.tsp.algorithm.character.Individual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Population<T> {

    private final ImmutableList<Individual<T>> population;

    public Population(List<Individual<T>> population) {
        this.population = ImmutableList.copyOf(population);
    }

    public Population(Population<T> population) {
        this.population = ImmutableList.copyOf(population.getPopulation());
    }

    public Population<T> concat(Population<T> population) {
        List<Individual<T>> concatenation = new ArrayList<>(this.getPopulation());
        concatenation.addAll(population.getPopulation());
        return new Population<>(concatenation);
    }

    public Population<T> sorted() {
        List<Individual<T>> sortedIndividuals = new ArrayList(population);
        Collections.sort(sortedIndividuals);
        return new Population<>(sortedIndividuals);
    }

    public double getAverageValueOfFitnessFunction() {
        return population.stream()
                         .mapToDouble(Individual::getValueOfFitnessFunction)
                         .average()
                         .orElseThrow(() -> new RuntimeException("Couldn't calculate calculateAverageValueOfFitnessFunction value of fitness function for population"));
    }

    public int getNumberOfIndividuals() {
        return population.size();
    }

    public Population<T> subPopulation(int fromIndex, int toIndex) {
        return new Population<>(population.subList(fromIndex, toIndex));
    }

    public List<Population<T>> partition(int size) {
        return Lists.partition(population, size).stream().map(Population::new).collect(Collectors.toList());
    }

    public List<Individual<T>> getPopulation() {
        return population;
    }

    public Individual<T> findTheBestIndividual() {
        return Collections.min(population);
    }

}
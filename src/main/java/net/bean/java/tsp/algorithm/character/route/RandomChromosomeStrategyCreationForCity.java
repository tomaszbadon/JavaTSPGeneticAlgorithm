package net.bean.java.tsp.algorithm.character.route;

import net.bean.java.tsp.algorithm.character.ChromosomeStrategyCreation;
import net.bean.java.tsp.algorithm.city.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomChromosomeStrategyCreationForCity implements ChromosomeStrategyCreation<City> {

    private final Random random = new Random();

    private final Set<City> genes;

    public RandomChromosomeStrategyCreationForCity(Set<City> genes) {
        this.genes = genes;
    }

    @Override
    public List<City> getChromosome() {
        List<City> sequenceOfCities = new ArrayList<>();
        List<City> listOfCities = new ArrayList<>(genes);
        while(listOfCities.size() > 0) {
            int index = random.nextInt(listOfCities.size());
            sequenceOfCities.add(listOfCities.remove(index));
        }
        return sequenceOfCities;
    }
}
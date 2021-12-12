package net.bean.java.tsp.algorithm.character.route;

import net.bean.java.tsp.algorithm.character.ChromosomeStrategyCreation;
import net.bean.java.tsp.algorithm.city.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TheClosestCityChromosomeStrategyCreation implements ChromosomeStrategyCreation<City> {

    private final Random random = new Random();

    private final Set<City> cities;

    public TheClosestCityChromosomeStrategyCreation(Set<City> cities) {
        this.cities = cities;
    }

    @Override
    public List<City> getChromosome() {
        List<City> sequenceOfCities = new ArrayList<>();
        List<City> listOfCities = new ArrayList<>(cities);
        City currentCity = listOfCities.get(random.nextInt(listOfCities.size()));
        listOfCities.remove(currentCity);
        sequenceOfCities.add(currentCity);
        while(listOfCities.size() > 0) {
            City next = findTheClosestCity(currentCity, listOfCities);
            listOfCities.remove(next);
            sequenceOfCities.add(next);
            currentCity = next;
        }
        return sequenceOfCities;
    }

    private City findTheClosestCity(City city, List<City> cities) {
        City min = cities.get(0);
        double minDistance = city.distanceTo(min);
        for(int i=1 ; i<cities.size() ; i++) {
            if(city.distanceTo(cities.get(i)) < minDistance) {
                min = cities.get(i);
                minDistance = city.distanceTo(cities.get(i));
            }
        }
        return min;
    }
}
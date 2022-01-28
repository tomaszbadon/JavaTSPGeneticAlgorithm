package net.bean.java.tsp.algorithm.city;

import com.google.common.collect.ImmutableSet;
import net.bean.java.gui.enums.NumberOfCities;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CityProvider {

    private final Random random = new Random();

    private final int MAX_CITY_BOUND = 500;

    public Set<City> provide(NumberOfCities numberOfCities) {
        if(numberOfCities.equals(NumberOfCities.BENCHMARK)) {
            return PredefinedCitySet.defaultSet();
        } else {
            CityProvider cityProvider = new CityProvider();
            return cityProvider.provide(numberOfCities.getNumberOfCities().getAsInt());
        }
    }

    public Set<City> provide(int numberOfCities) {
        Set<City> cities = new HashSet<>();
        do {
            cities.add(new City(cities.size() + 1, random.nextInt(MAX_CITY_BOUND), random.nextInt(MAX_CITY_BOUND)));
        } while(cities.size() < numberOfCities);
        return ImmutableSet.copyOf(cities);
    }
}
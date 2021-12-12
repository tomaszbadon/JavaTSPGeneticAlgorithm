package net.bean.java.tsp.algorithm.city;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Random;
import java.util.Set;

public class RandomCityGenerator {

    private final Random random = new Random();

    public Set<City> generate(int numberOfCities) {
        Set<City> cities = Sets.newHashSet();
        int sequence = 1;
        do {
            City city = new City(sequence, random.nextInt(500), random.nextInt(500));
            if(!cities.contains(city)) {
                cities.add(city);
                sequence++;
            }
        } while(sequence <= numberOfCities);
        return ImmutableSet.copyOf(cities);
    }
}
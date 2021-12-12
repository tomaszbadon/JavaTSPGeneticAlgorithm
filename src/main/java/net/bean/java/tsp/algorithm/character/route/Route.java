package net.bean.java.tsp.algorithm.character.route;

import net.bean.java.tsp.algorithm.character.Individual;
import net.bean.java.tsp.algorithm.character.IndividualBuilder;
import net.bean.java.tsp.algorithm.city.City;

import java.util.List;
import java.util.Objects;

public class Route implements Individual<City> {

    private final List<City> sequenceOfCities;

    public Route(List<City> sequenceOfCities) {
        this.sequenceOfCities = sequenceOfCities;
    }

    @Override
    public double getValueOfFitnessFunction() {
        double distance = 0;
        City previous = null;
        City next;
        for(City city : sequenceOfCities) {
            if(previous == null) {
                previous = city;
            } else {
                next = city;
                distance += previous.distanceTo(next);
                previous = city;
            }
        }
        return distance;
    }

    @Override
    public List<City> getChromosomes() {
        return sequenceOfCities;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        sequenceOfCities.stream().forEach(c-> sb.append(c.getIdentifier()).append(" "));
        sb.append("]");
        sb.append(" : ").append(getValueOfFitnessFunction());
        return  sb.toString();
    }

    @Override
    public int compareTo(Individual individual) {
        if(this.getValueOfFitnessFunction() < individual.getValueOfFitnessFunction()) return -1;
        if(this.getValueOfFitnessFunction() > individual.getValueOfFitnessFunction()) return 1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(sequenceOfCities, route.sequenceOfCities);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sequenceOfCities);
    }

    public static class RouteBuilder implements IndividualBuilder<City> {
        @Override
        public Individual<City> createNewIndividual(List<City> chromosome) {
            return new Route(chromosome);
        }
    }

}
package net.bean.java.tsp.algorithm.test;

import net.bean.java.tsp.algorithm.character.ChromosomeStrategyCreation;
import net.bean.java.tsp.algorithm.character.route.TheClosestCityChromosomeStrategyCreation;
import net.bean.java.tsp.algorithm.city.City;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TheClosestCityChromosomeStrategyCreationTest {

    private City a = new City(1, 0, 1);
    private City b = new City(2, 0, 2);
    private City c = new City(3, 0, 3);
    private City d = new City(4, 0, 4);
    private City e = new City(5, 0, 5);
    private City f = new City(6, 0, 6);
    private City g = new City(7, 0, 7);
    private City h = new City(8, 0, 8);
    private City i = new City(9, 0, 9);
    private City j = new City(10, 0, 10);
    private City k = new City(11, 0, 11);
    private City l = new City(12, 0, 12);
    private City m = new City(13, 0, 13);

    private final Set<City> cities = new HashSet<>(Arrays.asList(a,b,c,d,e,f,g,h,i,j,k,l,m));

    @Test
    public void test() {
        ChromosomeStrategyCreation<City> chromosomeStrategyCreation = new TheClosestCityChromosomeStrategyCreation(cities);
        for(int i=0 ; i <1000000 ; i++) {
            List<City> chromosome = chromosomeStrategyCreation.getChromosome();
            boolean containsNull = chromosome.contains(null);
            Assertions.assertFalse(containsNull);
            Assertions.assertTrue(chromosome.size() == cities.size());
            Assertions.assertTrue((new HashSet(chromosome)).size() == cities.size());
        }

    }

}

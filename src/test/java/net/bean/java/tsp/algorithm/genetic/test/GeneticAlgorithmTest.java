package net.bean.java.tsp.algorithm.genetic.test;

import com.google.common.collect.Lists;
import net.bean.java.tsp.algorithm.character.ChromosomeStrategyCreation;
import net.bean.java.tsp.algorithm.character.Individual;
import net.bean.java.tsp.algorithm.character.IndividualBuilder;
import net.bean.java.tsp.algorithm.character.route.RandomChromosomeStrategyCreationForCity;
import net.bean.java.tsp.algorithm.character.route.Route;
import net.bean.java.tsp.algorithm.city.City;
import net.bean.java.tsp.algorithm.crossover.Crossover;
import net.bean.java.tsp.algorithm.genetic.TSPGeneticAlgorithm;
import net.bean.java.tsp.algorithm.genetic.TSPGeneticAlgorithmBuilder;
import net.bean.java.tsp.algorithm.genetic.generation.GeneticAlgorithmResult;
import net.bean.java.tsp.algorithm.mutation.Mutation;
import net.bean.java.tsp.algorithm.mutation.SimpleMutation;
import net.bean.java.tsp.algorithm.population.Population;
import net.bean.java.tsp.algorithm.population.PopulationBuilder;
import net.bean.java.tsp.algorithm.selection.Selection;
import net.bean.java.tsp.algorithm.selection.ranking.RankingSelection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("I am checking if")
public class GeneticAlgorithmTest {

    private static City a = new City(1, 0, 1);
    private static City b = new City(2, 0, 2);
    private static City c = new City(3, 0, 3);
    private static City d = new City(4, 0, 4);
    private static City e = new City(5, 0, 5);
    private static City f = new City(6, 0, 6);
    private static City g = new City(7, 0, 7);
    private static City h = new City(8, 0, 8);
    private static City i = new City(9, 0, 9);
    private static City j = new City(10, 0, 10);
    private static City k = new City(11, 0, 11);
    private static City l = new City(12, 0, 12);
    private static City m = new City(13, 0, 13);

    private static TSPGeneticAlgorithm<City> geneticAlgorithm;
    private static Individual<City> theBestIndividual;

    @BeforeAll
    static void initializePopulation() {
        Set<City> cities = new HashSet<>(Arrays.asList(a, b, c, d, e, f, g, h, i, j, k, l, m));
        ChromosomeStrategyCreation<City> chromosomeStrategyCreation = new RandomChromosomeStrategyCreationForCity(cities);
        IndividualBuilder<City> individualBuilder = new Route.RouteBuilder();
        Population<City> population = PopulationBuilder.<City>builder()
                .setIndividualBuilder(individualBuilder)
                .setPopulation(200)
                .setChromosomeStrategyCreation(chromosomeStrategyCreation)
                .build();
        Mutation<City> mutation = new SimpleMutation<>();
        Crossover<City> crossover = new Crossover<>(individualBuilder);
        Selection<City> selection = new RankingSelection<City>(40);

        geneticAlgorithm = TSPGeneticAlgorithmBuilder.<City>getBuilder()
                .setSelection(selection)
                .setCrossover(crossover)
                .setInitialPopulation(population)
                .setMutation(mutation)
                .setNumberOfGenerations(1000)
                .setProbabilityOfMutation(0.1)
                .build();
        GeneticAlgorithmResult<City> gi = geneticAlgorithm.run();
        theBestIndividual = gi.getLastPopulationInfo().getTheBestIndividual();
    }

    @Test
    @DisplayName("the best individual has minimal value of fitness function")
    void checkingOptimalValueOfFitnessFunction() {
        assertThat(theBestIndividual.getValueOfFitnessFunction()).isEqualTo(12.0);
    }

    @Test
    @DisplayName("the best individual represents optimal sequence of cities to visit")
    void checkingOptimalValueOfPath() {
        Route optimalRoute1 = new Route(Arrays.asList(a,b,c,d,e,f,g,h,i,j,k,l,m));
        Route optimalRoute2 = new Route(Lists.reverse(new ArrayList<>(optimalRoute1.getChromosomes())));
        assertThat(theBestIndividual).isIn(optimalRoute1, optimalRoute2);
    }

    @Test
    @DisplayName("the genetic algorithm is searching for the shortest path")
    void checkingIfGeneticAlgorithmIsSearchingForTheShortestPath() {
        GeneticAlgorithmResult geneticAlgorithmResult = geneticAlgorithm.getGeneticAlgorithmResult();
        double averageValueFromInitialPop = geneticAlgorithmResult.getInitialPopulationInfo().getAverageValueOfFitnessFunction();
        double averageValueFromLastPop = geneticAlgorithmResult.getLastPopulationInfo().getAverageValueOfFitnessFunction();
        assertThat(averageValueFromInitialPop).isGreaterThan(averageValueFromLastPop);
    }
}
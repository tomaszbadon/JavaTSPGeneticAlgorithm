package net.bean.java.tsp.algorithm.test;

import net.bean.java.tsp.algorithm.character.Individual;
import net.bean.java.tsp.algorithm.crossover.Crossover;
import net.bean.java.tsp.algorithm.crossover.CrossoverOutput;
import net.bean.java.tsp.algorithm.crossover.CrossoverInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class CrossoverTest {

  private Individual<Integer> firstParent = MockIndividual.of(Arrays.asList(3, 5, 2, 1, 6, 7, 8, 4));
  private Individual<Integer> secondParent = MockIndividual.of(Arrays.asList(1, 2, 3, 6, 7, 8, 4, 5));
  private Individual<Integer> firstChild = MockIndividual.of(Arrays.asList(1, 5, 3, 6, 7, 8, 2, 4));
  private Individual<Integer> secondChild = MockIndividual.of(Arrays.asList(3, 8, 2, 1, 6, 7, 4, 5));

  @Test
  void crossoverTest() {
      CrossoverInput<Integer> crossoverInput = CrossoverInput.of(firstParent, secondParent);
      Crossover<Integer> crossover = new Crossover<>(MockIndividual.getIndividualBuilder());
      CrossoverOutput<Integer> children = crossover.makeCrossover(2, 4, crossoverInput);
      Assertions.assertEquals(children.getFirst(), firstChild);
      Assertions.assertEquals(children.getSecond(), secondChild);
  }

}
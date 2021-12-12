package net.bean.java.tsp.algorithm.crossover;

import net.bean.java.tsp.algorithm.character.Individual;
import net.bean.java.tsp.algorithm.character.IndividualBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Crossover<T> {

    private final Random random = new Random();

    private final IndividualBuilder<T> individualBuilder;

    public Crossover(IndividualBuilder<T> individualBuilder) {
        this.individualBuilder = individualBuilder;
    }

    public CrossoverOutput makeCrossover(CrossoverInput<T> crossoverInput) {
        int startPoint = random.nextInt(crossoverInput.getFirst().getChromosomes().size());
        int length = random.nextInt(crossoverInput.getFirst().getChromosomes().size() - startPoint);
        return makeCrossover(startPoint, length, crossoverInput);
    }

    public CrossoverOutput makeCrossover(int startPoint, int length, CrossoverInput<T> crossoverInput) {
        List<T> firstChromosome = newEmptyChromosome(crossoverInput.getFirst().getChromosomes().size());
        List<T> secondChromosome = newEmptyChromosome(crossoverInput.getSecond().getChromosomes().size());
        for (int i = startPoint; i < startPoint + length; i++) {
            T firstGene = crossoverInput.getFirst().getChromosomes().get(i);
            T secondGene = crossoverInput.getSecond().getChromosomes().get(i);
            firstChromosome.set(i, secondGene);
            secondChromosome.set(i, firstGene);
        }
        LinkedList<T> mappingForFirstChromosome = new LinkedList<>();
        LinkedList<T> mappingForSecondChromosome = new LinkedList<>();
        for(int i=0 ; i<firstChromosome.size() ; i++) {
            if (i < startPoint || i > startPoint + length - 1) {
                T firstGene = crossoverInput.getFirst().getChromosomes().get(i);
                T secondGene = crossoverInput.getSecond().getChromosomes().get(i);
                if(!contains(firstGene, firstChromosome)) {
                    firstChromosome.set(i,firstGene);
                } else {
                    mappingForSecondChromosome.add(firstGene);
                }
                if(!contains(secondGene, secondChromosome)) {
                    secondChromosome.set(i, secondGene);
                } else {
                    mappingForFirstChromosome.add(secondGene);
                }
            }
        }
        for(int i=0 ; i<firstChromosome.size() ; i++) {
            if(firstChromosome.get(i) == null) {
                firstChromosome.set(i, mappingForFirstChromosome.pollFirst());
            }
            if(secondChromosome.get(i) == null) {
                secondChromosome.set(i, mappingForSecondChromosome.pollFirst());
            }
        }
        Individual<T> firstIndividual = individualBuilder.createNewIndividual(firstChromosome);
        Individual<T> secondIndividual = individualBuilder.createNewIndividual(secondChromosome);
        return CrossoverOutput.of(firstIndividual, secondIndividual);
    }

    private List<T> newEmptyChromosome(int length) {
        List<T> chromosome = new ArrayList<>();
        IntStream.rangeClosed(1, length).forEach(i -> chromosome.add(null));
        return  chromosome;
    }

    private boolean contains(T searched, List<T> genes) {
        return genes.stream().anyMatch(g -> searched.equals(g));
    }

}
package net.bean.java.tsp.algorithm.character;

import java.util.List;

public interface IndividualBuilder<T> {

    Individual<T> createNewIndividual(List<T> chromosome);

}

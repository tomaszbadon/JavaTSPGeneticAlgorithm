package net.bean.java.tsp.algorithm.test;

import net.bean.java.tsp.algorithm.util.MinMaxNormalization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MinMaxNormalizationTest {

    @Test
    void normalizeFive() {
        MinMaxNormalization minMaxNormalization = new MinMaxNormalization(0, 0, 10, 100);
        double newValue = minMaxNormalization.normalize(5);
        Assertions.assertTrue(newValue == 50.0);
    }

    @Test
    void normalizeTen() {
        MinMaxNormalization minMaxNormalization = new MinMaxNormalization(-10, 0, 10, 10);
        double newValue = minMaxNormalization.normalize(0);
        Assertions.assertTrue(newValue == 5);
    }

}
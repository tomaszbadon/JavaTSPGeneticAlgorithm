package net.bean.java.tsp.algorithm.city;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

public class PredefinedCitySet {

    private final static int MARGIN = 50;

    private PredefinedCitySet() { }

    public static Set<City> defaultSet() {
        int x = 0;
        int y = 0;
        int identifier = 0;
        ImmutableSet.Builder<City> setBilder = ImmutableSet.<City>builder();
        for(int i=0 ; i<10 ; i++) {
            x = 0;
            for(int j=0; j<10 ; j++) {
                setBilder.add(new City(identifier, x, y));
                identifier++;
                x += MARGIN;
            }
            y += MARGIN;
        }
        return setBilder.build();
    }
}

package net.bean.java.tsp.algorithm.city;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class City {

    private final int identifier, x, y;

    public City(int identifier, int x, int y) {
        this.identifier = identifier;
        this.x = x;
        this.y = y;
    }

    public int getIdentifier() {
        return identifier;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return identifier == city.identifier && x == city.x && y == city.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, x, y);
    }

    @Override
    public String toString() {
        return "City{" +
                "identifier=" + identifier +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public double distanceTo(City city) {
        return Math.sqrt(Math.pow((this.getX() - city.getX()),2) + Math.pow((this.getY() - city.getY()),2));
    }

    public static class CityBuilder {

        private final static Pattern pattern = Pattern.compile("^\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)$");

        private CityBuilder() { }

        public static City fromLine(String line) {
            Matcher matcher = pattern.matcher(line);
            if(matcher.matches()) {
                int identifier = Integer.parseInt(matcher.group(1));
                int x = Integer.parseInt(matcher.group(2));
                int y = Integer.parseInt(matcher.group(3));
                return new City(identifier, x, y);
            } else {
                throw new RuntimeException("Cannot parse line: " + line);
            }
        }
    }
}
package net.bean.java.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.bean.java.tsp.algorithm.character.Individual;
import net.bean.java.tsp.algorithm.city.City;
import net.bean.java.tsp.algorithm.util.MinMaxNormalization;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class CityGraphicsContextDrawer {

    private final static int PADDING = 2;
    private final static Color MAIN_COLOR = Color.WHITE;
    private final static Color LINE_COLOR = Color.SILVER;

    private final Canvas canvas;
    private final GraphicsContext graphicsContext;

    public CityGraphicsContextDrawer(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    public void redrawAll(Individual<City> individual) {
        clearBackground();
        drawRoute(individual);
        drawCities(individual.getChromosomes());
    }

    public void clearBackground() {
        graphicsContext.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
    }

    public void drawCities(Collection<City> cities) {
        graphicsContext.setFill(MAIN_COLOR);
        int max = findMax(cities);
        for(City city : cities) {
            MinMaxNormalization norm = new MinMaxNormalization(0.0, PADDING, max, (long) (canvas.getHeight() - PADDING));
            double x = norm.normalize(city.getX()) - PADDING;
            double y = canvas.getHeight() - norm.normalize(city.getY()) - PADDING;
            graphicsContext.fillOval(x, y, 2 * PADDING, 2 * PADDING);
        }
    }

    public void drawRoute(Individual<City> individual) {
        graphicsContext.setFill(LINE_COLOR);
        int max = findMax(individual.getChromosomes());
        graphicsContext.setLineWidth(1);
        for(int i = 0; i < individual.getChromosomes().size() - 1 ; i++) {
            City c1 = individual.getChromosomes().get(i);
            City c2 = individual.getChromosomes().get(i + 1);
            MinMaxNormalization norm = new MinMaxNormalization(0.0, PADDING, max, (long) (canvas.getHeight() - PADDING));
            double x1 = norm.normalize(c1.getX());
            double y1 = canvas.getHeight() - norm.normalize(c1.getY());
            double x2 = norm.normalize(c2.getX());
            double y2 = canvas.getHeight() - norm.normalize(c2.getY());
            graphicsContext.setStroke(LINE_COLOR);
            graphicsContext.strokeLine(x1, y1, x2, y2);
        }
    }

    private int findMax(Collection<City> cities) {
        int maxX = Collections.max(cities, Comparator.comparing(City::getX)).getX();
        int maxY = Collections.max(cities, Comparator.comparing(City::getY)).getY();
        return maxY > maxX ? maxY : maxX;
    }

}
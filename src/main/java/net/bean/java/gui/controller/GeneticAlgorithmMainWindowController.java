package net.bean.java.gui.controller;

import com.google.common.base.Stopwatch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import net.bean.java.gui.CityGraphicsContextDrawer;
import net.bean.java.gui.callback.AfterEachIterationCallback;
import net.bean.java.gui.callback.OnStartCallback;
import net.bean.java.gui.enums.NumberOfCities;
import net.bean.java.gui.executor.GeneticAlgorithmExecutor;
import net.bean.java.gui.util.FxComponentStatusChanger;
import net.bean.java.gui.util.ProgressCalculator;
import net.bean.java.tsp.algorithm.city.City;
import net.bean.java.tsp.algorithm.city.CityProvider;
import net.bean.java.tsp.algorithm.genetic.generation.Generation;
import net.bean.java.tsp.algorithm.genetic.generation.GeneticAlgorithmResult;
import net.bean.java.tsp.algorithm.selection.Selection;
import net.bean.java.tsp.algorithm.selection.ranking.RankingSelection;
import net.bean.java.gui.callback.OnCompleteCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.dialog.ExceptionDialog;

import java.net.URL;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GeneticAlgorithmMainWindowController implements Initializable, AfterEachIterationCallback<Generation<City>>, OnCompleteCallback<GeneticAlgorithmResult<City>>, OnStartCallback {

    private final static Logger logger = LogManager.getLogger(GeneticAlgorithmMainWindowController.class);

    private Set<City> cities = Set.of();
    private CityGraphicsContextDrawer drawer;
    private OptionalInt numberOfGenerations = OptionalInt.empty();
    private Stopwatch stopwatch;
    private final CityProvider cityProvider = new CityProvider();
    private final FxComponentStatusChanger statusChanger = new FxComponentStatusChanger(this);

    @FXML private Canvas canvas;
    @FXML private TextField individualsTextField;
    @FXML private TextField generationsTextField;
    @FXML private Slider rankingRangeSlider;
    @FXML private Slider probabilityOfMutationSlider;
    @FXML private ComboBox<String> numberOfCitiesComboBox;
    @FXML private Label statusLabel;
    @FXML private Button startButton;
    @FXML private Label summaryLabel;
    @FXML private Label rankingRangeLabel;
    @FXML private Label mutationProbabilityLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberOfCitiesComboBox.setItems(FXCollections.observableArrayList(NumberOfCities.valuesAsString()));
        numberOfCitiesComboBox.setValue(NumberOfCities.BENCHMARK.getDescription());
        drawer = new CityGraphicsContextDrawer(canvas);
        numberOfCitiesWasChanged();
    }

    @FXML
    private void numberOfCitiesWasChanged() {
        NumberOfCities numberOfCities = NumberOfCities.findByDescription(numberOfCitiesComboBox.getValue()).get();
        cities = cityProvider.provide(numberOfCities);
        individualsTextField.setText(String.valueOf(numberOfCities.getDefaultNumberOfIndividuals()));
        generationsTextField.setText(String.valueOf(numberOfCities.getDefaultNumberOfGenerations()));
        drawer.clearBackground();
        drawer.drawCities(cities);
    }

    @FXML
    private void run() {
        try {
            stopwatch = Stopwatch.createStarted();
            summaryLabel.setText("I am working...");
            int numberOfIndividuals = getNumberOfIndividuals();
            numberOfGenerations = getNumberOfGenerations();
            double probability = probabilityOfMutationSlider.getValue();
            Selection selection = getSelection();
            GeneticAlgorithmExecutor executor = new GeneticAlgorithmExecutor(numberOfIndividuals, numberOfGenerations.getAsInt(), probability, selection, cities);
            executor.setOnStartCallback(this);
            executor.setAfterEachIterationCallback(this);
            executor.setOnFinishOnCompleteCallback(this);
            executor.execute();
        } catch (Exception e) {
            ExceptionDialog ed = new ExceptionDialog(e);
            ed.setHeaderText(e.getClass().getName() + ": " + e.getMessage());
            ed.show();
            changeStateOfComponents(true);
        }
    }

    @FXML
    private void onRangeValueChanged(Event event) {
        rankingRangeLabel.setText(((int) rankingRangeSlider.getValue()) + "%");
    }

    @FXML
    private void onMutationProbabilityValueChanged(Event event) {
        mutationProbabilityLabel.setText(Math.round(probabilityOfMutationSlider.getValue() * 100) + "%");
    }

    @Override
    public void onStart() {
        Platform.runLater(() -> {
            changeStateOfComponents(true);
        });
    }

    @Override
    public void afterEachIteration(Generation<City> generation) {
        Platform.runLater(() -> {
            numberOfGenerations.ifPresent(generations -> {
                long progress = Math.round(ProgressCalculator.calculate(generation, generations) * 100);
                statusLabel.setText("Progress: " + progress + "%");
            });
            if(shouldBeCanvasRedrawn(generation.getNumberOfGeneration())) {
                drawer.redrawAll(generation.getTheBestIndividual());
            }
        });
    }

    @Override
    public void onComplete(GeneticAlgorithmResult<City> geneticAlgorithmResult) {
        Platform.runLater(() -> {
            drawer.redrawAll(geneticAlgorithmResult.getLastPopulationInfo().getTheBestIndividual());
            changeStateOfComponents(false);
            summaryLabel.setText("Completed in: " + stopwatch.elapsed(TimeUnit.SECONDS) + " seconds!");
        });
    }

    private boolean shouldBeCanvasRedrawn(int numberOfGeneration) {
        final int updateCanvasEvery10Generations = 10;
        return numberOfGeneration % updateCanvasEvery10Generations == 0 || numberOfGeneration == 1;
    }

    private Selection getSelection() {
        int selectionRange = (int) (rankingRangeSlider.getValue() * getNumberOfIndividuals() * 0.01);
        return new RankingSelection<>(selectionRange);
    }

    private int getNumberOfIndividuals() {
        return Integer.parseInt(individualsTextField.getText());
    }

    private OptionalInt getNumberOfGenerations() {
        return OptionalInt.of(Integer.parseInt(generationsTextField.getText()));
    }

    private void changeStateOfComponents(boolean state) {
        statusChanger.changeStatusOfNodes(state);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void redrawCanvas() {
        drawer.clearBackground();
        drawer.drawCities(cities);
    }

}
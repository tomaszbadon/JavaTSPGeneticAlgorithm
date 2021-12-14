package net.bean.java.gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import net.bean.java.gui.controller.GeneticAlgorithmMainWindowController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class GeneticAlgorithmMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("GeneticAlgorithmMainWindow.fxml"));
            Parent root = loader.load();
            GeneticAlgorithmMainWindowController controller = loader.getController();
            Scene scene = new Scene(root);
            boolean added = scene.getStylesheets().add("modena_dark.css");
            stage.setMaximized(true);
            stage.setTitle("FXML Welcome");
            stage.setScene(scene);
            stage.setTitle("Travelling Salesman Problem - Genetic Algorithm");
            stage.show();

            ChangeListener<? super Number> listener = (obs, oldValue, newValue) -> {
                double canvasDimension = calculateCanvasDimension(stage);
                double positionX = calculateXPosition(stage, canvasDimension);
                Canvas c = controller.getCanvas();
                c.layoutXProperty().set(positionX);
                c.setWidth(canvasDimension);
                c.setHeight(canvasDimension);
                controller.redrawCanvas();
            };

            listener.changed(null, 0.0, 0.0);

            stage.widthProperty().addListener(listener);
            stage.heightProperty().addListener(listener);

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private double calculateCanvasDimension(Stage stage) {
        double stageWidth = stage.getWidth();
        double stageHeight = stage.getHeight();
        double canvasSide = (stageHeight > stageWidth ? stageWidth : stageHeight) * 0.9;
        return canvasSide;
    }

    private double calculateXPosition(Stage stage, double canvasSide) {
        double positionX = (stage.getWidth() - canvasSide) / 2.0;
        if(positionX < 150.0) {
            positionX = 150.0;
        }
        return positionX;
    }

}

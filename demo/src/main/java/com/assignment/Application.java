package com.assignment;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {
    // Override the start method in the Application class
    @Override
    public void start(Stage stage) throws IOException {
        // Root
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("scene1.fxml")));
        // Scene
        Scene scene = new Scene(root);
        // Css
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        // Icon
        Image icon = new Image("icon.jfif");
        stage.getIcons().add(icon);
        stage.setTitle("Gentle Reminder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
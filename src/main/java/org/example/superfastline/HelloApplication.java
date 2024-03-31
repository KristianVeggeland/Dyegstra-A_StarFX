package org.example.superfastline;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.superfastline.container.ApplicationBox;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ApplicationBox applicationBox = new ApplicationBox();
        Scene  scene = new Scene(applicationBox, 800, 800);
        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
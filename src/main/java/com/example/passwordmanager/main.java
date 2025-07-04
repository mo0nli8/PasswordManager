package com.example.passwordmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Classpath-based FXML loading
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/passwordmanager/mainScene.fxml"));
            Scene scene = new Scene(root, 800, 600);
            stage.setTitle("My App");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();  // will print cause if file not found
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

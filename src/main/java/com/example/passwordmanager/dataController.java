package com.example.passwordmanager;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.passwordmanager.mainController.entryList;

public class dataController {

    @FXML private TextField websiteField;
    @FXML private TextField userNameField;
    @FXML private PasswordField passwordField;
    @FXML private Button saveButton;


    public void initialize() {
        saveButton.setOnAction(event -> handleSave());
    }


    private void handleSave() {
        String website = websiteField.getText();
        String userName = userNameField.getText();
        String password = passwordField.getText();

        appData data = new appData(userName, password, website);
        DataBase.insert(data);
        entryList.clear();
        entryList.addAll(DataBase.getAllEntries());

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/passwordmanager/mainMenu.fxml"));
            Parent root = loader.load();

            mainController mainController = loader.getController();
            mainController.setEntryList(entryList);
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Password Manager");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

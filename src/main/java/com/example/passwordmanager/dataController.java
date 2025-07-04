package com.example.passwordmanager;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class dataController {

    //attributes for the class
    @FXML private TextField websiteField;
    @FXML private TextField userNameField;
    @FXML private PasswordField passwordField;
    @FXML private Button saveButton;

    // list for saving the data (allows to track changes when they occur)
    private ObservableList<appData> entryList;


    public void initialize() {
        saveButton.setOnAction(event -> handleSave());
    }

    //setting the list
    public void setEntryList(ObservableList<appData> entryList) {
        this.entryList = entryList;
    }

    // a method to save the data to the list
    private void handleSave(){
        // putting the data into the variables
        String website = websiteField.getText();
        String userName = userNameField.getText();
        String password = passwordField.getText();

        appData data = new appData(userName,password,website);
        entryList.add(data);
    }




}


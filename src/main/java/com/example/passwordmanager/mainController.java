package com.example.passwordmanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;


public class mainController {

    @FXML private TableView<appData> passwordTable;
    @FXML private TableColumn<appData, String> webSiteNameColumn;
    @FXML private TableColumn<appData, String> userNameColumn;
    @FXML private TableColumn<appData, String> passwordColumn;
    @FXML private javafx.scene.control.TextField searchBar;

    public static ObservableList<appData> entryList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        webSiteNameColumn.setCellValueFactory(new PropertyValueFactory<>("webSiteName"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        entryList.clear();
        entryList.setAll(DataBase.getAllEntries());
        passwordTable.setItems(entryList);


    }

    public void setEntryList(ObservableList<appData> entryList) {
        this.entryList = entryList;
        passwordTable.setItems(entryList);
    }
    @FXML
    private void openDataEntryForm(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/passwordmanager/mainScene.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Add New Entry");
            stage.show();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void searchPassword() {
        String keyword = searchBar.getText().trim();
        if (keyword.isEmpty()) {
            entryList.setAll(DataBase.getAllEntries());
        } else {
            entryList.setAll(DataBase.searchEntries(keyword));
        }
    }

}

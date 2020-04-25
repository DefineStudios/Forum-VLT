package pl.kacper.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class newMP extends Application {
    public Button register;
    public Button modify;
    public Button exit;
    public Button listSelective;
    public Button delete;
    public Button list;

    Parent root;
    Scene scene;
    Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(this.getClass().getResource("main_panel.fxml"));

        root = FXMLLoader.load(getClass().getResource("/main_panel.fxml"));

        scene = new Scene(root, 600, 400);

        stage = primaryStage;

        stage.setTitle("Choose an option");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void registerPanelOpen(javafx.event.ActionEvent actionEvent) {
    }

    public void modify(ActionEvent actionEvent) {
    }

    public void listAll(ActionEvent actionEvent) {
    }

    public void listSome(ActionEvent actionEvent) {
    }

    public void delete(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
        stage.close();
    }
}
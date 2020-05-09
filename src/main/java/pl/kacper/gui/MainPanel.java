package pl.kacper.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import pl.kacper.backend.exception.DomainException;
import pl.kacper.backend.model.User;
import pl.kacper.backend.service.UserService;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainPanel extends Application implements EventHandler<ActionEvent> {

    private Button buttonReg;
    private Button buttonShow;
    private Button buttonUp;
    private UserService userService;
    private TextField login;
    private TextField password;
    private TextField id;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initUI(primaryStage);
    }

    private void initUI(Stage primaryStage) {
        userService = new UserService();

        GridPane layout = new GridPane();
        Scene scene = new Scene(layout, 550, 100);

        layout.setPadding(new Insets(10,10,10,10));
        layout.setHgap(10);
        layout.setVgap(8);

        buttonReg = new Button();
        buttonReg.setText("Register new user");
        buttonShow = new Button();
        buttonShow.setText("Show all users");
        buttonUp = new Button();
        buttonUp.setText("Update user");

        Label loginLabel = new Label("Login: ");
        loginLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 10));

        login = new TextField();
        login.setEditable(true);

        Label passwordLabel = new Label("Password: ");
        passwordLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 10));

        password = new TextField();
        password.setEditable(true);

        Label idLabel = new Label("ID: ");
        idLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 10));

        id = new TextField();
        id.setEditable(true);

        GridPane.setFillWidth(buttonReg, true);
        GridPane.setFillWidth(buttonShow, true);
        GridPane.setFillWidth(buttonUp, true);
        GridPane.setFillWidth(loginLabel, true);
        GridPane.setFillWidth(passwordLabel, true);
        GridPane.setFillWidth(idLabel, true);
        buttonReg.setMaxWidth(150);
        buttonShow.setMaxWidth(150);
        buttonUp.setMaxWidth(150);
        buttonReg.setMaxHeight(50);
        buttonShow.setMaxHeight(50);
        buttonUp.setMaxHeight(50);
        buttonReg.setOnAction(this);
        buttonShow.setOnAction(this);
        buttonUp.setOnAction(this);
        GridPane.setConstraints(buttonReg, 0, 2);
        GridPane.setConstraints(buttonShow, 1, 2);
        GridPane.setConstraints(buttonUp, 2, 2);
        GridPane.setConstraints(loginLabel, 0,0);
        GridPane.setConstraints(passwordLabel,1,0);
        GridPane.setConstraints(idLabel,2,0);
        GridPane.setConstraints(login, 0,1);
        GridPane.setConstraints(password,1,1);
        GridPane.setConstraints(id,2,1);
        GridPane.setHalignment(loginLabel, HPos.CENTER);
        GridPane.setHalignment(passwordLabel, HPos.CENTER);
        GridPane.setHalignment(idLabel, HPos.CENTER);

        layout.getChildren().add(buttonReg);
        layout.getChildren().add(buttonShow);
        layout.getChildren().add(buttonUp);
        layout.getChildren().add(loginLabel);
        layout.getChildren().add(passwordLabel);
        layout.getChildren().add(idLabel);
        layout.getChildren().add(login);
        layout.getChildren().add(password);
        layout.getChildren().add(id);

        primaryStage.setTitle("Selection");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == buttonReg){
            User user = new User(login.getText(), password.getText());
            try{
                userService.save(user);
            }catch (DomainException e){
                System.out.println(e.getCodeException().getDetails() + " (" + e.getCodeException().getCode() + " - " + e.getCodeException().getMessage() + ')');
                JOptionPane.showMessageDialog(null, e.getCodeException().getDetails());
            }
//            try {
//                Stage stage = new Stage();
//                stage.setTitle("My New Stage Title");
//                stage.setScene(new Scene(, 450, 450));
//                stage.show();
//                // Hide this current window (if this is what you want)
//                ((Node)(event.getSource())).getScene().getWindow().hide();
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        else if(event.getSource() == buttonShow){
            Set<User> users = userService.getAll();
            for(User user : users){
                System.out.println(user);
            }
        }
        else if(event.getSource() == buttonUp){
            //userService.update(Long.parseLong(id.getText()), login.getText(), password.getText());
        }
    }
}
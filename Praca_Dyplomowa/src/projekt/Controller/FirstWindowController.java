/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller;

import herudi.animations.FadeInLeftTransition;
import herudi.animations.FadeInRightTransition;
import herudi.animations.FadeInUpTransition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Andrzej Kierepka
 */
public class FirstWindowController implements Initializable {
    ObservableList<String > sexList = FXCollections.observableArrayList("Kobieta", "Mężczyzna");
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField weight;
    @FXML
    private TextField age;
    @FXML
    private ChoiceBox<String> sex;
    @FXML
    private Label toplbl;
    @FXML
    private Label lblname;
    @FXML
    private Label lblsurname;
    @FXML
    private Label lblweight;
    @FXML
    private Label lblage;
    @FXML
    private Label lblsex;
    @FXML
    private Button next;
    @FXML
    private Button close;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              Platform.runLater(() -> {
            new FadeInRightTransition(name).play();
            new FadeInRightTransition(surname).play();
            new FadeInRightTransition(weight).play();
            new FadeInRightTransition(age).play();
            new FadeInRightTransition(sex).play();
            new FadeInLeftTransition(lblname).play();
            new FadeInLeftTransition(lblsurname).play();
            new FadeInLeftTransition(lblweight).play();
            new FadeInLeftTransition(lblage).play();
            new FadeInLeftTransition(lblname).play();
            new FadeInLeftTransition(lblsex).play();
            new FadeInUpTransition(toplbl).play();
            new FadeInUpTransition(next).play();
            new FadeInUpTransition(close).play();
        });
           sex.setItems(sexList);
    }    

    @FXML
    private void nextWindow(ActionEvent event) {
        System.out.println(sex.getValue());
    }

    @FXML
    private void close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void mouseClosed(MouseEvent event) {
        Platform.exit();
    }
    
}

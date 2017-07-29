/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Andrzej Kierepka
 */
public class EarlyChAgeController implements Initializable {

    @FXML
    private Label question;
    @FXML
    private RadioButton answer1;
    @FXML
    private ToggleGroup tg;
    @FXML
    private RadioButton answer2;
    @FXML
    private Button diagnose;
    @FXML
    private Button close;
    private  int point ;
    @Override
        /**
     *
     * Inicjalizacja kontriolera
     *
     * @param url wskaźnik do "zasobu" w sieci World Wide Web
     * @param rb wersja językowa
     */
    public void initialize(URL url, ResourceBundle rb) {
        point =0;
    }    

    @FXML
    private void answer1Action(ActionEvent event) {
        point = 0;
    }

    @FXML
    private void answer2Action(ActionEvent event) {
        point = 1;
    }

    @FXML
    private void diagnoseAction(ActionEvent event) {
    }

    @FXML
    private void closeAction(ActionEvent event) {
    }
    
}

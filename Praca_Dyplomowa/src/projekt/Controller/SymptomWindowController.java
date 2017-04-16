/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import projekt.Class.Person;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SymptomWindowController implements Initializable {
    private Person person;
    private List<String> factor = new ArrayList<>();
    @FXML
    private ListView<?> factors;
    @FXML
    private Button add;
    @FXML
    private Button remove;
    @FXML
    private ListView<?> addedFactor;
    @FXML
    private Button next;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void undoClick(MouseEvent event) {
    }

    @FXML
    private void factorClicked(MouseEvent event) {
    }

    @FXML
    private void addFactor(ActionEvent event) {
    }

    @FXML
    private void removeFactor(ActionEvent event) {
    }

    @FXML
    private void addedFactorRemove(MouseEvent event) {
    }

    @FXML
    private void nextWindow(ActionEvent event) {
        System.out.println(person.getAge());
        System.out.println(factor.size());
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setFactor(List<String> factor) {
        this.factor = factor;
    }
    
}

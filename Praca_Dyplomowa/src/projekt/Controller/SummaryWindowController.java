/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SummaryWindowController implements Initializable {

    @FXML
    private ListView<String> symptoms;
    @FXML
    private Button next;
    @FXML
    private ListView<String> factors;

    private FactorWindowController fwc;
    private CancerInFamillyController cif;
    private SymptomWindowController sw;
    ObservableList<String> dataFactors = FXCollections.observableArrayList();
    ObservableList<String> dataSymptoms = FXCollections.observableArrayList();

    public SummaryWindowController() {
        fwc = new FactorWindowController();
        cif = new CancerInFamillyController();
        sw = new SymptomWindowController();
            }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       factors.setItems(dataFactors);
        symptoms.setItems(dataSymptoms);

    }

    @FXML
    private void undoClick(MouseEvent event) {
    }

    @FXML
    private void nextWindow(ActionEvent event) {
    }

    @FXML
    private void fullScreen(ActionEvent event) {
    }

    @FXML
    private void minimalizeSscreen(ActionEvent event) {
    }

    @FXML
    private void maximalizeSscreen(ActionEvent event) {
    }

    @FXML
    private void closeeSscreen(ActionEvent event) {
    }

    @FXML
    private void backToFactor(ActionEvent event) {
    }

    @FXML
    private void backToSummary(ActionEvent event) {
    }

    /**
     ** Metoda pozwala na ustawienie kontrolera w oknie 2
     *
     * @param sw
     */
    public void setSymptomWindowController(SymptomWindowController sw) {
        this.sw = sw;
    }

    public void setCancerInFamillyController(CancerInFamillyController cif) {
        this.cif = cif;
    }

    public void setFactorWindowController(FactorWindowController fwc) {
        this.fwc = fwc;
    }

}

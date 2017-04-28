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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import projekt.Class.CancerFamilly;
import projekt.Class.Person;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class CancerInFamillyController implements Initializable {
    private Person person;
    private List<String> factor;
    private List<String> symptoms;
    @FXML
    private TableView<CancerFamilly> table;
    private TableColumn cancer;
    private TableColumn familly;
    private ObservableList<CancerFamilly> data;
    private ObservableList<String> listCancer;
    private ObservableList<String> listFamilly;
    private ComboBox<String> famillyCancer;
    @FXML
    private ComboBox<String> cancerCombo;
    @FXML
    private ComboBox<String> famillyCombo;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            data =FXCollections.observableArrayList();
            listCancer =FXCollections.observableArrayList();
            listFamilly =FXCollections.observableArrayList("Brat","Siostra");
            cancer = new TableColumn("Rak w rodzinie");
            cancer.setMinWidth(266);
            cancer.setCellValueFactory(
            new PropertyValueFactory<>("cancer"));
            familly = new TableColumn("Rodzina ");
            familly.setMinWidth(266);
            familly.setCellValueFactory(new PropertyValueFactory<>("familly"));
            table.setItems(data);
            table.getColumns().addAll(cancer, familly);
            factor = new ArrayList<>();
            symptoms= new ArrayList<>();
            famillyCombo.setItems(listFamilly);
            cancerCombo.setItems(listCancer);
    }    

    @FXML
    private void undoClick(MouseEvent event) {
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setFactor(List<String> factor) {
        this.factor = factor;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    public Person getPerson() {
        return person;
    }

    public List<String> getFactor() {
        return factor;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    @FXML
    private void addToTable(ActionEvent event) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projekt.Class.CancerFamilly;
import projekt.Class.Person;

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
    ObservableList<CancerFamilly> cancerFamilly;
    private Person person;
    ObservableList<String> dataFactors = FXCollections.observableArrayList();
    ObservableList<String> dataSymptoms = FXCollections.observableArrayList();
    @FXML
    private TableView<CancerFamilly> famillyCancer;
    private TableColumn cancer;
    private TableColumn familly;

    public SummaryWindowController() {

        cancerFamilly = FXCollections.observableArrayList();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        factors.setItems(dataFactors);
        symptoms.setItems(dataSymptoms);
        cancer = new TableColumn("Rak w rodzinie");
        cancer.setMinWidth(320);
        cancer.setCellValueFactory(
                new PropertyValueFactory<>("cancer"));
        familly = new TableColumn("Rodzina ");
        familly.setMinWidth(320);
        familly.setCellValueFactory(new PropertyValueFactory<>("familly"));
        famillyCancer.getColumns().addAll(cancer, familly);
        famillyCancer.setItems(cancerFamilly);
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
    private void backToFactor(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/FactorWindow.fxml"));
        FactorWindowController cnt = new FactorWindowController();
        Parent parent = load.load();
        cnt=load.getController();
        for (int i = 0; i <dataFactors.size() ; i++) {
            cnt.changeFactToRight(dataFactors.get(i));
        }
        cnt.setPerson(person);
        SymptomWindowController sw= new SymptomWindowController();
        sw.dataRight=dataSymptoms;
        cnt.setSymptomWindowController(sw);
        CancerInFamillyController cif= new CancerInFamillyController();
        for (int i = 0; i <cancerFamilly.size() ; i++) {
         cif.addCancer(cancerFamilly.get(i));
        }
        cnt.setCancerInFamillyController(cif);
        Scene scene = new Scene(parent);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        Stage stage;
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void backToSummary(ActionEvent event) {
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}

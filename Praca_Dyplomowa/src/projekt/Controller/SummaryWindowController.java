/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projekt.Class.CancerFamilly;
import projekt.Class.Person;

/**
 * FXML Controller class
 *
 * @author Andrzej Kierepka
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
    Stage stage;
    Rectangle2D rec2;
    Double w, h;
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
        rec2 = Screen.getPrimary().getVisualBounds();
        w = 0.1;
        h = 0.1;
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
    private void undoClick(MouseEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/CancerInFamilly.fxml"));
        CancerInFamillyController cnt = new CancerInFamillyController();
        Parent parent = load.load();
        cnt=load.getController();
        cnt.setFactor(dataFactors);
        cnt.setPerson(person);
        cnt.setSymptoms(dataSymptoms);
        for (int i = 0; i <cancerFamilly.size() ; i++) {
         cnt.addCancer(cancerFamilly.get(i));
        }
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
    private void nextWindow(ActionEvent event) {
    }

 @FXML
    private void fullScreen(ActionEvent event) {
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        } else {
            stage.setFullScreen(true);
        }
    }

    @FXML
    private void minimalizeSscreen(ActionEvent event) {
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        if (stage.isMaximized()) {
            w = rec2.getWidth();
            h = rec2.getHeight();
            stage.setMaximized(false);
            stage.setHeight(h);
            stage.setWidth(w);
            stage.centerOnScreen();
            Platform.runLater(() -> {
                stage.setIconified(true);
            });
        } else {
            stage.setIconified(true);
        }
    }

    @FXML
    private void maximalizeSscreen(ActionEvent event) {
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        if (stage.isMaximized()) {
            if (w == rec2.getWidth() && h == rec2.getHeight()) {
                stage.setMaximized(false);
                stage.setHeight(600);
                stage.setWidth(800);
                stage.centerOnScreen();
            } else {
                stage.setMaximized(false);

            }

        } else {
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
        }
    }

    @FXML
    private void closeeSscreen(ActionEvent event) {
        Platform.exit();
        System.exit(0);
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
    private void backToSummary(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/SymptomWindow.fxml"));
        SymptomWindowController cnt = new SymptomWindowController();
        Parent parent = load.load();
        cnt=load.getController();
        cnt.setFactor(dataFactors);
        cnt.setPerson(person);
        for (int i = 0; i <dataSymptoms.size() ; i++) {
         cnt.changeSymptomToRight(dataSymptoms.get(i));
        }
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

    public void setPerson(Person person) {
        this.person = person;
    }
    /**
     ** wyświetla KOMUNIKAT O BŁĘDZIE
     *
     * @param message treść komunikatu o błędzie
     */
    public void showOutputMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Treść błędu");
        alert.setContentText(message);
        alert.showAndWait();
    }
}

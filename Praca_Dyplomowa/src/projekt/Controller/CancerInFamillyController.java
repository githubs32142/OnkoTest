/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.sun.javafx.collections.ElementObservableListDecorator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projekt.Class.CancerFamilly;
import projekt.Class.Person;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class CancerInFamillyController implements Initializable {

    Stage stage;
    Rectangle2D rec2;
    Double w, h;
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
    private int index;
    @FXML
    private ComboBox<String> cancerCombo;
    @FXML
    private ComboBox<String> famillyCombo;
    @FXML
    private MenuItem delete;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private VBox box;
    @FXML
    private JFXHamburger hamburger;

    public CancerInFamillyController() {
        data = FXCollections.observableArrayList();
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
        
        listCancer = FXCollections.observableArrayList("Rak płuc", "Rak jelita grubrgo", "Rak piersi", "Rak jąder", "Rak gruczołu krokowego", "Guz mózgu", "Rak szyjki macicy", "Rak płuc", "Rak trzustki", "Rak żołądka", "Rak macicy", "Rak krtani");
        listFamilly = FXCollections.observableArrayList("Brat", "Siostra", "Ojciec", "Matka", "Dziadek", "Babcia", "Wujek", "Ciotka");
        cancer = new TableColumn("Rak w rodzinie");
        cancer.setMinWidth(280);
        cancer.setCellValueFactory(
                new PropertyValueFactory<>("cancer"));
        familly = new TableColumn("Rodzina ");
        familly.setMinWidth(280);
        familly.setCellValueFactory(new PropertyValueFactory<>("familly"));
        table.getColumns().addAll(cancer, familly);
        factor = new ArrayList<>();
        symptoms = new ArrayList<>();
        famillyCombo.setItems(listFamilly);
        cancerCombo.setItems(listCancer);
        index = -1;
        data.add(new CancerFamilly("", ""));
        table.setItems(data);
        drawer.setSidePane(box);
        drawer.close();
        HamburgerBasicCloseTransition transition = new HamburgerBasicCloseTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                transition.setRate(transition.getRate()*-1);
                transition.play();
                
                if(drawer.isShown())
                {
                    drawer.close();
                }else
                    drawer.open();
                    drawer.setPrefWidth(150);
            }
        });

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
        if (cancerCombo.getSelectionModel().getSelectedIndex() >= 0 && famillyCombo.getSelectionModel().getSelectedIndex() >= 0) {
            
            if(data.isEmpty()){
                data.add(new CancerFamilly(cancerCombo.getSelectionModel().getSelectedItem(), famillyCombo.getSelectionModel().getSelectedItem()));
            }
            else{
                if (data.get(0).getCancer().isEmpty()) {
                data.set(0, new CancerFamilly(cancerCombo.getSelectionModel().getSelectedItem(), famillyCombo.getSelectionModel().getSelectedItem()));
            } else {
                data.add(new CancerFamilly(cancerCombo.getSelectionModel().getSelectedItem(), famillyCombo.getSelectionModel().getSelectedItem()));
            }
            }
            
            table.setItems(data);
        } else {
            showOutputMessage("Nie można wprowadzić danych");
        }

    }

    @FXML
    private void deleteCancerInFamilly(ActionEvent event) {
        makeDelete();
    }

    @FXML
    private void tableClicked(MouseEvent event) {

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

    @FXML
    private void removeObject(ActionEvent event) {
        makeDelete();
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

    /**
     ** Metoda która dodaje rak w rodzienie do listy
     *
     * @param cf rak w rodzinie
     */
    public void addCancer(CancerFamilly cf) {
        data.add(cf);
    }

    /**
     ** Metoda, która wyświetla ilość elementów raka w rodzinie
     *
     * @return ilość elementów raka w rodzinie
     */
    public int getSizeCancerInFamilly() {
        return data.size();
    }

    /**
     ** Metoda, która wyświetla rak w rodzinie o podanym indeksie
     *
     * @return rak w rodzinei
     */
    public CancerFamilly getCancerInFamilly(int i) {
        return data.get(i);
    }

    /**
     ** Metoda, która ustawia rak w rodzinie o podanym indeksie
     *
     * @param i index na liście
     * @param cf rak w rodzinei
     */
    public void setCancerInFamilly(int i, CancerFamilly cf) {
        data.set(i, cf);
    }
    /**
     ** Metoda usuwa wiersz z tabeli 
     */
    private void makeDelete(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("OnkoTest");
        alert.setHeaderText("Zawartość poniższego komunikatu:");
        alert.setContentText("Czy na pewno chcesz usunąć zaznaczony rak w rodzinie?");
        ButtonType buttonTypeYes = new ButtonType("Tak");
        ButtonType buttonTypeNo = new ButtonType("Nie");
        alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            index = table.getSelectionModel().getSelectedIndex();
            if (index >= 0 && index < data.size()) {
                data.remove(index);
            } else {
                showOutputMessage("Nie zaznaczyłeś żadnego wiersza");
            }
        }
    }

    @FXML
    private void nextWindow(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/SummaryWindow.fxml"));
         SummaryWindowController cnt= new SummaryWindowController();   
         Parent parent= load.load();
         cnt=load.getController();
         for(int i=0;i<factor.size();i++){
             cnt.dataFactors.add(factor.get(i));
         }
         for(int i=0;i<symptoms.size();i++){
             cnt.dataSymptoms.add(symptoms.get(i));
         }
         for(int i=0;i<this.data.size();i++){
             cnt.cancerFamilly.add(this.data.get(i));
         }
         cnt.setPerson(person);
         Scene scene = new Scene(parent);
         Stage primaryStage = new Stage();
         primaryStage.setScene(scene);
         primaryStage.initStyle(StageStyle.UNDECORATED);
         primaryStage.show();
         stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
         stage.close();
    }

    @FXML
    private void undoClick(ActionEvent event) {
        try {
            FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/SymptomWindow.fxml"));
            SymptomWindowController cnt = new SymptomWindowController();
            Parent parent = load.load();
            cnt = load.getController();
            for (int i = 0; i < symptoms.size(); i++) {
                cnt.changeSymptomToRight(symptoms.get(i));
            }
            cnt.setFactor(factor);
            cnt.setPerson(person);
            cnt.setCancerInFamillyController(this);
            Scene scene = new Scene(parent);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
            stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    
}

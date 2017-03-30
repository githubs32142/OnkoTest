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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projekt.Class.Factor;
import projekt.Class.Person;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FactorWindowController implements Initializable {
    
    public Person person= new Person();
    WebEngine webEngine ;
    List <Factor> fact= new ArrayList<>();
    ObservableList<String> data = FXCollections.observableArrayList(
            "chocolate", "salmon", "gold", "coral", "darkorchid",
            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
            "blueviolet", "brown");
    @FXML
    private ListView<String> factors;
    @FXML
    private WebView webView;
    public FactorWindowController(Person person) {
        this.person=person;
    }    

    public FactorWindowController() {
       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fact.add(new Factor("Alkoholizm", "/projekt/HTML/alkoholizm.html", true));
        webEngine = webView.getEngine();
        final URL urlGoogleMaps = getClass().getResource("/projekt/HTML/alkoholizm.html");
        webEngine.load(urlGoogleMaps.toExternalForm());
        factors.setItems(data);
    }    
    /**
     ** Metoda która powoduje, że wchodzimy do poprzedniego okna okna głównego 
     * @param event 
     */
    @FXML
    private void undoClick(MouseEvent event) {
        try{

                FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/FirstWindow.fxml"));
                FirstWindowController cnt= new FirstWindowController();   
                Parent parent= load.load();
                cnt=load.getController();
                cnt.setPerson(person);
                Scene scene = new Scene(parent);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);          
                primaryStage.initStyle(StageStyle.UNDECORATED);
                primaryStage.setResizable(false);
                primaryStage.show();
                Stage stage;
                stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
                stage.close();
            }
            catch(Exception e){
                 Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
    }
    /**
     ** Metoda która ustawia dane osoby  
     * @param person 
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    @FXML
    private void factorClicked(MouseEvent event) {
        System.out.println(factors.getItems().get(factors.getSelectionModel().getSelectedIndex()));
    }
    
}

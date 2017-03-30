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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projekt.Class.Person;

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
    @FXML
    private TextField height;
    @FXML
    private Label lbllheight;
    
    /**
     * Initializes the controller class.
     */
    
    public FirstWindowController() {
    } 
    
    public FirstWindowController(Person p) {
        name.setText(p.getName());
        surname.setText(p.getSurName());
        weight.setText(String.valueOf(p.getWeight()));
        height.setText(String.valueOf(p.getHeight()));
        age.setText(String.valueOf(p.getAge()));
        
    } 
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
        if(!name.getText().isEmpty() && !surname.getText().isEmpty() ){
            try{
                Double w=Double.valueOf(weight.getText());
                Double h=Double.valueOf(height.getText());
                Double a=Double.valueOf(age.getText());
                Person p = new Person(name.getText(), surname.getText(), h, a, sex.getValue(), h);
                FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/FactorWindow.fxml"));
                FactorWindowController cnt= new FactorWindowController(p);   
                Parent parent= load.load();
                cnt=load.getController();
                cnt.setPerson(p);
                Scene scene = new Scene(parent);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
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
    }

    @FXML
    private void close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void mouseClosed(MouseEvent event) {
        Platform.exit();
    }
    public void setPerson(Person p){
        name.setText(p.getName());
        surname.setText(p.getSurName());
        weight.setText(String.valueOf(p.getWeight()));
        height.setText(String.valueOf(p.getHeight()));
        age.setText(String.valueOf(p.getAge()));
    }
}

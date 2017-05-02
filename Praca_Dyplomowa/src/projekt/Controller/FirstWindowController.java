/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller;

import herudi.animations.FadeInLeftTransition;
import herudi.animations.FadeInRightTransition;
import herudi.animations.FadeInUpTransition;
import herudi.animations.FadeOutUpTransition;
import java.io.IOException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projekt.Class.CheckReg;
import projekt.Class.ListFibre;
import projekt.Class.Person;
import projekt.Class.Product;

/**
 * FXML Controller class
 *
 * @author Andrzej Kierepka
 */
public class FirstWindowController implements Initializable {
    ObservableList<String > sexList = FXCollections.observableArrayList("Kobieta", "Mężczyzna");
    private boolean mail=false;
    @FXML
    public TextField name;
    @FXML
    public TextField surname;
    @FXML
    private TextField weight;
    @FXML
    private TextField age;
    @FXML
    private ChoiceBox<String> sex;
    @FXML
    public Label toplbl;
    @FXML
    public Label lblname;
    @FXML
    public Label lblsurname;
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
    @FXML
    private CheckBox eMail;
    
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
            new FadeInLeftTransition(lbllheight).play();
            new FadeInUpTransition(toplbl).play();
            new FadeInUpTransition(next).play();
            new FadeInUpTransition(close).play();
            new FadeInUpTransition(height).play();
            new FadeInUpTransition(eMail).play();
        });
        sex.setItems(sexList);
        sex.getSelectionModel().selectFirst();
    }    

    @FXML
    private void nextWindow(ActionEvent event) throws IOException {
        System.out.println(CheckReg.checkHeightMetr(weight.getText()));
        if(mail){
            if(!surname.getText().isEmpty() && !sex.getValue().isEmpty() ){
                try{
                Double w=Double.valueOf(weight.getText());
                Double h=Double.valueOf(height.getText());
                Double a=Double.valueOf(age.getText());
                Person p = new Person(surname.getText(), w, a, sex.getValue(), h);
                if(CheckReg.checkEmail(surname.getText())){
                FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/FactorWindow.fxml"));
                FactorWindowController cnt= new FactorWindowController(p);   
                Parent parent= load.load();
                cnt=load.getController();
                cnt.setPerson(p);
                if(p.getBmi()>25){
                   cnt.changeFactToRight("Otyłość"); 
                }    
                Scene scene = new Scene(parent);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.show();
                Stage stage;
                stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
                stage.close();
                }
                else{   
                showOutputMessage("Wprowadzony adres e-mail jest nieprawidłowy");
                }
                }
                catch(IOException | NumberFormatException e){
                    showOutputMessage(e.toString());
                }
            }
            else{
                showOutputMessage("Wypełnij odpowiednie pola");
            }
        }
        else{
            if(!name.getText().isEmpty() && !surname.getText().isEmpty() && !sex.getValue().isEmpty()  ){
            try{
                if(CheckReg.checkWord(name.getText()) && CheckReg.checkWord(surname.getText()) ){
                Double w=Double.valueOf(weight.getText());
                Double h=Double.valueOf(height.getText());
                Double a=Double.valueOf(age.getText());
                Person p = new Person(name.getText(), surname.getText(),w, a, sex.getValue(), h);
                FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/FactorWindow.fxml"));
                FactorWindowController cnt= new FactorWindowController(p);   
                Parent parent= load.load();
                cnt=load.getController();
                cnt.setPerson(p);
                if(p.getBmi()>25){
                   cnt.changeFactToRight("Otyłość"); 
                }
                Scene scene = new Scene(parent);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.show();
                Stage stage;
                stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
                stage.close();    
                }
            }
            catch(IOException | NumberFormatException e){
                showOutputMessage(e.toString());
            }
        }
        else{
               showOutputMessage("Wypełnij odpowiednie pola");
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

    @FXML
    private void eMailClicked(ActionEvent event) {
    mail=!mail;
    if(mail){
        lblname.setVisible(false);
        name.setVisible(false);
        lblsurname.setText("E-mail:");
        toplbl.setLayoutX(186);
        toplbl.setLayoutY(50);
    }
    else{
        toplbl.setLayoutY(10);
        lblname.setVisible(true);
        name.setVisible(true);
        lblsurname.setText("Nazwisko:");
    }
    }

    public void setMail(boolean mail) {
        this.mail = mail;
       
            eMail.setSelected(mail);
        
    }

    public CheckBox geteMail() {
        return eMail;
    }
        /**
     ** wyświetla okno dialogowe 
     * @param message wiadomość
     */
    private void showOutputMessage(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Powiadomienie");
        alert.setHeaderText("Treść komunikatu:");
        alert.setContentText(message);
        alert.showAndWait();
    }
    
}

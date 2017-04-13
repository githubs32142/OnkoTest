/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller;

import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import jess.JessException;
import jess.Rete;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class RadiotherapyTestController implements Initializable {
    private FactorWindowController window;
    private boolean end=false;// kiedy klikneliśmy na koniec(wystaw diagnozę)
    @FXML
    private Label question;
    @FXML
    private RadioButton answer1;
    @FXML
    private ToggleGroup tg;
    @FXML
    private RadioButton answer2;
    @FXML
    private Button close;
    List<Integer> pList= new ArrayList<>();// ilość punktów przyznanych za każdą odpowiedź
    int point ;
    @FXML
    private Button diagnose;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        point = 0 ; 
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
    private void closeAction(ActionEvent event) {
        Stage stage;
        stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
        stage.close();
        
    }
    @FXML
    private void diagnoseAction(ActionEvent event) {
        makeDiagnostic(toString());
    }
    /**
     ** Metoda ktora zwraca w postaci ciągu znaków wyrażenie które będzie potrzebne wykonania wniskowania 
     * @return 
     * @rerurn wyrażenie potrzebne do wykonania wniskowania
     */
    @Override
    public String toString(){
        StringBuilder tmp= new StringBuilder("( assert ( Point");
        tmp.append("( answer1").append(" ").append(point).append(") ) )");
        return tmp.toString();
    }
    /**
     ** wyświetla wyniki diagnozy  
     * @param message rezultat diagnozy
     */
    private void showOutputMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wynik diagnozy");
        alert.setHeaderText("Otrzymane rezulataty");
        alert.setContentText(message);
        alert.showAndWait();
    }
    /**
     ** Wykonyje diagnoze,wyświetla dignoze i czyści wartości zmiennych.
     */
    private void makeAllDiagnose(){
            makeDiagnostic(toString());

    }
        public void makeDiagnostic(String s){
            boolean add=false;
            StringBuilder text= new StringBuilder();
        try {
            Rete engine = new Rete();
            engine.reset();
            StringWriter o = new StringWriter();
            engine.addOutputRouter("t", o);
            String result = new String();
            // Load the pricing rules
            engine.batch("projekt/JESS/radioterapy.clp");
            engine.eval(s);
            engine.run();
            result = o.toString();
            engine.clear();
            if (result == null ? "" == null : result.equals("")) {
                result = "Brak diagnozy";
            }
            for(int i=0;i<result.length();i++){
                if(result.charAt(i)=='1'){
                    add=true;
                }
                else{
                    text.append(result.charAt(i));
                }
            }
            if(add){
                window.changeFactToRight("Radioterapia");
            }
            showOutputMessage(text.toString());

        } catch (JessException ex) {
            Logger.getLogger(SmokingTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    public void setWindow(FactorWindowController window) {
        this.window = window;
    }



}

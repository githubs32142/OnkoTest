/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller;

import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javax.swing.JOptionPane;
import jess.JessException;
import jess.Rete;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AuditTest implements Initializable {

    @FXML
    private ToggleGroup pyt1;
    @FXML
    private ToggleGroup pyt2;
    @FXML
    private ToggleGroup pyt3;
    public int one1=0,one2=0,one3=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void zeroPoint(ActionEvent event) {
        one1=0;
    }

    @FXML
    private void OnePoint(ActionEvent event) {
        one1=1;
    }

    @FXML
    private void TwouPoint(ActionEvent event) {
        one1=2;
    }

    @FXML
    private void ThreePoint(ActionEvent event) {
        one1=3;
    }

    @FXML
    private void FourPoint(ActionEvent event) {
        one1=4;
    }

    @FXML
    private void Zero2Point(ActionEvent event) {
        one2=0;
    }

    @FXML
    private void One2Point(ActionEvent event) {
        one2=1;
    }

    @FXML
    private void Two2Point(ActionEvent event) {
        one2=2;
    }

    @FXML
    private void Three2Point(ActionEvent event) {
        one2=3;
    }

    @FXML
    private void Four2Point(ActionEvent event) {
        one2=4;
    }

    @FXML
    private void Zero3Point(ActionEvent event) {
        one3=0;
    }

    @FXML
    private void One3Points(ActionEvent event) {
        one3=1;
    }

    @FXML
    private void Two3Point(ActionEvent event) {
        one3=2;
    }

    @FXML
    private void Three3Point(ActionEvent event) {
        one3=3;
    }

    @FXML
    private void Four3Points(ActionEvent event) {
        one3=4;
    }

    @FXML
    private void wykonaj(ActionEvent event) throws JessException {
        int suma = one1+one2+one3;
        String s= new String("( assert ( Punkty ( pkt "+suma+") ))");
        Rete engine = new Rete();
        engine.reset();
        StringWriter o = new StringWriter();
        engine.addOutputRouter("t", o);
        String result = "";
        // Load the pricing rules
        engine.batch("projekt/JESS/audit.clp");
        engine.eval(s);
        engine.run();
        result = o.toString();
        engine.clear();
        if (result == null ? "" == null : result.equals("")) {
            result = "Brak diagnozy";
        }
        JOptionPane.showMessageDialog(null, result);
    }
    
}

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import projekt.Class.ListFibre;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FibreTest implements Initializable {
    ListFibre listFibre;
    ObservableList<String> data = FXCollections.observableArrayList();
    @FXML
    private ListView<String> factors;
    @FXML
    private ListView<String> addedFactor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listFibre= new  ListFibre();
        listFibre.readData("src/projekt/Data/fibre.txt");
        for(int i=0;i<listFibre.size();i++){
            data.add(listFibre.getFibre(i).toString());
        }
        factors.setItems(data);
    }    

    @FXML
    private void factorClicked(MouseEvent event) {
        
    }

    @FXML
    private void addedFactorRemove(MouseEvent event) {
    }
    
}

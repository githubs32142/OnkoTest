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
import javafx.scene.control.TextField;
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
    private ListView<String> product;
    @FXML
    private ListView<?> addedProduct;
    @FXML
    private TextField weight;
    @FXML
    private TextField productName;

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
        product.setItems(data);
    }    

    @FXML
    private void productClicked(MouseEvent event) {
        int index = product.getSelectionModel().getSelectedIndex();
        if(index>=0){
            weight.setText(String.valueOf(listFibre.getWeight(index)));
            productName.setText(listFibre.getNameProduct(index));
        }
    }
    @FXML
    private void addedFactor(MouseEvent event) {
    }
    
}

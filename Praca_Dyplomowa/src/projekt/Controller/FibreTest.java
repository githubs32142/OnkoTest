/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller;

import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import jess.JessException;
import jess.Rete;
import projekt.Class.ListFibre;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FibreTest implements Initializable {
    int index;
    ListFibre listFibre;
    ListFibre addFibre;
    ObservableList<String> data = FXCollections.observableArrayList();
    ObservableList<String> addData = FXCollections.observableArrayList();
    ObservableList<String> optionSort =  FXCollections.observableArrayList();
    @FXML
    private ListView<String> product;
    @FXML
    private ListView<String> addedProduct;
    @FXML
    private TextField weight;
    @FXML
    private TextField productName;
    @FXML
    private ComboBox<String> sort;
    @FXML
    private Button removeButton;
    @FXML
    private Label sum;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listFibre= new  ListFibre();
        addFibre= new ListFibre();
        listFibre.readData("src/projekt/Data/fibre2.txt");
        for(int i=0;i<listFibre.size();i++){
            data.add(listFibre.getFibre(i).toString());
        }
        product.setItems(data);
        optionSort.add("Według nazw rosnąco");
        optionSort.add("Według nazw malejąco");
        optionSort.add("Według wartości błonnika rosnąco");
        optionSort.add("Według wartości błonnika malejąco");
        sort.setItems(optionSort);
        removeButton.setVisible(false);
    }    

    @FXML
    private void productClicked(MouseEvent event) {
        index = product.getSelectionModel().getSelectedIndex();
        if(index>=0){
            weight.setText(String.valueOf(listFibre.getWeight(index)));
            productName.setText(listFibre.getNameProduct(index));
        }
        removeButton.setVisible(false);
    }

    @FXML
    private void add(ActionEvent event) {
        index= listFibre.searchProduct(productName.getText());
        addFibre.addFibre(listFibre.getFibre(index));
        addFibre.setWeight(addFibre.size()-1, Double.parseDouble(weight.getText()));
        addData.add(addFibre.getFibre(addFibre.size()-1).toString());
        addedProduct.setItems(addData);
        sum.setText("Suma: "+addFibre.getSumFibre());
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    @FXML
    private void sortProduct(ActionEvent event) {
        listFibre.sort(sort.getSelectionModel().getSelectedIndex()+1);
        data.clear();
        for(int i=0;i<listFibre.size();i++){
            data.add(listFibre.getFibre(i).toString());
        }
        product.setItems(data);
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    private void makeTest(ActionEvent event) {
        makeDiagnostic(toString());
    }
    /**
     ** wyświetla wyniki diagnozy  
     * @param message rezultat diagnozy
     */
    public void showOutputMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wynik diagnozy");
        alert.setHeaderText("Otrzymane rezulataty");
        alert.setContentText(message);
        alert.showAndWait();
    }
        /**
     ** Metoda ktora zwraca w postaci ciągu znaków wyrażenie które będzie potrzebne wykonania wniskowania 
     * @rerurn wyrażenie potrzebne do wykonania wniskowania
     */
    @Override
    public String toString(){
        StringBuilder tmp= new StringBuilder("( assert ( Point ( sum ");
        tmp.append(addFibre.getSumFibre()).append(" ) ) )");
        return tmp.toString();
    }
        public void makeDiagnostic(String s){
        boolean add=false;
        StringBuilder text= new StringBuilder();
        try {
            Rete engine = new Rete();
            engine.reset();
            StringWriter o = new StringWriter();
            engine.addOutputRouter("t", o);
            String result;
            engine.batch("projekt/JESS/fibre.clp");
            engine.eval(s);
            engine.run();
            result = o.toString();
            engine.clear();
            System.out.println(result);
            if (result == null ? "" == null : result.equals("")) {
                result = "Brak diagnozy";
            }
                for(int i=0;i<result.length();i++){
                if(result.charAt(i)==10){
                        text.append("\n");
                    }
                if(result.charAt(i)=='1'){
                    add=true;
                }
                else{
                    text.append(result.charAt(i));
                }
                }
                if(add){
                //window.changeFactToRight("Spożywanie alkoholu");
                }
            showOutputMessage(text.toString());

        } catch (JessException ex) {
            Logger.getLogger(FibreTest.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }

    @FXML
    private void removeFibre(ActionEvent event) {
        if(index<addData.size()){
            addData.remove(index);
            addFibre.remove(index);
            addedProduct.setItems(addData);
            sum.setText("Suma: "+addFibre.getSumFibre());
        }
        
    }
    
    @FXML
    private void addedProductClicked(MouseEvent event) {
        removeButton.setVisible(true);
        index= addedProduct.getSelectionModel().getSelectedIndex();
    }
}

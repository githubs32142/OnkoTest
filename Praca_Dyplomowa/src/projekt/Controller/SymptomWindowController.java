/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ListView;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projekt.Class.Person;
import projekt.Class.ProductFibre;
/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SymptomWindowController implements Initializable {
    private Person person;
    private List<String> factor = new ArrayList<>();
    ObservableList<String> data = FXCollections.observableArrayList();
    ObservableList<String> dataRight = FXCollections.observableArrayList();
    @FXML
    private Button add;
    @FXML
    private Button remove;
    @FXML
    private Button next;
    @FXML
    private ListView<String> symptoms;
    @FXML
    private ListView<String> addedSymptoms;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        readData("src/projekt/Data/symptoms.txt");
        symptoms.setItems(data);
    }    

    @FXML
    private void undoClick(MouseEvent event) {
        try{

                FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/FactorWindow.fxml"));
                FactorWindowController cnt= new FactorWindowController();   
                Parent parent= load.load();
                cnt=load.getController();
                for(int i=0;i<factor.size();i++){
                    cnt.changeFactToRight(factor.get(i));
                }
                cnt.setPerson(person);
                Scene scene = new Scene(parent);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);          
                primaryStage.setResizable(false);
                primaryStage.show();
                Stage stage;
                stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
                stage.close();
            }
            catch(IOException e){
                 Logger logger = Logger.getLogger(getClass().getName());
                 logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
    }

    @FXML
    private void addFactor(ActionEvent event) {
    }

    @FXML
    private void removeFactor(ActionEvent event) {
    }


    @FXML
    private void nextWindow(ActionEvent event) throws IOException {
         FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/CancerInFamilly.fxml"));
         CancerInFamillyController cnt= new CancerInFamillyController();   
         Parent parent= load.load();
         cnt=load.getController();
         cnt.setPerson(person);
         cnt.setFactor(factor);
         cnt.setSymptoms(dataRight);
         Scene scene = new Scene(parent);
         Stage primaryStage = new Stage();
         primaryStage.setScene(scene);
         primaryStage.show();
         Stage stage;
         stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
         stage.close();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setFactor(List<String> factor) {
        this.factor = factor;
    }

    @FXML
    private void symptomsClicked(MouseEvent event) {
    }

    @FXML
    private void addedSymptomsClicked(MouseEvent event) {
    }
    
    static String readInput(String path) {
    StringBuilder buffer = new StringBuilder();
    try {
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis,"UTF8");
        try (Reader in = new BufferedReader(isr)) {
            int ch;
            while ((ch = in.read()) > -1) {
                buffer.append((char)ch);
            }
        }
        return buffer.toString();
    } 
    catch (IOException e) {
        e.printStackTrace();
        return null;
    }
    }

    public void readData(String path){
        String line= readInput(path);
        StringTokenizer st = new StringTokenizer(line, ",");
        while(st.hasMoreElements()){
            data.add(st.nextElement().toString());
        }
    }

    @FXML
    private void symptomsDragEntered(DragEvent event) {
        symptoms.setBlendMode(BlendMode.SRC_ATOP);
    }

    @FXML
    private void symptomsDragDetected(MouseEvent event) {
      Dragboard dragBoard = symptoms.startDragAndDrop(TransferMode.MOVE);
      ClipboardContent content = new ClipboardContent();
      content.putString(String.valueOf(symptoms.getSelectionModel().getSelectedIndex()));
      dragBoard.setContent(content); 
    }

    @FXML
    private void symptomsDragExited(DragEvent event) {
        symptoms.setBlendMode(null);
    }

    @FXML
    private void symptomsDragOver(DragEvent event) {
         event.acceptTransferModes(TransferMode.MOVE); 
    }


    @FXML
    private void symptomsDragDropped(DragEvent event) {
        int tmp = Integer.parseInt(event.getDragboard().getString());
        String aadd= addedSymptoms.getItems().get(tmp);
        dataRight.remove(tmp);
        data.add(aadd);
        symptoms.setItems(data);
        addedSymptoms.setItems(dataRight);
        
    }

    @FXML
    private void addedSymptomsDragDropped(DragEvent event) {
        int tmp = Integer.parseInt(event.getDragboard().getString());
        String aadd= symptoms.getItems().get(tmp);
        data.remove(tmp);
        dataRight.add(aadd);
        symptoms.setItems(data);
        addedSymptoms.setItems(dataRight);        
    }

    @FXML
    private void addedSymptomsDragDetected(MouseEvent event) {
      Dragboard dragBoard = addedSymptoms.startDragAndDrop(TransferMode.MOVE);
      ClipboardContent content = new ClipboardContent();
      content.putString(String.valueOf(addedSymptoms.getSelectionModel().getSelectedIndex()));
      dragBoard.setContent(content); 
    }

    @FXML
    private void addedSymptomsDragExited(DragEvent event) {
        addedSymptoms.setBlendMode(null);
    }

    @FXML
    private void addedSymptomsDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);  
    }

    @FXML
    private void addedSymptomsDragEntered(DragEvent event) {
        addedSymptoms.setBlendMode(BlendMode.SRC_ATOP);
    }
}

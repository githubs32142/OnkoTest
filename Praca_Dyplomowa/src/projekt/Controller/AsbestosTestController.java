/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AsbestosTestController implements Initializable {

    @FXML
    private ListView<String> job;
    @FXML
    private ListView<String> addedJob;
    @FXML
    private Button test;
    ObservableList<String> data = FXCollections.observableArrayList(
    "Mechanik samochodowy","Pracownik wykonywania kotłów","Stolarz","Montarz murów suchych","Elektryk","Hutnik"
    ,"Operator maszyn","Tokarz","Specjalista budowy młynów","Monter rur","Pracownik elektrowni","Kolejarz","Stoczniowiec");
    ObservableList<String> dataRight = FXCollections.observableArrayList();
    Stage stage;
    Rectangle2D rec2;
    Double w,h;
    /**
     * 
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rec2 = Screen.getPrimary().getVisualBounds(); 
        w = 0.1;
        h = 0.1;
        job.setItems(data);
    }    

    @FXML
    private void undoClick(MouseEvent event) {
    }

    private void factorsDragEntered(DragEvent event) {
        job.setBlendMode(BlendMode.SRC_ATOP);
    }

    @FXML
    private void factorClicked(MouseEvent event) {
    }

    @FXML
    private void addedFactorRemove(MouseEvent event) {
        
    }

    @FXML
    private void addedDragDetected(MouseEvent event) {
       Dragboard dragBoard = addedJob.startDragAndDrop(TransferMode.MOVE);
      ClipboardContent content = new ClipboardContent();
      content.putString(String.valueOf(addedJob.getSelectionModel().getSelectedIndex()));
      dragBoard.setContent(content); 
    }

    @FXML
    private void makeTest(ActionEvent event) {
    }

   @FXML
    private void fullScreen(ActionEvent event) {
         stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        }else{
            stage.setFullScreen(true);
        }
    }

    @FXML
    private void minimalizeSscreen(ActionEvent event) {
                stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
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
        }else{
            stage.setIconified(true);
        }    
    }

    @FXML
    private void maximalizeSscreen(ActionEvent event) {
        stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
        if (stage.isMaximized()) {
            if (w == rec2.getWidth() && h == rec2.getHeight()) {
                stage.setMaximized(false);
                stage.setHeight(600);
                stage.setWidth(800);
                stage.centerOnScreen();
            }else{
                stage.setMaximized(false);

            }
            
        }else{
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
        }
    }

    @FXML
    private void closeeSscreen(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void jobDragEntered(DragEvent event) {
        job.setBlendMode(BlendMode.SRC_ATOP);
    }

    @FXML
    private void jobDragDetected(MouseEvent event) {
      Dragboard dragBoard = job.startDragAndDrop(TransferMode.MOVE);
      ClipboardContent content = new ClipboardContent();
      content.putString(String.valueOf(job.getSelectionModel().getSelectedIndex()));
      dragBoard.setContent(content);
    }

    @FXML
    private void jobDragExited(DragEvent event) {
      job.setBlendMode(null);  
    }

    @FXML
    private void jobDragOver(DragEvent event) {
         event.acceptTransferModes(TransferMode.MOVE); 
    }

    @FXML
    private void jobDragDropped(DragEvent event) {
        int tmp = Integer.parseInt(event.getDragboard().getString());
        String aadd= addedJob.getItems().get(tmp);
        dataRight.remove(tmp);
        data.add(aadd);
        job.setItems(data);
        addedJob.setItems(dataRight);        
    }

    @FXML
    private void addToRightJob(ActionEvent event) {
        int index=job.getSelectionModel().getSelectedIndex();
        String tmp= data.remove(index);
        dataRight.add(tmp);
        job.setItems(data);
        addedJob.setItems(dataRight);//dataRight 
    }

    @FXML
    private void addToLeftJob(ActionEvent event) {
        int index=addedJob.getSelectionModel().getSelectedIndex();
        String tmp= dataRight.remove(index);
        data.add(tmp);
        addedJob.setItems(dataRight);
        job.setItems(data);
    }

    @FXML
    private void addedDragEntered(DragEvent event) {
        addedJob.setBlendMode(BlendMode.SRC_ATOP);
    }

    @FXML
    private void addedDragExited(DragEvent event) {
        addedJob.setBlendMode(null);
    }

    @FXML
    private void addedDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);    
    }

    @FXML
    private void addedDragDropped(DragEvent event) {
        int tmp = Integer.parseInt(event.getDragboard().getString());
        String aadd= job.getItems().get(tmp);
        data.remove(tmp);
        dataRight.add(aadd);
        job.setItems(data);
        addedJob.setItems(dataRight); 
    }
    
}

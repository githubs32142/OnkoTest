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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import projekt.Class.CancerFamilly;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class CancerInFamillyController implements Initializable {

    @FXML
    private TableView<CancerFamilly> table;
    private TableColumn cancer;
    private TableColumn familly;
    private final ObservableList<CancerFamilly> data =
        FXCollections.observableArrayList(
            new CancerFamilly("Jacob", "Smith"),
            new CancerFamilly("Isabella", "Johnson"),
            new CancerFamilly("Ethan", "Williams")
        );
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          cancer = new TableColumn("Rak w rodzinie");
        cancer.setMinWidth(266);
        cancer.setCellValueFactory(
                new PropertyValueFactory<>("cancer"));
 
        familly = new TableColumn("Rodzina ");
        familly.setMinWidth(266);
        familly.setCellValueFactory(
                new PropertyValueFactory<>("familly"));
 
        table.setItems(data);
        table.getColumns().addAll(cancer, familly);
    }    

    @FXML
    private void undoClick(MouseEvent event) {
    }
    
}

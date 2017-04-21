/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Admin
 */
public class RunClass extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("FXML/FirstWindow.fxml"));
        //FXMLLoader load = new FXMLLoader(this.getClass().getResource("FXML/FactorWindow.fxml"));
        Parent parent= load.load();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

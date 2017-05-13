/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Admin
 */
public class RunClass extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        loadSplash(primaryStage);
        /*
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("FXML/AsbestosTest.fxml"));
        //FXMLLoader load = new FXMLLoader(this.getClass().getResource("FXML/Fibre.fxml"));
        Parent parent= load.load();
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
        */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public void loadSplash (Stage primaryStage) throws IOException{
                FXMLLoader load = new FXMLLoader(this.getClass().getResource("FXML/SplashScreen.fxml"));
        //FXMLLoader load = new FXMLLoader(this.getClass().getResource("FXML/Fibre.fxml"));
        Parent parent= load.load();
        Scene scene = new Scene(parent);
        FadeTransition fin= new FadeTransition(Duration.seconds(10), parent);
        fin.setFromValue(0);
        fin.setToValue(6);
        fin.setCycleCount(1);
        fin.play();
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();  
        fin.setOnFinished( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader load = new FXMLLoader(this.getClass().getResource("FXML/FirstWindow.fxml"));
                Parent parent = null;
                try {
                    parent = load.load();
                } catch (IOException ex) {
                    Logger.getLogger(RunClass.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(parent);
                FadeTransition fin= new FadeTransition(Duration.seconds(5), parent);
                fin.setFromValue(0);
                fin.setToValue(3);
                fin.setCycleCount(1);
                fin.play();
                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.show();
            }
        });
    }
}

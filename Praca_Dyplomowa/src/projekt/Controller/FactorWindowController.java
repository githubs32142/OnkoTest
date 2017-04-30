
/**
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projekt.Class.Factor;
import projekt.Class.Person;

/**
 * FXML Controller class
 *
 * @author Andrzej Kierepka
 */
public class FactorWindowController implements Initializable {
    private int leftSelected,rightSeleted=0;
    public Person person= new Person();
    WebEngine webEngine ;
    List <Factor> fact= new ArrayList<>();
    ObservableList<String> data = FXCollections.observableArrayList("Spożywanie alkoholu","Otyłość","Promieniowanie jonizujące","Radioterapia","Lampy solarium","Palenie papierosów",
            "Brak aktywności fizycznej","Niewłaściwa dieta","Brak naturalnych antyoksydantów","Menopauza + otyłość","Brak błonnika" );
    ObservableList<String> dataRight = FXCollections.observableArrayList();
    private int index;
    @FXML
    private ListView<String> factors;
    @FXML
    private WebView webView;
    @FXML
    private Button test;
    @FXML
    private ListView<String> addedFactor;
    @FXML
    private Button next;
    public FactorWindowController(Person person) {
        this.leftSelected = 0;
        this.person=person;
    }    

    public FactorWindowController() {
        this.leftSelected = 0;
       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //AuditTest
        fact.add(new Factor("Spożywanie alkoholu", "/projekt/HTML/alkoholizm.html", true,"/projekt/FXML/AuditTest.fxml"));
        // Samo dodaje
        fact.add(new Factor("Otyłość", "/projekt/HTML/otylosc.html", false,""));
        // IonizingRadiation
        fact.add(new Factor("Promieniowanie jonizujące", "/projekt/HTML/promieniowanie.html", true,"/projekt/FXML/IonizingRadiation.fxml"));
        //  Radiotherapy
        fact.add(new Factor("Radioterapia", "/projekt/HTML/radioterapia.html", true,"/projekt/FXML/Radiotherapy.fxml"));
        // Solarium
        fact.add(new Factor("Lampy solarium", "/projekt/HTML/solarium.html", true,"/projekt/FXML/Solarium.fxml"));
        // Smoking
        fact.add(new Factor("Palenie papierosów", "/projekt/HTML/papierosy.html", true,"/projekt/FXML/SmokingTest.fxml"));
        //  da się zrobić
        fact.add(new Factor("Brak aktywności fizycznej", "/projekt/HTML/aktywnosc_fizyczna.html", false,""));
       // zrpbione??
        fact.add(new Factor("Niewłaściwa dieta", "/projekt/HTML/brak_owocow.html", false,""));
        //
        fact.add(new Factor("Brak naturalnych antyoksydantów", "/projekt/HTML/brak_naturalnych_antyoksydantow.html", false,""));
        // Menopause 
        fact.add(new Factor("Menopauza + otyłość", "/projekt/HTML/wzrost_bmi.html",true,"/projekt/FXML/Menopause.fxml"));
        // Fibre
        fact.add(new Factor("Brak błonnika", "/projekt/HTML/brak_naturalnych_antyoksydantow.html", true,"/projekt/FXML/Fibre.fxml"));
        webEngine = webView.getEngine();
       // final URL urlFactor = getClass().getResource("/projekt/HTML/alkoholizm.html");
       // webEngine.load(urlFactor.toExternalForm());
        factors.setItems(data);
        test.setVisible(false);
        index=-1;
    }    
    /**
     ** Metoda która powoduje, że wchodzimy do poprzedniego okna okna głównego 
     * @param event 
     */
    @FXML
    private void undoClick(MouseEvent event) {
        try{

                FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/FirstWindow.fxml"));
                FirstWindowController cnt= new FirstWindowController();   
                Parent parent= load.load();
                cnt=load.getController();
                cnt.setPerson(person);
                if(person.getName().equals("")){
                    cnt.lblname.setVisible(false);
                    cnt.name.setVisible(false);
                    cnt.lblsurname.setText("E-mail:");
                    cnt.toplbl.setLayoutX(186);
                    cnt.toplbl.setLayoutY(50);
                    cnt.surname.setText(person.getEmail());
                    cnt.setMail(true);
                }
                Scene scene = new Scene(parent);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);          
                primaryStage.initStyle(StageStyle.UNDECORATED);
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
    private void factorClicked(MouseEvent event) {
        String clickedFact= factors.getItems().get(factors.getSelectionModel().getSelectedIndex());
        index=ifFact(clickedFact);
        if(index>=0){
            leftSelected=factors.getSelectionModel().getSelectedIndex();
            final URL urlFactor = getClass().getResource(fact.get(index).getSymptom());
            webEngine.load(urlFactor.toExternalForm());
            if(fact.get(index).isTest()){
                test.setVisible(true);
            }
            else{
                test.setVisible(false);
            }
        }
    }
    public int ifFact(String facts){
         for(int i=0;i<fact.size();i++){
         if(fact.get(i).getFactor().equals(facts)){
             return i;
         }   
        }
         return -1;
    }

    @FXML
    private void makeTest(ActionEvent event) {
        if(index>=0){
            if(fact.get(index).isTest()){
                FXMLLoader load = new FXMLLoader(this.getClass().getResource(fact.get(index).getUrlTest()));
                Parent parent = null;
                try {
                    parent = load.load();
                } catch (IOException ex) {
                    Logger.getLogger(FactorWindowController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(parent);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene); 
                if(fact.get(index).getFactor().equals("Spożywanie alkoholu")){
                AuditTest cnt= new AuditTest();
                cnt=load.getController();
                cnt.setWindow(this); 
                }
                if(fact.get(index).getFactor().equals("Palenie papierosów")){
                SmokingTestController cnt= new SmokingTestController();
                cnt=load.getController();
                cnt.setWindow(this); 
                }
                if(fact.get(index).getFactor().equals("Radioterapia")){
                RadiotherapyTestController cnt= new RadiotherapyTestController();
                cnt=load.getController();
                cnt.setWindow(this); 
                }
                if(fact.get(index).getFactor().equals("Promieniowanie jonizujące")){
                IonizingRadiationTestController cnt= new IonizingRadiationTestController ();
                cnt=load.getController();
                cnt.setWindow(this); 
                }
                if(fact.get(index).getFactor().equals("Menopauza + otyłość")){
                MenopauseTestController cnt= new MenopauseTestController();
                cnt=load.getController();
                cnt.setWindow(this); 
                }
                if(fact.get(index).getFactor().equals("Lampy solarium")){
                SolariumTestController cnt= new SolariumTestController();
                cnt=load.getController();
                cnt.setWindow(this); 
                }
                if(fact.get(index).getFactor().equals("Brak błonnika")){
                FibreTest cnt= new FibreTest();
                cnt=load.getController();
                cnt.setWindow(this); 
                }
                //"Menopauza + otyłość"
                //primaryStage.initStyle(StageStyle.UNDECORATED);
                primaryStage.setResizable(false);
                primaryStage.show();
                
            }
        }
    }

    @FXML
    private void addedFactorRemove(MouseEvent event) {
        String clickedFact= addedFactor.getItems().get(addedFactor.getSelectionModel().getSelectedIndex());
        System.out.println(clickedFact);
        int tmpindex=ifFact(clickedFact);
        index=tmpindex;
        if(tmpindex>=0){
            rightSeleted=addedFactor.getSelectionModel().getSelectedIndex();
            final URL urlFactor = getClass().getResource(fact.get(tmpindex).getSymptom());
            webEngine.load(urlFactor.toExternalForm());
            if(fact.get(tmpindex).isTest()){
                test.setVisible(true);
            }
            else{
                test.setVisible(false);
            }
        }
    }
    /**
     ** Metoda która przenosi podaną jako parametr fakt na listę czynników użytkowanika 
     * @param fact podany fakt
     */
    public void changeFactToRight(String fact){
        for(int i=0;i<data.size();i++){
            if(data.get(i).equals(fact)){
                dataRight.add(fact);
                data.remove(i);
                factors.setItems(data);
                addedFactor.setItems(dataRight);
                return ;
            }
        }
    }

    @FXML
    private void nextWindow(ActionEvent event) throws IOException {
         FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/SymptomWindow.fxml"));
         SymptomWindowController cnt= new SymptomWindowController();   
         Parent parent= load.load();
         cnt=load.getController();
         cnt.setPerson(person);
         cnt.setFactor(dataRight);
         Scene scene = new Scene(parent);
         Stage primaryStage = new Stage();
         primaryStage.setScene(scene);
         primaryStage.show();
         Stage stage;
         stage = (Stage) ((Node)(event.getSource())).getScene().getWindow();
         stage.close();
    }
    @FXML
    private void factorsDragEntered(DragEvent event) {
        factors.setBlendMode(BlendMode.SRC_ATOP);
    }

    @FXML
    private void factorsDragDetected(MouseEvent event) {
      Dragboard dragBoard = factors.startDragAndDrop(TransferMode.MOVE);
      ClipboardContent content = new ClipboardContent();
      content.putString(String.valueOf(factors.getSelectionModel().getSelectedIndex()));
      dragBoard.setContent(content);  
    }

    @FXML
    private void factorsDragExited(DragEvent event) {
        factors.setBlendMode(null);
    }

    @FXML
    private void factorsDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE); 
    }

    @FXML
    private void addedFactorDragEntered(DragEvent event) {
        addedFactor.setBlendMode(BlendMode.SRC_ATOP);
    }

    @FXML
    private void addedFactorDragExited(DragEvent event) {
        addedFactor.setBlendMode(null);
    }

    @FXML
    private void addedFactorDragOver(DragEvent event) {
    event.acceptTransferModes(TransferMode.MOVE);    
    }

    @FXML
    private void addedFactorDragDropped(DragEvent event) {
        int tmp = Integer.parseInt(event.getDragboard().getString());
        String aadd= factors.getItems().get(tmp);
        data.remove(tmp);
        dataRight.add(aadd);
        factors.setItems(data);
        addedFactor.setItems(dataRight);
    }

    @FXML
    private void factorDragDropped(DragEvent event) {
        int tmp = Integer.parseInt(event.getDragboard().getString());
        String aadd= addedFactor.getItems().get(tmp);
        dataRight.remove(tmp);
        data.add(aadd);
        factors.setItems(data);
        addedFactor.setItems(dataRight);
    }

    @FXML
    private void addedDragDetected(MouseEvent event) {
      Dragboard dragBoard = addedFactor.startDragAndDrop(TransferMode.MOVE);
      ClipboardContent content = new ClipboardContent();
      content.putString(String.valueOf(addedFactor.getSelectionModel().getSelectedIndex()));
      dragBoard.setContent(content); 
    }
     /**
     ** Metoda która ustawia dane osoby  
     * @param person 
     */
    public void setPerson(Person person) {
        this.person = person;
        
    }
}

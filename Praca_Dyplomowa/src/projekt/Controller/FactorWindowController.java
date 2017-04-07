
/**9
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
import javafx.scene.input.MouseEvent;
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
    ObservableList<String> data = FXCollections.observableArrayList("Alkoholizm","Otyłość","Promieniowanie jonizujące","Radioterapia","Lampy solarium","Palenie papierosów",
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
    private Button add;
    @FXML
    private Button remove;
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
        // dodano
        fact.add(new Factor("Alkoholizm", "/projekt/HTML/alkoholizm.html", true,"/projekt/FXML/AuditTest.fxml"));
        // doadaje automatycznie (program sam oblicza BMI  i jak >25 dodaje)
        fact.add(new Factor("Otyłość", "/projekt/HTML/otylosc.html", false,""));
        fact.add(new Factor("Promieniowanie jonizujące", "/projekt/HTML/promieniowanie.html", false,""));
        fact.add(new Factor("Radioterapia", "/projekt/HTML/radioterapia.html", false,""));
        fact.add(new Factor("Lampy solarium", "/projekt/HTML/solarium.html", false,""));
        // znalezniono test do opracowania
        fact.add(new Factor("Palenie papierosów", "/projekt/HTML/papierosy.html", true,"/projekt/FXML/SmokingTest.fxml"));
        fact.add(new Factor("Brak aktywności fizycznej", "/projekt/HTML/aktywnosc_fizyczna.html", false,""));
       // piramida zywieniowa i z niej pytania
        fact.add(new Factor("Niewłaściwa dieta", "/projekt/HTML/brak_owocow.html", false,""));
        fact.add(new Factor("Brak naturalnych antyoksydantów", "/projekt/HTML/brak_naturalnych_antyoksydantow.html", false,""));
        fact.add(new Factor("Menopauza + otyłość", "/projekt/HTML/wzrost_bmi.html", false,""));
        // opracowywanie!
        fact.add(new Factor("Brak błonnika", "/projekt/HTML/brak_naturalnych_antyoksydantow.html", false,""));
        webEngine = webView.getEngine();
       // final URL urlFactor = getClass().getResource("/projekt/HTML/alkoholizm.html");
       // webEngine.load(urlFactor.toExternalForm());
        factors.setItems(data);
        test.setVisible(false);
        index=-1;
        add.setVisible(false);
        remove.setVisible(false);
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
            catch(Exception e){
                 Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window.", e);
            }
    }
    /**
     ** Metoda która ustawia dane osoby  
     * @param person 
     */
    public void setPerson(Person person) {
        this.person = person;
        
    }

    @FXML
    private void factorClicked(MouseEvent event) {
        //System.out.println(factors.getItems().get(factors.getSelectionModel().getSelectedIndex()));
        String clickedFact= factors.getItems().get(factors.getSelectionModel().getSelectedIndex());
        int tmpindex=ifFact(clickedFact);
        index=tmpindex;
        if(tmpindex>=0){
            leftSelected=factors.getSelectionModel().getSelectedIndex();
            System.out.println(leftSelected);
            final URL urlFactor = getClass().getResource(fact.get(tmpindex).getSymptom());
            webEngine.load(urlFactor.toExternalForm());
            if(fact.get(tmpindex).isTest()){
                test.setVisible(true);
            }
            else{
                test.setVisible(false);
            }
            add.setVisible(true);
            remove.setVisible(false);
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
                if(fact.get(index).getFactor().equals("Alkoholizm")){
                AuditTest cnt= new AuditTest();
                cnt=load.getController();
                cnt.setWindow(this); 
                }
                //primaryStage.initStyle(StageStyle.UNDECORATED);
                primaryStage.setResizable(false);
                primaryStage.show();
                
            }
        }
    }

    @FXML
    private void addFactor(ActionEvent event) {
        String tmp=data.remove(leftSelected);
        dataRight.add(tmp);
        factors.setItems(data);
        addedFactor.setItems(dataRight);
    }

    @FXML
    private void removeFactor(ActionEvent event) {
        String tmp=dataRight.remove(rightSeleted);
        data.add(tmp);
        addedFactor.setItems(dataRight);
        factors.setItems(data);
    }

    @FXML
    private void addedFactorRemove(MouseEvent event) {
        String clickedFact= addedFactor.getItems().get(addedFactor.getSelectionModel().getSelectedIndex());
        int tmpindex=ifFact(clickedFact);
        index=tmpindex;
        if(tmpindex>=0){
            rightSeleted=addedFactor.getSelectionModel().getSelectedIndex();
            System.out.println(leftSelected);
            final URL urlFactor = getClass().getResource(fact.get(tmpindex).getSymptom());
            webEngine.load(urlFactor.toExternalForm());
            if(fact.get(tmpindex).isTest()){
                test.setVisible(true);
            }
            else{
                test.setVisible(false);
            }
            add.setVisible(false);
            remove.setVisible(true);
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
}

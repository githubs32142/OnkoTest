package projekt.Controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projekt.Class.Factor;
import projekt.Class.OperationFactor;
import projekt.Class.Person;

/**
 * Kontroler do ppliku FXML
 *
 * @author Andrzej Kierepka
 */
public class FactorWindowController implements Initializable {
    private double xOffset = 0;
    private double yOffset = 0;
    private int leftSelected, rightSeleted = 0;
    public Person person = new Person();
    WebEngine webEngine;
    List<Factor> fact = new ArrayList<>();
    ObservableList<String> data;
    ObservableList<String> dataRight;
    private int index;
    Stage stage;
    Rectangle2D rec2;
    Double w, h;
    @FXML
    private Button test;
    @FXML
    private ListView<String> addedFactor;
    @FXML
    private Button next;
    private SymptomWindowController sw;
    private CancerInFamillyController cif;
    @FXML
    private VBox box;
    @FXML
    private JFXListView<String> factors;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private WebView webHTML;


    /**
     ** Konstruktor w którym podajemy instancje klasy Person
     *
     * @param person instancja klasy Person
     */
    public FactorWindowController(Person person) {
        this.leftSelected = 0;
        this.person = person;
        sw = new SymptomWindowController();
        cif = new CancerInFamillyController();
        data = FXCollections.observableArrayList("Spożywanie alkoholu", "Otyłość", "Promieniowanie jonizujące", "Radioterapia", "Lampy solarium", "Palenie papierosów",
                "Brak aktywności fizycznej", "Niewłaściwa dieta", "Brak naturalnych antyoksydantów", "Menopauza + otyłość", "Brak błonnika", "Pole elektromagnetyczne", "Kontakt z azbestem");
        dataRight = FXCollections.observableArrayList();
    }

    /**
     ** Konstruktor bezparametrowy
     */
    public FactorWindowController() {
        this.leftSelected = 0;
        sw = new SymptomWindowController();
        cif = new CancerInFamillyController();
        data = FXCollections.observableArrayList("Spożywanie alkoholu", "Otyłość", "Promieniowanie jonizujące",
                "Radioterapia", "Lampy solarium", "Palenie papierosów",
                "Brak aktywności fizycznej", "Niewłaściwa dieta", "Brak naturalnych antyoksydantów", "Menopauza + otyłość",
                "Brak błonnika", "Pole elektromagnetyczne", "Kontakt z azbestem", "Wczesne współżycie seksualne","Wczesny wiek rodzenia");
        dataRight = FXCollections.observableArrayList();
        factors = new JFXListView<>();
        factors.setItems(data);
        addedFactor = new ListView<>(dataRight);
    }

    /**
     *
     * Inicjalizacja kontriolera
     *
     * @param url wskaźnik do "zasobu" w sieci World Wide Web
     * @param rb wersja językowa
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rec2 = Screen.getPrimary().getVisualBounds();
        w = 0.1;
        h = 0.1;
        OperationFactor.initFactor(fact);
        webEngine = webHTML.getEngine();
        //webEngine.setUserStyleSheetLocation(getClass().getResource("/projekt/HTML/style.css").toString());
        // final URL urlFactor = getClass().getResource("/projekt/HTML/style.css");
       //  webEngine.load(urlFactor.toExternalForm());
       //webEngine.setUserStyleSheetLocation(urlFactor.toExternalForm());
        factors.setItems(data);
        test.setVisible(false);
        index = -1;
        drawer.setSidePane(box);
        drawer.close();
        HamburgerBasicCloseTransition transition = new HamburgerBasicCloseTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
                drawer.setPrefWidth(150);
            }
        });
        mainPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        //set mouse drag
        mainPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage;
                stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    /**
     ** Metoda, która wywołana jest poprzez kliknięcie w listę czynników ryzyka
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void factorClicked(MouseEvent event) {
        String clickedFact = factors.getItems().get(factors.getSelectionModel().getSelectedIndex());
        index = ifFact(clickedFact);
        if (index >= 0) {
            leftSelected = factors.getSelectionModel().getSelectedIndex();
            final URL urlFactor = getClass().getResource(fact.get(index).getUrlHTML());
            webEngine.load(urlFactor.toExternalForm());
            if (fact.get(index).isTest()) {
                test.setVisible(true);
            } else {
                test.setVisible(false);
            }
        }
    }

    /**
     ** Metoda sprawdza, czy podany czynnik ryzyka znajduje się na liście
     * czynników ryzyka
     *
     * @param facts czynnik ryzyka
     * @return index do danego czynnika ryzyka >0 czynnik znajduje się
     */
    public int ifFact(String facts) {
        for (int i = 0; i < fact.size(); i++) {
            if (fact.get(i).getFactor().equals(facts)) {
                return i;
            }
        }
        return -1;
    }

    /**
     ** Metoda, która wykonuje test
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void makeTest(ActionEvent event) {
        if (index >= 0) {
            if (fact.get(index).isTest()) {
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
                primaryStage.setResizable(false);
                if (fact.get(index).getFactor().equals("Spożywanie alkoholu")) {
                    AuditTest cnt = new AuditTest();
                    cnt = load.getController();
                    cnt.setWindow(this);
                }
                if (fact.get(index).getFactor().equals("Palenie papierosów")) {
                    SmokingTestController cnt = new SmokingTestController();
                    cnt = load.getController();
                    cnt.setWindow(this);
                }
                if (fact.get(index).getFactor().equals("Radioterapia")) {
                    RadiotherapyTestController cnt = new RadiotherapyTestController();
                    cnt = load.getController();
                    cnt.setWindow(this);
                }
                if (fact.get(index).getFactor().equals("Promieniowanie jonizujące")) {
                    IonizingRadiationTestController cnt = new IonizingRadiationTestController();
                    cnt = load.getController();
                    cnt.setWindow(this);
                }
                if (fact.get(index).getFactor().equals("Menopauza + otyłość")) {
                    MenopauseTestController cnt = new MenopauseTestController();
                    cnt = load.getController();
                    cnt.setWindow(this);
                }
                if (fact.get(index).getFactor().equals("Lampy solarium")) {
                    SolariumTestController cnt = new SolariumTestController();
                    cnt = load.getController();
                    cnt.setWindow(this);
                }
                if (fact.get(index).getFactor().equals("Brak błonnika")) {
                    FibreTest cnt = new FibreTest();
                    cnt = load.getController();
                    cnt.setWindow(this);
                }//azbest
                if (fact.get(index).getFactor().equals("Kontakt z azbestem")) {
                    AsbestosTestController cnt = new AsbestosTestController();
                    cnt = load.getController();
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    primaryStage.setResizable(true);
                    cnt.setWindow(this);
                }
                if (fact.get(index).getFactor().equals("Wczesne współżycie seksualne")) {
                    EarlyChAgeController cnt = new EarlyChAgeController();
                    cnt = load.getController();
                    cnt.setWindow(this);
                }
                if (fact.get(index).getFactor().equals("Wczesny wiek rodzenia")) {
                    EarlyBabeController cnt = new EarlyBabeController();
                    cnt = load.getController();
                    cnt.setWindow(this);
                }//
                //"Menopauza + otyłość"
                //primaryStage.initStyle(StageStyle.UNDECORATED);

                primaryStage.show();

            }
        }
    }

    /**
     ** Metoda która przenosi podaną jako parametr fakt na listę czynników
     * użytkowanika
     *
     * @param fact podany fakt
     */
    public void changeFactToRight(String fact) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(fact)) {
                dataRight.add(fact);
                data.remove(i);
                factors.setItems(data);
                addedFactor.setItems(dataRight);
                return;
            }
        }
    }

    /**
     ** Metoda która przenosi podaną jako parametr fakt na listę czynników
     *
     * @param fact podany fakt
     */
    public void changeFactToLeft(String fact) {
        for (int i = 0; i < dataRight.size(); i++) {
            if (dataRight.get(i).equals(fact)) {
                data.add(fact);
                dataRight.remove(i);
                factors.setItems(data);
                addedFactor.setItems(dataRight);
                return;
            }
        }
    }

    /**
     ** Metoda, która powoduje perzejście do kolejnego okna
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void nextWindow(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/SymptomWindow.fxml"));
        SymptomWindowController cnt = new SymptomWindowController();
        Parent parent = load.load();
        cnt = load.getController();
        cnt.setPerson(person);
        cnt.setFactor(dataRight);
        for (int i = 0; i < sw.dataRight.size(); i++) {
            cnt.changeSymptomToRight(sw.dataRight.get(i));
        }
        cnt.setCancerInFamillyController(cif);
        Scene scene = new Scene(parent);
        Stage primaryStage = new Stage();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    /**
     ** Przeciągnięcie z miejsca zródłowego do miejsca docelowego
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void factorsDragEntered(DragEvent event) {
        factors.setBlendMode(BlendMode.SRC_ATOP);
    }

    /**
     ** Metoda, która wykrywa przeciąganie i rozpoczyna drag & drop
     *
     * @param event obsługa zdarzenia MouseEvent
     */
    @FXML
    private void factorsDragDetected(MouseEvent event) {
        Dragboard dragBoard = factors.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(String.valueOf(factors.getSelectionModel().getSelectedIndex()));
        dragBoard.setContent(content);
    }

    /**
     ** Wyjście poza obszar formularza
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void factorsDragExited(DragEvent event) {
        factors.setBlendMode(null);
    }

    /**
     ** Metoda, która odpowiada za przeciąganie danych z źródała do miejsca
     * docelowego
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void factorsDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
    }

    /**
     ** Przeciągnięcie z miejsca zródłowego do miejsca docelowego
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void addedFactorDragEntered(DragEvent event) {
        addedFactor.setBlendMode(BlendMode.SRC_ATOP);
    }

    /**
     ** Wyjście poza obszar formularza
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void addedFactorDragExited(DragEvent event) {
        addedFactor.setBlendMode(null);
    }

    /**
     ** Metoda, która odpowiada za przeciąganie danych z źródała do miejsca
     * docelowego
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void addedFactorDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
    }

    /**
     ** Metoda, która odpowiada za opuszczenie przeciąganego czynnika ryzyka
     *
     * @param event obsługa zdarzeniA
     */
    @FXML
    private void addedFactorDragDropped(DragEvent event) {
        int tmp = Integer.parseInt(event.getDragboard().getString());
        String aadd = factors.getItems().get(tmp);
        data.remove(tmp);
        dataRight.add(aadd);
        factors.setItems(data);
        addedFactor.setItems(dataRight);
    }

    /**
     ** Metoda, która odpowiada za opuszczenie przeciąganego czynnika ryzyka
     *
     * @param event obsługa zdarzeniA
     */
    @FXML
    private void factorDragDropped(DragEvent event) {
        int tmp = Integer.parseInt(event.getDragboard().getString());
        String aadd = addedFactor.getItems().get(tmp);
        dataRight.remove(tmp);
        data.add(aadd);
        factors.setItems(data);
        addedFactor.setItems(dataRight);
    }

    /**
     ** Metoda, która wykrywa przeciąganie i rozpoczyna drag & drop
     *
     * @param event obsługa zdarzenia MouseEvent
     */
    @FXML
    private void addedDragDetected(MouseEvent event) {
        Dragboard dragBoard = addedFactor.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(String.valueOf(addedFactor.getSelectionModel().getSelectedIndex()));
        dragBoard.setContent(content);
    }

    /**
     ** Metoda która ustawia dane osoby
     *
     * @param person instancja klasy PERSON
     */
    public void setPerson(Person person) {
        this.person = person;

    }

    /**
     ** Metoda, która powoduje, że formulrz rozciąga się na cały ekran
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void fullScreen(ActionEvent event) {
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        } else {
            stage.setFullScreen(true);
        }
    }

    /**
     ** Metoda która minimalizuje formularz
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void minimalizeSscreen(ActionEvent event) {
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
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
        } else {
            stage.setIconified(true);
        }
    }

    /**
     ** Metoda, która maksymalizuje formularz
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void maximalizeSscreen(ActionEvent event) {
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        if (stage.isMaximized()) {
            if (w == rec2.getWidth() && h == rec2.getHeight()) {
                stage.setMaximized(false);
                stage.setHeight(600);
                stage.setWidth(800);
                stage.centerOnScreen();
            } else {
                stage.setMaximized(false);

            }

        } else {
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
        }
    }

    /**
     ** Metoda, która powoduje wyjście z programu
     *
     * @param event obsługa zdarzenia
     */
    @FXML
    private void closeeSscreen(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    /**
     ** Metoda, która powoduje przeniesienie czynnika ryzyka z listy dostępnych
     * czynników ryzyka(dostępnych po lewej stronie okna) do wybrnych czynników
     * ryzyka
     *
     * @param event obsługa zdarzenia kliknięcia w przycisk
     */
    @FXML
    private void addToRightFact(ActionEvent event) {
        try {
            index = factors.getSelectionModel().getSelectedIndex();
            String tmp = data.remove(index);
            dataRight.add(tmp);
            addedFactor.setItems(dataRight);
            factors.setItems(data);
        } catch (Exception ex) {
            showOutputMessage("Nie zaznaczyłeś wiersza");
        }
    }

    /**
     ** Metoda, która przenosi zaznaczony czynnik ryzyka do listy w której
     * znajduje się lista czynników ryzyka
     *
     * @param event obsługa zdarzenia kliknięcia w przycisk
     */
    @FXML
    private void addToLeftFact(ActionEvent event) {
        try {
            index = addedFactor.getSelectionModel().getSelectedIndex();
            String tmp = dataRight.remove(index);
            data.add(tmp);
            addedFactor.setItems(dataRight);
            factors.setItems(data);
        } catch (Exception e) {
            showOutputMessage("Nie zaznaczyłeś wiersza");
        }
    }

    /**
     ** Metoda pozwala na ustawienie kontrolera w oknie 2
     *
     * @param sw kontroler
     */
    public void setSymptomWindowController(SymptomWindowController sw) {
        this.sw = sw;
    }

    /**
     ** Metoda pozwala na ustawienie kontrolera w oknie 3
     *
     * @param cif kontroler
     */
    public void setCancerInFamillyController(CancerInFamillyController cif) {
        this.cif = cif;
    }

    /**
     ** wyświetla KOMUNIKAT O BŁĘDZIE
     *
     * @param message treść komunikatu o błędzie
     */
    public void showOutputMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("Treść błędu");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     ** Metoda, która przechodz do okna w którym podajemy swoje dane
     *
     * @param event obsługa zdarzenia kliknięcia w przycisk
     */
    @FXML
    private void undoClick(ActionEvent event) {
        try {

            FXMLLoader load = new FXMLLoader(this.getClass().getResource("/projekt/FXML/FirstWindow.fxml"));
            FirstWindowController cnt = new FirstWindowController();
            Parent parent = load.load();
            cnt = load.getController();
            cnt.setPerson(person);
            if (person.getName().isEmpty()) {
                cnt.lblname.setVisible(false);
                cnt.name.setVisible(false);
                cnt.lblsurname.setText("E-mail:");
                cnt.toplbl.setLayoutX(186);
                cnt.toplbl.setLayoutY(50);
                cnt.surname.setText(person.getEmail());
                cnt.setMail(true);
            }
            cnt.setFactorWindowController(this);
            cnt.setSymptomWindowController(sw);
            cnt.setCancerInFamillyController(cif);
            Scene scene = new Scene(parent);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setResizable(false);
            primaryStage.show();
            Stage stage;
            stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

}

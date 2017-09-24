package projekt.Controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import projekt.Class.CancerFamilly;
import projekt.Class.Person;

/**
 * FXML Controller class
 *
 * @author Andrzej Kierepka
 */
public class DiagnoseWindowController implements Initializable {

    private StringBuilder string;
    ObservableList<CancerFamilly> cancerFamilly;
    private Person person;
    ObservableList<String> dataFactors;
    ObservableList<String> dataSymptoms;
    public WebEngine webEngine;
    Stage stage;
    Rectangle2D rec2;
    Double w, h;
    @FXML
    private WebView webView;
    @FXML
    private Button btnLogout;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane box;
    private String CSS;
    @FXML
    private AnchorPane root;
    private double xOffset = 0;
    private double yOffset = 0;

    /**
     * Konstruktor bezparametrowy
     */
    public DiagnoseWindowController() {
        cancerFamilly = FXCollections.observableArrayList();
        dataFactors = FXCollections.observableArrayList();
        dataSymptoms = FXCollections.observableArrayList();
        person = new Person();
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
        // webEngine = webView.getEngine();

        drawer.setSidePane(box);
        drawer.close();
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        //set mouse drag
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
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
     ** Metoda, która powoduje, że formulrz rozciąga się naa cały ekran
     *
     * @param event
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
     ** Metoda która minimalizuje okno
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
     ** Metoda, która maksymalizuje okno
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
     ** Metoda, która zamyka program
     *
     * @param event
     */
    @FXML
    private void closeeSscreen(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    /**
     ** Metoda która pozwala na ustawienie obiektu immitującego ooę
     *
     * @param person instancja Klasy Person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    @FXML
    private void undoClicked(ActionEvent event) {
    }

    /**
     ** Metoda, która pozwala na zapisa wyniku diagnozy do pliku pdf
     *
     * @param event obsługa zdarznia
     * @throws FileNotFoundException
     * @throws DocumentException
     * @throws IOException Wyjątek wejścia/ wyjścia
     */
    @FXML
    private void saveToPdf(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Pliki PDF", "*.pdf"));

        File fileSel = fileChooser.showSaveDialog(new Stage());

        if (fileSel != null) {
            Document document = new Document();
            createPdf(fileSel);
        }
    }

    /**
     ** Metoda, która tworzy plik pdf na podaną ścieżkę
     *
     * @param file ścieżka dostępu do pluku na którym ma być zapisaby plik pdf
     * @throws IOException wyjątek wejścia/ wyjścia
     * @throws DocumentException wyjatek podczas tworzenia dokumentu
     */
    public void createPdf(File file) throws IOException, DocumentException {
        Document document = new Document();
        String HTML = "src/projekt/HTML/Diagnoza/diagnoza.html";
        String CSS = "src/projekt/HTML/Diagnoza/styl.css";
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        writer.setInitialLeading(12.5f);
        document.open();
        CSSResolver cssResolver = new StyleAttrCSSResolver();
        CssFile cssFile = XMLWorkerHelper.getCSS(new FileInputStream(CSS));
        cssResolver.addCss(cssFile);
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
        XMLWorker worker = new XMLWorker(css, true);
        XMLParser p = new XMLParser(worker);
        p.parse(new FileInputStream(HTML));
        document.close();
    }

    public WebView getWebView() {
        return webView;
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }

    public void setString(StringBuilder string) {
        this.string = string;
    }

    @FXML
    private void newDiagnose(ActionEvent event) {
        stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.close();
        Stage primaryStage= new Stage();
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("FXML/FirstWindow.fxml"));
                Parent parent = null;
                try {
                    parent = load.load();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
                Scene scene = new Scene(parent);
                FadeTransition fin = new FadeTransition(Duration.seconds(5), parent);
                fin.setFromValue(0);
                fin.setToValue(3);
                fin.setCycleCount(1);
                fin.play();
                primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.show();
    }

}

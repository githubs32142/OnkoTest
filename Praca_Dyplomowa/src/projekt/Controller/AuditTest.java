/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Controller;

import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import jess.JessException;
import jess.Rete;

/**
 * FXML Controller class
 *
 * @author Andrrzej Kierepka
 */
public class AuditTest implements Initializable {
    @FXML
    private Label question;
    @FXML
    private RadioButton answer1;
    @FXML
    private ToggleGroup tg;
    @FXML
    private RadioButton answer2;
    @FXML
    private RadioButton answer3;
    @FXML
    private RadioButton answer4;
    @FXML
    private RadioButton answer5;
    @FXML
    private Button next;
    private int index;
    private boolean end=false;// kiedy klikneliśmy na koniec(wystaw diagnozę)
    List<String> qList= new ArrayList<>();// lista pytań
    List<String> a1List= new ArrayList<>();
    List<String> a2List= new ArrayList<>();
    List<String> a3List= new ArrayList<>();
    List<String> a4List= new ArrayList<>();
    List<String> a5List= new ArrayList<>();// lista odpowiedzi na 5 pytanie
    List<Integer> pList= new ArrayList<>();// ilość punktów przyznanych za każdą odpowiedź

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        index=0;
        qList.add("1. Czy kiedykolwiek miał(a) Pan(i) poczucie, że powinien/powinna Pan(i) ograniczyć picie?");
        qList.add("2. Czy zdarzyło się, że ktoś Pana/Panią zdenerwował, krytykując Pana/Pani picie?");
        qList.add("3. Czy czuł(a) się Pan(i) źle lub miał(a) poczucie winy z powodu swojego picia?");
        qList.add("4. Czy rozpoczynał(a) Pan(i) dzień od wypicia rana alkoholu dla uspokojenia lub dla złagodzenia kaca?");
        qList.add("5. Jak często pije Pan(i) napoje alkoholowe?");
        qList.add("6. Jak dużo porcji napojów alkoholowych wypija Pan(i) typowego dnia, kiedy Pan(i) pije?");
        qList.add("7. Jak często wypija Pan(i) 6 lub więcej drinków za jednym razem?");
        qList.add("8. Jak często w ciągu minionego roku okazywało się, że nie był(a) Pan(i) w stanie przerwać picia, gdy juz Pan(i) już zaczął/zaczęła?");
        qList.add("9. Jak często w minionym roku z powodu picia nie zrobił(a) Pan(i) czegoś, co normalnie powinien/powinna Pan(i) zrobić?");
        qList.add("10. Jak często w minionym roku musiał(a) Pan(i) rano napić się, aby dojść do siebie po spożyciu zncznej ilości alkoholu poprzedniego dnia?");
        qList.add("11. Jak często w minionym roku musiał(a) Pan(i) poczucie winy lub wyrzuty sumienia po piciu?");
        qList.add("12. Jak często w minionym roku nie pamiętał(a) Pan(i) wydarzeń z poprzedniej nocu z powodu picia?");
        qList.add("13. Czy Pan(i) lub ktokolwiek inny doznał urazu z powod Pan/Pani picia?");
        qList.add("14. Czy krewny, przyjaciel, lekarz lub inny pracownik medyczny martwił się Pana/Pani piciem lub sugeroawł Panu/Pani zaprzestanie picia?");
        for(int i=0;i<4;i++){
            a1List.add(" Nie");
        }
        for(int i=0;i<8;i++){
            if(i==1){
               a1List.add(" 1 lub 2"); 
            }
            else{
              a1List.add(" Nigdy");  
            }
            
        }
        for(int i=0;i<2;i++){
           a1List.add(" Nie");  
        }
        
        for(int i=0;i<4;i++){
            a2List.add(" Tak");
        }
        a2List.add(" Raz w miesiącu lub rzadziej ");
        a2List.add(" 3 lub 4");
        for(int i=0;i<6;i++){
            a2List.add(" Rzadziej niż raz w miesiącu");
        }
        for(int i=0;i<2;i++){
           a2List.add(" Tak, ale nie w minionym roku");  
        }
        
        for(int i=0;i<4;i++){
            a3List.add(" -");
        }
        a3List.add(" 2-4 razy w miesiącu");
        a3List.add(" 5 lub 6");
        for(int i=0;i<6;i++){
            a3List.add(" Raz w miesiącu");
        }
        for(int i=0;i<2;i++){
            a3List.add(" Tak, w mionym roku");
        }
        for(int i=0;i<4;i++){
            a4List.add(" -");
        }        
        a4List.add(" 2-3 razy w tygodniu");
        a4List.add(" od 7 do 9");
        for(int i=0;i<8;i++){
            a4List.add(" Raz w tygodniu ");
        }
        for(int i=0;i<4;i++){
            a5List.add(" -");
        }
        a5List.add(" 4 lub więcej razy w tygodniu");
        a5List.add(" 10 lub więcej");
        for(int i=0;i<8;i++){
            a5List.add(" Codziennie lub prawie codziennie ");
        }
        for(int i=0;i<14;i++){
            pList.add(0);
        }
        question.setText(qList.get(0));
        answer1.setText(a1List.get(0));
        answer2.setText(a2List.get(0));
        answer3.setText(a3List.get(0));
        answer4.setText(a4List.get(0));
        answer5.setText(a5List.get(0));
        answer3.setVisible(false);
        answer4.setVisible(false);
        answer5.setVisible(false);
    }    
    
    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
    public void makeDiagnostic(String s){
        try {
            Rete engine = new Rete();
            engine.reset();
            StringWriter o = new StringWriter();
            engine.addOutputRouter("t", o);
            String result = new String();
            // Load the pricing rules
            engine.batch("projekt/JESS/audit.clp");
            engine.eval(s);
            engine.run();
            result = o.toString();
            engine.clear();
            if (result == null ? "" == null : result.equals("")) {
                result = "Brak diagnozy";            
            }
            showOutputMessage(result);
        } catch (JessException ex) {
            Logger.getLogger(AuditTest.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }

    @FXML
    private void next(ActionEvent event) {
        if(getIndex()+1<14){// jeżeli możemy przejść do następnego pytanie (od 0 do 12 = 13)
            index++;
        }
        if(getIndex()+1>12 || getIndex()<4){
         if(getIndex()<4){
            answer3.setVisible(false); 
         }
         else{
             answer3.setVisible(true); 
         }
         answer4.setVisible(false);
         answer5.setVisible(false);           
        }
        else{
          answer3.setVisible(true);
          answer4.setVisible(true);
          answer5.setVisible(true);               
        }
        if(end){// jezeli kliknięto nA KLAWISZ zakończ
            makeAllDiagnose();
           // System.out.println("Koniec");
            System.out.println(toString());
        }
        question.setText(qList.get(getIndex()));
        answer1.setText(a1List.get(getIndex()));
        answer2.setText(a2List.get(getIndex()));
        answer3.setText(a3List.get(getIndex()));
        answer4.setText(a4List.get(getIndex()));
        answer5.setText(a5List.get(getIndex()));
        if(getIndex()<12 ){
        if(pList.get(getIndex())==0){
            answer1.setSelected(true);
        }
        if(pList.get(getIndex())==1){
            answer2.setSelected(true);
        }
        if(pList.get(getIndex())==2){
            answer3.setSelected(true);
        }
        if(pList.get(getIndex())==3){
            
            answer4.setSelected(true);
        }
        if(pList.get(getIndex())==4){
            answer5.setSelected(true);
        }
        }
        else{
            if(pList.get(getIndex())==0){
            answer1.setSelected(true);
            }
            if(pList.get(getIndex())==2){
            answer2.setSelected(true);
            }
            if(pList.get(getIndex())==4){
            answer3.setSelected(true);
            }
        }
        if(getIndex()+1==14){
            next.setText("Zakończ");
            end=true;
        }   
    }

    @FXML
    private void back(ActionEvent event) {// klikneliśmy wstecz
        end=false;
        next.setText("Dalej >");
        if(getIndex()-1>=0){
            index--;
        }
        if(getIndex()+1>12 || getIndex()<4){
         if(getIndex()<4){
            answer3.setVisible(false); 
         }
         else{
             answer3.setVisible(true); 
         }
         answer4.setVisible(false);
         answer5.setVisible(false);           
        }
        else{
          answer3.setVisible(true);
          answer4.setVisible(true);
          answer5.setVisible(true);               
        }
        question.setText(qList.get(getIndex()));
        answer1.setText(a1List.get(getIndex()));
        answer2.setText(a2List.get(getIndex()));
        answer3.setText(a3List.get(getIndex()));
        answer4.setText(a4List.get(getIndex()));
        answer5.setText(a5List.get(getIndex()));
        if(getIndex()<12){
        if(pList.get(getIndex())==0){
            answer1.setSelected(true);
        }
        if(pList.get(getIndex())==1){
            answer2.setSelected(true);
        }
        if(pList.get(getIndex())==2){
            answer3.setSelected(true);
        }
        if(pList.get(getIndex())==3){
            
            answer4.setSelected(true);
        }
        if(pList.get(getIndex())==4){
            answer5.setSelected(true);
        }
        }
        else{
            if(pList.get(getIndex())==0){
            answer1.setSelected(true);
            }
            if(pList.get(getIndex())==2){
            answer2.setSelected(true);
            }
            if(pList.get(getIndex())==4){
            answer3.setSelected(true);
            }
        }

    }
    
    @FXML
    private void answer1Action(ActionEvent event) {
        
        pList.set(getIndex(),0);
    }

    @FXML
    private void answer2Action(ActionEvent event) {
        if(getIndex()<12){
           pList.set(getIndex(),1); 
        }
        else{
           pList.set(getIndex(),2) ; 
        }
    }

    @FXML
    private void answer3Action(ActionEvent event) {
        //pList.set(getIndex(),2);
        if(getIndex()<12){
           pList.set(getIndex(),2); 
        }
        else{
           pList.set(getIndex(),4) ; 
        }
    }

    @FXML
    private void answer4Action(ActionEvent event) {
        pList.set(getIndex(),3);
    }

    @FXML
    private void answer5Action(ActionEvent event) {
        pList.set(getIndex(),4);
    }
    
    @Override
    /**
     ** Metoda ktora zwraca w postaci ciągu znaków wyrażenie które będzie potrzebne wykonania wniskowania 
     * @rerurn wyrażenie potrzebne do wykonania wniskowania
     */
    public String toString(){
        StringBuilder tmp= new StringBuilder("( assert ( Point");
        for(int i=0;i<pList.size();i++){
            tmp.append("( answer").append(i+1).append(" ").append(pList.get(i)).append(") ");
        }
        tmp.append(") )");
        return tmp.toString();
    }
    /**
     ** wyświetla wyniki diagnozy  
     * @param message rezultat diagnozy
     */
    public void showOutputMessage(String message){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Wynik diagnozy");
        alert.setHeaderText("Otrzymane rezulataty");
        alert.setContentText(message);
        alert.showAndWait();
    }
    /**
     ** Wykonyje diagnoze,wyświetla dignoze i czyści wartości zmiennych.
     */
    public void makeAllDiagnose(){
            makeDiagnostic(toString());
           // System.out.println(toString());
            index=0;
            for(int i=0;i<pList.size();i++){
                pList.set(i, 0);
            }
             index=0;
             end=false;
             next.setText("Dalej >");
             question.setText(qList.get(getIndex()));
             answer1.setText(a1List.get(getIndex()));
             answer2.setText(a2List.get(getIndex()));
             answer3.setText(a3List.get(getIndex()));
             answer4.setText(a4List.get(getIndex()));
             answer5.setText(a5List.get(getIndex()));
             answer3.setVisible(false);
             answer4.setVisible(false);
             answer5.setVisible(false);
    }
    @FXML
    private void fastDiagnose(ActionEvent event) {
        makeAllDiagnose();
    }
    
}
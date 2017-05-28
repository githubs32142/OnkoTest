/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Class;

import javafx.collections.ObservableList;

public class DiagnozeHTML {
    private ObservableList<CancerFamilly> cancerFamilly;
    private Person person;
    private ObservableList<String> dataFactors;
    private ObservableList<String> dataSymptoms;
    public StringBuilder text;
    public DiagnozeHTML(ObservableList<CancerFamilly> cancerFamilly, Person person, ObservableList<String> dataFactors, ObservableList<String> dataSymptoms) {
        this.cancerFamilly = cancerFamilly;
        this.person = person;
        this.dataFactors = dataFactors;
        this.dataSymptoms = dataSymptoms;
        text = new StringBuilder();
    }

    public void setCancerFamilly(ObservableList<CancerFamilly> cancerFamilly) {
        this.cancerFamilly = cancerFamilly;
    }

    public void setDataFactors(ObservableList<String> dataFactors) {
        this.dataFactors = dataFactors;
    }

    public void setDataSymptoms(ObservableList<String> dataSymptoms) {
        this.dataSymptoms = dataSymptoms;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    private StringBuilder makePersonInTable(){
        StringBuilder str = new StringBuilder();
        str.append("<div class=\"datagrid\">\n");
        str.append("<table>\n");
        if(person.getEmail().isEmpty()){
         str.append("<tr><td>Imię</td> <td>").append(person.getName()).append("</td></tr>\n");
         str.append("<tr class=\"alt\"> <td>Nazwisko</td> <td>").append(person.getSurName()).append("</td></tr>\n");
         str.append("<tr > <td>Wiek</td> <td>").append(person.getAge()).append("</td></tr>\n");
         str.append("<tr class=\"alt\"> <td>Wzrost</td> <td>").append(person.getHeight()).append("</td></tr>\n");
         str.append("<tr > <td>Waga</td> <td>").append(person.getWeight()).append("</td></tr>\n");
         str.append("<tr class=\"alt\"> <td>Płeć:</td> <td>").append(person.getSex()).append("</td></tr>\n");
         str.append("<tr ><td>BMI:</td> <td>").append(person.getBmi()).append("</td></tr>\n");
        }
        else{
         str.append("<tr class=\"alt\"> <td>E-mail</td> <td>").append(person.getEmail()).append("</td></tr>\n");
         str.append("<tr > <td>Wiek</td> <td>").append(person.getAge()).append("</td></tr>\n");
         str.append("<tr class=\"alt\"> <td>Wzrost</td> <td>").append(person.getHeight()).append("</td></tr>\n");
         str.append("<tr > <td>Waga</td> <td>").append(person.getWeight()).append("</td></tr>\n");
         str.append("<tr class=\"alt\"> <td>Płeć:</td> <td>").append(person.getSex()).append("</td></tr>\n");
         str.append("<tr ><td>BMI:</td> <td>").append(person.getBmi()).append("</td></tr>\n");   
        }
        str.append("</table>\n");
        str.append("</div>\n");
        return str;
    }
    public void parseHTML(){
        StringBuilder str= new StringBuilder();
        str.append("<!DOCTYPE html>\n");
        str.append("<html>\n");
        str.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"  />\n");
        str.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" /> \n");
        str.append("<meta charset=\"utf-8\">\n");
        str.append("<body>\n");
        str.append("<div class=\"w3-container\" >\n");
        str.append("<div class=\"w3-card-4\" style=\"width:100%\">\n");
        str.append("<header class=\"w3-container w3-blue\">\n");
        str.append("<h3 align=\"center\">Dane pacjenta</h3>\n");
        str.append("</header>\n");
        str.append("<div class=\"w3-container\">\n");
        str.append(makePersonInTable());
        str.append("</div>\n");
        str.append("</div>\n");
        str.append("</div>\n");
        str.append("</body>\n");
        
        str.append("</html>\n");
        text=str;
    }
}

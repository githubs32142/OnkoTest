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
    public StringBuilder textCss;

    public DiagnozeHTML(ObservableList<CancerFamilly> cancerFamilly, Person person, ObservableList<String> dataFactors, ObservableList<String> dataSymptoms) {
        this.cancerFamilly = cancerFamilly;
        this.person = person;
        this.dataFactors = dataFactors;
        this.dataSymptoms = dataSymptoms;
        text = new StringBuilder();
        textCss = new StringBuilder();
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

    private StringBuilder makePersonInTable() {
        StringBuilder str = new StringBuilder();
        str.append("<table>\n");
        if (person.getEmail().isEmpty()) {
            str.append("<tr><td>Imię</td> <td>").append(person.getName()).append("</td></tr>\n");
            str.append("<tr class=\"alt\"> <td>Nazwisko</td> <td>").append(person.getSurName()).append("</td></tr>\n");
            str.append("<tr > <td>Wiek</td> <td>").append(person.getAge()).append("</td></tr>\n");
            str.append("<tr class=\"alt\"> <td>Wzrost</td> <td>").append(person.getHeight()).append("</td></tr>\n");
            str.append("<tr > <td>Waga</td> <td>").append(person.getWeight()).append("</td></tr>\n");
            str.append("<tr class=\"alt\"> <td>Płeć:</td> <td>").append(person.getSex()).append("</td></tr>\n");
            str.append("<tr ><td>BMI:</td> <td>").append(person.getBmi()).append("</td></tr>\n");
        } else {
            str.append("<tr class=\"alt\"> <td>E-mail</td> <td>").append(person.getEmail()).append("</td></tr>\n");
            str.append("<tr > <td>Wiek</td> <td>").append(person.getAge()).append("</td></tr>\n");
            str.append("<tr class=\"alt\"> <td>Wzrost</td> <td>").append(person.getHeight()).append("</td></tr>\n");
            str.append("<tr > <td>Waga</td> <td>").append(person.getWeight()).append("</td></tr>\n");
            str.append("<tr class=\"alt\"> <td>Płeć:</td> <td>").append(person.getSex()).append("</td></tr>\n");
            str.append("<tr ><td>BMI:</td> <td>").append(person.getBmi()).append("</td></tr>\n");
        }
        str.append("</table>\n");
        return str;
    }

    public void parseHTML() {
        StringBuilder str = new StringBuilder();
        str.append("<!DOCTYPE html>\n");
        str.append("<html>\n");
        textCss.append(str);
        str.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"styl.css\"c=r_963\" /> \n");
        str.append("<meta charset=\"utf-8\"/>\n");
        str.append("<body>\n");
        str.append("<table>\n");
        str.append("<tr class=\"alt\"> <td><h1> Dane Pacjenta:</h1></td> </tr>\n");
        str.append("</table>");
        str.append(makePersonInTable());
        str.append("<table>\n");
        str.append("<br/>\n");
        str.append("<br/>\n");
        str.append("<tr class=\"alt\"> <td><h1> Czynniki ryzyka:</h1></td> </tr>\n");
        str.append("</table>");
        str.append(createFactor());
        str.append("</body>\n");
        str.append("</html>\n");
        text = str;
        textCss.append("<meta charset=\"utf-8\"/>\n");
        textCss.append("<body>\n");
        textCss.append(addCSS());
        textCss.append("<table>\n");
        textCss.append("<tr class=\"alt\"> <td><h1> Dane Pacjenta:</h1></td> </tr>\n");
        textCss.append("</table>");
        textCss.append(makePersonInTable());
        textCss.append("<br/>\n");
        textCss.append("<br/>\n");
        textCss.append("<table>\n");
        textCss.append("<tr class=\"alt\"> <td><h1> Czynniki ryzyka:</h1></td> </tr>\n");
        textCss.append("</table>");
        textCss.append(createFactor());
        textCss.append("</body>\n");
        textCss.append("</html>\n");

    }

    private StringBuilder addCSS() {
        StringBuilder str = new StringBuilder();
        str.append("<style type=\"text/css\">\n");
        str.append("h1 {\n");
        str.append("text-align: center;\n");
        str.append("color:#2196F3;\n");
        str.append("font-family:\"Segoe UI\",Arial,sans-serif;\n");
        str.append("font-weight:400;\n");
        str.append("font-size: 22px;\n");
        str.append("margin:10px 0;\n");
        str.append("padding: 20px 20px;\n");
        str.append("border-right: 2px solid #6499F4;\n");
        str.append("overflow: hidden; \n");
        str.append("width: 80%;\n");
        str.append("}\n");
        str.append("table { border: 2px solid #6499F4; text-align: left; width: 100%; font: normal 12px/150% Verdana, Arial, Helvetica, sans-serif; background: #fff; overflow: hidden; -webkit-border-radius: 20px; -moz-border-radius: 20px; border-radius: 20px; width: 80%;}\n");
        str.append("th { padding: 20px 20px; }\n");
        str.append("td { color: #00557F; font-size: 18px;border-bottom: 2px solid #447FAB; border-right: 2px solid #6499F4;font-weight: normal;  padding: 20px 20px;}\n");
        str.append(".alt td { background: #E1EEf4; color: #00557F; }\n");
        str.append("td:first-child { border-left: none; }\n");
        str.append("tr:last-child td { border-bottom: none;}\n");
        str.append("label {\n");
        str.append("float:left;\n");
        str.append("width:25%;\n");
        str.append("margin-right:0.5em;\n");
        str.append("padding-top:0.2em;\n");
        str.append("text-align:right;\n");
        str.append("font-weight:bold;\n");
        str.append("}\n");
        str.append("</style>\n");
        return str;
    }

    private StringBuilder createFactor() {
        StringBuilder str = new StringBuilder();
        str.append("<table>\n");
        for (int i = 0; i < dataFactors.size(); i++) {
            if (i % 2 == 0) {
                str.append("<tr> <td>").append(dataFactors.get(i)).append("</td></tr>\n");
            } else {
                str.append("<tr class=\"alt\"> <td>").append(dataFactors.get(i)).append("</td></tr>\n");
            }
        }
        str.append("</table>\n");
        return str;
    }
}

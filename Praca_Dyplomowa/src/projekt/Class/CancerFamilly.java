/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Class;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Admin
 */
public class CancerFamilly {   
        private SimpleStringProperty cancer;
        private SimpleStringProperty familly;

    public CancerFamilly(String cancer, String familly) {
        this.cancer =  new SimpleStringProperty(cancer);
        this.familly =  new SimpleStringProperty(familly);
    }
    
    public String getCancer() {
        return cancer.get();
    }
    
    public String getFamilly(){
        return familly.get();
    }
    
    public void setCancer(String cancer){
        this.cancer =  new SimpleStringProperty(cancer);
    }
    
    public void setFamilly(String familly){
        this.familly =  new SimpleStringProperty(familly);
    }
    
}

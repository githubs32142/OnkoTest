/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Class;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Andrzej Kierepka
 */
public class ListFibre {
    List<ProductFibre> listFibre = new ArrayList<>();
    public ListFibre() {
    }
static String readInput(String path) {
    StringBuilder buffer = new StringBuilder();
    try {
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis,"UTF8");
        Reader in = new BufferedReader(isr);
        int ch;
        while ((ch = in.read()) > -1) {
            buffer.append((char)ch);
        }
        in.close();
        return buffer.toString();
    } 
    catch (IOException e) {
        e.printStackTrace();
        return null;
    }
    }
    public void readData(String path){
        String line= readInput(path);
        StringTokenizer st = new StringTokenizer(line, "-;");
        boolean count=true;
        while(st.hasMoreElements()){
            if(count){
                listFibre.add(new ProductFibre());
                listFibre.get(listFibre.size()-1).setNameProduct(st.nextElement().toString());
            }
            else{
                 listFibre.get(listFibre.size()-1).setFibre(Double.valueOf(st.nextElement().toString()));
            }
            count=!count;
        }
    }
    public int size(){
        return listFibre.size();
    }

    public int searchProduct(String name){
        for(int i=0;i<listFibre.size();i++){
            if(listFibre.get(i).getNameProduct().equals(name)){
                return i;
            }
        }
        return -1;
    }
    public String getNameProduct(int i) {
        return listFibre.get(i).getNameProduct();
    }
    public void setNameProduct(int i,String nameProduct) {
        listFibre.get(i).setNameProduct(nameProduct);
    }
    
    public ProductFibre getFibre(int i){
        return listFibre.get(i);
    }
    public void setFibre(int i,Double fibre) {
        listFibre.get(i).setFibre(fibre);
    }
    
    public Double getWeight(int i) {
        return listFibre.get(i).getWeight();
    }
    public void setWeight(int i,Double weight) {
        listFibre.get(i).setWeight(weight);
    }
}
//pr.readData("src/projekt/Data/jeżyna.txt");

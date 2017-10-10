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
import projekt.Interface.Operation;
import projekt.Interface.ReadData;

/**
 *
 * @author Admin
 */
public class FCList implements ReadData,Operation{
    
    List<FCObject> list = new ArrayList<>();
    public FCList(String path) {
        readData(path);
    }

    
    @Override
    public String readInput(String path) {
        StringBuilder buffer = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis, "UTF8");
            try (Reader in = new BufferedReader(isr)) {
                int ch;
                while ((ch = in.read()) > -1) {
                    buffer.append((char) ch);
                }
            }
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void readData(String path) {
         list.clear();
        String line = readInput(path);
        StringTokenizer st = new StringTokenizer(line, "-;:");
        int count = 0;
        while (st.hasMoreElements()) {
            switch(count%3){
                case 0: {
                    list.add(new FCObject());
                    list.get(list.size()-1).setFamily(st.nextElement().toString());
                }break;
                case 1:{
                    list.get(list.size()-1).setCancer(st.nextElement().toString());
                }break;
                case 2:{
                    list.get(list.size()-1).setAlians(st.nextElement().toString());
                }break;
            }
            count++;
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i =0 ; i < list.size() ; i++ ){
            str.append(list.get(i).getFamily()).append(" ").append(list.get(i).getCancer()).append(" ").append(list.get(i).getAlians());
        }
        return str.toString();
    }

    @Override
    public boolean contains(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String makeAssert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void makeOperation(List<String> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int size(){
        return this.list.size();
    }
    public String getAlias(int i){
        return list.get(i).getAlians();
    }
}

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
import projekt.Interface.*;
/**
 *
 * @author Admin
 */
public class ListRiskFactor implements ReadData,Operation {

    private List<RiskFactor> listRiskFactor = new ArrayList<>();

    public ListRiskFactor() {
    }

    public ListRiskFactor(String path) {
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
        listRiskFactor.clear();
        String line = readInput(path);
        StringTokenizer st = new StringTokenizer(line, "-\n");
        boolean count = true;
        while (st.hasMoreElements()) {
            if (count) {
                listRiskFactor.add(new RiskFactor());
                listRiskFactor.get(listRiskFactor.size() - 1).setFactor(st.nextElement().toString());
            } else {
                listRiskFactor.get(listRiskFactor.size() - 1).setAlias(st.nextElement().toString());
               // System.out.println(listRiskFactor.get(listRiskFactor.size() - 1).getAlias());
                //  System.out.println(st.nextElement().toString());
            }
            count = !count;
        }
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        for(int i =0; i<listRiskFactor.size() ; i++){
            str.append(listRiskFactor.get(i).getAlias()).append("\n");
        }
        System.out.println(listRiskFactor.size());
        return str.toString();

    }

    @Override
    public boolean contains(String s) {
        for(int i = 0; i< listRiskFactor.size();i++){
            if(listRiskFactor.get(i).getFactor().equals(s))
                return true;
        }
        return false;
    }

    @Override
    public String makeAssert() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       StringBuilder str = new StringBuilder();
       for(int i =0 ; i < listRiskFactor.size() ; i ++){
           str.append(listRiskFactor.get(i).getFactor()).append(" ").append(listRiskFactor.get(i).isAdded()).append("\n");
       }
       return str.toString();
    }

    @Override
    public void makeOperation(List<String> list) {
        for(int i =0 ; i < listRiskFactor.size(); i++){
            for(int j=0 ;j<list.size();j++){
                if(listRiskFactor.get(i).getFactor().equals(list.get(j))){
                    listRiskFactor.get(i).setIsAdded(true);
                }
            }
        }
        for(int i =0; i<listRiskFactor.size();i++){
            System.out.println("Czynnik ryzyka:"+ listRiskFactor.get(i).getFactor()+ " jest "+String.valueOf(listRiskFactor.get(i).isAdded()) );
        }
    }
}

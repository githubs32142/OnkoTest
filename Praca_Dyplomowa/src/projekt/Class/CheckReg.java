/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Class;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Andrzej Kierepka
 */
public class CheckReg {
    public static boolean checkEmail(String mail){
        Pattern  p=  Pattern.compile("[a-zA-Z0-9-_.]+@[a-z0-9-.]+.[a-z0-9]{1,4}");
        Matcher matcher = p.matcher(mail);
        return matcher.matches(); //zwraca true lub false
    }
        public static boolean checkWord(String word){
        Pattern  p=  Pattern.compile("[A-Z]{1}[a-z]+");
        Matcher matcher = p.matcher(word);
        return matcher.matches(); //zwraca true lub false
    }
        public static boolean checkHeightMetr(String word){
        Pattern  p=  Pattern.compile("[0-2]{1}[.]{1}[0-9]{2}");
        Matcher matcher = p.matcher(word);
        return matcher.matches(); //zwraca true lub false
    }
        public static boolean checkHeightCent(String word){
        Pattern  p=  Pattern.compile("[1-9]{1}[0-9]{2}");
        Matcher matcher = p.matcher(word);
        return matcher.matches(); //zwraca true lub false
    }
        public static boolean checkWeight(String word){
        Pattern  p=  Pattern.compile("[1-9]{1}[0-9]{1,2}[.]{1}[0-9]*");
        Matcher matcher = p.matcher(word);
        return matcher.matches(); //zwraca true lub false
    }
}

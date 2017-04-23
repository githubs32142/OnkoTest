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
public class CheckEMail {
    public static boolean checkEmail(String mail){
        Pattern  p=  Pattern.compile("[a-zA-Z0-9-_.]+@[a-z0-9-.]+.[a-z0-9]{1,4}");
        Matcher matcher = p.matcher("cdsa@o2.pl");
        return matcher.matches(); //zwraca true lub false
    }
}

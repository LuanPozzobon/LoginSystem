package loginsystem.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luanp
 */
public class InputReader {
    private Scanner sc = null;
    
    public InputReader(){
        try {
            sc = new Scanner(new BufferedReader(new InputStreamReader(System.in, "UTF-8")));
        } catch (UnsupportedEncodingException ex) {
           Logger.getLogger(InputReader.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
    }
    
    public String getNextLine(){
        return sc.nextLine();
    }
    
    public int getNextInt(){
        return Integer.parseInt(sc.nextLine());
    }
    
    public char getNext(){
        return sc.nextLine().charAt(0);
    }
}
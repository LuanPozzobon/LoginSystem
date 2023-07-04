package loginsystem.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import loginsystem.models.Users;

/**
 *
 * @author luanp
 */
public class UsersList {
        private Object openFile(char flag){
        if(flag == 'r'){
            try {
                return new BufferedReader(new InputStreamReader(new FileInputStream("./users.txt"), "UTF-8"));
            } catch(FileNotFoundException ex){
                System.out.println("File not found!");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(flag == 'w'){
            try{
                return new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./users.txt"), "UTF-8"));
            } catch (FileNotFoundException ex) {
                System.out.println("File not found!");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    public List<Users> readFile(){
        
        BufferedReader file = (BufferedReader) openFile('r');
        String line;
        List<Users> users = new ArrayList<>();
        
        if(file == null){
            return users;
        }
        
        try {
            while((line = file.readLine()) != null){
                users.add(new Users(line));
            }
            
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }
    
    public boolean writeFile(List<Users> users){
        try {
            BufferedWriter file = (BufferedWriter) openFile('w');
            
            if(file == null){
                return false;
            }
            
            for(Users user : users){
                file.write(user.toString());
            }
            file.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public int searchName(List<Users> users, String username){
        if(users == null){
            return -1;
        }
        
        for(int i = 0; i < users.size(); ++i){
            Users user = users.get(i);
            if(user.getUsername().equals(username)){
                return i;
            }
        
        }
        return -1;
    }
}

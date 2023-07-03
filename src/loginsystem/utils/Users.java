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

/**
 *
 * @author luanp
 */
public class Users{
    
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
    
    public List<String> readFile(){
        
        BufferedReader file = (BufferedReader) openFile('r');
        String line;
        List<String> users = new ArrayList<>();
        
        if(file == null){
            return users;
        }
        
        try {
            while((line = file.readLine()) != null){
                users.add(line);
            }
            
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }
    
    public boolean writeFile(List<String> users){
        try {
            BufferedWriter file = (BufferedWriter) openFile('w');
            
            if(file == null){
                return false;
            }
            
            for(String user : users){
                file.write(user + '\n');
            }
            file.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public int searchName(List<String> users, String username){
        if(users == null){
            return -1;
        }
        for(int i = 0; i < users.size(); ++i){
            String name = "";
            for(int j = 0; users.get(i).charAt(j) != ','; ++j){
                name += users.get(i).charAt(j);
            }
            if(name.equals(username)){
                return i;
            }
        }
        
        return -1;
    }
    
    public boolean verifyPassword(List<String> users, int index, String username, String password){
        String tmp = "";
        for(int i = username.length() + 1; users.get(index).charAt(i) != ','; ++i){
            tmp += users.get(index).charAt(i);
        }
        return password.equals(tmp);
    }
    
    public void changePassword(List<String> users, String username, String password){
        int index = searchName(users, username);
        String userType = getUserType(users, username);
        users.set(index, username + ',' + password + ',' + userType);
    }
    
    public String getUserType(List<String> users, String username){
        int index = searchName(users, username);
        String user = users.get(index);
        String userType = "";
        int i;
        for(i = username.length() + 1; user.charAt(i) != ','; ++i){}
        for(i = i + 1; i < user.length(); ++i){
            userType += user.charAt(i);
        }
        return userType;
    }
}

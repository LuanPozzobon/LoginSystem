package loginsystem.models;

/**
 *
 * @author luanp
 */
public class Users{
    private String username = "";
    private String password = "";
    private String userType = "";
    
    public Users(){}
    
    public Users(String username, String password, String userType){
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
    
    private Users(Users user, String newPassword){
        Users updatedUser = new Users(user.username, user.password, user.userType);
        updatedUser.password = newPassword;
    }
   
    public Users(String line){
        int i;
        for(i = 0; line.charAt(i) != ','; ++i){
            username += line.charAt(i);
        }
        for(i = i + 1; line.charAt(i) != ','; ++i){
            password += line.charAt(i);
        }
        for(i = i + 1; i < line.length(); ++i){
            userType += line.charAt(i);
        }
        
    }
    
    private void parseUsername(String line){
        for(int i = 0; line.charAt(i) != ','; ++i){
            username += line.charAt(i);
        }
    }
    
    private void parsePassword(String line){
        for(int i = username.length() + 1; line.charAt(i) != ','; ++i){
            password += line.charAt(i);
        }
    }
    
    private void parseUserType(String line){
        for(int i = password.length() + 1; i < line.length(); ++i){
            userType += line.charAt(i);
        }
    }
    
    public boolean verifyPassword(String password){
        return this.password.equals(password);
        
    }
    
    public void changePassword(String newPassword){
        setPassword(newPassword);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    @Override
    public String toString() {
        return getUsername() + ',' + getPassword() + ',' + getUserType() + '\n';
    }
}

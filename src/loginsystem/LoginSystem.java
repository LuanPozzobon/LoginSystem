package loginsystem;

import loginsystem.models.UserTypes;
import loginsystem.models.Users;
import java.util.List;
import loginsystem.utils.*;

/**
 *
 * @author luanp
 */
public class LoginSystem {
    private static UsersList file = new UsersList();
    private static List<Users> usersList = file.readFile();
    private static final InputReader sc = new InputReader();
    
    
    public static void main(String[] args){
        String username, password, userType;
        
        while(true){
            System.out.println("Bem-Vindo ao sistema!");
            System.out.println("Faça login ou crie uma conta para acessar o sistema!");
            System.out.println("01 - Login");
            System.out.println("02 - Cadastrar");
            System.out.println("03 - Sair");

            
            switch(sc.getNextInt()){
                case 1:
                    if(usersList.isEmpty()){
                        System.out.println("0 Registered Users!");
                        break;
                    }
                    System.out.print("Username: ");
                    username = sc.getNextLine();
                    
                    int index = -1;
                    while((index = file.searchName(usersList, username)) < 0){
                        System.out.println("Username doesn't exists!");
                        System.out.print("Username: ");
                        username = sc.getNextLine();
                    }
                    
                    System.out.print("Password: ");
                    password = sc.getNextLine();
                    
                    Users user = usersList.get(index);
                    while(!(user.verifyPassword(password))){
                        System.out.println("Invalid Password!");
                        System.out.print("Passowrd: ");
                        password = sc.getNextLine();
                    }
                    
                    System.out.println("Bem-vindo " + username + '!');
                    mainMenu(user);
                    break;
                case 2:
                    System.out.print("Username: ");
                    username = sc.getNextLine();
                    while(file.searchName(usersList, username) != -1){
                        System.out.println("Username already exists!");
                        System.out.print("Username: ");
                        username = sc.getNextLine();
                    }
                    
                    do{
                        System.out.print("Password: ");
                        password = sc.getNextLine();
                        System.out.print("Confirm Password: ");
                    } while(!(sc.getNextLine().equals(password)));
                    
                    System.out.println("User Type: ");
                    for(UserTypes u : UserTypes.values()){
                        System.out.println(u.toString());
                    }
                    userType = sc.getNextLine();
                    
                    usersList.add(new Users(username, password, userType));
                    
                    break;
                case 3:
                    file.writeFile(usersList);
                    System.exit(0);
                default:
                    System.out.println("Opção Inválida! Tente Novamente!");
                    break;
            }
        }
    }
    
    public static void mainMenu(Users user){
        while(true){
            System.out.println("01 - Configurações da Conta");
            System.out.println("02 - Comprar (Usuário)");
            System.out.println("03 - Relatórios (Funcionário)");
            System.out.println("04 - Gerenciar usuários (Administrador)");
            System.out.println("05 - Sair");

            switch(sc.getNextInt()){
                case 1:
                    accountConfig(user);
                    break;
                case 2:
                    // TODO
                    break;
                case 3:
                    if(user.getUserType().equals("USER")){
                        System.out.println("Permission denied!");
                    } else{
                        // TODO
                    }
                    break;
                case 4:
                    if(user.getUserType().equals("MANAGER")){
                        // TODO
                    } else {
                        System.out.println("Permission denied!");
                    }
                    break;

                case 5:
                    return;
            }
        }
    }
    
    public static void accountConfig(Users user){
        System.out.println("Username: " + user.getUsername());
        System.out.println("01 - Alterar Senha");
        System.out.println("02 - Voltar");
        
        switch(sc.getNextInt()){
            case 1:
                String password;
                do{
                    System.out.print("New Password: ");
                    password = sc.getNextLine();
                    System.out.print("Confirm Password: ");
                } while(!(sc.getNextLine().equals(password)));
                user.changePassword(password);
                usersList.set(file.searchName(usersList, user.getUsername()), user);
                
                break;
            case 2:
                break;
        }
    }   
}
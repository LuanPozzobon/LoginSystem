package loginsystem;

import java.util.List;
import loginsystem.utils.*;

/**
 *
 * @author luanp
 */
public class LoginSystem {
    private static String username, password, userType;
    private static final Users users = new Users();
    private static List<String> usersList = users.readFile();
    private static final InputReader sc = new InputReader();
    
    
    public static void main(String[] args){
        
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
                    while((index = users.searchName(usersList, username)) < 0){
                        System.out.println("Username doesn't exists!");
                        System.out.print("Username: ");
                        username = sc.getNextLine();
                    }
                    
                    System.out.print("Password: ");
                    password = sc.getNextLine();
                    
                    while(!(users.verifyPassword(usersList, index, username, password))){
                        System.out.println("Invalid Password!");
                        System.out.print("Passowrd: ");
                        password = sc.getNextLine();
                    }
                    
                    System.out.println("Bem-vindo " + username + '!');
                    userType = users.getUserType(usersList, username);
                    
                    mainMenu();
                    break;
                case 2:
                    System.out.print("Username: ");
                    username = sc.getNextLine();
                    while(users.searchName(usersList, username) != -1){
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
                    
                    usersList.add(username + ',' + password + ',' + userType);
                    
                    break;
                case 3:
                    users.writeFile(usersList);
                    System.exit(0);
                default:
                    System.out.println("Opção Inválida! Tente Novamente!");
                    break;
            }
        }
    }
    
    public static void mainMenu(){
        while(true){
            System.out.println("01 - Configurações da Conta");
            System.out.println("02 - Comprar (Usuário)");
            System.out.println("03 - Relatórios (Funcionário)");
            System.out.println("04 - Gerenciar usuários (Administrador)");
            System.out.println("05 - Sair");

            switch(sc.getNextInt()){
                case 1:
                    accountConfig();
                    break;
                case 2:
                    // TODO
                    break;
                case 3:
                    if(userType.equals("USER")){
                        System.out.println("Permission denied!");
                    } else{
                        System.out.println(userType);
                        // TODO
                    }
                    break;
                case 4:
                    if(userType.equals("MANAGER")){
                        // TODO
                    } else {
                        System.out.println("Permission denied!");
                    }
                    break;

                case 5:
                    username = "";
                    password = "";
                    userType = "";
                    return;
            }
        }
    }
    
    public static void accountConfig(){
        System.out.println("Username: " + username);
        System.out.println("01 - Alterar Senha");
        System.out.println("02 - Voltar");
        
        switch(sc.getNextInt()){
            case 1:
                do{
                    System.out.print("New Password: ");
                    password = sc.getNextLine();
                    System.out.print("Confirm Password: ");
                } while(!(sc.getNextLine().equals(password)));
                users.changePassword(usersList, username, password);
                break;
            case 2:
                break;
        }
    }
}
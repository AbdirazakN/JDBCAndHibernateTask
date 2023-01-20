package peaksoft;

import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import peaksoft.util.Util;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        User user = new User();
//        user.setName("Alim");
//        user.setLastName("Alibaev");
//        user.setAge((byte)18);

//        System.out.println(user);

        UserService service = new UserServiceImpl();
        while (true) {
            System.out.println("""
              <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< COMMANDS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                                                        || 1 ||  ---  Create table
                                                        || 2 ||  ---  Delete table
                                                        || 3 ||  ---  Save User
                                                        || 4 ||  ---  Remove User
                                                        || 5 ||  ---  Get all Users
                                                        || 6 ||  ---  Clean table
                                                   
                                                        || 0 ||  ---  Exit program
                                                        """);
            System.out.print("Enter commands:");
            int com = new Scanner(System.in).nextInt();
            if (com==1) service.createUsersTable();
            if (com==2) service.dropUsersTable();
            if (com==3){System.out.println("Enter name,surname and age user's to save: "); service.saveUser(new Scanner(System.in).nextLine(),new Scanner(System.in).nextLine(),new Scanner(System.in).nextByte());}
            if (com==4){System.out.println("Enter ID user's to Remove: "); service.removeUserById(new Scanner(System.in).nextLong());}
            if (com==5) System.out.println(service.getAllUsers());
            if (com==6) service.cleanUsersTable();
            if (com==0) break;
        }

    }
}

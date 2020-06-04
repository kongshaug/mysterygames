/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.EntityFactoryImpl;
import entities.interfaces.EntityFactory;
import entities.interfaces.User;
import errorhandling.NotFoundException;
import java.util.List;


/**
 *
 * @author sofieamalielandt
 */
public class testFacade {
    
    public static void main(String[] args) throws NotFoundException {
        
       EntityFactory e = EntityFactoryImpl.getFactory();
       
//       e.addUser("bømand");
//       List<User> users = e.getAllUsers();
//       
//       users.forEach((user) -> {
//           System.out.println(user.getUsername());
//        });
//        
//       User user = e.getUser("sveske");
//        System.out.println(user.getUsername());
//        
//        User other = e.getUser(user.getId());
//        System.out.println(other.getUsername());
        

       
       
       
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FunctionDAO;
import dao.UserDAO;
import java.util.ArrayList;
import model.Function;
import model.User;
import view.NewUserView;

/**
 *
 * @author Formation
 */
public class NewUserController {

    boolean isValid = true;
    String errorMessage = "";
    
    private UserDAO userDAO;
    
//------------------------------------------------------------------------------
    
    public NewUserController(UserDAO userDAO) {
       
        this.userDAO = userDAO;
    }

//------------------------------------------------------------------------------
    // create new user with informations
    
    public boolean CreateNewUser(NewUserView newUserView) {     
        
        User user = new User();
        
    
        String pseudo = newUserView.getTf_pseudo().getText();
        String password = newUserView.getPf_password().getText();
        String firstname = newUserView.getTf_firstname().getText();
        String lastname = newUserView.getTf_lastname().getText();
        String mail = newUserView.getTf_mail().getText();
        String tel = newUserView.getTf_tel().getText();
        String address = newUserView.getTf_address().getText();
        String city = newUserView.getTf_city().getText();
        String country = newUserView.getTf_country().getText();
    
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setMail(mail);
        user.setTel(tel);
        user.setAddress(address);
        user.setCity(city);
        user.setCountry(country);
    
    // if fields are empty
       if( pseudo.equals("") || password.equals("") ||firstname.equals("") || 
           lastname.equals("") || mail.equals("") ||
          tel.equals("") || address.equals("") ||
           city.equals("") || country.equals("") ) {
        
       }else {
     
    // else create user       
           user =  userDAO.create(user);
       }
        boolean result = this.userDAO.isValid(user);
     
    return result;     
  
}
//------------------------------------------------------------------------------    
    // ArrayList comboBox for function user
    
    public ArrayList<String> addComboBox(){
    
        ArrayList<String> listFunctions = new ArrayList<>();
        FunctionDAO functionDAO = new FunctionDAO();
        ArrayList <Function> functions = new ArrayList<>();
    
        functions = functionDAO.getAll();
    
        listFunctions.add("");   
    
        for(int i = 0; i < functions.size(); i++){
        listFunctions.add(functions.get(i).getDefinition());
    }
    return listFunctions;
}
}

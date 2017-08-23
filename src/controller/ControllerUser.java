/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dao.AccessBackofficeDAO;
import dao.AccessSiteDAO;
import dao.FunctionDAO;
import dao.UserDAO;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import model.AccessBackoffice;
import model.AccessSite;

import model.Function;
import model.User;
import view.UseurView;

/**
 *
 * @author Formation
 */


public class ControllerUser {

    
    private UserDAO userDAO ;

    public ControllerUser(UserDAO userDao) {
        this.userDAO=userDao;
    }
    
   

    public JTable addRowTable(JTable jt_listUser) {
        
        DefaultTableModel model = (DefaultTableModel) jt_listUser.getModel();
        ArrayList<User> list_User = userDAO.getAllDetails();
        Object rowData[] = new Object[13];

        for (User user : list_User) {

            rowData[0] = user.getId();
            rowData[1] = user.getFirstname();
            rowData[2] = user.getLastname();
            rowData[3] = user.getAddress();
            rowData[4] = user.getCity();
            rowData[5] = user.getCountry();
            rowData[6] = user.getTel();
            rowData[7] = user.getMail();
            rowData[8] = user.getFunction();
            rowData[9] = user.getNickname_back();
            rowData[10] = user.isIsBlocked();
            rowData[11] = user.isIsAdmin();           
            rowData[12] = user.getNickname_site();
     

            model.addRow(rowData);
        }
        return jt_listUser;
    }
     public void UserInfo(UseurView userView, Long id) {
        User user = this.userDAO.find(id);
        
        
    
        ArrayList<String> values = new ArrayList<>();
        ArrayList<Boolean> valuesBollean = new ArrayList<>();
       
        
        // Add all values in ArraList
        
        values.add(String.valueOf(user.getFirstname()));
        values.add(String.valueOf(user.getLastname()));
        values.add(String.valueOf(user.getAddress()));
        values.add(String.valueOf(user.getCity()));
        values.add(String.valueOf(user.getCountry()));
        values.add(String.valueOf(user.getTel()));
        values.add(String.valueOf(user.getMail()));
        values.add(String.valueOf(userDAO.findDefinition(id)));
       
        values.add(String.valueOf(user.getNickname_back()));
        
        values.add(String.valueOf(user.getNickname_site()));
        valuesBollean.add(user.isIsBlocked());
        valuesBollean.add(user.isIsAdmin());
        
        userView.updateUserInfo(values,valuesBollean);
         
        
    }
    
   public void delete(Long id){
        User user = this.userDAO.find(id);
        userDAO.delete(user.getId());
    }
   public void addCombobox(JComboBox cb_Metier) {

        // ************************ ADD Function
        FunctionDAO functionDAO = new FunctionDAO();
        ArrayList<Function> metier = new ArrayList<>();

        metier = functionDAO.getAll();

        cb_Metier.addItem("");
        cb_Metier.addItem("null");
        

        for (int i = 0; i < metier.size(); i++) {
            cb_Metier.addItem(metier.get(i).getDefinition());

        }

    }
   public void updatamdp(Long id, String password) {
       AccessBackofficeDAO accessBackofficeDAO = new AccessBackofficeDAO();
       AccessSiteDAO accessSiteDAO = new AccessSiteDAO();
       if ( accessSiteDAO.find(id).getUser_id() != 0){
           AccessSite as = accessSiteDAO.find(id);
           as.setPassword(password);
           accessSiteDAO.update(as);
       }else if ( accessBackofficeDAO.find(id).getUser_id() != 0){
           AccessBackoffice ab = accessBackofficeDAO.find(id);
           ab.setPassword(password);
           ab.setHasChanged(true);
           ab.setIsBlocked(false);
           accessBackofficeDAO.update(ab);
       }
       
   }
     public boolean updateUser(ArrayList<String> userFields, ArrayList<Boolean> userFieldsB, Long id) {
        AccessBackofficeDAO accessBackofficeDAO = new AccessBackofficeDAO();
        AccessSiteDAO accessSiteDAO = new AccessSiteDAO();
        FunctionDAO functiondao = new FunctionDAO();
        
        User user = this.userDAO.find(id);
        AccessBackoffice ab = accessBackofficeDAO.find(id);
        AccessSite as = accessSiteDAO.find(id);
        
        user.setFirstname(userFields.get(0));
        user.setLastname(userFields.get(1));
        user.setFunction(functiondao.find(userFields.get(4)));
        user.setCity(userFields.get(5));
        user.setCountry(userFields.get(6));
        user.setTel(userFields.get(7));
        user.setMail(userFields.get(8));
        user.setAddress(userFields.get(9));
        
        if ( accessSiteDAO.find(id).getUser_id() != 0){
            as.setNickname(userFields.get(3));
            System.out.println(userFields.get(3));
            accessSiteDAO.update(as);
        }else if ( accessBackofficeDAO.find(id).getUser_id() != 0){
            ab.setNickname(userFields.get(2));
            ab.setIsAdmin(userFieldsB.get(0));
            accessBackofficeDAO.update(ab);
        }
        
        return userDAO.update(user);

//        4 getInfo.add(cb_Metier.getSelectedItem().toString());
    }
}

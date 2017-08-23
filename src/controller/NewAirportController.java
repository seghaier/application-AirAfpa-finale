/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AirportDAO;
import model.Airport;
/**
 *
 * @author Formation
 */
public class NewAirportController {

    private AirportDAO aeroportDAO;

    public NewAirportController(AirportDAO aeroportDAO) {
        this.aeroportDAO = aeroportDAO;

    }

   public boolean create(String aita ,String city ,String contry){
       
       boolean resultCreate = true;
        Airport airport = new Airport(aita,city,contry);
        airport = aeroportDAO.create(airport);
        
        if(!aeroportDAO.isValid(airport)){
            resultCreate = false;
        }
        return resultCreate;
    }
    
    
   
    
}

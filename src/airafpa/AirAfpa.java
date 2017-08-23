/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airafpa;

import controller.WelcomeController;
import dao.BookingDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Flight;
import view.WelcomeView;

/**
 *
 * @author Formation
 */
public class AirAfpa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        WelcomeController welcomeController = new WelcomeController();
        
        WelcomeView welcomeView = new WelcomeView(null,true,welcomeController);
        
        welcomeView.setVisible(true);
    }
    
}

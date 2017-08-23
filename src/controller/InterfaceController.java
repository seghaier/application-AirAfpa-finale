/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import view.*;
import controller.*;
import model.*;
import dao.*;
import java.awt.Dimension;
import javax.swing.JPanel;
/**
 *
 * @author Formation
 */
public class InterfaceController {
    AirportDAO airportDAO;
    FlightDAO flightDAO;
    UserDAO userDAO;
    
    
    public InterfaceController(){
    
    
    
    }

    public JPanel airportsDisplay(InterfaceView intView) {
        
        intView.getDisplayPanel().removeAll();

        AirportDAO airportDAO = new AirportDAO();
        
        AirportController airportController = new AirportController(airportDAO);
        
       JPanel airportView = new AirportsView(airportController);
       airportView.setSize(new Dimension (1000, 691));
        
       
       return airportView;
       
    }
    
    
    
    public JPanel newAirportDisplay(InterfaceView intView){
           
       intView.getDisplayPanel().removeAll();
            
       AirportDAO airportDAO = new AirportDAO();
       
       
        NewAirportController airportController = new NewAirportController(airportDAO);
        
       JPanel newAirportView = new NewAirportView(airportController);
       newAirportView.setSize(new Dimension (1000, 691));
        return newAirportView;
    
    }
    
    public JPanel plannedFlightDisplay(InterfaceView intView){
    
    intView.getDisplayPanel().removeAll();
            
       FlightDAO flightDAO = new FlightDAO();
       
       
        FlightController airportController = new FlightController(flightDAO);
        
       JPanel flightView = new FlightView(airportController,true);
       flightView.setSize(new Dimension (1000, 691));
        return flightView;
        
        
    }
    public JPanel waitingFlightDisplay(InterfaceView intView){
    
    intView.getDisplayPanel().removeAll();
            
       FlightDAO flightDAO = new FlightDAO();
       
       
        FlightController airportController = new FlightController(flightDAO);
        
       JPanel flightView = new FlightView(airportController,false);
       flightView.setSize(new Dimension (1000, 691));
        return flightView;
        
        
    }
    
    public JPanel newFlightDisplay(InterfaceView initView){
    
        initView.getDisplayPanel().removeAll();
        FlightDAO flightDAO = new FlightDAO();
       
       
        NewFlightController airportController = new NewFlightController(flightDAO);
        
       JPanel flightView = new NewFlightView(airportController);
       flightView.setSize(new Dimension (1000, 691));
        return flightView;
        
        
    }
        public JPanel newUserDisplay(InterfaceView intView){
    
        intView.getDisplayPanel().removeAll();
        UserDAO userDAO = new UserDAO();
       
       
        NewUserController userController = new NewUserController(userDAO);
        
       JPanel userView = new NewUserView(userController);
       userView.setSize(new Dimension (1000, 691));
        return userView;
        
    }
        public JPanel userDisplay(InterfaceView intView){
    
        intView.getDisplayPanel().removeAll();
        UserDAO userDAO = new UserDAO();
       
       
        ControllerUser userController = new ControllerUser(userDAO);
        
       JPanel userView = new UseurView(userController);
       userView.setSize(new Dimension (1000, 691));
        return userView;
        } 
}

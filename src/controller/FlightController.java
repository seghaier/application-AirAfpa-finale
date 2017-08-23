/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AirportDAO;
import dao.FlightDAO;
import dao.UserDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Airport;
import model.Flight;
import view.FlightView;

/**
 *
 * @author Formation
 */
public class FlightController {
    
    private FlightDAO flightDAO;
    
    /**
     * Constructor
     * @param flightDAO 
     */
    public FlightController(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }
    
    /**
     * fills the table
     * @param jt_listFlight
     * @return jTable full
     */
    public JTable addRowTable(JTable jt_listFlight, boolean isPlanned) {
        DefaultTableModel model = (DefaultTableModel) jt_listFlight.getModel();
        ArrayList<Flight> list_flights = this.flightDAO.getAll();

        Object rowData[] = new Object[11];

        for (Flight flight : list_flights) {
                // Fills planned flight
            if(isPlanned){
                if(flight.isPlanned()){
                    rowData[0] = "DF" + flight.getId();
                    rowData[1] = flight.getDeparting_city();
                    rowData[2] = flight.getDeparting_country();
                    rowData[3] = flight.getDeparting_aita();
                    rowData[4] = flight.getArrival_city();
                    rowData[5] = flight.getArrival_country();
                    rowData[6] = flight.getArrival_aita();
                    rowData[7] = flight.getDeparting_hour().substring(0, 16);
                    rowData[8] = arrivedDate(flight).substring(0, 16);
                    rowData[9] = flight.getDuration();
                    rowData[10] = flight.getPrice();

                    model.addRow(rowData);
                }
                // Fills not planned flight
            } else{
                if(!flight.isPlanned()){
                    rowData[0] = "DF" + flight.getId();
                    rowData[1] = flight.getDeparting_city();
                    rowData[2] = flight.getDeparting_country();
                    rowData[3] = flight.getDeparting_aita();
                    rowData[4] = flight.getArrival_city();
                    rowData[5] = flight.getArrival_country();
                    rowData[6] = flight.getArrival_aita();
                    rowData[7] = flight.getDeparting_hour().substring(0, 16);
                    rowData[8] = arrivedDate(flight).substring(0, 16);;
                    rowData[9] = flight.getDuration();
                    rowData[10] = flight.getPrice();

                    model.addRow(rowData);
                }
            }
        }
        return jt_listFlight;
    }
    
    /**
     * Update a flight
     * @param flightFields
     * @param id
     * @return true if update run
     */
    public boolean updateDB(ArrayList<String> flightFields, Long id) {
        AirportDAO airportDAO = new AirportDAO();
            
            // Find IATA in termes of city  
        String aitaD = airportDAO.findIATA(flightFields.get(0));
        String aitaA = airportDAO.findIATA(flightFields.get(1));
            // Convert from a String in Numbers
        int duration = Integer.parseInt(flightFields.get(3));
        double price = Double.parseDouble(flightFields.get(4));
        long pilote = Long.parseLong(flightFields.get(5));
        long copilote = Long.parseLong(flightFields.get(6));
        long staff1 = Long.parseLong(flightFields.get(7));
        long staff2 = Long.parseLong(flightFields.get(8));
        long staff3 = Long.parseLong(flightFields.get(9));
        
            // Find the flight and update in Model
        Flight flight = this.flightDAO.find(id);
        flight.setDeparting_aita(aitaD);
        flight.setArrival_aita(aitaA);
        flight.setDeparting_hour(flightFields.get(2));
        flight.setDuration(duration);
        flight.setPrice(price);
        flight.setId_pilot(pilote);
        flight.setId_copilot(copilote);
        flight.setId_staff1(staff1);
        flight.setId_staff2(staff2);
        flight.setId_staff3(staff3);
            
            // Update in DB
        return flightDAO.update(flight);
    }
    
    /**
     * Validate a fligh
     * @param id 
     */
    public void validate(Long id){
        Flight flight = this.flightDAO.find(id);
        flight.setPlanned(true);
        flightDAO.update(flight);
    }
    
    /**
     * Delete a flight
     * @param id 
     */
    public void delete(Long id){
        Flight flight = this.flightDAO.find(id);
        flightDAO.delete(flight.getId());
    }
    
    /**
     * Add item on all combox
     * @param cb_cityStart
     * @param cb_cityArrived
     * @param cb_idPilote
     * @param cb_idcopilote
     * @param cb_staff1
     * @param cb_staff2
     * @param cb_staff3 
     */
    public void addCombobox(JComboBox cb_cityStart, JComboBox cb_cityArrived,
        JComboBox cb_idPilote, JComboBox cb_idcopilote, JComboBox cb_staff1, 
        JComboBox cb_staff2, JComboBox cb_staff3) {
        
        // ************************ ADD CITIES
        AirportDAO airportDAO = new AirportDAO();
        ArrayList<Airport> airports = new ArrayList<>();
        
        airports = airportDAO.getAll();
        
        cb_cityStart.addItem("");
        cb_cityArrived.addItem("");
        for(int i = 0; i < airports.size(); i++){
            cb_cityStart.addItem(airports.get(i).getCity());
            cb_cityArrived.addItem(airports.get(i).getCity());
        }
        
        // ************************ ADD PILOTES
        UserDAO userDAO = new UserDAO();
        ArrayList<Long> staff = new ArrayList<>();
        staff = userDAO.getAll("Pilote");
        
        cb_idPilote.addItem("");
        for (int i = 0; i < staff.size(); i++) {
            cb_idPilote.addItem(staff.get(i).toString());
        }
        
        // ************************ ADD COPILOTES
        staff = userDAO.getAll("Co-Pilote");
        
        cb_idcopilote.addItem("");
        for (int i = 0; i < staff.size(); i++) {
            cb_idcopilote.addItem(staff.get(i).toString());
        }
        
        // ************************ ADD STAFF
        staff = userDAO.getAll("Steward");
        staff.addAll(userDAO.getAll("Hôtesse"));

        cb_staff1.addItem("");
        cb_staff2.addItem("");
        cb_staff3.addItem("");
        for (int i = 0; i < staff.size(); i++) {
            cb_staff1.addItem(staff.get(i).toString());
            cb_staff2.addItem(staff.get(i).toString());
            cb_staff3.addItem(staff.get(i).toString());
        }
        
    }
    
    
    /**
     * Add file selected in textfield
     * @param jt_listFlight
     */
    public void flightInfo(FlightView flightView, Long id) {
        Flight flight = this.flightDAO.find(id);
       
        ArrayList<String> values = new ArrayList<>();
        
        // Add all values in ArraList
        values.add("" + flight.getId());
        values.add(String.valueOf(flight.getDeparting_city()));
        values.add(flight.getDeparting_country());
        values.add(flight.getDeparting_aita());
        values.add(String.valueOf(flight.getArrival_city()));
        values.add(flight.getArrival_country());
        values.add(flight.getArrival_aita());
        values.add(flight.getDeparting_hour().substring(0, 10));
        values.add(flight.getDeparting_hour().substring(11, 16));
        values.add(arrivedDate(flight).substring(0, 10));
        values.add(arrivedDate(flight).substring(11, 16));
        values.add("" + flight.getDuration());
        values.add("" + flight.getPrice());
        values.add(String.valueOf(flight.getId_pilot()));
        values.add(String.valueOf(flight.getId_copilot()));
        values.add(String.valueOf(flight.getId_staff1()));
        values.add(String.valueOf(flight.getId_staff2()));
        values.add(String.valueOf(flight.getId_staff3()));
        
            // Update textfield
        flightView.updateFlighInfo(values);
    }
    
    /**
     * Calculate arrival date whith duration
     * @param flight
     * @return the date of arrival
     */
    public String arrivedDate(Flight flight){
            // Take date of the flight
        String arrivedDate = flight.getDeparting_hour();
            // Create new format for this date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        try {
                // Convert departure date to Date format
            Date dateTime = sdf.parse(arrivedDate);
            arrivedDate = sdf.format(dateTime).toString();
            
                // Adds the duration of the flight
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(dateTime);
            c.add(GregorianCalendar.MINUTE, flight.getDuration());
            dateTime = c.getTime();
            
            arrivedDate = sdf.format(dateTime).toString();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return arrivedDate;
        }
        return arrivedDate;
    }
    
    /**
     * 
     * @param id of flight selected
     * @return false is they are booking on flight selected
     */
    public boolean isBooking(Long id){
        Flight flight = this.flightDAO.find(id);
        if(flight.isPlanned()){
            if(flightDAO.isBooked(flight)){
                return false;
            }
        }
        return true;
    }
}

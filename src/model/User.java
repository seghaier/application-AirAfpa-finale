/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author Formation
 */
public class User {

    private long id;
    private String firstname;
    private String lastname;
    private String address;
    private String city;
    private String country;
    private String tel;
    private String mail;
    private int function;
    private boolean isBlocked;
        private boolean isAdmin;
    private String nickname_site;
    private String nickname_back;
    private String definitions ;
    public User() {
        this.id = 0;
        this.firstname = "";
        this.lastname = "";
        this.address = "";
        this.city = "";
        this.country = "";
        this.tel = "";
        this.mail = "";
        this.function = 0;
        this.isBlocked = false;
        this.isAdmin = false;
        this.nickname_site ="";
        this.nickname_back=""; 
    }

    public User(long id, String firstname, String lastname, String address, String city, String country, String tel, String mail, int function, boolean isBlocked,  boolean isAdmin,String nickname_site, String nickname_back,String definitions) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.city = city;
        this.country = country;
        this.tel = tel;
        this.mail = mail;
        this.function = function;
        this.isBlocked = isBlocked;
        this.isAdmin = isAdmin;
        this.nickname_site = nickname_site;
        this.nickname_back = nickname_back;
        this.definitions =definitions;
    }

    public String getDefinitions() {
        return definitions;
    }

    public void setDefinitions(String definitions) {
        this.definitions = definitions;
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getFunction() {
        return function;
    }

    public void setFunction(int function) {
        this.function = function;
    }

    public boolean isIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getNickname_site() {
        return nickname_site;
    }

    public void setNickname_site(String nickname_site) {
        this.nickname_site = nickname_site;
    }

    public String getNickname_back() {
        return nickname_back;
    }

    public void setNickname_back(String nickname_back) {
        this.nickname_back = nickname_back;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", address=" + address + ", city=" + city + ", country=" + country + ", tel=" + tel + ", mail=" + mail + ", function=" + function + ", isBlocked=" + isBlocked + ", isAdmin=" + isAdmin + ", nickname_site=" + nickname_site + ", nickname_back=" + nickname_back + '}';
    }
    
    
}

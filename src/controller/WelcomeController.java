/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.InitialisationView;
import view.InterfaceView;
import view.WelcomeView;
import dao.AccessBackofficeDAO;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import model.AccessBackoffice;

/**
 *
 * @author Formation
 */
public class WelcomeController {

    AccessBackoffice accessBackoffice;
    AccessBackofficeDAO dao;
    int nombreDessaiMDP = 3;
    long currentUserId;
    boolean isAdmin;
    boolean isBlocked;
    boolean connected;
    boolean hasChanged;

    public WelcomeController() {
        this.accessBackoffice = new AccessBackoffice();
        this.dao = new AccessBackofficeDAO();
    }

    public WelcomeController(AccessBackoffice accessBackOffice, AccessBackofficeDAO dao) {
        this.accessBackoffice = accessBackOffice;
        this.dao = dao;
        currentUserId = 0L;
    }

    public boolean isAdmin() {

        return this.accessBackoffice.isIsAdmin();

    }

    public boolean isBlocked() {

        return this.accessBackoffice.isIsBlocked();

    }

    public boolean hasChanged() {

        return this.accessBackoffice.isHasChanged();

    }

    public void testIdentifying(WelcomeView welcome, InitialisationView initView) {

        // recup le nickname
        String nickname = welcome.getTf_nickname().getText();

        //cherche dans la base de donnée cet utilisateur
        AccessBackoffice currentUser = dao.find(nickname);

        // recupère le mot de passe dans la vue pour le traiter
        String passwordToHash = welcome.getTf_password().getText();
        String generatedPassword = null;

        try {

            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        // chick if the current user do not exist 
        if (!dao.isValid(currentUser)) {

            JOptionPane.showMessageDialog(welcome, "Nom d'utilisateur inconnu", "ERREUR LOGIN", JOptionPane.WARNING_MESSAGE);

        } else {

            //compare the generated password (password field haseh) to the current user password
            if (generatedPassword.equals(currentUser.getPassword())) {

                if (currentUser.isIsBlocked()) {
                    JOptionPane.showMessageDialog(welcome, "Votre compte à été bloqué\nVeuillez contacter votre administrateur ", "ERREUR MOT DE PASSE", JOptionPane.ERROR_MESSAGE);
                }

                this.currentUserId = currentUser.getUser_id();
                nombreDessaiMDP = 3;

                //if the account changed since last connexion
                if (currentUser.isHasChanged()) {

                    this.currentUserHasChanged(initView);

                    initView.setVisible(true);

                }
                InterfaceView mainview = new InterfaceView();
                // check if he is an admin to able or not some functions 
                if (!currentUser.isIsAdmin()) {
                   
             
                    mainview.getPb_users().setVisible(false);
                    mainview.getPb_newUser().setVisible(false);
                    mainview.setVisible(true);

                    welcome.dispose();

                } else {
                    mainview.setVisible(true);
                    welcome.dispose();
                }

            } else {
                //if the password is wrong you have 3 try
                if (nombreDessaiMDP > 0) {
                    String erreurMDP = "Mot de passe invalide " + nombreDessaiMDP + " essaies restants";

                    JOptionPane.showMessageDialog(welcome, erreurMDP, "ERREUR MOT DE PASSE", JOptionPane.WARNING_MESSAGE);

                    nombreDessaiMDP--;

                } //if your 3 try are past , your account is blocked
                else {
                    JOptionPane.showMessageDialog(welcome, "Votre compte à été bloqué\nVeuillez contacter votre administrateur ", "ERREUR MOT DE PASSE", JOptionPane.ERROR_MESSAGE);
                    currentUser.setIsBlocked(true);
                    dao.update(currentUser);
                }
            }
        }

    }

    public void currentUserHasChanged(InitialisationView initView) {

        AccessBackoffice currentUser = dao.find(this.currentUserId);

        String newPassword = initView.getTf_newMDP().getText();
        if ((newPassword).equals(initView.getTf_confirmMDP().getText())) {

            currentUser.setHasChanged(false);

            currentUser.setPassword(newPassword);
            //a mettre coté view
            JOptionPane.showMessageDialog(initView, "Votre mot de passe à été modifié pensez à le noter", "Nouveau Mot De Passe", JOptionPane.WARNING_MESSAGE);

            initView.dispose();

            dao.update(currentUser);

        }

    }

}

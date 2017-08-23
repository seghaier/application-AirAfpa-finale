/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import model.User;

/**
 *
 * @author Formation
 */
public class UserDAO extends DAO<User, Long> {

    public UserDAO() {
        super();
    }

    /**
     *
     * Create user
     *
     * @param user
     * @return user object
     */
    @Override
    public User create(User user) {

        User userCreate = new User();
        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "INSERT INTO users ( "
                        + "firstname,\n"
                        + " lastname,\n"
                        + " address,\n"
                        + " city,\n"
                        + " country,\n"
                        + " tel,\n"
                        + " mail,\n"
                        + " function\n"
                        + ") VALUES (?,?,?,?,?,?,?,?)";
                // prepared requete and get return generated key
                PreparedStatement pst = this.bddmanager.getConnectionManager()
                        .prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
                // insert value in requete
                pst.setString(1, user.getFirstname());
                pst.setString(2, user.getLastname());
                pst.setString(3, user.getAddress());
                pst.setString(4, user.getCity());
                pst.setString(5, user.getCountry());
                pst.setString(6, user.getTel());
                pst.setString(7, user.getMail());
                // if it's null
                if (user.getFunction() == 0) {
                    pst.setNull(8, Types.TINYINT);
                } else {
                    pst.setLong(8, user.getFunction());
                }
                // excute insert row in table
                int insert = pst.executeUpdate();
                // if insert in table 
                if (insert != 0) {
                    // get generate key
                    ResultSet id_increment = pst.getGeneratedKeys();
                    // if it's generate key
                    if (id_increment.next()) {
                        // assign key in user object
                        user.setId(id_increment.getInt(1));
                        // assign object user in object return
                        userCreate = user;
                    }

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return userCreate;
            }

        } else {
            return userCreate;
        }

        return userCreate;
    }

    /**
     * Update user
     *
     * @param user
     * @return true is update user, false isn't update
     */
    @Override
    public boolean update(User user) {
        boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "Update users set"
                        + " firstname = ?,\n"
                        + " lastname = ?,\n"
                        + " address = ?,\n"
                        + " city = ?,\n"
                        + " country = ?,\n"
                        + " tel = ?,\n"
                        + " mail = ?,\n"
                        + " function = ?\n"
                        + " WHERE id = ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager
                        .getConnectionManager().prepareStatement(requete);
                // insert value in requete
                pst.setString(1, user.getFirstname());
                pst.setString(2, user.getLastname());
                pst.setString(3, user.getAddress());
                pst.setString(4, user.getCity());
                pst.setString(5, user.getCountry());
                pst.setString(6, user.getTel());
                pst.setString(7, user.getMail());
                // if it's null
                if (user.getFunction() == 0) {
                    pst.setNull(8, Types.TINYINT);
                } else {
                    pst.setLong(8, user.getFunction());
                }
                pst.setLong(9, user.getId());
                // excute update row in table
                int insert = pst.executeUpdate();
                // if insert in table 
                if (insert != 0) {
                    success = true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return success;
            }

        } else {
            return success;
        }
        return success;
    }

    /**
     * delete user
     *
     * @param primary_key
     * @return true is delete user, false isn't delete
     */
    @Override
    public boolean delete(Long primary_key) {
        boolean success = false;

        if (this.bddmanager.connect()) {

            try {

                // create requete 
                String requete = "DELETE FROM users WHERE id = ?";
                // prepared requete 
                PreparedStatement pst = this.bddmanager.getConnectionManager()
                        .prepareStatement(requete);
                // insert value in requete
                pst.setLong(1, primary_key);
                // excute delete row in table
                int insert = pst.executeUpdate();
                // if delete in table 
                if (insert != 0) {
                    success = true;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return success;
            }

        } else {
            return success;
        }
        return success;

    }

    /**
     * get all users
     *
     * @return all users
     */
    @Override
    public ArrayList getAll() {
        // create array list user empty
        ArrayList<User> listUser = new ArrayList<>();
        if (this.bddmanager.connect()) {

            try {
                // create statement 
                Statement st = this.bddmanager
                        .getConnectionManager()
                        .createStatement();
                // create requete 
                String requete = "SELECT * FROM users";
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // insert all users in array object user

                while (rs.next()) {
                    User el = new User(
                            rs.getLong("id"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("address"),
                            rs.getString("city"),
                            rs.getString("country"),
                            rs.getString("tel"),
                            rs.getString("mail"),
                            rs.getInt("function"),
                            false, 
                            false,
                            "",
                            "",
                            rs.getString("Definition")
                    );
                    listUser.add(el);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return listUser;
            }

        } else {
            return listUser;
        }

        return listUser;
    }
     public ArrayList getAllDetails() {
        // create array list user empty
        ArrayList<User> listUser = new ArrayList<>();
        if (this.bddmanager.connect()) {

            try {
                // create statement 
                Statement st = this.bddmanager
                        .getConnectionManager()
                        .createStatement();
                // create requete 
                String requete = "SELECT \n" +
"                                user.id, \n" +
"                                user.firstname, \n" +
"                                user.lastname, \n" +
"                                user.address, \n" +
"                                user.city, \n" +
"                                user.country, \n" +
"                                user.tel, \n" +
"                                user.mail, \n" +
"                                user.function,\n" +
"                                back.nickname as nickname_back,\n" +
"                                back.isBlocked as isBlocked,\n" +
"                                back.isAdmin as isAdmin,\n" +
"                                site.nickname as nickname_site,\n" +
"                                func.definition as functions\n" +
"                                FROM users as user\n" +
"                                LEFT JOIN access_backoffice as back ON back.user_id = user.id \n" +
"                                LEFT JOIN access_site as site ON site.user_id = user.id\n" +
"                                LEFT JOIN functions as func ON func.id = user.function";
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // insert all users in array object user

                while (rs.next()) {
                    User el = new User(
                            rs.getLong("id"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("address"),
                            rs.getString("city"),
                            rs.getString("country"),
                            rs.getString("tel"),
                            rs.getString("mail"),
                            rs.getInt("function"),
                            rs.getBoolean("isBlocked"),
                            rs.getBoolean("isAdmin"),
                            rs.getString("nickname_back"),
                            rs.getString("nickname_site"),
                            rs.getString("functions")
                            
                    );
                    listUser.add(el);

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return listUser;
            }

        } else {
            return listUser;
        }

        return listUser;
    }

    /**
     * find user
     *
     * @param primary_key
     * @return user
     */
    @Override
    public User find(Long primary_key) {
        // create array user empty
        User user = new User();
        //check if connect db
        if (this.bddmanager.connect()) {

            try {
                // create statement for find 
                Statement st = this.bddmanager.getConnectionManager()
                        .createStatement();
                // create requete add primary key
                String requete = "SELECT \n" +
"                                user.id,\n" +
"                                user.firstname, \n" +
"                                user.lastname, \n" +
"                                user.address, \n" +
"                                user.city, \n" +
"                                user.country, \n" +
"                                user.tel, \n" +
"                                user.mail, \n" +
"                                user.function,\n" +
"                                back.nickname as nickname_back,\n" +
"                                back.isBlocked as isBlocked,\n" +
"                                back.isAdmin as isAdmin,\n" +
"                                site.nickname as nickname_site\n" +
"                                FROM users as user\n" +
"                                LEFT JOIN access_backoffice as back ON back.user_id = user.id \n" +
"                                LEFT JOIN access_site as site ON site.user_id = user.id WHERE user.id = " + primary_key;
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // if result is full
                if (rs.next()) {
                    // insert users in object                   

                    user.setId(rs.getLong("id"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setAddress(rs.getString("address"));
                    user.setCity(rs.getString("city"));
                    user.setCountry(rs.getString("country"));
                    user.setTel(rs.getString("tel"));
                    user.setMail(rs.getString("mail"));
                    user.setFunction(rs.getInt("function"));
                    user.setNickname_back(rs.getString("nickname_back"));
                    user.setIsBlocked(rs.getBoolean("isBlocked"));
                    user.setIsAdmin(rs.getBoolean("isAdmin"));
                    user.setNickname_site(rs.getString("nickname_site"));
                    
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return user;
            }

        } else {
            return user;
        }

        return user;
    }
     public String findDefinition(Long primary_key) {
       String definition =null ;
        //check if connect db
        if (this.bddmanager.connect()) {

            try {
                // create statement for find 
                Statement st = this.bddmanager.getConnectionManager()
                        .createStatement();
                // create requete add primary key
                String requete = "SELECT functions.definition FROM users JOIN functions ON functions.id = users.function WHERE users.id = " + primary_key;
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // if result is full
                if (rs.next()) {
                    // insert users in object                   

                    definition = rs.getString("definition") ;
                    
                    
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                return definition;
            }

        } else {
            return definition;
        }

        return definition;
    }

    /**
     * It checks if the object user is filled or empty
     *
     * @param user
     * @return false is empty and true is full
     */
    public boolean isValid(User user) {
        boolean isValid = true;

        // if id is empty
        if (user.getId() == 0) {
            isValid = false;
        }

        return isValid;
    }
    
 
    public ArrayList<Long> getAll(String function) {
        // create array list user empty
        ArrayList<Long> listUser = new ArrayList<>();
        if (this.bddmanager.connect()) {

            try {
                // create statement 
                Statement st = this.bddmanager
                        .getConnectionManager()
                        .createStatement();
                // create requete 
                String requete = "SELECT users.id FROM users JOIN functions ON functions.id=users.function WHERE functions.definition LIKE '"+ function + "'";
                // excute requete
                ResultSet rs = st.executeQuery(requete);
                // insert all users in array object user

                while (rs.next()) {

                    listUser.add(rs.getLong("id"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return listUser;
            }

        } else {
            return listUser;
        }

        return listUser;
    }
 
}

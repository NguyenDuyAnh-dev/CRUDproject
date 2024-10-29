/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.register;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import anhnd.util.DBHelper;

/**
 *
 * @author PHUCHAU
 */
public class RegisterDAO implements Serializable {

    //public boolean checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
    public RegisterDTO checkLogin(String username, String password) throws SQLException, ClassNotFoundException {    
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //boolean result = false;
        RegisterDTO result = null;
        try {
            //1connect DB
            con = DBHelper.getConnection(); // buoc 11
            if (con != null) {   // buoc 12 
                //2 tao cau lenh sql
                String sql = "select lastname, isAdmin "
                        + "from register "
                        + "where username = ? "
                        + "and password = ?";
                //3 create Statement Obj
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4execute query
                rs = stm.executeQuery();
                //5process result
                if (rs.next()) {  // buoc 13 map
                    //get tu resultset
                    //set vao du lieu dto
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    result = new RegisterDTO(username, password, fullName, role);
                }// user name va password are verifiried
            }//connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    //search
    private List<RegisterDTO> accounts;

    public List<RegisterDTO> getAccounts() {
        return accounts;
    }

    public void searchLastName(String searchValue) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1connect DB
            con = DBHelper.getConnection(); // buoc 11
            if (con != null) {   // buoc 12 
                //2 tao cau lenh sql
                String sql = "select username, password, lastname, isAdmin "
                        + "from register "
                        + "where lastname like ?";
                //3 create Statement Obj
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

                //4execute query
                rs = stm.executeQuery();
                //5process result
                while (rs.next()) {
                    //map
                    //get du lieu tu Result set
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    // set data to DTO properties
                    RegisterDTO dto = new RegisterDTO(username, password, lastname, role);
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }
                    this.accounts.add(dto);
                }
                // user name va password are verifiried
            }//connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    //delete account 
    public boolean deleteAcount(String username) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //1connect DB
            con = DBHelper.getConnection(); // buoc 11
            if (con != null) {   // buoc 12 
                //2 tao cau lenh sql
                String sql = "delete from register "
                        + "where username = ?";
                //3 create Statement Obj
                stm = con.prepareStatement(sql);
                stm.setString(1, username);

                //4execute query
                int affectedRows = stm.executeUpdate();
                //5process result
                if (affectedRows > 0) {  // buoc 13 map
                    result = true;
                }// user name va password are verifiried
            }//connection has been available
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    //update account 
    public boolean updateAccount(String username, String passwword, boolean Role) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Update register set password = ?, isAdmin = ? where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, passwword);
                stm.setBoolean(2, Role);
                stm.setString(3, username);
                int rowUpdate = stm.executeUpdate();
                if(rowUpdate > 0){
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    
    // tao account
    public boolean createAccount(RegisterDTO dto)throws SQLException, ClassNotFoundException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //boolean result = false;
        boolean result = false;
        try {
            //1connect DB
            con = DBHelper.getConnection(); // buoc 11
            if (con != null) {   // buoc 12 
                //2 tao cau lenh sql
                String sql = "insert into register("
                        + "username, password, lastname, isAdmin"
                        + ") values("
                        + "?, ?, ?, ?"
                        + ")";
                //3 create Statement Obj
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());
                //4execute query
                int rowUpdate = stm.executeUpdate();
                //5process result
                if (rowUpdate > 0) {  // buoc 13 map
                    result = true;
                }// user name va password are verifiried
            }//connection has been available
        } finally {
            
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }
}

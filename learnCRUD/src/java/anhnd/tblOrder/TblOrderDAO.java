/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.tblOrder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import anhnd.util.DBHelper;

/**
 *
 * @author PHUCHAU
 */
public class TblOrderDAO implements Serializable {

    public boolean createOrder(String id, Date date, String customer, String address, String email, float total) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "INSERT INTO tblOrder\n"
                        + "           ([id]\n"
                        + "           ,[date]\n"
                        + "           ,[customer]\n"
                        + "           ,[address]\n"
                        + "           ,[email]\n"
                        + "           ,[total]) "
                        + " values(?, ?, ?, ?, ?, ?)";

                stm = con.prepareStatement(sql);

                stm.setString(1, id);
                stm.setDate(2, date);
                stm.setString(3, customer);
                stm.setString(4, address);
                stm.setString(5, email);
                stm.setFloat(6, total);

                int rowUpdate = stm.executeUpdate();
                if (rowUpdate > 0) {
                    result = true;
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

    //get order
    public String getOrders() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String orderId = "Od001"; // Default ID
        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "select id "
                        + "from tblOrder "
                        + "Order By id DESC ";
                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();
                if (rs.next()) {
                    String lastId = rs.getString("id");
                    int nextId = Integer.parseInt(lastId.substring(2)) + 1;
                    orderId = String.format("Od%03d", nextId);
                }
            }
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
        return orderId;
    }

}

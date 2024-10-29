/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.orderDetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import anhnd.tblProduct.TblProductDTO;
import anhnd.util.DBHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author PHUCHAU
 */
public class OrderDetailDAO implements Serializable {

    public boolean addOrderDetail(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DBHelper.getConnection();
            if (con != null) {

                String sql = "INSERT INTO orderDetail\n"
                        + "           ([id]\n"
                        + "           ,[producted]\n"
                        + "           ,[unitprice]\n"
                        + "           ,[quantity]\n"
                        + "           ,[orderId]\n"
                        + "           ,[total]) "
                        + "values(?, ?, ?, ?, ?, ?)";

                stm = con.prepareStatement(sql);

                stm.setInt(1, orderDetailDTO.getId());
                stm.setInt(2, orderDetailDTO.getProducted());
                stm.setFloat(3, orderDetailDTO.getUnitprice());
                stm.setInt(4, orderDetailDTO.getQuantity());
                stm.setString(5, orderDetailDTO.getOrderId());
                stm.setFloat(6, orderDetailDTO.getTotal());
                
                int rowUpdate = stm.executeUpdate();
                if(rowUpdate > 0){
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
    
    public String getorderById() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        Random random = new Random();
        String uniqueId;
        boolean isUnique;

        try {
            con = DBHelper.getConnection();
            do {
                uniqueId = String.format("%06d", random.nextInt(1000000));
                String sql = "Select id From orderDetail Where id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, uniqueId);
                rs = stm.executeQuery();
                isUnique = !rs.next();
            } while (!isUnique);
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
        return uniqueId;
    }
    
    private List<OrderDetailDTO> order;

    public List<OrderDetailDTO> getOrder() {
        return order;
    }
    public void showOrder(String orderId) throws SQLException, ClassNotFoundException {
     
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        
        try {
            // 1. Connect DB
            con = DBHelper.getConnection();
            if (con != null) {

                // 2. creat SQL string
                 String sql = "Select id, producted, unitprice, quantity, orderId, total "
                    + "From orderDetail "
                    + "Where orderId = ?";
                        

                // 3. Create Statement obj
            stm = con.prepareStatement(sql);
            stm.setString(1, orderId);
                
                
                // 4. Execute query
                rs = stm.executeQuery();
                
                // 5. Process Result ---> step 13. DAO map
                while (rs.next()) {
                    // map data
                    // get data from ResultSet
                    
                int id = rs.getInt("id");
                int productID = rs.getInt("producted");
                float unitPrice = rs.getFloat("unitprice");
                int quantity = rs.getInt("quantity");
                String orderID = rs.getString("orderId");
                float total = rs.getFloat("total");
                    
                    
                    // set data to DTO properties
                    OrderDetailDTO dto = new OrderDetailDTO(id, productID, unitPrice, quantity, orderID, total);
                if (this.order == null) {
                    this.order = new ArrayList<>();
                }//account site unavailable
                this.order.add(dto);
                    
                }// traverse Result Set of result Lastname
                
            }// Connection obj has already been available
            
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
}

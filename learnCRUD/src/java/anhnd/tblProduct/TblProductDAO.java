/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.tblProduct;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class TblProductDAO implements Serializable {

    // get product
    List<TblProductDTO> products;

    public List<TblProductDTO> getProduct() {
        return products;
    }

    public void showBookList() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //connection DB
            con = DBHelper.getConnection();
            if (con != null) {
                //tao cau lenh sql
                String sql = "SELECT [sku]\n"
                        + "      ,[name]\n"
                        + "      ,[description]\n"
                        + "      ,[quantity]\n"
                        + "      ,[price]\n"
                        + "      ,[status]\n"
                        + "  FROM tblProduct";
                //tao staement obj
                stm = con.prepareStatement(sql);
                // execute query
                rs = stm.executeQuery();
                //process result
                while (rs.next()) {
                    //get du lieu 
                    int sku = rs.getInt("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    boolean status = rs.getBoolean("status");
                    //set du lieu cho DTO
                    TblProductDTO dto = new TblProductDTO(sku, name, description, quantity, price, status);
                    if (this.products == null) {
                        this.products = new ArrayList<>();
                    }
                    this.products.add(dto);
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
    }
    
    public void updateProductQuantity(int productId, int quantity) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.getConnection();
            if (con != null) {
                String sql = "Update tblProduct Set quantity = quantity - ?, status = CASE WHEN quantity - ? <= 0 THEN 0 ELSE 1 END Where sku = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, quantity);
                stm.setInt(2, quantity);
                stm.setInt(3, productId);
                stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
//    public ProductDTO getProductBySKU(int sku) 
//        throws SQLException, ClassNotFoundException{
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        
//        try {
//            con = DBHelper.getConnection();
//            if (con != null) {
//                String sql = "Select Name, Price, Description, Quantity "
//                        + "From Product "
//                        + "Where SKU = ?";
//                stm = con.prepareStatement(sql);
//                stm.setInt(1, sku);
//                rs = stm.executeQuery();
//                if (rs.next()) {
//                    String name = rs.getString("Name");
//                    float price = rs.getFloat("Price");
//                    String description = rs.getString("Description");
//                    int quantity = rs.getInt("Quantity");
//                    boolean status = rs.getBoolean("status");
//                    ProductDTO result = new ProductDTO(sku, name, description, quantity, price, status);
//                    return result;
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return null;
//    }
    
    
}

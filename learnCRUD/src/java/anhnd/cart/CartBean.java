/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.cart;

;
import anhnd.tblProduct.TblProductDTO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PHUCHAU
 */
public class CartBean implements Serializable{
    private Map<Integer, TblProductDTO> items;

    public CartBean() {
       
    }

    public CartBean(Map<Integer, TblProductDTO> items) {
        this.items = items;
    }
    
    
    public Map<Integer, TblProductDTO> getItems(){
        return items;
    }
    
    
    public void addItemToCart(TblProductDTO product)throws SQLException, ClassNotFoundException{
        if (product == null) {
            return;
        }
        //.check exited items
        if(this.items == null){
            this.items = new HashMap<>();
        }
        //2.check exited items
        int quantity = 1;
        if(this.items.containsKey(product.getSku())){
            quantity = this.items.get(product.getSku()).getQuantity() + 1;
            product.setQuantity(quantity);
        }
        //3.grop item to items
        this.items.put(product.getSku(), product);
    }
    
    public void RemoveItemToCart(int item){
        
        //1.check exited items
        if(this.items == null){
            return;
        }
        //2.check exited items
        if(this.items.containsKey(item)){
            //3.remove item from items
            this.items.remove(item);
            if(this.items.isEmpty()){
                this.items = null;
            }
        }
    }
    
//    public String checkOutItemsOfCart(String name, String address, String email,float total, 
//            ArrayList<ProductDTO> checkedItems) 
//        throws SQLException, ClassNotFoundException{
//        if (this.items == null) {
//            return null;
//        }
//        
//        OrderDAO ordersDAO = new OrderDAO();
//        String orderID = ordersDAO.createOrder(name, address, email, total);
//        
//        if (orderID != null) {
//            OrderDetailDAO orderDetailsDAO = new OrderDetailDAO();
//            boolean result = 
//                    orderDetailsDAO.addOrderDetail(orderID, checkedItems);
//            if (result) {
//                return orderID;
//            }
//        }
//        return null;
//    }
//    
//    public ArrayList<OrderDetailDTO> addItemsToOrderDetailsDTO
//        (ArrayList<ProductDTO> checkedItems, String orderID) {
//        ArrayList<OrderDetailDTO> list = new ArrayList<>();
//        
//        for (ProductDTO productDTO : checkedItems) {
//            int sku = productDTO.getSku();
//            String name = productDTO.getName();
//            float price = productDTO.getPrice();
//            int quantity = checkedItems.size();
//            float total;
//            total = productDTO.getPrice() * (float)productDTO.getQuantity();
//            
//            OrderDetailDTO orderDetailsDTO = 
//                    new OrderDetailDTO(sku, quantity, price, quantity, orderID, total);
//            
//            list.add(orderDetailsDTO);
//        }
//        
//        return list;
//    }
}

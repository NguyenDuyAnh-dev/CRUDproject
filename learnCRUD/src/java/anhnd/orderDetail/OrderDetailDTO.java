/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.orderDetail;

import java.io.Serializable;

/**
 *
 * @author PHUCHAU
 */
public class OrderDetailDTO implements Serializable{
    /*id	int	Unchecked
producted	int	Checked
unitprice	float	Checked
quantity	int	Checked
OrderId	varchar(5)	Checked
total	float	Checked
		Unchecked*/
    private int id;
    private int producted;
    private float unitprice;
    private int quantity;
    private String orderId;
    private float total;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int id, int producted, float unitprice, int quantity, String orderId, float total) {
        this.id = id;
        this.producted = producted;
        this.unitprice = unitprice;
        this.quantity = quantity;
        this.orderId = orderId;
        this.total = total;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the producted
     */
    public int getProducted() {
        return producted;
    }

    /**
     * @param producted the producted to set
     */
    public void setProducted(int producted) {
        this.producted = producted;
    }

    /**
     * @return the unitprice
     */
    public float getUnitprice() {
        return unitprice;
    }

    /**
     * @param unitprice the unitprice to set
     */
    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }
    
}

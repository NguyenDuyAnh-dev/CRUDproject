/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.tblProduct;

import java.io.Serializable;

/**
 *
 * @author PHUCHAU
 */
public class TblProductDTO implements Serializable{
    /*sku	int	Unchecked
name	nvarchar(50)	Checked
description	nvarchar(100)	Checked
quantity	int	Checked
price	float	Checked
status	bit	Checked
		Unchecked*/
    private int sku;
    private String name;
    private String description;
    private int quantity;
    private float price;
    private boolean status;

    public TblProductDTO() {
    }

    public TblProductDTO(int sku, String name, String description, int quantity, float price, boolean status) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    /**
     * @return the sku
     */
    public int getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(int sku) {
        this.sku = sku;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
}

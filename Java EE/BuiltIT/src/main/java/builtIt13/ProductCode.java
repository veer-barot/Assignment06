/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builtIt13;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author c0719943
 */
@Entity
@Table(name = "PRODUCT_CODE")
public class ProductCode {
    @Id
    @Column(name = "PROD_CODE")
    private String prodCode;
    @Column(name = "DISCOUNT_CODE")
    private String discountCode;
    @Column(name = "DESCRIPTION")
    private String description;

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}

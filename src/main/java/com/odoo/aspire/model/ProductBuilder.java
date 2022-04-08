package com.odoo.aspire.model;

import com.odoo.aspire.enums.data.ProductCategory;
import com.odoo.aspire.enums.data.ProductType;
import com.odoo.aspire.enums.data.UnitType;

public class ProductBuilder {

    private String productName;
    private ProductType productType;
    private UnitType unitType;
    private ProductCategory productCategory;
    private int salesPrice;
    private int cost;
    private int quantity;

    public ProductBuilder productName(String productName) {
        this.productName = productName;
        return this;
    }

    public ProductBuilder productType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    public ProductBuilder unitType(UnitType unitType) {
        this.unitType = unitType;
        return this;
    }

    public ProductBuilder productCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        return this;
    }

    public ProductBuilder salesPrice(int salesPrice) {
        this.salesPrice = salesPrice;
        return this;
    }

    public ProductBuilder cost(int cost) {
        this.cost = cost;
        return this;
    }

    public ProductBuilder quantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Product build() {
        return new Product(productName, productType, unitType, productCategory, salesPrice, cost, quantity);
    }
}

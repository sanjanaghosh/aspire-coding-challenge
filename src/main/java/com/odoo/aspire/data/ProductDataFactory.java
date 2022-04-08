package com.odoo.aspire.data;

import com.odoo.aspire.enums.data.ProductCategory;
import com.odoo.aspire.enums.data.ProductType;
import com.odoo.aspire.enums.data.UnitType;
import com.odoo.aspire.model.Product;
import com.odoo.aspire.model.ProductBuilder;
import com.github.javafaker.Faker;
import com.odoo.aspire.config.AppConfigManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.Random;

public class ProductDataFactory {
    private final Faker faker;
    private static final Logger logger = LogManager.getLogger(ProductDataFactory.class);

    public ProductDataFactory() {
        faker = new Faker(new Locale(AppConfigManager.configuration().faker()));
    }

    public Product getProductData() {
        Product product = new ProductBuilder().
                productName(faker.food().dish()).
                productType(ProductType.getProductType()).
                unitType(UnitType.getUnitType()).
                productCategory(ProductCategory.getProductCategory()).
                salesPrice(generateSalesPrice()).
                cost(generateCost()).
                quantity(generateQuantity()).
                build();

        logger.info(product);
        return product;
    }

    public int generateSalesPrice() {
        int number = 0;
        while (number < 1) {
            number = generateRandomIntNumber();
        }
        System.out.println(number);
        return number;
    }

    public int generateCost() {
        int number = 0;
        while (number < 1) {
            number = generateRandomIntNumber();
        }
        System.out.println(number);
        return number;
    }

    public int generateQuantity() {
        int number = 0;
        while (number < 10) {
            number = generateRandomIntNumber();
        }
        return number;
    }

    public int generateRandomIntNumber() {
        Random rd = new Random();
        return rd.nextInt(100);
    }
}

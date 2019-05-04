package com.sumrid_k.pos.Report.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ProductQuantityTest {
    @Test
    public void testCreateObject() {
        ProductQuantity productQuantity = new ProductQuantity();
        productQuantity.setId(1);
        productQuantity.setProductJson("json here");
        productQuantity.setQuantity(1);

        assertEquals("json here", productQuantity.getProductJson());
        assertEquals(1, productQuantity.getQuantity());
        assertEquals(1, productQuantity.getId());
    }

    @Test
    public void testCreateOject() {
        ProductQuantity productQuantity = new ProductQuantity("json here", 1);

        assertEquals("json here", productQuantity.getProductJson());
        assertEquals(1, productQuantity.getQuantity());
    }
}
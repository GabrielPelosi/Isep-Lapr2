/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Francisco Ferreira
 */
public class CategoryTest {
    
    /**
     * Test of hasId method, of class Category.
     */
    @Test
    public void testHasId() {
        System.out.println("hasCode");
        String strId = "12345";
        Category instance = new Category("1234","Category_Test");
        boolean expResult = false;
        boolean result = instance.hasId(strId);
        assertEquals(result, expResult);
    }


    /**
     * Test of validate method, of class Category.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        Category instance = new Category("id","Validate_Test");
        boolean expResult = true;
        boolean result = instance.validate();
        assertEquals(result, expResult);
    }

    /**
     * Test of hashCode method, of class Category.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Category instance = new Category("1234567","HashCode_Test");
        int expResult = 2018166485;
        int result = instance.hashCode();
        assertEquals(result, expResult);
    }

    /**
     * Test of equals method, of class Category.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Category c1 = new Category("1234567","Equals_Test");
        Object o = (Category) c1 ;
        Category instance = new Category("123456","Equals_Test_1");
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(result, expResult);
    }

}

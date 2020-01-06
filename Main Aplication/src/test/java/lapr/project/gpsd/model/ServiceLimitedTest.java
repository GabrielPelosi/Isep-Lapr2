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
public class ServiceLimitedTest {
    
    /**
     * Test of getCat method, of class ServiceLimited.
     */
    @Test
    public void testGetCat() {
        System.out.println("getCat");
        Category cat = new Category ("strId", "strDescription");
        ServiceLimited instance = new ServiceLimited("id", "descShort", "descLong", 11, cat);
        Category expResult = cat;
        Category result = instance.getCat();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class ServiceLimited.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Category cat = new Category ("strId", "strDescription");
        ServiceLimited instance = new ServiceLimited("id", "descShort", "descLong", 11, cat);
        String expResult = "id";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class ServiceLimited.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Category cat = new Category ("strId", "strDescription");
        ServiceLimited instance = new ServiceLimited("id", "descShort", "descLong", 11, cat);
        String expResult = "Fixed";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescriptionShort method, of class ServiceLimited.
     */
    @Test
    public void testGetDescriptionShort() {
        System.out.println("getDescriptionShort");
        Category cat = new Category ("strId", "strDescription");
        ServiceLimited instance = new ServiceLimited("id", "descShort", "descLong", 11, cat);
        String expResult = "descShort";
        String result = instance.getDescriptionShort();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescriptionFull method, of class ServiceLimited.
     */
    @Test
    public void testGetDescriptionFull() {
        System.out.println("getDescriptionFull");
        Category cat = new Category ("strId", "strDescription");
        ServiceLimited instance = new ServiceLimited("id", "descShort", "descLong", 11, cat);
        String expResult = "descLong";
        String result = instance.getDescriptionFull();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTime method, of class ServiceLimited.
     */
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        Category cat = new Category ("strId", "strDescription");
        ServiceLimited instance = new ServiceLimited("id", "descShort", "descLong", 11, cat);
        Time expResult = null;
        Time result = instance.getTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValue method, of class ServiceLimited.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Category cat = new Category ("strId", "strDescription");
        ServiceLimited instance = new ServiceLimited("id", "descShort", "descLong", 11, cat);
        double expResult = 11.0;
        double result = instance.getValue();
        assertEquals(expResult, result, 0.0);
    }

//    /**
//     * Test of toString method, of class ServiceLimited.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Category cat = new Category ("strId", "strDescription");
//        ServiceLimited instance = new ServiceLimited("id", "descShort", "descLong", 11, cat);
//        String expResult = "id - descShort - 11,00";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//    }
//    
}

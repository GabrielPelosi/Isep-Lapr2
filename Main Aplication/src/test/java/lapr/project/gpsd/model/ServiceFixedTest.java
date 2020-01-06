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
public class ServiceFixedTest {
    
    /**
     * Test of getCat method, of class ServiceFixed.
     */
    @Test
    public void testGetCat() {
        System.out.println("getCat");
        Time time = new Time ("123");
        Category cat = new Category ("strId", "strDescription");
        ServiceFixed instance = new ServiceFixed ("id", "descShort", "descLong", 12,cat, time);
        Category expResult = cat;
        Category result = instance.getCat();
        assertEquals(expResult, result);

    }

    /**
     * Test of getId method, of class ServiceFixed.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Time time = new Time ("123");
        Category cat = new Category ("strId", "strDescription");
        ServiceFixed instance = new ServiceFixed ("id", "descShort", "descLong", 12,cat, time);
        String expResult = "id";
        String result = instance.getId();
        assertEquals(expResult, result);

    }

    /**
     * Test of getType method, of class ServiceFixed.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Time time = new Time ("123");
        Category cat = new Category ("strId", "strDescription");
        ServiceFixed instance = new ServiceFixed ("id", "descShort", "descLong", 12,cat, time);
        String expResult = "Fixed";
        String result = instance.getType();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDescriptionShort method, of class ServiceFixed.
     */
    @Test
    public void testGetDescriptionShort() {
        System.out.println("getDescriptionShort");
        Time time = new Time ("123");
        Category cat = new Category ("strId", "strDescription");
        ServiceFixed instance = new ServiceFixed ("id", "descShort", "descLong", 12,cat, time);
        String expResult = "descShort";
        String result = instance.getDescriptionShort();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDescriptionFull method, of class ServiceFixed.
     */
    @Test
    public void testGetDescriptionFull() {
        System.out.println("getDescriptionFull");
        Time time = new Time ("123");
        Category cat = new Category ("strId", "strDescription");
        ServiceFixed instance = new ServiceFixed ("id", "descShort", "descLong", 12,cat, time);
        String expResult = "descLong";
        String result = instance.getDescriptionFull();
        assertEquals(expResult, result);

    }

    /**
     * Test of getTime method, of class ServiceFixed.
     */
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        Time time = new Time ("123");
        Category cat = new Category ("strId", "strDescription");
        ServiceFixed instance = new ServiceFixed ("id", "descShort", "descLong", 12,cat, time);
        Time expResult = time;
        Time result = instance.getTime();
        assertEquals(expResult, result);

    }

    /**
     * Test of getValue method, of class ServiceFixed.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Time time = new Time ("123");
        Category cat = new Category ("strId", "strDescription");
        ServiceFixed instance = new ServiceFixed ("id", "descShort", "descLong", 12,cat, time);
        double expResult = 12.0;
        double result = instance.getValue();
        assertEquals(expResult, result, 0.0);

    }

//    /**
//     * Test of toString method, of class ServiceFixed.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Time time = new Time ("123");
//        Category cat = new Category ("strId", "strDescription");
//        ServiceFixed instance = new ServiceFixed ("id", "descShort", "descLong", 12,cat, time);
//        String expResult = "id - descShort - 12,00";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//
//    }
    
}

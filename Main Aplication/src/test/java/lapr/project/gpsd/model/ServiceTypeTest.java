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
public class ServiceTypeTest {

    /**
     * Test of getName method, of class ServiceType.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        ServiceType instance = new ServiceType("name", "className");
        String expResult = "name";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class ServiceType.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "name";
        ServiceType instance = new ServiceType("name1","classNome");
        instance.setName(name);

    }

    /**
     * Test of getClassName method, of class ServiceType.
     */
    @Test
    public void testGetClassName() {
        System.out.println("getClassName");
        ServiceType instance = new ServiceType("name", "className");
        String expResult = "className";
        String result = instance.getClassName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setClassName method, of class ServiceType.
     */
    @Test
    public void testSetClassName() {
        System.out.println("setClassName");
        String className = "className123";
        ServiceType instance = new  ServiceType("className", "className");
        instance.setClassName(className);
    }

    /**
     * Test of toString method, of class ServiceType.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ServiceType instance = new ServiceType ("name", "className");
        String expResult = "name";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}

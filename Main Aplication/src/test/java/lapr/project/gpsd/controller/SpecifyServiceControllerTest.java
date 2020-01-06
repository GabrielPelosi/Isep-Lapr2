/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.controller;

import java.util.List;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.ServiceType;
import lapr.project.gpsd.model.Time;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rickropes
 */
public class SpecifyServiceControllerTest {
    
    public SpecifyServiceControllerTest() {
    }

    @Test
    public void testNewService() {
        System.out.println("newService");
        String id = "";
        String descShort = "";
        String descLong = "";
        double priceHour = 0.0;
        Category cat = null;
        Time time = null;
        ServiceType type = null;
        SpecifyServiceController instance = new SpecifyServiceController();
        instance.newService(id, descShort, descLong, priceHour, cat, time, type);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetServiceTypes() {
        System.out.println("getServiceTypes");
        SpecifyServiceController instance = new SpecifyServiceController();
        List<ServiceType> expResult = null;
        List<ServiceType> result = instance.getServiceTypes();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}

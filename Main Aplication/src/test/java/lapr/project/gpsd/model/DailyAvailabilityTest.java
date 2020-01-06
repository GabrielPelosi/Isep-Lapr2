/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Utilizador
 */
public class DailyAvailabilityTest {
    
    public DailyAvailabilityTest() {
    }

    /**
     * Test of getDataI method, of class DailyAvailability.
     */
    @Test
    public void testGetDataI() {
        System.out.println("getDataI");
        Data dataI = new Data(2020,8,12);
        String horaI = "12:00";
        String horaF = "20:00";
        DailyAvailability instance = new DailyAvailability (dataI,horaI,horaF);
        Data expResult =dataI;
        Data result = instance.getDataI();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHoraI method, of class DailyAvailability.
     */
    @Test
    public void testGetHoraI() {
        System.out.println("getHoraI");
        Data dataI = new Data(2020,8,12);
        String horaI = "12:00";
        String horaF = "20:00";
        DailyAvailability instance = new DailyAvailability (dataI,horaI,horaF);
        String expResult = horaI.trim();
        String result = instance.getHoraI();
        assertEquals(expResult, result);
        }

    /**
     * Test of getHoraF method, of class DailyAvailability.
     */
    @Test
    public void testGetHoraF() {
        System.out.println("getHoraF");
        Data dataI = new Data(2020,8,12);
        String horaI = "12:00";
        String horaF = "20:00";
        DailyAvailability instance = new DailyAvailability (dataI,horaI,horaF);
        String expResult = horaF.trim();
        String result = instance.getHoraF();
        assertEquals(expResult, result);
    }

    /**
     * Test of toStringValidate method, of class DailyAvailability.
     */
    @Test
    public void testToStringValidate() {
        System.out.println("toStringValidate");
        Data dataI = new Data(2020,8,12);
        String horaI = "12:00";
        String horaF = "20:00";
        DailyAvailability a= new DailyAvailability (dataI,horaI,horaF);
        DailyAvailability instance = new DailyAvailability (dataI,horaI,horaF);
        String expResult = a.toStringValidate();
        String result = instance.toStringValidate();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class DailyAvailability.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Data dataI = new Data(2020,8,12);
        String horaI = "12:00";
        String horaF = "20:00";
        DailyAvailability a= new DailyAvailability (dataI,horaI,horaF);
        DailyAvailability instance = new DailyAvailability (dataI,horaI,horaF);
        String expResult = a.toString();
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}

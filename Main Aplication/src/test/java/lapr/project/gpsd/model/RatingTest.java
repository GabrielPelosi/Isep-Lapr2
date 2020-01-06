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
public class RatingTest {
 
    /**
     * Test of getValue method, of class Rating.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Rating instance = new Rating(2);
        int expResult = 2;
        int result = instance.getValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Rating.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Rating rating = new Rating(4);
        Rating instance = new Rating (4);
        boolean expResult = true;
        boolean result = instance.equals(rating);
        assertEquals(expResult, result);
    }
    
}

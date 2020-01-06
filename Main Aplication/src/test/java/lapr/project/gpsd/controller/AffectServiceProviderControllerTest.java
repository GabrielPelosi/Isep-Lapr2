/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.controller;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rickropes
 */
public class AffectServiceProviderControllerTest {
    
    public AffectServiceProviderControllerTest() {
    }

    @Test
    public void testAffectServiceProvider() {
        System.out.println("affectServiceProvider");
        AffectServiceProviderController instance = new AffectServiceProviderController(null, null, null);
        instance.affectServiceProvider();

    }
    
}

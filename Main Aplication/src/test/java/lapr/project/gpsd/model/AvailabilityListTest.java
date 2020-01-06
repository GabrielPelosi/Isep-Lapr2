/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import lapr.project.gpsd.controller.ApplicationGPSD;
import lapr.project.gpsd.ui.console.utils.Bootable;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Utilizador
 */
public class AvailabilityListTest {
    Company empresa;
    
    public AvailabilityListTest() {
        empresa = ApplicationGPSD.getInstance().getEmpresa();
        PostalAddress pa = new PostalAddress ("address", "postalCode", "locality");
        ServiceProvider sP1 = new ServiceProvider("numeroMecanografico", "nomeC", "nomeA", "email", pa);
        RegisterSP servProviderRec = ApplicationGPSD.getInstance().getEmpresa().getServiceProvidersRegister();
        servProviderRec.registerServiceProviderFile(sP1,"d");
        empresa.getAutorizacaoFacade().doLogin("email", "d");
    }


    /**
     * Test of novoPedidoDisponibilidades method, of class AvailabilityList.
     */
    @Test
    public void testNovoPedidoDisponibilidades() {
        System.out.println("novoPedidoDisponibilidades");
        Data dataI = new Data(2020,8,12);
        String horaI = "12:00";
        String horaF = "20:00";
        AvailabilityList instance = new AvailabilityList();
        String expResult = new DailyAvailability(dataI,horaI,horaF).toString();
        String result = instance.newSchedule(dataI, horaI, horaF).toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of registarPeriodoDisponibilidades method, of class AvailabilityList.
     */
    @Test
    public void testRegistarPeriodoDisponibilidades() {
        System.out.println("registarPeriodoDisponibilidades");
        AvailabilityList instance = new AvailabilityList();
        Data dataI = new Data(2019,8,12);
        String horaI = "12:00";
        String horaF = "20:00";
        instance.newSchedule(dataI, horaI, horaF);
        DailyAvailability disp = new DailyAvailability(dataI,horaI,horaF);
        boolean expResult = true;
        boolean result = instance.registerSchedule(disp);
        assertEquals(expResult, result);
    }

    /**
     * Test of validaDisponibilidade method, of class AvailabilityList.
     */
    @Test
    public void testValidaDisponibilidade() {
        System.out.println("validaDisponibilidade");
        Data dataI = new Data(2019,8,12);
        String horaI = "12:00";
        String horaF = "20:00";
        DailyAvailability disp = new DailyAvailability(dataI,horaI,horaF);
        AvailabilityList instance = new AvailabilityList();
        boolean expResult = true;
        boolean result = instance.validateSchedule(disp);
        assertEquals(expResult, result);
    }

    
}

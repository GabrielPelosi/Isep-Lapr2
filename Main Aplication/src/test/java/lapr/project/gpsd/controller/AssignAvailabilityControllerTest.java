/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.controller;
import lapr.project.autorizacao.AuthorizationFacade;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.Constantes;
import lapr.project.gpsd.model.DailyAvailability;
import lapr.project.gpsd.model.Data;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.PostalCode;
import lapr.project.gpsd.model.RegisterPC;
import lapr.project.gpsd.model.RegisterSP;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.ui.console.utils.Bootable;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Utilizador
 */
public class AssignAvailabilityControllerTest {
    Company empresa; 
    AuthorizationFacade autorizacao;

    public AssignAvailabilityControllerTest() {
        empresa = ApplicationGPSD.getInstance().getEmpresa();
        PostalAddress pa = new PostalAddress ("address", "postalCode", "locality");
        ServiceProvider sP1 = new ServiceProvider("numeroMecanografico", "nomeC", "nomeA", "email", pa);
        RegisterSP servProviderRec = ApplicationGPSD.getInstance().getEmpresa().getServiceProvidersRegister();
        servProviderRec.registerServiceProviderFile(sP1,"d");
        empresa.getAutorizacaoFacade().doLogin("email", "d");
    }

    
    
    /**
     * Test of indicarPeriodoDisponibilidades method, of class AssignAvailabilityController.
     */
    @Test
    public void testIndicarPeriodoDisponibilidades() {
        System.out.println("indicarPeriodoDisponibilidades");
        Data dataI = new Data(2019,9,22);
        String horaI = "10:00";
        String horaF = "16:00";
        AssignAvailabilityController instance = new AssignAvailabilityController();
        DailyAvailability expResult = new DailyAvailability(dataI,horaI,horaF);
        DailyAvailability result = instance.newSchedule(dataI, horaI, horaF);
        assertEquals(expResult.toString(), result.toString());
        
    }

    /**
     * Test of registoDisponibilidade method, of class AssignAvailabilityController.
     */
    @Test
    public void testRegistoDisponibilidade() {
        System.out.println("registoDisponibilidade");
        AssignAvailabilityController instance = new AssignAvailabilityController();
        Data dataI = new Data(2019,8,22);
        String horaI = "12:00";
        String horaF = "16:00";
        instance.newSchedule(dataI, horaI, horaF);
        boolean expResult = true;
        boolean result = instance.registerSchedule();
        assertEquals(expResult, result);
    }

    @Test
    public void testNewSchedule() {
        System.out.println("newSchedule");
        Data dataI = new Data(2019,8,22);
        String horaI = "12:00";
        String horaF = "16:00";
        AssignAvailabilityController instance = new AssignAvailabilityController();
        DailyAvailability expResult = new DailyAvailability(dataI, horaI, horaF);
        DailyAvailability result = instance.newSchedule(dataI, horaI, horaF);
        assertEquals(expResult.toString(), result.toString());
    }

    @Test
    public void testRegisterSchedule() {
        System.out.println("registerSchedule");
        AssignAvailabilityController instance = new AssignAvailabilityController();
        boolean expResult = false;
        boolean result = instance.registerSchedule();
        assertEquals(expResult, result);
    }

  

   
    


    
}

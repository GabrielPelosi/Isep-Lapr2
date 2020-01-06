/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.controller;

import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.PostalAddress;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Utilizador
 */
public class RegisterClientControllerTest {
    String strNome;
    String strNIF;
    String strTelefone;
    String strEmail;
    String strPwd;
    String strLocal;
    String strCodPostal;
    String strLocalidade;
    RegisterClientController instance; 

    public RegisterClientControllerTest() {
        strNome = "Diogo";
        strNIF = "123456789";
        strTelefone = "987654321";
        strEmail = "d";
        strPwd = "d";
        strLocal = "Marco";
        strCodPostal = "4000-011";
        strLocalidade = "Marco"; 
        instance = new RegisterClientController();
    }

    
    /**
     * Test of novoCliente method, of class RegisterClientController.
     */
    @Test
    public void testNovoCliente() {
        System.out.println("novoCliente");
        boolean expResult = true;
        boolean result = instance.newClient(strNome, strNIF, strTelefone, strEmail, strPwd, strLocal, strCodPostal, strLocalidade);
        assertEquals(expResult, result);
    }

    /**
     * Test of addEnderecoPostal method, of class RegisterClientController.
     */
    @Test
    public void testAddEnderecoPostal() {
        System.out.println("addEnderecoPostal");
        String strLocal = "Penafiel";
        String strCodPostal = "4000-009";
        String strLocalidade = "Penafiel";
        instance.newClient(strNome, strNIF, strTelefone, strEmail, strPwd, strLocal, strCodPostal, strLocalidade);
        boolean expResult = true;
        boolean result = instance.addPostalAddress(strLocal, strCodPostal, strLocalidade);
        assertEquals(expResult, result);
    }

    /**
     * Test of registaCliente method, of class RegisterClientController.
     */
    @Test
    public void testRegistaCliente() {
        System.out.println("registaCliente");
        instance.newClient(strNome, strNIF, strTelefone, strEmail, strPwd, strLocal, strCodPostal, strLocalidade);
        boolean expResult = true;
        boolean result = instance.registerClient();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClienteString method, of class RegisterClientController.
     */
    @Test
    public void testGetClienteString() {
        System.out.println("getClienteString");
        instance.newClient(strNome, strNIF, strTelefone, strEmail, strPwd, strLocal, strCodPostal, strLocalidade);
        Client c=instance.getClient();
        String expResult =c.toString();
        String result = instance.getClientString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClient method, of class RegisterClientController.
     */
    @Test
    public void testGetClient() {
        System.out.println("getClient");
        PostalAddress p=new PostalAddress(strLocal, strCodPostal, strLocalidade);
        Client c= new Client(strNome, strNIF, strTelefone, strEmail, p );
        instance.newClient(strNome, strNIF, strTelefone, strEmail, strPwd, strLocal, strCodPostal, strLocalidade);
        Client expResult =c;
        Client result = instance.getClient();
        assertEquals(expResult, result);
        
    }

}

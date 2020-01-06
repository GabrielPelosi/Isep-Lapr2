package lapr.project.gpsd.controller;

import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.PostalCode;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class AssociatePostalAddressControllerTest {

    /**
     * Test of newAddress method, of class AssociatePostalAddressController.
     */
    @Test
    public void testNewAddress() {
        System.out.println("newAddress");
        String address1 = "rua do isep";
        String cod1 = "4000-16";
        PostalCode postalCode1 = new PostalCode(cod1);
        String locality1 = "porto";
        String strNome = "gabriel";
        String strNIF = "564348976";
        String strTelefone = "923456432";
        String strEmail = "gabriel@isep.ipp.pt";
        String address = "rua do isep";
        String postalCode = "4000-016";
        String locality = "porto";
        PostalAddress oMorada = new PostalAddress(address, postalCode, locality);
        Client instance1 = new Client(strNome, strNIF, strTelefone, strEmail, oMorada);
        String strDesignacaoC = "limpezas";
        String strNIFC = "453678943";
        Company company = new Company(strDesignacaoC, strNIFC);
        AssociatePostalAddressController instance = new AssociatePostalAddressController(company, instance1);
        PostalAddress expResult = new PostalAddress(address1, postalCode1, locality1);
        PostalAddress result = instance.newAddress(address1, postalCode1, locality1);
        assertEquals(expResult, result);
    }

    /**
     * Test of addressRegister method, of class
     * AssociatePostalAddressController.
     */
    @Test
    public void testAddressRegister() {
        System.out.println("addressRegister");
        String strNome = "gabriel";
        String strNIF = "564348976";
        String strTelefone = "923456432";
        String strEmail = "gabriel@isep.ipp.pt";
        String address = "rua do isep";
        String postalCode = "4000-016";
        String locality = "porto";
        PostalAddress oMorada = new PostalAddress(address, postalCode, locality);
        Client instance1 = new Client(strNome, strNIF, strTelefone, strEmail, oMorada);
        String strDesignacaoC = "limpezas";
        String strNIFC = "453678943";
        Company company = new Company(strDesignacaoC, strNIFC);
        AssociatePostalAddressController instance = new AssociatePostalAddressController(company, instance1);
        boolean expResult = true;
        boolean result = instance.addressRegister(instance1, oMorada);
        assertEquals(expResult, result);
    }

}

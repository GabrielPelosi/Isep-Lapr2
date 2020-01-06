package lapr.project.gpsd.model;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class ClientTest {
   
    /**
     * Test of hasEmail method, of class Client.
     */
    @Test
    public void testHasEmail() {
        System.out.println("hasEmail");
        String strNome = "gabriel";
        String strNIF = "564348976";
        String strTelefone = "923456432";
        String strEmail = "gabriel@isep.ipp.pt";
        String address = "rua do isep";
        String postalCode = "4000-016";
        String locality = "porto";
        PostalAddress oMorada = new PostalAddress(address, postalCode, locality);
        Client instance = new Client(strNome, strNIF, strTelefone, strEmail, oMorada);
        boolean expResult = true;
        boolean result = instance.hasEmail(strEmail);
        assertEquals(expResult, result);
    }

    /**
     * Test of addPostalAddress method, of class Client.
     */
    @Test
    public void testAddPostalAddress() {
        System.out.println("addPostalAddress");
        String strNome = "gabriel";
        String strNIF = "564348976";
        String strTelefone = "923456432";
        String strEmail = "gabriel@isep.ipp.pt";
        String address = "rua do isep";
        String postalCode = "4000-016";
        String locality = "porto";
        PostalAddress oMorada = new PostalAddress(address, postalCode, locality);
        Client instance = new Client(strNome, strNIF, strTelefone, strEmail, oMorada);
        boolean expResult = true;
        boolean result = instance.addPostalAddress(oMorada);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeEnderecoPostal method, of class Client.
     */
    @Test
    public void testRemoveEnderecoPostal() {
        System.out.println("removeEnderecoPostal");
        String strNome = "gabriel";
        String strNIF = "564348976";
        String strTelefone = "923456432";
        String strEmail = "gabriel@isep.ipp.pt";
        String address = "rua do isep";
        String postalCode = "4000-016";
        String locality = "porto";
        PostalAddress oMorada = new PostalAddress(address, postalCode, locality);
        Client instance = new Client(strNome, strNIF, strTelefone, strEmail, oMorada);
        boolean expResult = true;
        boolean result = instance.removeEnderecoPostal(oMorada);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Client.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        String strNome = "gabriel";
        String strNIF = "564348976";
        String strTelefone = "923456432";
        String strEmail = "gabriel@isep.ipp.pt";
        String address = "rua do isep";
        String postalCode = "4000-016";
        String locality = "porto";
        PostalAddress oMorada = new PostalAddress(address, postalCode, locality);
        Client instance = new Client(strNome, strNIF, strTelefone, strEmail, oMorada);
        int expResult = 221707753;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Client.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        String strNome = "gabriel";
        String strNIF = "564348976";
        String strTelefone = "923456432";
        String strEmail = "gabriel@isep.ipp.pt";
        String address = "rua do isep";
        String postalCode = "4000-016";
        String locality = "porto";
        PostalAddress oMorada = new PostalAddress(address, postalCode, locality);
        Client instance = new Client(strNome, strNIF, strTelefone, strEmail, oMorada);
        Object o = instance;
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of newAddress method, of class Client.
     */
    @Test
    public void testNewAddress_3args_1() {
        System.out.println("newAddress");
        String address = "rua do isep";
        PostalCode postalCode = new PostalCode("4000-016");
        String locality = "porto";
        PostalAddress expResult = new PostalAddress(address, postalCode, locality);
        PostalAddress result = Client.newAddress(address, postalCode, locality);
        assertEquals(expResult, result);
    }

    /**
     * Test of newAddress method, of class Client.
     */
    @Test
    public void testNewAddress_3args_2() {
        System.out.println("newAddress");
        String address = "rua do isep";
        PostalCode postalCode = new PostalCode("4000-016");
        String locality = "porto";
        PostalAddress expResult = new PostalAddress(address, postalCode, locality);
        PostalAddress result = Client.newAddress(address, postalCode, locality);
        assertEquals(expResult, result);
    }

    /**
     * Test of validAddress method, of class Client.
     */
    @Test
    public void testValidAddress() {
        System.out.println("validAddress");
        String strNome = "gabriel";
        String strNIF = "564348976";
        String strTelefone = "923456432";
        String strEmail = "gabriel@isep.ipp.pt";
        String address = "rua do isep";
        String postalCode = "4000-016";
        String locality = "porto";
        PostalAddress end1 = new PostalAddress(address, postalCode, locality);
        Client instance = new Client(strNome, strNIF, strTelefone, strEmail, end1);
        boolean expResult = true;
        boolean result = instance.validAddress(end1);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNome method, of class Client.
     */
    @org.junit.Test
    public void testGetNome() {
        System.out.println("getNome");
        Client instance = null;
        String expResult = "";
        String result = instance.getNome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNIF method, of class Client.
     */
    @org.junit.Test
    public void testGetNIF() {
        System.out.println("getNIF");
        Client instance = null;
        String expResult = "";
        String result = instance.getNIF();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class Client.
     */
    @org.junit.Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Client instance = null;
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMoradas method, of class Client.
     */
    @org.junit.Test
    public void testGetMoradas() {
        System.out.println("getMoradas");
        Client instance = null;
        List<PostalAddress> expResult = null;
        List<PostalAddress> result = instance.getMoradas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Client.
     */
    @org.junit.Test
    public void testToString() {
        System.out.println("toString");
        Client instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

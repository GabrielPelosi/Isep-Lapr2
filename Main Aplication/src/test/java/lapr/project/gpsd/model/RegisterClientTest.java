package lapr.project.gpsd.model;

import java.util.HashSet;
import java.util.Set;
import lapr.project.autorizacao.AuthorizationFacade;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class RegisterClientTest {

    /**
     * Test of novoCliente method, of class RegisterClient.
     */
    @Test
    public void testNovoCliente() {
        System.out.println("novoCliente");
        String strNome = "joaquim";
        String strNIF = "4537892309";
        String strTelefone = "914576208";
        String strEmail = "joaquim@isep.ipp.pt";
        PostalAddress morada = new PostalAddress("rua das virtudes", "4567-123", "porto");
        AuthorizationFacade authorization = new AuthorizationFacade();
        RegisterClient instance = new RegisterClient(authorization);
        Client expResult = new Client(strNome, strNIF, strTelefone, strEmail, morada);
        Client result = instance.newClient(strNome, strNIF, strTelefone, strEmail, morada);
        assertEquals(expResult, result);
    }

    /**
     * Test of registaCliente method, of class RegisterClient.
     */
    @Test
    public void testRegistaCliente() {
        System.out.println("registaCliente");
        String strNome = "joaquim";
        String strNIF = "4537892309";
        String strTelefone = "914576208";
        String strEmail = "joaquim@isep.ipp.pt";
        PostalAddress morada = new PostalAddress("rua das virtudes", "4567-123", "porto");
        Client oCliente = new Client(strNome, strNIF, strTelefone, strEmail, morada);
        String strPwd = "joaquim123";
        AuthorizationFacade authorization = new AuthorizationFacade();
        RegisterClient instance = new RegisterClient(authorization);
        boolean expResult = true;
        boolean result = instance.registerClient(oCliente, strPwd);
        assertEquals(expResult, result);
    }

    /**
     * Test of validaCliente method, of class RegisterClient.
     */
    @Test
    public void testValidaCliente() {
        System.out.println("validaCliente");
        String strNome = "joaquim";
        String strNIF = "4537892309";
        String strTelefone = "914576208";
        String strEmail = "joaquim@isep.ipp.pt";
        PostalAddress morada = new PostalAddress("rua das virtudes", "4567-123", "porto");
        Client oCliente = new Client(strNome, strNIF, strTelefone, strEmail, morada);
        String strPwd = "joaquim123";
        AuthorizationFacade authorization = new AuthorizationFacade();
        RegisterClient instance = new RegisterClient(authorization);
        boolean expResult = true;
        boolean result = instance.validateClient(oCliente, strPwd);
        assertEquals(expResult, result);
    }

    /**
     * Test of getClienteByEmail method, of class RegisterClient.
     */
    @Test
    public void testGetClienteByEmail() {
        System.out.println("getClienteByEmail");
        String strEMail = "gabriel@isep.ipp.pt";
        Set<Client> clients = new HashSet<>();
        AuthorizationFacade authorization = new AuthorizationFacade();
        RegisterClient instance = new RegisterClient(authorization, clients);
        PostalAddress oMorada = new PostalAddress("rua do isep", "4000-016", "porto");
        Client expResult = new Client("gabriel", "453456787", "934567234", strEMail, oMorada);
        clients.add(expResult);
        Client result = instance.getClienteByEmail(strEMail);
        assertEquals(expResult, result);
    }

}

package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lapr.project.autorizacao.AuthorizationFacade;

public class RegisterClient {

    /**
     * The authorization
     */
    private final AuthorizationFacade oAuthorization;

    /**
     * Set of clients
     */
    private final Set<Client> lstClientes;

    /**
     * Constructs an intance of RegisterClient
     *
     * @param oAutorizacao the authorization
     */
    public RegisterClient(AuthorizationFacade oAutorizacao) {
        this.oAuthorization = oAutorizacao;
        this.lstClientes = new HashSet<>();
    }

    /**
     * Constructs an intance of RegisterClient
     *
     * @param oAutorizacao the authorization
     * @param lstClientes the set of clients
     */
    public RegisterClient(AuthorizationFacade oAutorizacao, Set<Client> lstClientes) {
        this.oAuthorization = oAutorizacao;
        this.lstClientes = lstClientes;
    }

    /**
     * Returns the client with a given email
     *
     * @param strEMail email's client
     * @return the client with a given email
     */
    public Client getClienteByEmail(String strEMail) {
        for (Client cliente : this.lstClientes) {
            if (cliente.hasEmail(strEMail)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Constructs a new Client
     *
     * @param strNome client´s name
     * @param strNIF clients´s NIF
     * @param strTelefone client´s phone number
     * @param strEmail client´s email
     * @param morada client´s address
     * @return a new Client
     */
    public Client newClient(String strNome, String strNIF, String strTelefone, String strEmail, PostalAddress morada) {
        return new Client(strNome, strNIF, strTelefone, strEmail, morada);
    }

    /**
     * Regists a new client
     *
     * @param oCliente the client
     * @param strPwd the password
     * @return a new client
     */
    public boolean registerClient(Client oCliente, String strPwd) {
        if (this.validateClient(oCliente, strPwd)) {
            if (this.oAuthorization.registaUtilizadorComPapel(oCliente.getNome(), oCliente.getEmail(), strPwd, Constantes.PAPEL_CLIENTE)) {
                return addCliente(oCliente);
            }
        }
        return false;
    }

    /**
     * Adds a new client to the set of clients
     *
     * @param oCliente the client
     * @return a set of clients with the new client
     */
    private boolean addCliente(Client oCliente) {
        return lstClientes.add(oCliente);
    }

    /**
     * Returns a list of clients
     *
     * @return list of clients
     */
    public List<Client> getClients() {
        List<Client> clients = new ArrayList<Client>();
        for (Client d : lstClientes) {
            clients.add(d);
        }
        return clients;
    }

    /**
     * Validates the client´s information
     *
     * @param oCliente the client
     * @param strPwd the password
     * @return true if the client´s information is correct or false if is
     * incorrect
     */
    public boolean validateClient(Client oCliente, String strPwd) {
        boolean bRet = true;
        if (this.oAuthorization.existeUtilizador(oCliente.getEmail())) {
            bRet = false;
        }
        if (strPwd == null) {
            bRet = false;
        }
        if (strPwd.isEmpty()) {
            bRet = false;
        }
        return bRet;
    }

}

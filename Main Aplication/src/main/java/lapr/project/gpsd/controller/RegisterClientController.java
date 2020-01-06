package lapr.project.gpsd.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.ui.console.utils.Utils;

/**
 *
 * @author Utilizador
 */
public class RegisterClientController {

    private final Company company;
    private Client client;
    private String strPwd;

    /**
     *
     */
    public RegisterClientController() {
        this.company = ApplicationGPSD.getInstance().getEmpresa();
    }

    /**
     * Return true if the data is valid and Client is created, returns false if the data is invalid
     * @param strNome
     * @param strNIF
     * @param strTelefone
     * @param strEmail
     * @param strPwd
     * @param strLocal
     * @param strCodPostal
     * @param strLocalidade
     * @return true if the data is valid and Client is created, returns false if the data is invalid
     */
    public boolean newClient(String strNome, String strNIF, String strTelefone, String strEmail, String strPwd, String strLocal, String strCodPostal, String strLocalidade) {
        try {
            this.strPwd = strPwd;
            PostalAddress morada = Client.newAddress(strLocal, strCodPostal, strLocalidade);
            this.client = this.company.getRegistoCliente().newClient(strNome, strNIF, strTelefone, strEmail, morada);
            return this.company.getRegistoCliente().validateClient(this.client, this.strPwd);
        } catch (RuntimeException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            this.client = null;
            return false;
        }
    }

    /**
     * Creates a PostalAddress and returns true if it is valid and successfully added to the client, otherwise returns false
     * @param strLocal
     * @param strCodPostal
     * @param strLocalidade
     * @return true if the PostalAddress it is valid and successfully added to the client, otherwise returns false
     */
    public boolean addPostalAddress(String strLocal, String strCodPostal, String strLocalidade) {
        if (this.client != null) {
            try {
                PostalAddress morada = Client.newAddress(strLocal, strCodPostal, strLocalidade);
                return this.client.addPostalAddress(morada);
            } catch (RuntimeException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }

    /**
     * Returns true if the client is successfully registered, otherwise returns false
     * @return true if the client is successfully registered, otherwise returns false
     */
    public boolean registerClient() {
        return this.company.getRegistoCliente().registerClient(this.client, this.strPwd);
    }

    /**
     * Return Client to String
     * @return client.toString()
     */
    public String getClientString() {
        return this.client.toString();
    }
    
    /**
     * Returns this.Client
     * @return this.Client
     */
    public Client getClient(){
        return this.client;
    }
}

package lapr.project.gpsd.controller;

import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.Constantes;
import lapr.project.gpsd.model.PostalCode;

public class AssociatePostalAddressController {

    private final Company company;
    private Client cli;
    private PostalAddress end1;

    /**
     * Constructs an instance of AssociatePostalAddressController
     */
    public AssociatePostalAddressController() {
        if (!ApplicationGPSD.getInstance().getSessaoAtual().isLoggedInComPapel(Constantes.PAPEL_CLIENTE)) {
            throw new IllegalStateException("Unauthorized User");
        }
        this.company = ApplicationGPSD.getInstance().getEmpresa();
        this.cli = this.company.getRegistoCliente().getClienteByEmail(this.company.getAutorizacaoFacade().getSessaoAtual().getEmailUtilizador());
    }

    /**
     * Constructs an instance of AssociatePostalAddressController
     *
     * @param company the company
     * @param cli the client
     */
    public AssociatePostalAddressController(Company company, Client cli) {
        this.company = company;
        this.cli = cli;
    }

    /**
     * Returns the postal address
     *
     * @param address the address
     * @param postalCode the postal code
     * @param locality the locality
     * @return postal address
     */
    public PostalAddress newAddress(String address, PostalCode postalCode, String locality) {
        end1 = cli.newAddress(address, postalCode, locality);
        return end1;
    }

    /**
     * Returns true if the address is correct or false if address is incorrect
     *
     * @return true if the address is correct or false if address is incorrect
     */
    public boolean addressRegister() {
        if (cli.validAddress(end1)) {
            return cli.addPostalAddress(end1);
        } else {
            return false;
        }
    }

    /**
     * Returns true if the address is correct or false if address is incorrect
     *
     * @param cli the client
     * @param end1 the postal address
     * @return true if the address is correct or false if address is incorrect
     */
    public boolean addressRegister(Client cli, PostalAddress end1) {
        if (cli.validAddress(end1)) {
            return cli.addPostalAddress(end1);
        } else {
            return false;
        }
    }
}

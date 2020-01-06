package lapr.project.gpsd.controller;

import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.Constantes;
import lapr.project.gpsd.model.Rating;
import lapr.project.gpsd.model.ServiceProvider;

public class RatingController {

    /**
     * The rating
     */
    private Rating rat;

    /**
     * The company
     */
    private Company company;

    /**
     * The client
     */
    private Client cli;
    
    private ServiceProvider serviceProvider;

    /**
     * Constructs an instance of RatingController
     */
    public RatingController() {
        if (!ApplicationGPSD.getInstance().getSessaoAtual().isLoggedInComPapel(Constantes.PAPEL_CLIENTE)) {
            throw new IllegalStateException("Unauthorized User");
        }
        this.company = ApplicationGPSD.getInstance().getEmpresa();
        this.cli = this.company.getRegistoCliente().getClienteByEmail(this.company.getAutorizacaoFacade().getSessaoAtual().getEmailUtilizador());
    }

    public RatingController(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    /**
     * Constructs an instance of Rating
     *
     * @param rating the rating
     * @return an instance of Rating
     */
    public Rating newRating(int rating) {
        rat = serviceProvider.newRating(rating);
        return rat;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
}

package lapr.project.gpsd.model;

import java.util.Timer;
import lapr.project.autorizacao.AuthorizationFacade;

public class Company {

    private String strDesignacao;
    private String strNIF;
    private final AuthorizationFacade oAuthorization;
    private final RegisterClient registoCliente;
    private final RegisterSP registoPS;
    private final RegistoHR registoRH;
    private final RegisterService registoServico;
    private final RegisterCategory registoCategoria;
    private final RegisterArea registoArea;
    private final RegisterPC registoCP;
    private final RegisterApplication registerApplication;
    private final RegisterRequest registoPedido;
    private final RegisterServiceType registerServiceType;
    private final AffectServiceProviderTask affectspt;
    private long delay = 30 * 1000; // 1 sec delay
    private long interval = 10 * 1000; // each 10 sec
    private Timer timer;

    /**
     * Constructs an instance of Company
     *
     * @param strDesignacao designation of the company
     * @param strNIF nif of the company
     */
    public Company(String strDesignacao, String strNIF) {
        if ((strDesignacao == null) || (strNIF == null)
                || (strDesignacao.isEmpty()) || (strNIF.isEmpty())) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        }
        this.strDesignacao = strDesignacao;
        this.strNIF = strNIF;
        this.oAuthorization = new AuthorizationFacade();
        this.registoCliente = new RegisterClient(oAuthorization);
        this.registoPS = new RegisterSP(oAuthorization);
        this.registoCategoria = new RegisterCategory();
        this.registoArea = new RegisterArea();
        this.registoRH = new RegistoHR();
        this.registoCP = new RegisterPC();
        this.registoPedido = new RegisterRequest();
        this.registerApplication = new RegisterApplication();
        this.registoServico = new RegisterService();
        this.registerServiceType = registoServico.getRst();
        this.affectspt = new AffectServiceProviderTask(getRegistoPedido(), getRegistoCP(), getServiceProvidersRegister());
        this.delay = getDelay();
        this.interval = getInterval();
        this.timer = new Timer();
        timer.schedule(affectspt, delay, interval);
    }

    /**
     * Returns the authorization
     *
     * @return the authorization
     */
    public AuthorizationFacade getAutorizacaoFacade() {
        return this.oAuthorization;
    }

    /**
     * Returns the client register
     *
     * @return client register
     */
    public RegisterClient getRegistoCliente() {
        return registoCliente;
    }

    /**
     * return the service provider register
     *
     * @return RegisterServiceProvider instance
     */
    public RegisterSP getServiceProvidersRegister() {
        return registoPS;
    }

    /**
     * return the service register
     *
     * @return RegisterService instance
     */
    public RegisterService getRegistoServico() {
        return registoServico;
    }

    /**
     * return the categories register
     *
     * @return RegisterCategory instance
     */
    public RegisterCategory getRegistoCategoria() {
        return registoCategoria;
    }

    /**
     * return the postal code register
     *
     * @return RegisterPC instance
     */
    public RegisterPC getRegistoCP() {
        return registoCP;
    }

    /**
     * return the service service requests register
     *
     * @return RegisterRequest instance
     */
    public RegisterRequest getRegistoPedido() {
        return registoPedido;
    }

    /**
     * return the application register
     *
     * @return RegisterApplication instance
     */
    public RegisterApplication getRegisterApplication() {
        return registerApplication;
    }

    /**
     * return the areas register
     *
     * @return RegisterArea instance
     */
    public RegisterArea getRegisterArea() {
        return registoArea;
    }

    /**
     * return the service type register
     *
     * @return RegisterServiceType instance
     */
    public RegisterServiceType getRegisterServiceType() {
        return registerServiceType;
    }

    /**
     * get the delay attribute
     *
     * @return long with delay
     */
    public long getDelay() {
        return delay;
    }

    /**
     * get the interval attribute
     *
     * @return long with interval
     */
    public long getInterval() {
        return interval;
    }

    /**
     * get timer attribute
     *
     * @return Timer instance
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * get the affect service provider attribute
     *
     * @return AffectServiceProviderTask attribute
     */
    public AffectServiceProviderTask getAffectspt() {
        return affectspt;
    }

}

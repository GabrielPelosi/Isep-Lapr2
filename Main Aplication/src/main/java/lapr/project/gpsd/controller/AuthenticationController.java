package lapr.project.gpsd.controller;

import java.util.List;
import lapr.project.autorizacao.model.UserPapers;

public class AuthenticationController {

    private final ApplicationGPSD oApp;

    public AuthenticationController() {
        this.oApp = ApplicationGPSD.getInstance();
    }

    public boolean doLogin(String strId, String strPwd) {
        return this.oApp.doLogin(strId, strPwd);
    }

    public List<UserPapers> getPapeisUtilizador() {
        if (this.oApp.getSessaoAtual().isLoggedIn()) {
            return this.oApp.getSessaoAtual().getPapeisUtilizador();
        }
        return null;
    }

    public void doLogout() {
        this.oApp.doLogout();
    }
}

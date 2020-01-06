package lapr.project.autorizacao.model;

import java.util.List;

public class UserSession {

    private User oUser = null;

    private UserSession() {
    }

    public UserSession(User oUtilizador) {
        if (oUtilizador == null) {
            throw new IllegalArgumentException("Argument can not be null.");
        }
        this.oUser = oUtilizador;
    }

    public void doLogout() {
        this.oUser = null;
    }

    public boolean isLoggedIn() {
        return this.oUser != null;
    }

    public boolean isLoggedInComPapel(String strPapel) {
        if (isLoggedIn()) {
            return this.oUser.hasPapel(strPapel);
        }
        return false;
    }

    public String getNomeUtilizador() {
        if (isLoggedIn()) {
            this.oUser.getNome();
        }
        return null;
    }

    public String getIdUtilizador() {
        if (isLoggedIn()) {
            this.oUser.getId();
        }
        return null;
    }

    public String getEmailUtilizador() {
        if (isLoggedIn()) {
            return this.oUser.getEmail();
        }
        return null;
    }

    public List<UserPapers> getPapeisUtilizador() {
        return this.oUser.getPapeis();
    }
}

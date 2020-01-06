package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegisterPC {

    private final List<PostalCode> listCP;

    public RegisterPC() {
        this.listCP = new ArrayList<PostalCode>();
    }

    public List<PostalCode> getCP(PostalCode catId) {
        List<PostalCode> lc = new ArrayList<>();
        for (PostalCode serv : this.listCP) {
            lc.add(serv);
        }
        return lc;
    }

    public PostalCode getCPById(String cod) {
        cod = cod.trim();
        for (PostalCode serv : this.listCP) {
            if (serv.getCod().equals(cod)) {
                return serv;
            }
        }
        return null;
    }

    public PostalCode novoCP(String cod, double lat, double lon) {
        return new PostalCode(cod, lat, lon);
    }

    public boolean registaCP(PostalCode cp) {
        if (this.validaCP(cp)) {
            return addCP(cp);
        }
        return false;
    }

    private boolean addCP(PostalCode cp) {
        return listCP.add(cp);
    }

    public boolean validaCP(PostalCode cp) {
        boolean bRet = true;
        for (PostalCode c : this.listCP) {
            if (c.equals(cp)) {
                bRet = false;
            }
        }
        return bRet;
    }

    public List<PostalCode> getListCP() {
        return listCP;
    }

    
    
}

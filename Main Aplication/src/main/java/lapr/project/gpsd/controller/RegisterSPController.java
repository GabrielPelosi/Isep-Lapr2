package lapr.project.gpsd.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.RegisterApplication;
import lapr.project.gpsd.model.RegisterPC;
import lapr.project.gpsd.ui.console.utils.Utils;

public class RegisterSPController {

    Company e = Utils.getEmpresa();
    RegisterApplication rp = e.getRegisterApplication();
    RegisterPC rpc = e.getRegistoCP();

    PostalAddress add;
    List<Category> cat = new ArrayList<Category>();

    public void newApplication(String name, String nif, String pn, String email, String ah, String ph) {
        rp.registerApplication(rp.newApplication(name, nif, pn, email, ah, ph, add, cat));
    }

    public void addAddress(String city, String pc1, String pc2, String address) throws Exception {
        String pcString = String.format("%s-%s", pc1.trim(), pc2.trim());
        city = city.trim();
        address = address.trim();

        if (city == null || address == null) {
            throw new Exception("Insuficient Info");
        }

        if (rpc.getCPById(pcString) != null) {

            PostalAddress pc = new PostalAddress(address, pcString, city);
            add = pc;
            return;
        }
        throw new Exception("Postal Address not yet supported");

    }
    
    public void addAddress(String city, String pc1, String address) throws Exception {
        String pcString = pc1.trim();
        city = city.trim();
        address = address.trim();

        if (city == null || address == null) {
            throw new Exception("Insuficient Info");
        }

        if (rpc.getCPById(pcString) != null) {

            PostalAddress pc = new PostalAddress(address, pcString, city);
            add = pc;
            return;
        }
        throw new Exception("Postal Address not yet supported");

    }

    public void addCategory(Category c) throws Exception {
        for (Category cc : cat) {
            if (cc == c) {
                throw new Exception("Category already added");
            }
        }
        cat.add(c);
    }

    public PostalAddress getAdd() {
        return add;
    }

    public List<Category> getCat() {
        return cat;
    }

}

package lapr.project.gpsd.controller;

import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.PostalCode;
import lapr.project.gpsd.model.RegisterArea;
import lapr.project.gpsd.model.RegisterPC;
import lapr.project.gpsd.ui.console.utils.Utils;

public class RegisterAreaController {

    RegisterArea ra;
    RegisterPC rpc;
    String es = "XPTO";
    
    public RegisterAreaController() {
        Company c = Utils.getEmpresa();
        ra = c.getRegisterArea();
        rpc = c.getRegistoCP();
    }

    public void newArea(String name, String cp1, String cp2, String radius, String cost) throws Exception {
        if (ra.nameExists(name)) {
            throw new Exception("Area already exists with that name.");
        }
        PostalCode pc = rpc.getCPById(String.format("%s-%s", cp1.trim(), cp2.trim()));
        if (pc == null) {
            throw new Exception("Postal Code not found on the system.");
        }
        
        cost = radius.replace(',', '.');

        int rad = Integer.parseInt(cost.trim());
        double cos = Double.parseDouble(cost.trim());
        
        ra.registaArea(ra.novaArea(name, pc, rad, cos,es));
        System.out.println(ra.getAreas());

    }
}

package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.gpsd.ui.console.utils.Utils;

public class ExternalServiceXPTOAdapter implements ExternalService {

    @Override
    public List<ActsOn> getActing(PostalCode pc, int radius) {

        ExternalServiceXPTOAPI es = new ExternalServiceXPTOAPI();
        RegisterPC rpc = Utils.getEmpresa().getRegistoCP();
        RegisterArea ra = Utils.getEmpresa().getRegisterArea();
        List<PostalCode> lpc = rpc.getListCP();

        List<ActsOn> ao = new ArrayList<ActsOn>();

        ao = es.codesInsideArea(lpc, pc, radius);

        return ao;
    }

}

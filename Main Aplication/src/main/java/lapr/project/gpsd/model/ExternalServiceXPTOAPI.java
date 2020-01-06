package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.gpsd.ui.console.utils.Utils;

public class ExternalServiceXPTOAPI extends ExternalServiceXPTOAdapter {

    public List<ActsOn> codesInsideArea(List<PostalCode> lpc, PostalCode pc, int radius) {
        List<ActsOn> ao = new ArrayList<ActsOn>();
        RegisterArea ra = Utils.getEmpresa().getRegisterArea();
        
        for (PostalCode pcs : lpc) {
            double distance = distanceBetweenRegions(pc, pcs);
            if (distance <= radius) {
                boolean same = false;
                for (Area a : ra.getAreas()) {
                    List<ActsOn> list = a.getAo();
                    List<ActsOn> i = new ArrayList<ActsOn>(list);
                    for (ActsOn act : i) {
                        if (act.getPc() == pcs) {
                            same = true;
                            if (distance < act.getDistance()) {
                                a.getAo().remove(act);
                                ao.add(new ActsOn(pcs, distance));
                            }
                        }
                    }
                }
                if (same) {
                    continue;
                }
                ao.add(new ActsOn(pcs, distance));
            }
        }
        return ao;
    }

    public double distanceBetweenRegions(PostalCode pc, PostalCode pcs) {
        return Utils.distancia(pc.getLat(), pc.getLon(), pcs.getLat(), pcs.getLon());
    }
}

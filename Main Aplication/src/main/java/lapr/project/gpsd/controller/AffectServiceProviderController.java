package lapr.project.gpsd.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lapr.project.gpsd.model.AffectSPTA;
import lapr.project.gpsd.model.Data;
import lapr.project.gpsd.model.DailyAvailability;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.PostalCode;
import lapr.project.gpsd.model.RegisterPC;
import lapr.project.gpsd.model.RegisterRequest;
import lapr.project.gpsd.model.RegisterSP;
import lapr.project.gpsd.model.Request;
import lapr.project.gpsd.model.ServiceOrder;
import lapr.project.gpsd.model.RequestTime;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.model.Time;
import lapr.project.gpsd.ui.console.utils.Utils;

/**
 *
 * @author rickropes
 */
public class AffectServiceProviderController {

    String SEPARADOR_DADOS = ";";
    String extClass = "1";
    RegisterRequest rp;
    RegisterPC rpc;
    RegisterSP rsp;
    AffectSPTA algorithm;

    public AffectServiceProviderController(RegisterRequest rp, RegisterPC rpc, RegisterSP rsp) {
        this.rp = rp;
        this.rpc = rpc;
        this.rsp = rsp;
    }

    public void affectServiceProvider() {
        try {
            List<Request> listR = rp.getList();

            Class<?> oClass = Class.forName(String.format("lapr.project.gpsd.model.AffectSPTA%s", extClass.trim()));
            algorithm = (AffectSPTA) oClass.newInstance();

            for (Request r : listR) {
                List<String[]> informationList = new ArrayList<String[]>();
                boolean doIt = true;
                for (ServiceOrder i : r.getServico()) {
                    if (i.getTempSP() == null && i.getaSP() == null) {
                        String[] data = algorithm.getBestServiceProvider(r,i, rp, rpc, rsp);
                        informationList.add(data);
                    }
                }
                for (String[] d : informationList) {
                    if (d == null) {
                        doIt = false;
                        break;
                    }
                }
                if (doIt) {
                    for (int i = 0; i<informationList.size(); i++) {
                        String[] data = informationList.get(i);
                        ServiceOrder so = r.getServico().get(i);
                        ServiceProvider sp = rsp.getServiceProviderByMechNum(data[0].trim());
                        String timeString = data[1].trim();
                        Time time = new Time(timeString);
                        Data date = new Data(data[2].trim().replace("/", "-"));
                        RequestTime rt = new RequestTime(date, time);
                        so.setTempAvailability(rt);
                        so.setTempSP(sp);
                    }
                    r.setTempInfo(true);
                }
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

}

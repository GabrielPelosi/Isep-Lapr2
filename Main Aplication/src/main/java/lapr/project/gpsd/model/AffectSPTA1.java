package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.gpsd.ui.console.utils.Utils;

public class AffectSPTA1 implements AffectSPTA {

    String SEPARADOR_DADOS = ";";
    private Data choosenDate;

    @Override
    public String[] getBestServiceProvider(Request req, ServiceOrder so, RegisterRequest rp, RegisterPC rpc, RegisterSP rsp) {
        System.out.println("CALCULATING");
        RegisterArea ra = Utils.getEmpresa().getRegisterArea();
        List<Double> ratings = new ArrayList<Double>();
        List<ServiceProvider> listSP = new ArrayList<ServiceProvider>();
        List<Time> listAvailable = new ArrayList<Time>();
        Time available = null;
        PostalCode cp = req.getCP();

        for (ServiceProvider sp : rsp.getServiceProvidersList()) {
            PostalCode tempPC = sp.getEndereco().getPostalCode();
            boolean area = false;
            boolean category = false;
            for (Area a : ra.getAreas()) {
                for (ActsOn ao : a.getAo()) {
                    if (ao.getPc() == tempPC) {
                        area = true;
                    }
                }
            }
            for (Category c : sp.getListCategory()) {
                if (c == so.getService().getCat()) {
                    category = true;
                }
            }
            if (area && category) {
                available = null;
                available = checkAvailable(req, so, sp);
                if (available != null) {
                    System.out.println("FOUND AVAILABLE");
                    double score = sp.getServiceProviderScoreList().getMeanScore();
                    boolean last = true;
                    for (int i = 0; i < ratings.size(); i++) {
                        if (score > ratings.get(i)) {
                            last = false;
                            ratings.add(i, score);
                            listSP.add(i, sp);
                            listAvailable.add(i, available);
                        }
                    }
                    if (last) {
                        ratings.add(score);
                        listSP.add(sp);
                        listAvailable.add(available);
                    }
                }
            }

        }

        if (ratings.size() > 1) {
            if (ratings.get(0) == ratings.get(1)) {
                double maxValue = ratings.get(0);
                List<Double> listDistance = new ArrayList<Double>();
                List<ServiceProvider> listSPDistance = new ArrayList<ServiceProvider>();
                List<Time> listAvailableDistance = new ArrayList<Time>();

                for (int i = 0; i < listSP.size(); i++) {
                    if (ratings.get(i) < maxValue) {
                        break;
                    }

                    ServiceProvider sp = listSP.get(i);
                    PostalCode pcSP = sp.getEndereco().getPostalCode();
                    PostalCode pcRequest = req.getCP();
                    double distance = Utils.distancia(pcSP.getLat(), pcSP.getLon(), pcRequest.getLat(), pcRequest.getLon());
                    boolean last = true;
                    for (int j = 0; j < listDistance.size(); j++) {
                        if (distance > listDistance.get(i)) {
                            last = false;
                            listDistance.add(j, distance);
                            listSPDistance.add(j, sp);
                            listAvailableDistance.add(j, available);
                        }
                        if (last) {
                            listDistance.add(distance);
                            listSPDistance.add(sp);
                            listAvailableDistance.add(available);
                        }
                    }
                }

                if (listDistance.size() > 1) {
                    if (listDistance.get(0) == listDistance.get(1)) {

                        ServiceProvider finalSP = listSPDistance.get(0);
                        Time finalAvailable = listAvailableDistance.get(0);

                        for (int i = 1; i < listSP.size(); i++) {
                            ServiceProvider temp = listSPDistance.get(i);
                            if (finalSP.getAbvName().compareTo(temp.getAbvName()) < 0) {
                                finalSP = temp;
                                finalAvailable = listAvailableDistance.get(i);
                            } else {
                                break;
                            }
                        }
                        if (checkRejected(so, finalAvailable)) {
                            System.out.println("FOUND COMPATIBLE");
                            return new String[]{finalSP.getTypingNumber(), finalAvailable.toString(), choosenDate.toAnoMesDiaString()};
                        }
                    }
                }
                for (int i = 0; i < listAvailableDistance.size(); i++) {
                    if (checkRejected(so, listAvailableDistance.get(i))) {
                        System.out.println("FOUND COMPATIBLE");
                        return new String[]{listSPDistance.get(i).getTypingNumber(), listAvailableDistance.get(i).toString(), choosenDate.toAnoMesDiaString()};
                    }
                }
            }
        }
        if (listSP.size() > 0) {
            for (int i = 0; i < listAvailable.size(); i++) {
                if (checkRejected(so, listAvailable.get(i))) {
                    System.out.println("FOUND COMPATIBLE");
                    return new String[]{listSP.get(0).getTypingNumber(), listAvailable.get(0).toString(), choosenDate.toAnoMesDiaString()};
                }
            }
        }
        return null;

    }

    private Time checkAvailable(Request rq, ServiceOrder so , ServiceProvider sp) {
        List<DailyAvailability> listAvailable = sp.getListaDisponibilidades();
        List<DailyAvailability> listBusy = sp.getBusyList();

        for (DailyAvailability d : listAvailable) {
            Data dateSP = d.getDataI();
            String hourISP = d.getHoraI();
            String[] hourISPTemp = hourISP.split(":");
            Time providerBegin = new Time(Integer.parseInt(hourISPTemp[0].trim()), Integer.parseInt(hourISPTemp[1].trim()));

            String hourFSP = d.getHoraF();
            String[] hourFSPTemp = hourFSP.split(":");
            Time providerEnd = new Time(Integer.parseInt(hourFSPTemp[0].trim()), Integer.parseInt(hourFSPTemp[1].trim()));

            for (String h : rq.getHorario()) {
                String[] info = h.split(SEPARADOR_DADOS);
                Data dateR = new Data(info[0]);
                String[] temp = info[1].split(":");

                int horaI = Integer.parseInt(temp[0].trim());
                int minutosI = Integer.parseInt(temp[1].trim());
                Time pedidoInicio = new Time(horaI, minutosI);

                int horaF = Integer.parseInt(temp[0].trim()) + so.getExecTime().getHh();
                int minutosF = Integer.parseInt(temp[1].trim()) + so.getExecTime().getMm();
                Time pedidoFim = new Time(horaF, minutosF);
                if (dateR.same(dateSP) && !providerBegin.isMaior(pedidoInicio) && providerEnd.isMaior(pedidoFim)) {
                    boolean notBusy = true;
                    for (DailyAvailability b : listBusy) {
                        if (b.getDataI().equals(dateR)) {
                            notBusy = false;
                            String busyHourI = d.getHoraI();
                            String[] busyHourITemp = busyHourI.split(":");
                            Time busyHourBegin = new Time(Integer.parseInt(busyHourITemp[0].trim()), Integer.parseInt(busyHourITemp[1].trim()));

                            String busyHourF = d.getHoraF();
                            String[] busyHourFTemp = busyHourF.split(":");
                            Time busyHourEnd = new Time(Integer.parseInt(busyHourFTemp[0].trim()), Integer.parseInt(busyHourFTemp[1].trim()));

                            if (!pedidoInicio.isMaior(busyHourBegin) || pedidoInicio.isMaior(busyHourEnd)) {
                                if (!pedidoFim.isMaior(busyHourBegin) || pedidoFim.isMaior(busyHourEnd)) {
                                    this.choosenDate = d.getDataI();
                                    return pedidoInicio;
                                }
                            }

                        }

                    }
                    if (notBusy) {
                        this.choosenDate = d.getDataI();
                        return pedidoInicio;
                    }
                }
            }

        }

        return null;
    }

    private boolean checkRejected(ServiceOrder pedido, Time time) {
        for (RequestTime t : pedido.getrAvailability()) {
            if (t.getDate().same(choosenDate) && t.getTime().diferencaS(time) == 0) {
                return false;
            }
        }
        return true;
    }

}

package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegisterRequest {

    private final Set<Request> listRequests;

    private static final String DATA_TAB_DATE = ";";

    public RegisterRequest() {
        this.listRequests = new HashSet<>();
    }

    public List<Request> getList() {
        List<Request> lc = new ArrayList<>();

        for (Request ped : this.listRequests) {
            lc.add(ped);
        }
        return lc;
    }

    public Request novoPedido(Client cl, PostalAddress morada, List<Service> servico, List<String> horario, String infoAdicional, double preco, List<Time> tempo) {
        return new Request(cl, morada, servico, horario, infoAdicional, preco, tempo);
    }

    public boolean registaPedido(Request cp) {
        if (this.validaPedido(cp)) {
            return addPedido(cp);
        }
        return false;
    }

    private boolean addPedido(Request cp) {
        return listRequests.add(cp);
    }

    public boolean addPedidoLista(List<Request> cp) {
        return listRequests.addAll(cp);
    }

    public boolean validaPedido(Request cp) {
        for (Request c : this.listRequests) {
            if (c.allInfo().equals(cp.allInfo())) {
                return false;
            }
        }
        return true;
    }

    /*public List<ServiceOrder> getRequestsWithPeriod(String period) {
        String[] periodArray = period.split(DATA_TAB_DATE);
        String startDate = periodArray[0].trim();
        String[] startSplit = startDate.split(":");
        String[] finalSplit = startDate.split(":");
        String[] dataSplit = startDate.split("-");
        String finalDate = periodArray[2].trim();
        String[] datafSplit = finalDate.split("-");

        String startHour = periodArray[1].trim();
        String endHour = periodArray[3].trim();
        Data d = new Data(Integer.parseInt(dataSplit[0].trim()), Integer.parseInt(dataSplit[1].trim()), Integer.parseInt(dataSplit[2].trim()));
        Data f = new Data(Integer.parseInt(dataSplit[0].trim()), Integer.parseInt(dataSplit[1].trim()), Integer.parseInt(dataSplit[2].trim()));
        for (RequestCompleted ped : this.listRequestsDone) {

            if (!d.isMaior(ped.getRequest().getRsp().getaAvailability().getDate()) && !ped.getRequest().getRsp().getaAvailability().getDate().isMaior(f)) {
                if (Integer.parseInt(startSplit[0]) <= ped.getRequest().getRsp().getaAvailability().getTime().getHh() && Integer.parseInt(startSplit[1]) <= ped.getRequest().getRsp().getaAvailability().getTime().getMm()) {
                    if (true) {

                    }
                }
            }

        }
        return null;

    }*/

}

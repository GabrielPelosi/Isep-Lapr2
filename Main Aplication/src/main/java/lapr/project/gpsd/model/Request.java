package lapr.project.gpsd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lapr.project.gpsd.ui.console.utils.Utils;

public class Request implements Serializable {

    Client cl;
    boolean tempInfo = false;
    boolean assignedInfo = false;
    boolean completed = false;
    PostalAddress morada;
    List<ServiceOrder> servico = new ArrayList<ServiceOrder>();
    List<ServiceOrder> servicoCompleto = new ArrayList<ServiceOrder>();
    List<String> horario;
    String infoAdicional;
    double preco;
    List<Time> tempo;

    public Request(Client cl, PostalAddress morada, List<Service> servico, List<String> horario, String infoAdicional, double preco, List<Time> tempo) {
        this.morada = morada;
        this.horario = new ArrayList<String>(horario);
        this.infoAdicional = infoAdicional;
        this.preco = preco;
        this.cl = cl;
        this.tempo = tempo;
        for (int i = 0; i < servico.size(); i++) {
            Service s = servico.get(i);
            Time t = tempo.get(i);
            this.servico.add(new ServiceOrder(s, t));
        }
    }

    public void confirmSP() {
        for (ServiceOrder i : servico) {
            i.setaAvailability(i.getTempAvailability());
            i.setaSP(i.getTempSP());
        }

    }

    public void rejectSP() {
        for (ServiceOrder i : servico) {
            i.getrAvailability().add(i.getTempAvailability());
            i.setTempAvailability(null);
            i.setTempSP(null);
        }
    }

    public void setDone(ServiceOrder so) {
        so.setFinished();
        servicoCompleto.add(so);
        servico.remove(so);
        checkDone();
    }

    public void setDone(ServiceOrder so, String p, String s) {
        servico.remove(so);
        so.setFinished(p, s);
        servicoCompleto.add(so);
        checkDone();
    }

    private void checkDone() {
        System.out.println("FINISHED");
        if (servico.size() == 0) {
            completed = true;
        }

        /*if(servico.size() == 0){
            Utils.getEmpresa().getRegistoPedido().registaPedidoDone(this, new RequestCompleted(this));
        }*/
    }

    public List<ServiceOrder> getServico() {
        return servico;
    }

    public PostalCode getCP() {
        if (morada.getPostalCode() == null) {
            throw new IllegalArgumentException("Postal Code is null");
        }
        return morada.getPostalCode();
    }

    public List<Time> getTempo() {
        return tempo;
    }

    public List<String> getHorario() {
        return horario;
    }

    public Client getCl() {
        return cl;
    }

    public PostalAddress getMorada() {
        return morada;
    }

    @Override
    public String toString() {

        return String.format("%s; Horario %s; Morada: %s", CatServ(), horario, morada);
    }

    public String allInfo() {
        return "Pedido{" + "cl=" + cl + ", morada=" + morada + ", servico=" + servico + ", horario=" + horario + ", infoAdicional=" + infoAdicional + ", preco=" + preco + '}';
    }

    public String CatServ() {
        String tempc = "";
        String temps = "";
        for (ServiceOrder s : servico) {
            tempc += s.getService().getCat() + ", ";
            temps += s.getService().getId() + ", ";
        }

        return String.format("%s - %s", tempc, temps);
    }

    public void setTempInfo(boolean tempInfo) {
        this.tempInfo = tempInfo;
    }

    public void setAssignedInfo(boolean assignedInfo) {
        this.assignedInfo = assignedInfo;
    }

    public boolean getTempInfo() {
        return tempInfo;
    }

    public boolean getAssignedInfo() {
        return assignedInfo;
    }

    public List<ServiceOrder> getServicoCompleto() {
        return servicoCompleto;
    }

}

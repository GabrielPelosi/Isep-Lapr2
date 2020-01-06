package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.List;

public class ServiceOrder {

    Service service;
    ServiceProvider tempSP;
    RequestTime tempAvailability;
    ServiceProvider aSP;
    RequestTime aAvailability;
    List<ServiceProvider> rSP;
    List<RequestTime> rAvailability;
    boolean finished = false;
    boolean rated = false;
    String problem = "";
    String solution = "";
    Time execTime;
    private double parseDouble;

    public ServiceOrder(Service service, Time execTime) {
        this.service = service;
        this.execTime = execTime;
        this.tempSP = null;
        this.tempAvailability = null;
        this.aSP = null;
        this.aAvailability = null;
        this.rSP = new ArrayList<ServiceProvider>();
        this.rAvailability = new ArrayList<RequestTime>();
    }

    public boolean getRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }

    public void setFinished() {
        this.finished = true;
    }
    
    public void setFinished(String p, String s) {
        this.finished = true;
        this.problem = p;
        this.solution = s;
    }
    
    public Service getService() {
        return service;
    }

    public void addSP(ServiceProvider tempSP, RequestTime tempAvailability) {
        this.tempSP = tempSP;
        this.tempAvailability = tempAvailability;
    }

    public ServiceProvider getTempSP() {
        return tempSP;
    }

    public RequestTime getTempAvailability() {
        return tempAvailability;
    }

    public ServiceProvider getaSP() {
        return aSP;
    }

    public RequestTime getaAvailability() {
        return aAvailability;
    }

    public List<ServiceProvider> getrSP() {
        return rSP;
    }

    public List<RequestTime> getrAvailability() {
        return rAvailability;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setTempSP(ServiceProvider tempSP) {
        this.tempSP = tempSP;
    }

    public void setTempAvailability(RequestTime tempAvailability) {
        this.tempAvailability = tempAvailability;
    }

    public void setaSP(ServiceProvider aSP) {
        this.aSP = aSP;
    }

    public void setaAvailability(RequestTime aAvailability) {
        this.aAvailability = aAvailability;
    }

    public void setrSP(List<ServiceProvider> rSP) {
        this.rSP = rSP;
    }

    public void setrAvailability(List<RequestTime> rAvailability) {
        this.rAvailability = rAvailability;
    }

    public boolean isFinished() {
        return finished;
    }

    public String getProblem() {
        return problem;
    }

    public String getSolution() {
        return solution;
    }

    public Time getExecTime() {
        return execTime;
    }
    
    

    @Override
    public String toString() {
        System.out.println(String.format("Service: %s,%nBy:%s,%nAt:%s%s%n", service.getDescriptionShort(), tempSP.getAbvName(), tempAvailability.getDate().toAnoMesDiaString(), tempAvailability.getTime().toString()));
        return String.format("Service: %s,%nBy:%s,%nAt:%s%s%n", service.getDescriptionShort(), tempSP.getAbvName(), tempAvailability.getDate().toAnoMesDiaString(), tempAvailability.getTime().toString());
    }

    public void addValue(double parseDouble) {
        this.parseDouble = parseDouble;
    }

    
}

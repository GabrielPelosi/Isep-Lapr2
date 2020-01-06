package lapr.project.gpsd.controller;

import java.util.List;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.RegisterService;
import lapr.project.gpsd.model.ServiceType;
import lapr.project.gpsd.model.Time;
import lapr.project.gpsd.ui.console.utils.Utils;


public class SpecifyServiceController {

    private Company company = Utils.getEmpresa();
    private RegisterService register = Utils.getEmpresa().getRegistoServico();

    public SpecifyServiceController() {
    }
    
    public void newService(String id, String descShort, String descLong, double priceHour, Category cat, Time time, ServiceType type) {
        register.registerService(register.newService(id, descShort, descLong, priceHour, cat, time, type));
        System.out.println(register.getList());
    }
    
    public List<ServiceType> getServiceTypes(){
        return register.getServiceTypes();
    }
    
    
    

}

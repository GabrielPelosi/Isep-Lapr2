package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.gpsd.controller.ReadFileController;

public class RegisterServiceType {

    List<ServiceType> list;

    public RegisterServiceType() {
        list = new ArrayList<ServiceType>();
        ReadFileController rfc = new ReadFileController();
        try {
            list.addAll(rfc.criaTiposServicosSuportados(rfc.readProperties()));
            System.out.println(list);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addServiceTyper(ServiceType st) {
        list.add(st);
    }

    public ServiceType addServiceTyper(String name, String className) {
        return new ServiceType(name, className);
    }

    public List<ServiceType> getList() {
        return list;
    }
    
    

}

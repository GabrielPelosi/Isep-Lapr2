package lapr.project.gpsd.model;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lapr.project.gpsd.controller.ReadFileController;
import lapr.project.gpsd.ui.console.utils.Utils;

public class RegisterService {

    private final Set<Service> serviceList;
    private final RegisterServiceType rst;

    public RegisterService() {
        this.serviceList = new HashSet<>();
        rst = new RegisterServiceType();
        
    }

    public Service newService(String id, String descShort, String descLong, double priceHour, Category cat, Time time, ServiceType type) {
        try {
            //System.out.println(type);
            Class<?> oClass = Class.forName(type.getClassName());
            Class[] argsClasses;
            if (time == null) {
                argsClasses = new Class[]{String.class, String.class, String.class, double.class, Category.class};
            } else {
                argsClasses = new Class[]{String.class, String.class, String.class, double.class, Category.class, Time.class};
            }
            Constructor constructor = oClass.getConstructor(argsClasses);

            Object[] argsValues;
            if (time == null) {
                argsValues = new Object[]{id, descShort, descLong, priceHour, cat};
            } else {
                argsValues = new Object[]{id, descShort, descLong, priceHour, cat, time};
            }

            Service serv = (Service) constructor.newInstance(argsValues);

            return serv;

        } catch (Exception ex) {
            System.out.println("FUCK: " + ex.getLocalizedMessage());
        }
        return null;
    }

    public boolean registerService(Service oServico) {
        if (this.validatesService(oServico)) {
            return addService(oServico);
        }
        return false;
    }

    private boolean addService(Service oServico) {
        return serviceList.add(oServico);
    }

    public boolean validatesService(Service oServico) {
        boolean bRet = true;

        // Escrever aqui o código de validação
        //
        return bRet;
    }

    public List<ServiceType> getServiceTypes() {
        return rst.getList();
    }

    public List<Service> getServicos(String catId) {
        List<Service> lc = new ArrayList<>();

        for (Service serv : this.serviceList) {
            if (serv.getCat().getId().equals(catId)) {
                lc.add(serv);
            }
        }
        return lc;
    }

    public Set<Service> getList() {
        return serviceList;
    }

    public RegisterServiceType getRst() {
        return rst;
    }
    
    

}

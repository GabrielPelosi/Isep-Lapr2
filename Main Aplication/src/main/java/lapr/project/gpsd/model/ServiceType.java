package lapr.project.gpsd.model;


public class ServiceType {

    String name;
    String className;

    public ServiceType(String name, String className) {
        this.name = name.trim();
        this.className = className.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
    
    
    
}

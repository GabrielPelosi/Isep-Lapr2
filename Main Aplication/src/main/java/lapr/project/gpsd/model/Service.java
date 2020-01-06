package lapr.project.gpsd.model;

public interface Service {
     
     public Category getCat();
     
     public String getId();
     
     public String getType();
     
     public String getDescriptionShort();
     
     public String getDescriptionFull();
     
     public Time getTime();
     
     public double getValue();
     
     
}

package lapr.project.gpsd.model;

public class ActsOn {

    double distance;
    PostalCode pc;

    public ActsOn(PostalCode pc, double distance) {
        this.distance = distance;
        this.pc = pc;
    }

    public double getDistance() {
        return distance;
    }

    public PostalCode getPc() {
        return pc;
    }

    @Override
    public String toString() {
        return String.format("%s, %.2f", pc, distance);
    }
    
    

    
    
}

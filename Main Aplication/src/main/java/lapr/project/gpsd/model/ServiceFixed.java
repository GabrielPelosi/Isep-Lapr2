package lapr.project.gpsd.model;

public class ServiceFixed implements Service {

    private String id;
    private String descShort;
    private String descLong;
    private double priceHour;
    private Category cat;
    private Time time;

    public ServiceFixed(String id, String descShort, String descLong, double priceHour, Category cat, Time time) {
        this.id = id;
        this.descShort = descShort;
        this.descLong = descLong;
        this.priceHour = priceHour;
        this.cat = cat;
        this.time = time;
    }

    @Override
    public Category getCat() {
        return cat;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return "Fixed";
    }

    @Override
    public String getDescriptionShort() {
        return descShort;
    }

    @Override
    public String getDescriptionFull() {
        return descLong;
    }

    @Override
    public Time getTime() {
        return time;
    }

    @Override
    public double getValue() {
        return priceHour;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %.2f", id, descShort, priceHour);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

/**
 *
 * @author rickropes
 */
public class ServiceExpandible implements Service {

    private String id;
    private String descShort;
    private String descLong;
    private double priceHour;
    private Category cat;

    public ServiceExpandible(String id, String descShort, String descLong, double priceHour, Category cat) {
        this.id = id;
        this.descShort = descShort;
        this.descLong = descLong;
        this.priceHour = priceHour;
        this.cat = cat;
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
        return "Expandible";
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
        return null;
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

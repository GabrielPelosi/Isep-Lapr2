package lapr.project.gpsd.model;

import java.io.Serializable;

public class PostalCode implements Serializable {

    /**
     * The postal code
     */
    String cod;

    /**
     * The latitude
     */
    double lat;

    /**
     * The longitude
     */
    double lon;

    /**
     * Constructs an instance of PostalCode
     *
     * @param cod the postal code
     * @param lat the latitude
     * @param lon the longitude
     */
    public PostalCode(String cod, double lat, double lon) {
        if ((cod == null) || (lat == 0) || (lon == 0) || (cod.isEmpty())) {
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        }
        this.cod = cod;
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * Constructs an intance of PostalCode
     *
     * @param cod the postal code
     */
    public PostalCode(String cod) {
        this.cod = cod;
    }

    /**
     * Returns the postal code
     *
     * @return postal code
     */
    public String getCod() {
        return cod;
    }

    /**
     * Returns the postal code information
     *
     * @return postal code information
     */
    @Override
    public String toString() {
        return String.format("%s", cod);
    }

    /**
     * Returns the postal code information
     *
     * @return postal code information
     */
    public String toStringFull() {
        return String.format("%s; %f; %f", cod, lat, lon);
    }

    /**
     * Returns the latitude
     *
     * @return the latitude
     */
    public double getLat() {
        return lat;
    }

    /**
     * Returns the longitude
     *
     * @return the longitude
     */
    public double getLon() {
        return lon;
    }

}

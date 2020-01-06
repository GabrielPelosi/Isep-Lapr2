package lapr.project.gpsd.model;

import java.io.Serializable;
import java.util.Objects;

public class PostalAddress implements Serializable {

    private final String address;
    private final PostalCode postalCode;
    private final String locality;

    /**
     * Constructs an intance of PostalAddress
     *
     * @param address the address
     * @param postalCode the postal code
     * @param locality the locality
     */
    public PostalAddress(String address, PostalCode postalCode, String locality) {
        if ((address == null) || (postalCode == null) || (locality == null)
                || (address.isEmpty()) || (locality.isEmpty())) {
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        }
        this.address = address;
        this.locality = locality;
        this.postalCode = postalCode;
    }

    /**
     * Constructs an intance of PostalAddress
     *
     * @param address the address
     * @param postalCode the postal code
     * @param locality the locality
     */
    public PostalAddress(String address, String postalCode, String locality) {
        if ((address == null) || (postalCode == null) || (locality == null)
                || (address.isEmpty()) || (postalCode.isEmpty()) || (locality.isEmpty())) {
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        }
        this.address = address;
        this.locality = locality;
        this.postalCode = new PostalCode(postalCode);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hash(this.address, this.postalCode, this.locality);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        // Inspirado em https://www.sitepoint.com/implement-javas-equals-method-correctly/

        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }
        // field comparison
        PostalAddress obj = (PostalAddress) o;
        return (Objects.equals(address, obj.address)
                && Objects.equals(postalCode, obj.postalCode)
                && Objects.equals(locality, obj.locality));
    }

    /**
     * Returns the postal address information
     *
     * @return postal address information
     */
    @Override
    public String toString() {
        return String.format("%s - %s - %s", this.address, this.postalCode, this.locality);
    }

    /**
     * Return address of Postal Address
     *
     * @return address of postal address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Return postal code of Postal Address
     *
     * @return postal code of postal address
     */
    public PostalCode getPostalCode() {
        return postalCode;
    }

    /**
     * Returns locality of Postal Address
     *
     * @return locality of postal address
     */
    public String getLocality() {
        return locality;
    }

}

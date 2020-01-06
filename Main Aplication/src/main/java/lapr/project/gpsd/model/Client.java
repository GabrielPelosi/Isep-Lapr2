package lapr.project.gpsd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client implements Serializable {

    private final String strName;
    private final String strNIF;
    private final String strTelephone;
    private final String strEmail;
    private final List<PostalAddress> lstMoradas = new ArrayList<>();

    /**
     * Constructs an instance of Client
     *
     * @param strNome client´s name
     * @param strNIF client´s nif
     * @param strTelefone client´s phone number
     * @param strEmail client´s email
     * @param oMorada client´s address
     */
    public Client(String strNome, String strNIF, String strTelefone, String strEmail, PostalAddress oMorada) {
        if ((strNome == null) || (strNIF == null) || (strTelefone == null)
                || (strEmail == null) || (oMorada == null)
                || (strNome.isEmpty()) || (strNIF.isEmpty()) || (strTelefone.isEmpty())
                || (strEmail.isEmpty())) {
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        }

        this.strName = strNome;
        this.strEmail = strEmail;
        this.strNIF = strNIF;
        this.strTelephone = strTelefone;
        lstMoradas.add(oMorada);
    }

    /**
     * Returns the client´s name
     *
     * @return client´s name
     */
    public String getNome() {
        return this.strName;
    }

    /**
     * Returns the client´s NIF
     *
     * @return client´s NIF
     */
    public String getNIF() {
        return strNIF;
    }

    /**
     * Returns the client´s email
     *
     * @return client´s email
     */
    public String getEmail() {
        return this.strEmail;
    }

    /**
     * Returns the list of postal addresses
     *
     * @return list of postal addresses
     */
    public List<PostalAddress> getMoradas() {
        return this.lstMoradas;
    }

    /**
     * Returns true if email exists or false if not
     *
     * @param strEmail the client´s email
     * @return true if email exists or false if not
     */
    public boolean hasEmail(String strEmail) {
        return this.strEmail.equalsIgnoreCase(strEmail);
    }

    /**
     * Adds the postal address to a list
     *
     * @param oMorada the postal address
     * @return true if the addition to a list occur with success or false if
     * occur with no success
     */
    public boolean addPostalAddress(PostalAddress oMorada) {
        return this.lstMoradas.add(oMorada);
    }

    /**
     * Removes the postal address
     *
     * @param oMorada the postal address
     * @return true if the postal address is removed with success or false if
     * not
     */
    public boolean removeEnderecoPostal(PostalAddress oMorada) {
        return this.lstMoradas.remove(oMorada);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.strEmail);
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
        Client obj = (Client) o;
        return (Objects.equals(strEmail, obj.strEmail) || Objects.equals(strNIF, obj.strNIF));
    }

    /**
     * Returns the client´s information
     *
     * @return client´s information
     */
    @Override
    public String toString() {
        String str = String.format("%s - %s - %s - %s", this.strName, this.strNIF, this.strTelephone, this.strEmail);
        str = this.lstMoradas.stream().map((morada) -> "\nMorada:\n" + morada.toString()).reduce(str, String::concat);
        return str;
    }

    /**
     * Returns an instance of PostalAddress
     *
     * @param address the address
     * @param postalCode the postal code
     * @param locality the locality
     * @return an instance of PostalAddress
     */
    public static PostalAddress newAddress(String address, PostalCode postalCode, String locality) {
        return new PostalAddress(address, postalCode, locality);
    }

    /**
     * Returns an instance of PostalAddress
     *
     * @param address the address
     * @param postalCode the postal code
     * @param locality the locality
     * @return an instance of PostalAddress
     */
    public static PostalAddress newAddress(String address, String postalCode, String locality) {
        return new PostalAddress(address, postalCode, locality);
    }

    /**
     * Validates the postal address
     *
     * @param end1 the postal address
     * @return true if the postal address is correct or false if is incorrect
     */
    public boolean validAddress(PostalAddress end1) {
        return !((end1.getAddress() == null) || (end1.getPostalCode() == null) || (end1.getLocality() == null)
                || (end1.getAddress().isEmpty()) || (end1.getLocality().isEmpty()));
    }

}

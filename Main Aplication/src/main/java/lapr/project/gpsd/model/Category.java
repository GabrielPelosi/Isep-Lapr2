package lapr.project.gpsd.model;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable {

    /**
     * The category code
     */
    private String id;

    /**
     * The category description
     */
    private String description;

    private static final String CODE_VAZIO = "without code";
    private static final String DESCRIPTION = "without description";

    public Category() {
        this.id = null;
        this.description = null;
    }

    /**
     * Constructs a category instance by getting the id and description for that
     * category
     *
     * @param strId the category id
     * @param strDescription the category description
     */
    public Category(String strId, String strDescription) {
        if ((strId == null) || (strDescription == null)
                || (strId.isEmpty()) || (strDescription.isEmpty())) {
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        }

        this.id = strId;
        this.description = strDescription;
    }

    /**
     * Returns true or false if the category code received per parameter is
     * equal or not to the category id
     *
     * @param strCode the category id to compare
     * @return true or false if the category id received per paramete is equal
     * or not to the category id
     */
    public boolean hasId(String strCode) {
        return this.id.equalsIgnoreCase(strCode);
    }

    /**
     * Returns the category id
     *
     * @return category id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the category description
     *
     * @return the category description
     */
    public String getM_strDescricao() {
        return description;
    }

    /**
     * Change category id
     *
     * @param id new category id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Change category description
     *
     * @param desc new category description
     */
    public void setDescription(String desc) {
        this.description = desc;
    }

    /**
     * Returns true or false depending on whether the created category is valid
     * or invalid, respectively.
     *
     * @return true or false depending on wheter the created category is valid
     * or invalid, respectively.
     */
    public boolean validate() {
        if ((this.id == null) || (this.description == null)
                || (this.id.isEmpty()) || (this.description.isEmpty())) {
            throw new NumberFormatException();
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Returns true or false depending on whether or not the category is the
     * same as the category received by parameter.
     *
     * @param o category to compare
     * @return true or flase depending on whether or not the category is the
     * same as the category received by parameter.
     */
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
        Category obj = (Category) o;
        return (Objects.equals(id, obj.id));
    }

    /**
     * Returns the textual description of category
     *
     * @return the textual description of category
     */
    @Override
    public String toString() {
        return String.format("%s - %s ", this.id, this.description);
    }

}

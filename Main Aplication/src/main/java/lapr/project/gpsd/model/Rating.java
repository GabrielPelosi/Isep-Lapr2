package lapr.project.gpsd.model;

public class Rating {

    /**
     * The rating value.
     */
    private int value;

    /**
     * Constructs a Rating instance by getting the value for that rating
     * @param value rating value
     */
    public Rating(int value) {
        this.value = value;
    }

    /**
     * Returns the rating value
     * @return the rating value
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Returns true or false if rating is equals rating(parameter)
     * @param rating rating to compare
     * @return true or false if rating is equals rating(parameter)
     */
    public boolean equals (Rating rating){

        if (this == rating) {
            return true;
        }

        if (rating == null) {
            return false;
        }
        
        if (value == rating.getValue()){
            return true;
        }
        return false;
    }
}

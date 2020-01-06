package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RatingRegister {

    /**
     * The set of rates
     */
    private final Set<Rating> scoreList;

    /**
     * Constructs an intance of RatingRegister
     */
    public RatingRegister() {
        this.scoreList = new HashSet<>();
    }

    /**
     * Constructs an intance of RatingRegister
     *
     * @param ratingList the set os rates
     */
    public RatingRegister(Set<Rating> ratingList) {
        this.scoreList = ratingList;
    }

    /**
     * Returns the mean of rates
     *
     * @return mean of rates
     */
    public double getMeanScore() {
        double scoreTotal = 0;
        for (Rating score : scoreList) {
            scoreTotal += score.getValue();
        }
        return scoreTotal / scoreList.size();
    }

    /**
     * Returns the rates´s list
     *
     * @return rates´s list
     */
    public List getScoreList() {
        List<Rating> ScoreList1 = new ArrayList<>();
        ScoreList1.addAll(this.scoreList);
        return ScoreList1;
    }

    /**
     * Validates the rate
     *
     * @param rat the rate
     * @return true if rate is correct or false if is incorrect
     */
    public boolean validRating(int rat) {
        if (rat < 0 || rat > 5) {
            return false;
        }
        return true;
    }

    /**
     * Returns the sum of rates
     *
     * @return sum of rates
     */
    public double getRateSum() {
        double scoreTotal = 0;
        for (Rating score : scoreList) {
            scoreTotal += score.getValue();
        }
        return scoreTotal;
    }

    /**
     * Adds a new rate to a set of rates
     *
     * @param rat the rate
     * @return true if the rate is added with success or false if is not
     */
    public boolean addRating(Rating rat) {
        return this.scoreList.add(rat);
    }

    /**
     * Constructs a new Rating
     *
     * @param rating the rating
     * @return a new Rating
     */
    public Rating newRating(int rating) {
        return new Rating(rating);
    }

    /**
     * Returns the size of the set of rates
     *
     * @return size of the set of rates
     */
    public int getRatingNum() {
        return scoreList.size();
    }

}

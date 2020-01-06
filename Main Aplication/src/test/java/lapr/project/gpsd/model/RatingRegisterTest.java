/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Francisco Ferreira
 */
public class RatingRegisterTest {
    
    
    /**
     * Test of getMeanScore method, of class RatingRegister.
     */
    @Test
    public void testGetMeanScore() {
        System.out.println("getMeanScore");
        Set<Rating> ratingList = new HashSet<>();
        Rating r1 = new Rating (3);
        Rating r2 = new Rating (4);
        Rating r3 = new Rating (5);
        ratingList.add(r1);
        ratingList.add(r2);
        ratingList.add(r3);
        RatingRegister instance = new RatingRegister(ratingList);
        double expResult = 4.0;
        double result = instance.getMeanScore();
        assertEquals(result, expResult, 0.5);
    }

    /**
     * Test of getScoreList method, of class RatingRegister.
     */
//    @Test
//    public void testGetScoreList() {
//        System.out.println("getScoreList");
//        Set<Rating> ratingSet = new HashSet<>();
//        Rating r1 = new Rating (3);
//        Rating r2 = new Rating (4);
//        Rating r3 = new Rating (5);
//        ratingSet.add(r1);
//        ratingSet.add(r2);
//        ratingSet.add(r3);
//        RatingRegister instance = new RatingRegister(ratingSet);
//        List<Rating> ratingList = new ArrayList<>();
//        ratingList.add (r1);
//        ratingList.add (r2);
//        ratingList.add (r3);
//        List expResult = ratingList;
//        List result = instance.getScoreList();
//        assertEquals(result, expResult);
//    }

    /**
     * Test of validRating method, of class RatingRegister.
     */
    @Test
    public void testValidRating() {
        System.out.println("validRating");
        int rat = 3;
        RatingRegister instance = new RatingRegister();
        boolean expResult = true;
        boolean result = instance.validRating(rat);
        assertEquals(result, expResult);
    }

    /**
     * Test of getRateSum method, of class RatingRegister.
     */
    @Test
    public void testGetRateSum() {
        System.out.println("getRateSum");
        Set<Rating> ratingSet = new HashSet<>();
        Rating r1 = new Rating (3);
        Rating r2 = new Rating (4);
        Rating r3 = new Rating (5);
        ratingSet.add(r1);
        ratingSet.add(r2);
        ratingSet.add(r3);
        RatingRegister instance = new RatingRegister(ratingSet);
        double expResult = 12.0;
        double result = instance.getRateSum();
        assertEquals(result, expResult, 0.0);
    }

    /**
     * Test of addRating method, of class RatingRegister.
     */
    @Test
    public void testAddRating() {
        System.out.println("addRating");
        Set<Rating> ratingSet = new HashSet<>();
        Rating r1 = new Rating (3);
        Rating r2 = new Rating (4);
        Rating r3 = new Rating (5);
        Rating r4 = new Rating (3);
        Rating rat = r4;
        RatingRegister instance = new RatingRegister();
        boolean expResult = true;
        boolean result = instance.addRating(rat);
        assertEquals(result, expResult);
    }

    /**
     * Test of newRating method, of class RatingRegister.
     */
    @Test
    public void testNewRating() {
        System.out.println("newRating");
        int rating = 4;
        RatingRegister instance = new RatingRegister();
        Rating expResult = new Rating(rating);
        Rating result = instance.newRating(rating);
        result.equals(expResult);
    }

    /**
     * Test of getRatingNum method, of class RatingRegister.
     */
    @Test
    public void testGetRatingNum() {
        System.out.println("getRatingNum");
         Set<Rating> ratingSet = new HashSet<>();
        Rating r1 = new Rating (3);
        Rating r2 = new Rating (4);
        Rating r3 = new Rating (5);
        ratingSet.add(r1);
        ratingSet.add(r2);
        ratingSet.add(r3);
        RatingRegister instance = new RatingRegister(ratingSet);
        int expResult = 3;
        int result = instance.getRatingNum();
        assertEquals(result, expResult);
    }
    
}
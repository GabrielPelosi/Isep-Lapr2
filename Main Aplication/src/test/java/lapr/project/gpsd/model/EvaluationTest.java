/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

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
public class EvaluationTest {
    

    /**
     * Test of getMeanScore method, of class Evaluation.
     */
    @Test
    public void testGetMeanScore() {
        System.out.println("getMeanScore");
        Evaluation instance = new Evaluation (2,"Outstanding provider");
        double expResult = 2.0;
        double result = instance.getMeanScore();
        assertEquals(result, expResult, 0.0);
    }

    /**
     * Test of getClassification method, of class Evaluation.
     */
    @Test
    public void testGetClassification() {
        System.out.println("getClassification");
        Evaluation instance = new Evaluation (2,"Regular Provider");
        String expResult = "Regular Provider";
        String result = instance.getClassification();
        assertEquals(result, expResult);
    }

    /**
     * Test of setMeanScore method, of class Evaluation.
     */
    @Test
    public void testSetMeanScore() {
        System.out.println("setMeanScore");
        double newMeanScore = 5.0;
        Evaluation instance = new Evaluation (4,"Regular Provider");
        instance.setMeanScore(newMeanScore);
    }

    /**
     * Test of setClassification method, of class Evaluation.
     */
    @Test
    public void testSetClassification() {
        System.out.println("setClassification");
        String newEvaluation = "Outstanding Provider";
        Evaluation instance = new Evaluation (5,"Regular Provider");
        instance.setClassification(newEvaluation);
    }
    
}

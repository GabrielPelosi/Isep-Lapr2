/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.controller;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.Rating;
import lapr.project.gpsd.model.RatingRegister;
import lapr.project.gpsd.model.RegisterSP;
import lapr.project.gpsd.model.ServiceProvider;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author Francisco Ferreira
 */
public class EvaluateServiceProvidersControllerTest {
    
    public EvaluateServiceProvidersControllerTest() {
    }

   
   
//     @BeforeAll
//    public static void instanciateSP() throws UnsupportedEncodingException, FileNotFoundException {
//        
//        PostalAddress pa = new PostalAddress ("address", "postalCode", "locality");
//        
//        ServiceProvider sP1 = new ServiceProvider("numeroMecanografico", "nomeC", "nomeA", "email", pa);
//        ServiceProvider sP2 = new ServiceProvider("numeroMecanografico1", "nomeC1", "nomeA1", "email1", pa);
//        ServiceProvider sP3 = new ServiceProvider("numeroMecanografico2", "nomeC2", "nomeA2", "email2", pa);
//
//        RegisterSP servProviderRec = ApplicationGPSD.getInstance().getEmpresa().getServiceProvidersRegister();
//        
//        servProviderRec.registerServiceProvider(sP1);
//        servProviderRec.registerServiceProvider(sP2);
//        servProviderRec.registerServiceProvider(sP3);
//        RatingRegister rateList = new RatingRegister();
//        Rating r1 = new Rating(3);
//        Rating r2 = new Rating(5);
//        Rating r3 = new Rating(3);
//        Rating r4 = new Rating(2);
//        Rating r5 = new Rating(1);
//        rateList.addRating(r1);
//        rateList.addRating(r2);
//        rateList.addRating(r3);
//        rateList.addRating(r4);
//        rateList.addRating(r5);
//        RatingRegister rateList1 = new RatingRegister();
//        rateList1.addRating(r2);
//        rateList1.addRating(r2);
//        rateList1.addRating(r3);
//        rateList1.addRating(r3);
//        rateList1.addRating(r5);
//        RatingRegister rateList2 = new RatingRegister();
//        rateList2.addRating(r4);
//        rateList2.addRating(r4);
//        rateList2.addRating(r4);
//        rateList2.addRating(r4);
//        rateList2.addRating(r2);
//        sP1.setScoreList(rateList);
//        sP2.setScoreList(rateList1);
//        sP3.setScoreList(rateList2);
//    }
//    
//    /**
//     * Test of initiateEvaluation method, of class EvaluateServiceProvidersController.
//     */
//    @Test
//    public void testInitiateEvaluation() {
//        System.out.println("initiateEvaluation");
//        PostalAddress pa = new PostalAddress ("address", "postalCode", "locality");
//        EvaluateServiceProvidersController instance = new EvaluateServiceProvidersController();
//        RegisterSP servProviderRec = ApplicationGPSD.getInstance().getEmpresa().getServiceProvidersRegister();
//        List<ServiceProvider> lServiceProviders = servProviderRec.getServiceProvidersList();
//        List<ServiceProvider> expResult = servProviderRec.getServiceProvidersList();
//         ServiceProvider sP1 = new ServiceProvider("numeroMecanografico", "nomeC", "nomeA", "email", pa);
//        ServiceProvider sP2 = new ServiceProvider("numeroMecanografico1", "nomeC1", "nomeA1", "email1", pa);
//        ServiceProvider sP3 = new ServiceProvider("numeroMecanografico2", "nomeC2", "nomeA2", "email2", pa);
//        List<ServiceProvider> result = new ArrayList<>();
//        result.add(sP1);
//        result.add(sP2);
//        result.add(sP3);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of startStatisticsServiceProvider method, of class EvaluateServiceProvidersController.
     */
    @Test
    public void testStartStatisticsServiceProvider() {
        System.out.println("startStatisticsServiceProvider");
        String pMechNum = "numeroMecanografico1";
        //
        PostalAddress pa = new PostalAddress ("address", "postalCode", "locality");
        ServiceProvider sP = new ServiceProvider("numeroMecanografico12", "nomeC1", "nomeA1", "email1", pa);
        ServiceProvider sP1 = new ServiceProvider("numeroMecanografico", "nomeC", "nomeA", "email", pa);
        ServiceProvider sP2 = new ServiceProvider("numeroMecanografico1", "nomeC1", "nomeA1", "email1", pa);
        ServiceProvider sP3 = new ServiceProvider("numeroMecanografico2", "nomeC2", "nomeA2", "email2", pa);
        Set<ServiceProvider> spSet = new HashSet<>();
        spSet.add(sP1);
        spSet.add(sP2);
        spSet.add(sP3);
        RegisterSP rSP = new RegisterSP (spSet);
        //
        EvaluateServiceProvidersController instance = new EvaluateServiceProvidersController(rSP,sP);
        ServiceProvider expResult = sP2;
        ServiceProvider result = instance.startStatisticsServiceProvider(pMechNum);
        assertEquals(expResult, result);

    }

//    /**
//     * Test of getServiceProvidersScoreList method, of class EvaluateServiceProvidersController.
//     */
//    @Test
//    public void testGetServiceProvidersScoreList() {
//        System.out.println("getServiceProvidersScoreList");
//        EvaluateServiceProvidersController instance = new EvaluateServiceProvidersController();
//        RatingRegister expResult = null;
//        RatingRegister result = instance.getServiceProvidersScoreList();
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of getProviderMeanRate method, of class EvaluateServiceProvidersController.
//     */
//    @Test
//    public void testGetProviderMeanRate() {
//        System.out.println("getProviderMeanRate");
//        EvaluateServiceProvidersController instance = new EvaluateServiceProvidersController();
//        double expResult = 0.0;
//        double result = instance.getProviderMeanRate();
//        assertEquals(expResult, result, 0.0);
//
//    }
//
//    /**
//     * Test of getProviderStandardDeviation method, of class EvaluateServiceProvidersController.
//     */
//    @Test
//    public void testGetProviderStandardDeviation() {
//        System.out.println("getProviderStandardDeviation");
//        EvaluateServiceProvidersController instance = new EvaluateServiceProvidersController();
//        double expResult = 0.0;
//        double result = instance.getProviderStandardDeviation();
//        assertEquals(expResult, result, 0.0);
//
//    }
//
//    /**
//     * Test of getProviderEvaluation method, of class EvaluateServiceProvidersController.
//     */
//    @Test
//    public void testGetProviderEvaluation() {
//        System.out.println("getProviderEvaluation");
//        EvaluateServiceProvidersController instance = new EvaluateServiceProvidersController();
//        String expResult = "";
//        String result = instance.getProviderEvaluation();
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of getProviderAbsDeviation method, of class EvaluateServiceProvidersController.
//     */
//    @Test
//    public void testGetProviderAbsDeviation() {
//        System.out.println("getProviderAbsDeviation");
//        EvaluateServiceProvidersController instance = new EvaluateServiceProvidersController();
//        double expResult = 0.0;
//        double result = instance.getProviderAbsDeviation();
//        assertEquals(expResult, result, 0.0);
//
//    }
//
//    /**
//     * Test of displaysEvaluation method, of class EvaluateServiceProvidersController.
//     */
//    @Test
//    public void testDisplaysEvaluation() {
//        System.out.println("displaysEvaluation");
//        double totalServiceProvidersMeanScore = 0.0;
//        double providerMeanScore = 0.0;
//        double totalStandardDeviation = 0.0;
//        EvaluateServiceProvidersController instance = new EvaluateServiceProvidersController();
//        String expResult = "";
//        String result = instance.displaysEvaluation(totalServiceProvidersMeanScore, providerMeanScore, totalStandardDeviation);
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of setEvaluation method, of class EvaluateServiceProvidersController.
//     */
//    @Test
//    public void testSetEvaluation() {
//        System.out.println("setEvaluation");
//        String newServiceProviderEvaluation = "";
//        EvaluateServiceProvidersController instance = new EvaluateServiceProvidersController();
//        boolean expResult = false;
//        boolean result = instance.setEvaluation(newServiceProviderEvaluation);
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of evaluationRegister method, of class EvaluateServiceProvidersController.
//     */
//    @Test
//    public void testEvaluationRegister() {
//        System.out.println("evaluationRegister");
//        EvaluateServiceProvidersController instance = new EvaluateServiceProvidersController();
//        boolean expResult = false;
//        boolean result = instance.evaluationRegister();
//        assertEquals(expResult, result);
//
//    }
//    
}

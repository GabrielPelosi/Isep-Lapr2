/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Francisco Ferreira
 */
public class ServiceProviderTest {
    
    /**
     * Test of getBusyList method, of class ServiceProvider.
     */
//    @Test
//    public void testGetBusyList() {
//        System.out.println("getBusyList");
//        //
//        Category c = new Category ( "strId", "strDescription");
//        List<Category> listCategory = new ArrayList<>();
//        listCategory.add(c);
//        PostalCode p = new PostalCode ("123");
//        List<DailyAvailability> listDailyAvailability = new ArrayList<>();
//        Data dataI = new Data (2012, 12, 11);
//        DailyAvailability dA = new DailyAvailability (dataI, "horaI", "horaF");
//        listDailyAvailability.add (dA);
//        PostalAddress pA = new PostalAddress ("address", p, "locality");
//        //
//        List<DailyAvailability> availabilityList = new ArrayList<>();
//        Data date = new Data (2012, 12, 11);
//        DailyAvailability d1 = new DailyAvailability(date,"horaI","horaF");
//        availabilityList.add (d1);
//        ServiceProvider instance = new ServiceProvider (listCategory, listDailyAvailability, pA);
//        List expResult = availabilityList;
//        List result = instance.getBusyList();
//        assertEquals(result, expResult);
//    }

    /**
     * Test of changeInfo method, of class ServiceProvider.
     */
    @Test
    public void testChangeInfo() {
        System.out.println("changeInfo");
        String tNumber = "";
        String fName = "";
        String aName = "";
        String email = "";
        PostalCode pC = new PostalCode ("cod");
        PostalAddress pA = new PostalAddress ("aName", pC, "email");
        ServiceProvider instance = new ServiceProvider ("mechanographicalNum","cName","aName","email",pA);
        instance.changeInfo(tNumber, fName, aName, email);
    }

    /**
     * Test of addCategory method, of class ServiceProvider.
     */
    @Test
    public void testAddCategory() {
        Category c1 = new Category ("1234567","description1");
        Category c2 = new Category ("1232456","description2");
        Category c3 = new Category ("123456","description3");
        List<Category> listCategory = new ArrayList<>();
        listCategory.add(c1);
        listCategory.add(c2);
        listCategory.add(c3);
        System.out.println("addCategory");
        Category c4 = new Category ("12345678","description4");
        Category cat = c4;
        ServiceProvider instance = new ServiceProvider (listCategory,null,null,null);
        instance.addCategory(cat);
    }

    /**
     * Test of removeCat method, of class ServiceProvider.
     */
    @Test
    public void testRemoveCat() {
        Category c1 = new Category ("1234567","description1");
        Category c2 = new Category ("1232456","description2");
        Category c3 = new Category ("123456","description3");
        List<Category> listCategory = new ArrayList<>();
        listCategory.add(c1);
        listCategory.add(c2);
        listCategory.add(c3);
        System.out.println("removeCat");
        Category cat = c2;
        ServiceProvider instance = new ServiceProvider (listCategory,null,null,null);
        instance.removeCat(cat);
    }

    /**
     * Test of validadeCategory method, of class ServiceProvider.
     */
    @Test
    public void testValidadeCategory() {
//        System.out.println("validadeCategory");
//        Category cat = new Category ("1223456","description1");
//        ServiceProvider instance = new ServiceProvider();
//        instance.validadeCategory(cat);
    }

    /**
     * Test of addGeographicArea method, of class ServiceProvider.
     */
//    @Test
//    public void testAddGeographicArea() {
//        List<Area> areasList = new ArrayList<>();
//        PostalCode pC  = new PostalCode("cod");
//        PostalAddress pA = new PostalAddress("address",pC , "locality");
//        Area a1 = new Area ("name", pC, 2,12,"es");
//        System.out.println("addGeographicArea");
//        areasList.add(a1);
//        Area a2 = new Area ("name1",pC,2,12,"es1");
//        Area geo = a2;
//        ServiceProvider instance = new ServiceProvider("nMecanografico", "nomeC", "nomeA", "email", pA);
//        Category c = new Category ( "strId", "strDescription");
//        List<Category> listCategory = new ArrayList<>();
//        listCategory.add(c);
//        PostalCode p = new PostalCode ("123");
//        Area a = new Area ("nome",p, 20, 10, "es");
//        List<DailyAvailability> listDailyAvailability = new ArrayList<>();
//        Data dataI = new Data (2000, 3, 4);
//        DailyAvailability dA = new DailyAvailability (dataI, "horaI", "horaF");
//        listDailyAvailability.add (dA);
//        ServiceProvider instance1 = new ServiceProvider (listCategory, areasList, listDailyAvailability, pA);
//        instance1.addGeographicArea(geo);
//    }

    /**
     * Test of removeArea method, of class ServiceProvider.
     */
//    @Test
//    public void testRemoveArea() {
//        System.out.println("removeArea");
//        //
//        Category c = new Category ( "strId", "strDescription");
//        List<Category> listCategory = new ArrayList<>();
//        listCategory.add(c);
//        PostalCode p = new PostalCode ("123");
//        Area a1 = new Area ("nome", p, 1, 1);
//        List<DailyAvailability> listDailyAvailability = new ArrayList<>();
//        Data dataI = new Data (2000, 3, 4);
//        DailyAvailability dA = new DailyAvailability (dataI, "horaI", "horaF");
//        listDailyAvailability.add (dA);
//        PostalAddress pA = new PostalAddress ("address", p, "locality");
//        //
//        Area a2 = new Area ("name2",p,2,123);
//        Area area = a2;
//        List<Area> areasList = new ArrayList<>();
//        ServiceProvider instance = new ServiceProvider (null,areasList,null,null);
//        ServiceProvider instance1 = new ServiceProvider (listCategory, areasList, listDailyAvailability, pA);
//        instance.removeArea(area);
//    }

    /**
     * Test of validadeArea method, of class ServiceProvider.
     */
//    @Test
//    public void testValidadeArea() {
//        System.out.println("validadeArea");
//        PostalCode pC = new PostalCode ("cod");
//        Area geo = new Area ("name3",pC,2,1234,"es");
//        ServiceProvider instance = new ServiceProvider();
//        instance.validadeArea(geo);
//    }

    /**
     * Test of getListaDisponibilidades method, of class ServiceProvider.
     */
//    @Test
//    public void testGetListaDisponibilidades() {
//        System.out.println("getListaDisponibilidades");
//        List<DailyAvailability> listDailyAvailability = new ArrayList<>();
//        DailyAvailability d1 = new DailyAvailability(null,"horaI","horaF");
//        listDailyAvailability.add (d1);
//        ServiceProvider instance = new ServiceProvider (null,null,listDailyAvailability,null);
//        List expResult = listDailyAvailability;
//        List result = instance.getListaDisponibilidades();
//        assertEquals(result, expResult);
//    }


    /**
     * Test of addDisponibilidades method, of class ServiceProvider.
     */
    @Test
    public void testAddDisponibilidades() {
//        System.out.println("addDisponibilidades");
//        Data date = new Data (2019,12,19);
//        DailyAvailability disp = new DailyAvailability (date,"horaI","horaF");
//        List<DailyAvailability> listAvailabilitys = new ArrayList<>();
//        ServiceProvider instance = new ServiceProvider();
//        boolean expResult = true;
//        boolean result = instance.addDisponibilidades(disp);
//        assertEquals(result, expResult);
    }


    /**
     * Test of novaCategoria method, of class ServiceProvider.
     */
    @Test
    public void testNovaCategoria() {
        System.out.println("novaCategoria");
        String strCodigo = "code";
        String strDescricao = "description";
        Category expResult = new Category("code","description");
        Category result = ServiceProvider.novaCategoria(strCodigo, strDescricao);
        assertEquals(result, expResult);
    }

    /**
     * Test of hasEmail method, of class ServiceProvider.
     */
    @Test
    public void testHasEmail() {
        System.out.println("hasEmail");
        String strEmail = "completeName@gmail.com";
        ServiceProvider instance = new ServiceProvider("mechanographicalNumber","completeName", "aName","completeName@gmail.com",null);
        boolean expResult = true;
        boolean result = instance.hasEmail(strEmail);
        assertEquals(result, expResult);
    }

    /**
     * Test of addEvaluation method, of class ServiceProvider.
     */
    @Test
    public void testAddEvaluation_double_String() {
        System.out.println("addEvaluation");
        //
        Category c = new Category ( "strId", "strDescription");
        List<Category> listCategory = new ArrayList<>();
        listCategory.add(c);
        PostalCode p = new PostalCode ("123");
        List<DailyAvailability> listDailyAvailability = new ArrayList<>();
        Data dataI = new Data (2000, 3, 4);
        DailyAvailability dA = new DailyAvailability (dataI, "horaI", "horaF");
        listDailyAvailability.add (dA);
        PostalAddress pA = new PostalAddress ("address", p, "locality");
        //
        double providerMeanScore = 12.0;
        String serviceProviderEvaluation = "NormalProvider";
        ServiceProvider instance = new ServiceProvider (listCategory, listDailyAvailability, pA);
        Evaluation expResult = new Evaluation(12.0, "NormalProvider");
        Evaluation result = instance.addEvaluation(providerMeanScore, serviceProviderEvaluation);
        result.equals(expResult);
    }

  
    /**
     * Test of evaluationValidate method, of class ServiceProvider.
     */
    @Test
    public void testEvaluationValidate() {
        System.out.println("evaluationValidate");
        Evaluation evat = new Evaluation (12.0,"Normal Provider");
        ServiceProvider instance = new ServiceProvider();
        boolean expResult = true;
        boolean result = instance.evaluationValidate(evat);
        assertEquals(result, expResult);
    }

    /**
     * Test of validateEvaluation method, of class ServiceProvider.
     */
    @Test
    public void testValidateEvaluation() {
        System.out.println("validateEvaluation");
        Evaluation evat = new Evaluation(12.0, "Outstanding Provider");
        //
        Category c = new Category ( "strId", "strDescription");
        List<Category> listCategory = new ArrayList<>();
        listCategory.add(c);
        PostalCode p = new PostalCode ("123");
        List<DailyAvailability> listDailyAvailability = new ArrayList<>();
        Data dataI = new Data (2000, 3, 4);
        DailyAvailability dA = new DailyAvailability (dataI, "horaI", "horaF");
        listDailyAvailability.add (dA);
        PostalAddress pA = new PostalAddress ("address", p, "locality");
        //
        ServiceProvider instance = new ServiceProvider (listCategory, listDailyAvailability, pA);
        boolean expResult = true;
        boolean result = instance.validateEvaluation(evat);
        assertEquals(result, expResult);
    }

    /**
     * Test of addEvaluation method, of class ServiceProvider.
     */
    @Test
    public void testAddEvaluation_Evaluation() {
        System.out.println("addEvaluation");
         //
        Category c = new Category ( "strId", "strDescription");
        List<Category> listCategory = new ArrayList<>();
        listCategory.add(c);
        PostalCode p = new PostalCode ("123");
        List<DailyAvailability> listDailyAvailability = new ArrayList<>();
        Data dataI = new Data (2000, 3, 4);
        DailyAvailability dA = new DailyAvailability (dataI, "horaI", "horaF");
        listDailyAvailability.add (dA);
        PostalAddress pA = new PostalAddress ("address", p, "locality");
        //
        Evaluation evat = new Evaluation (12.0, "Normal Provider");
        ServiceProvider instance = new ServiceProvider ();
        boolean expResult = true;
        boolean result = instance.addEvaluation(evat);
        assertEquals(result, expResult);
    }
    
}
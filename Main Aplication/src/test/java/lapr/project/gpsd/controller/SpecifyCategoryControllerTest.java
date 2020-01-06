/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.controller;

import java.util.HashSet;
import java.util.Set;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.RegisterCategory;
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
public class SpecifyCategoryControllerTest {
  
    /**
     * Test of newCategory method, of class SpecifyCategoryController.
     */
    @Test
    public void testNewCategory() {
        System.out.println("newCategory");
        Category c1 = new Category("strId", "strDescription");
        Category c2 = new Category ("strId2", "strDescription1");
        Set<Category> categorySet = new HashSet<>();
        categorySet.add(c1);
        categorySet.add(c2);
        RegisterCategory rc = new RegisterCategory(categorySet);
        SpecifyCategoryController instance = new SpecifyCategoryController(c1,rc);
        boolean expResult = true;
        boolean result = instance.newCategory();
        assertEquals(expResult, result);
    }

    /**
     * Test of setData method, of class SpecifyCategoryController.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        String cod = "123";
        String desc = "description1";
        Category c1 = new Category("strId", "strDescription");
        Category c2 = new Category ("strId2", "strDescription1");
        Set<Category> categorySet = new HashSet<>();
        categorySet.add(c1);
        categorySet.add(c2);
        RegisterCategory rc = new RegisterCategory(categorySet);
        SpecifyCategoryController instance = new SpecifyCategoryController(c1,rc);
        instance.setData(cod, desc);
    }

    /**
     * Test of registerCategory method, of class SpecifyCategoryController.
     */
    @Test
    public void testRegisterCategory() {
        System.out.println("registerCategory");
        Category c1 = new Category("strId", "strDescription");
        Category c2 = new Category ("strId2", "strDescription1");
        Set<Category> categorySet = new HashSet<>();
        categorySet.add(c1);
        categorySet.add(c2);
        RegisterCategory rc = new RegisterCategory(categorySet);
        SpecifyCategoryController instance = new SpecifyCategoryController(c1,rc);
        boolean expResult = false;
        boolean result = instance.registerCategory();
        assertEquals(expResult, result);

    }

    /**
     * Test of getCategoriaString method, of class SpecifyCategoryController.
     */
    @Test
    public void testGetCategoriaString() {
        System.out.println("getCategoriaString");
        Category c1 = new Category("strId", "strDescription");
        Category c2 = new Category ("strId2", "strDescription1");
        Set<Category> categorySet = new HashSet<>();
        categorySet.add(c1);
        categorySet.add(c2);
        RegisterCategory rc = new RegisterCategory(categorySet);
        SpecifyCategoryController instance = new SpecifyCategoryController(c1,rc);
        String expResult = "strId - strDescription ";
        String result = instance.getCategoriaString();
        assertEquals(expResult, result);
    }
    
}

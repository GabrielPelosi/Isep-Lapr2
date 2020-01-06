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
public class RegisterCategoryTest {
    
    /**
     * Test of getCategoriaById method, of class RegisterCategory.
     */
    @Test
    public void testGetCategoriaById() {
        System.out.println("getCategoriaById");
        String strId = "1234";
        Category c1 = new Category ("12334","description1");
        Category c2 = new Category ("12345","description2");
        Category c3 = new Category ("1234", "description3");
        Set<Category> categorySet = new HashSet<>();
        categorySet.add(c1);
        categorySet.add(c2);
        categorySet.add(c3);
        RegisterCategory instance = new RegisterCategory(categorySet);
        Category expResult = c3;
        Category result = instance.getCategoriaById(strId);
        assertEquals(result, expResult);
    }

    /**
     * Test of newCategory method, of class RegisterCategory.
     */
    @Test
    public void testNewCategory_String_String() {
        System.out.println("newCategory");
        String strCodigo = "1234";
        String strDescricao = "description1";
        RegisterCategory instance = new RegisterCategory();
        Category c1 = new Category ("1234","description1");
        Category expResult = c1;
        Category result = instance.newCategory(strCodigo, strDescricao);
        assertEquals(result, expResult);
    }

    /**
     * Test of newCategory method, of class RegisterCategory.
     */
    @Test
    public void testNewCategory_0args() {
        System.out.println("newCategory");
        RegisterCategory instance = new RegisterCategory();
        Category expResult = new Category();
        Category result = instance.newCategory();
        assertEquals(result, expResult);
    }

    /**
     * Test of registerCategory method, of class RegisterCategory.
     */
    @Test
    public void testRegisterCategory() {
        System.out.println("registerCategory");
        Category cat = new Category ("id1","description1");
        RegisterCategory instance = new RegisterCategory();
        boolean expResult = true;
        boolean result = instance.registerCategory(cat);
        assertEquals(result, expResult);
    }

    /**
     * Test of validateCategory method, of class RegisterCategory.
     */
    @Test
    public void testValidateCategory() {
        System.out.println("validateCategory");
        Category cat = new Category("123456","description1232");
        RegisterCategory instance = new RegisterCategory();
        boolean expResult = true;
        boolean result = instance.validateCategory(cat);
        assertEquals(result, expResult);
    }

    /**
     * Test of getCategorias method, of class RegisterCategory.
     */
    @Test
    public void testGetCategorias() {
        System.out.println("getCategorias");
        Category c1 = new Category ("12334","description1");
        Category c2 = new Category ("12345","description2");
        Category c3 = new Category ("1234", "description3");
        Set<Category> categorySet = new HashSet<>();
        categorySet.add(c1);
        categorySet.add(c2);
        categorySet.add(c3);
        RegisterCategory instance = new RegisterCategory(categorySet);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(c3);
        categoryList.add(c1);
        categoryList.add(c2);
        List expResult = categoryList;
        List result = instance.getCategorias();
        assertEquals(result, expResult);
    }
    
}

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
import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

/**
 *
 * @author Pelosi
 */
public class RegisterApplicationTest {
	
	public RegisterApplicationTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of newApplication method, of class RegisterApplication.
	 */
	@Test
	public void testNewApplication() {
		System.out.println("newApplication");
		RegisterApplication instance = new RegisterApplication();
		String name = "gabriel";
		String nif = "123456789";
		String pn = "321654987";
		String email = "gabriel@gmail.com";
		String ah = "aaaaa";
		String ph = "bbbbb";
		PostalAddress add = new PostalAddress("rua abc", "4000-009", "porto");
		List<Category> list = new ArrayList<>();
		list.add(new Category("123", "12345"));
		
		//Application expResult = new Application(name, nif, pn, email, ah, ph, add, list);
		
		Application result = instance.newApplication(name, nif, pn, email, ah, ph, add, list);
		instance.registerApplication(result);
		Application expResult = instance.getApplicationByNIF(nif);
		assertEquals(expResult, result);
	}

	/**
	 * Test of registerApplication method, of class RegisterApplication.
	 */
	@Test
	public void testRegisterApplication() {
		System.out.println("registerApplication");
		String name = "gabriel";
		String nif = "123456789";
		String pn = "321654987";
		String email = "gabriel@gmail.com";
		String ah = "aaaaa";
		String ph = "bbbbb";
		PostalAddress add = new PostalAddress("rua abc", "4000-009", "porto");
		List<Category> list = new ArrayList<>();
		list.add(new Category("123", "12345"));
		Application a = new Application(name, nif, pn, email, ah, ph, add, list);
		RegisterApplication instance = new RegisterApplication();
		instance.registerApplication(a);
	}

	/**
	 * Test of getApplicationByNIF method, of class RegisterApplication.
	 */
	@Test
	public void testGetApplicationByNIF() {
		
		System.out.println("getApplicationByNIF");
		
		RegisterApplication instance = new RegisterApplication();
		
		String name = "gabriel";
		String nif = "123456789";
		String pn = "321654987";
		String email = "gabriel@gmail.com";
		String ah = "aaaaa";
		String ph = "bbbbb";
		PostalAddress add = new PostalAddress("rua abc", "4000-009", "porto");
		List<Category> list = new ArrayList<>();
		list.add(new Category("123", "12345"));
		

		Application result = instance.newApplication(name, nif, pn, email, ah, ph, add, list);
		instance.registerApplication(result);
		Application expResult = instance.getApplicationByNIF(nif);
		assertEquals(expResult, result);
		
	}

	/**
	 * Test of getListApplication method, of class RegisterApplication.
	 */
	@Test
	public void testGetListApplication() {
		System.out.println("getListApplication");
		RegisterApplication instance = new RegisterApplication();
		Set<Application> expResult = new HashSet<>();
		
		String name = "gabriel";
		String nif = "123456789";
		String pn = "321654987";
		String email = "gabriel@gmail.com";
		String ah = "aaaaa";
		String ph = "bbbbb";
		
		PostalAddress add = new PostalAddress("rua abc", "4000-009", "porto");
		List<Category> list = new ArrayList<>();
		list.add(new Category("123", "12345"));
		
		Application a = instance.newApplication(name, nif, pn, email, ah, ph, add, list);
		instance.registerApplication(a);
		expResult.add(a);
		expResult.add(a);
		Set<Application> result = instance.getListApplication();
		assertEquals(expResult, result);
		
		
	}

	/**
	 * Test of getListNifApplications method, of class RegisterApplication.
	 */
	@Test
	public void testGetListNifApplications() {
		System.out.println("getListNifApplications");
		RegisterApplication instance = new RegisterApplication();
		List<String> expResult = new ArrayList<>();
		
				
		String name = "gabriel";
		String nif = "123456789";
		String pn = "321654987";
		String email = "gabriel@gmail.com";
		String ah = "aaaaa";
		String ph = "bbbbb";
		
		PostalAddress add = new PostalAddress("rua abc", "4000-009", "porto");
		List<Category> list = new ArrayList<>();
		list.add(new Category("123", "12345"));
		
		Application a = instance.newApplication(name, nif, pn, email, ah, ph, add, list);
		instance.registerApplication(a);
		
		expResult.add(a.getNif());
		List<String> result = instance.getListNifApplications();
		
		assertEquals(expResult, result);
		
		
	}
	
}

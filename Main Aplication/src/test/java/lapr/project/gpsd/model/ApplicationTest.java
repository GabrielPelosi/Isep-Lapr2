/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pelosi
 */
public class ApplicationTest {
	
	public ApplicationTest() {
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
	 * Test of getNif method, of class Application.
	 */
	@Test
	public void testGetNif() {
		List<Category> list = new ArrayList<>();
		list.add(new Category());
		Application app = new Application("gabriel", "123456789", "3216549897", "gabriel@gmail.com", "123", "123123", new PostalAddress("fsdfsdf", "4000-009", "porto"), list);
		System.out.println("getNif");
		String expResult = "123456789";
		String result = app.getNif();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getName method, of class Application.
	 */
	@Test
	public void testGetName() {
		List<Category> list = new ArrayList<>();
		list.add(new Category());
		Application app = new Application("gabriel", "123456789", "3216549897", "gabriel@gmail.com", "123", "123123", new PostalAddress("fsdfsdf", "4000-009", "porto"), list);
		System.out.println("getName");
		String expResult = "gabriel";
		String result = app.getName();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getEmail method, of class Application.
	 */
	@Test
	public void testGetEmail() {
		List<Category> list = new ArrayList<>();
		list.add(new Category());
		Application app = new Application("gabriel", "123456789", "3216549897", "gabriel@gmail.com", "123", "123123", new PostalAddress("fsdfsdf", "4000-009", "porto"), list);
		System.out.println("getEmail");
		String expResult = "gabriel@gmail.com";
		String result = app.getEmail();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getAh method, of class Application.
	 */
	@Test
	public void testGetAh() {
		List<Category> list = new ArrayList<>();
		list.add(new Category());
		Application app = new Application("gabriel", "123456789", "3216549897", "gabriel@gmail.com", "123", "123123", new PostalAddress("fsdfsdf", "4000-009", "porto"), list);
		System.out.println("getAh");
		String expResult = "123";
		String result = app.getAh();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getPh method, of class Application.
	 */
	@Test
	public void testGetPh() {
		List<Category> list = new ArrayList<>();
		list.add(new Category());
		Application app = new Application("gabriel", "123456789", "3216549897", "gabriel@gmail.com", "123", "123123", new PostalAddress("fsdfsdf", "4000-009", "porto"), list);
		System.out.println("getPh");
		String expResult = "123123";
		String result = app.getPh();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getAdd method, of class Application.
	 */
	@Test
	public void testGetAdd() {
		List<Category> list = new ArrayList<>();
		list.add(new Category());
		
		System.out.println("getAdd");
		
		PostalAddress expResult = new PostalAddress("fsdfsdf", "4000-009", "porto");
		Application app = new Application("gabriel", "123456789", "3216549897", "gabriel@gmail.com", "123", "123123", expResult, list);
		PostalAddress result = app.getAdd();
		
		assertEquals(expResult, result);
	}

	/**
	 * Test of getCat method, of class Application.
	 */
	@Test
	public void testGetCat() {
		List<Category> list = new ArrayList<>();
		list.add(new Category());
		Application app = new Application("gabriel", "123456789", "3216549897", "gabriel@gmail.com", "123", "123123", new PostalAddress("fsdfsdf", "4000-009", "porto"), list);
		System.out.println("getCat");
		List<Category> expResult = list;
		List<Category> result = app.getCat();
		assertEquals(expResult, result);
	}

	/**
	 * Test of toString method, of class Application.
	 */
	@Test
	public void testToString() {
		List<Category> list = new ArrayList<>();
		list.add(new Category());
		Application app = new Application("gabriel", "123456789", "3216549897", "gabriel@gmail.com", "123", "123123", new PostalAddress("fsdfsdf", "4000-009", "porto"), list);
		System.out.println("toString");
		System.out.println(app.toString());
		String expResult = "Application{name=gabriel, nif=123456789, pn=3216549897, email=gabriel@gmail.com, ah=123, ph=123123, add=fsdfsdf - 4000-009 - porto, cat=[null - null ]}";
		String result = app.toString();
		assertEquals(expResult, result);
	}
	
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pelosi
 */
public class PostalAddressTest {
	
	public PostalAddressTest() {
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
	 * Test of toStringValidate method, of class PostalAddress.
	 */
	@Test
	public void testToStringValidate() {
		PostalAddress pa = new PostalAddress("abc def", "porto", "4000-009");
		System.out.println("toStringValidate");
		String expResult = "abc defporto4000-009";
		System.out.println(pa.toStringValidate());
		String result = pa.toStringValidate();
		assertEquals(expResult, result);
	}

	/**
	 * Test of toString method, of class PostalAddress.
	 */
	@Test
	public void testToString() {
		PostalAddress pa = new PostalAddress("abc def", "porto", "4000-009");
		System.out.println("toString");
		String expResult = "Address: abc def, Locality: porto, Postal Code: 4000-009";
		System.out.println(pa.toString());
		String result = pa.toString();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getAddress method, of class PostalAddress.
	 */
	@Test
	public void testGetAddress() {
		PostalAddress pa = new PostalAddress("abc def", "porto", "4000-009");
		System.out.println("getAddress");
		String expResult = "abc def";
		String result = pa.getAddress();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getLocality method, of class PostalAddress.
	 */
	@Test
	public void testGetLocality() {
		System.out.println("getLocality");
		PostalAddress pa = new PostalAddress("abc def", "porto", "4000-009");
		String expResult = "porto";
		String result = pa.getLocality();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getPostalCode method, of class PostalAddress.
	 */
	@Test
	public void testGetPostalCode() {
		PostalAddress pa = new PostalAddress("abc def", "porto", "4000-009");
		System.out.println("getPostalCode");
		String expResult = "4000-009";
		String result = pa.getPostalCode();
		assertEquals(expResult, result);
	}
	
}

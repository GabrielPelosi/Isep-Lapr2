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
public class ExecutionOrderTest {
	
	public ExecutionOrderTest() {
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
	 * Test of toStringOrdenade method, of class ExecutionOrder.
	 */
	@Test
	public void testToStringOrdenade() {
		ExecutionOrder exec = new ExecutionOrder("1", "gabriel", "123", "CAT1", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		System.out.println("toStringOrdenade");
		String expResult = "gabriel123CAT119/07/0622:00:00EXPENSIVEabc defporto4000-009";
		String result = exec.toStringOrdenade();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getNumber method, of class ExecutionOrder.
	 */
	@Test
	public void testGetNumber() {
		ExecutionOrder exec = new ExecutionOrder("1", "gabriel", "123", "CAT1", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		System.out.println("getNumber");
		String expResult = "1";
		String result = exec.getNumber();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getName method, of class ExecutionOrder.
	 */
	@Test
	public void testGetName() {
		ExecutionOrder exec = new ExecutionOrder("1", "gabriel", "123", "CAT1", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		System.out.println("getName");
		String expResult = "gabriel";
		String result = exec.getName();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getDistance method, of class ExecutionOrder.
	 */
	@Test
	public void testGetDistance() {
		ExecutionOrder exec = new ExecutionOrder("1", "gabriel", "123", "CAT1", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		System.out.println("getDistance");
		String expResult = "123";
		String result = exec.getDistance();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getCategory method, of class ExecutionOrder.
	 */
	@Test
	public void testGetCategory() {
		ExecutionOrder exec = new ExecutionOrder("1", "gabriel", "123", "CAT1", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		System.out.println("getCategory");
		String expResult = "CAT1";
		String result = exec.getCategory();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getServiceType method, of class ExecutionOrder.
	 */
	@Test
	public void testGetServiceType() {
		ExecutionOrder exec = new ExecutionOrder("1", "gabriel", "123", "CAT1", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		System.out.println("getServiceType");
		String expResult = "EXPENSIVE";
		String result = exec.getServiceType();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getDate method, of class ExecutionOrder.
	 */
	@Test
	public void testGetDate() {
		ExecutionOrder exec = new ExecutionOrder("1", "gabriel", "123", "CAT1", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		System.out.println("getDate");
		Data expResult = new Data("07/06/19");
		Data result = exec.getDate();
		boolean a = equalsDate(result, expResult);
		if (!a) {
			fail("The data objects are diferents!!!");
		}
	}

	/**
	 * Test of getStartTime method, of class ExecutionOrder.
	 */
	@Test
	public void testGetStartTime() {
		ExecutionOrder exec = new ExecutionOrder("1", "gabriel", "123", "CAT1", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		System.out.println("getStartTime");
		String expResult = "22:00:00";
		String result = exec.getStartTime();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getpAddress method, of class ExecutionOrder.
	 */
	@Test
	public void testGetpAddress() {
		PostalAddress expResult = new PostalAddress("abc def", "porto", "4000-009");
		ExecutionOrder exec = new ExecutionOrder("1", "gabriel", "123", "CAT1", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		System.out.println("getpAddress");
		
		PostalAddress result = exec.getpAddress();
		
		boolean res = equalsAddress(result, expResult);
		
		if (!res) {
			fail("Objects PostalAddres not equals!!!");
		}
		
	}

	/**
	 * Test of toString method, of class ExecutionOrder.
	 */
	@Test
	public void testToString() {
		ExecutionOrder exec = new ExecutionOrder("1", "gabriel", "123", "CAT1", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		System.out.println("toString");
		String expResult = "ExecutionOrder: Number: 1, Name: gabriel, Distance: 123, Category: CAT1, Service Type: EXPENSIVE, Date: 19/07/06, Start Time: 22:00:00, Postal Address: Address: abc def, Locality: porto, Postal Code: 4000-009";
		System.out.println(exec.toString());
		String result = exec.toString();
		assertEquals(expResult, result);
	}
	
	public boolean equalsAddress(PostalAddress a , PostalAddress b){
		if (a.getAddress().equals(b.getAddress()) && (a.getLocality().equals(b.getLocality())) && (a.getPostalCode().equals(b.getPostalCode()))) {
			return true;
		}
		return false;
	}
	
	public boolean equalsDate(Data a , Data b){
		if (a.getDia()  == b.getDia() && a.getMes() == b.getMes() && a.getAno() == b.getAno()) {
			return true;
		}
		return false;
	}
}

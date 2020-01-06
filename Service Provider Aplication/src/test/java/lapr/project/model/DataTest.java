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
public class DataTest {
	
	public DataTest() {
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
	 * Test of getAno method, of class Data.
	 */
	@Test
	public void testGetAno() {
		System.out.println("getAno");
		Data instance = new Data("06/06/2019");
		int expResult = 19;
		int result = instance.getAno();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getMes method, of class Data.
	 */
	@Test
	public void testGetMes() {
		System.out.println("getMes");
		Data instance = new Data("06/06/2019");
		int expResult = 06;
		int result = instance.getMes();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getDia method, of class Data.
	 */
	@Test
	public void testGetDia() {
		System.out.println("getDia");
		Data instance = new Data("06/06/2019");
		int expResult = 06;
		int result = instance.getDia();
		assertEquals(expResult, result);
	}


	/**
	 * Test of toAnoMesDiaString method, of class Data.
	 */
	@Test
	public void testToAnoMesDiaString() {
		System.out.println("toAnoMesDiaString");
		Data instance = new Data("06/06/2019");
		String expResult = "19/06/06";
		String result = instance.toAnoMesDiaString();
		assertEquals(expResult, result);
	}
	
}

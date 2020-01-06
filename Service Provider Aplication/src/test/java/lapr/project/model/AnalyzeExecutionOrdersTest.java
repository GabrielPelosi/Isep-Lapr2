/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pelosi
 */
public class AnalyzeExecutionOrdersTest {
	
	
	/**
	 * Test of ordenateAll method, of class AnalyzeExecutionOrders.
	 */
	@Test
	public void testOrdenateAll() {
		System.out.println("ordenateAll");
		ExecutionOrder exec1 = new ExecutionOrder("1", "aabriel", "12", "CAT1", "EXPENSIVE", "2019/06/07", "22:00:00", "abc def", "porto", "4000-009");
		ExecutionOrder exec2 = new ExecutionOrder("1", "babriel", "16", "CAT1", "EXPENSIVE", "2019/06/08", "22:00:00", "abc def", "porto", "4000-009");
		ExecutionOrder exec3 = new ExecutionOrder("1", "cabriel", "19", "CAT1", "EXPENSIVE", "2019/06/09", "22:00:00", "abc def", "porto", "4000-009");
		List<ExecutionOrder> expResult = new ArrayList<>();
		expResult.add(exec1);
		expResult.add(exec2);
		expResult.add(exec3);
		AnalyzeExecutionOrders instance = new AnalyzeExecutionOrders();
		List<ExecutionOrder> result = instance.ordenateName(expResult);
		assertEquals(expResult, result);
		
	}

	/**
	 * Test of ordenateName method, of class AnalyzeExecutionOrders.
	 */
	@Test
	public void testOrdenateName() {
		System.out.println("ordenateName");
		ExecutionOrder exec1 = new ExecutionOrder("1", "Gabriel", "123", "CAT1", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		ExecutionOrder exec2 = new ExecutionOrder("1", "Babriel", "123", "CAT1", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		ExecutionOrder exec3 = new ExecutionOrder("1", "Dabriel", "123", "CAT1", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		List<ExecutionOrder> expResult = new ArrayList<>();
		expResult.add(exec3);
		expResult.add(exec1);
		expResult.add(exec1);
		AnalyzeExecutionOrders instance = new AnalyzeExecutionOrders();
		List<ExecutionOrder> result = instance.ordenateName(expResult);
		assertEquals(expResult, result);
	}

	/**
	 * Test of ordenateDistance method, of class AnalyzeExecutionOrders.
	 */
	@Test
	public void testOrdenateDistance() {
		System.out.println("ordenateDistance");
		ExecutionOrder exec1 = new ExecutionOrder("1", "Gabriel", "12", "CAT1", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		ExecutionOrder exec2 = new ExecutionOrder("1", "Babriel", "16", "CAT1", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		ExecutionOrder exec3 = new ExecutionOrder("1", "Dabriel", "19", "CAT1", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		List<ExecutionOrder> expResult = new ArrayList<>();
		expResult.add(exec1);
		expResult.add(exec2);
		expResult.add(exec3);
		AnalyzeExecutionOrders instance = new AnalyzeExecutionOrders();
		List<ExecutionOrder> result = instance.ordenateName(expResult);
		assertEquals(expResult, result);
	}

	/**
	 * Test of ordenateCategory method, of class AnalyzeExecutionOrders.
	 */
	@Test
	public void testOrdenateCategory() {
		System.out.println("ordenateCategory");
		ExecutionOrder exec1 = new ExecutionOrder("1", "Gabriel", "12", "CAT1", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		ExecutionOrder exec2 = new ExecutionOrder("1", "Babriel", "16", "CAT2", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		ExecutionOrder exec3 = new ExecutionOrder("1", "Dabriel", "19", "CAT3", "EXPENSIVE", "07/06/19", "22:00:00", "abc def", "porto", "4000-009");
		List<ExecutionOrder> expResult = new ArrayList<>();
		expResult.add(exec1);
		expResult.add(exec2);
		expResult.add(exec3);
		AnalyzeExecutionOrders instance = new AnalyzeExecutionOrders();
		List<ExecutionOrder> result = instance.ordenateName(expResult);
		assertEquals(expResult, result);
	}

	/**
	 * Test of ordenateDate method, of class AnalyzeExecutionOrders.
	 */
	@Test
	public void testOrdenateDate() {
		System.out.println("ordenateDate");
		ExecutionOrder exec1 = new ExecutionOrder("1", "Gabriel", "12", "CAT1", "EXPENSIVE", "2019/06/07", "22:00:00", "abc def", "porto", "4000-009");
		ExecutionOrder exec2 = new ExecutionOrder("1", "Babriel", "16", "CAT1", "EXPENSIVE", "2019/06/08", "22:00:00", "abc def", "porto", "4000-009");
		ExecutionOrder exec3 = new ExecutionOrder("1", "Dabriel", "19", "CAT1", "EXPENSIVE", "2019/06/09", "22:00:00", "abc def", "porto", "4000-009");
		List<ExecutionOrder> expResult = new ArrayList<>();
		expResult.add(exec1);
		expResult.add(exec2);
		expResult.add(exec3);
		AnalyzeExecutionOrders instance = new AnalyzeExecutionOrders();
		List<ExecutionOrder> result = instance.ordenateName(expResult);
		assertEquals(expResult, result);
	}

	/**
	 * Test of ordenateService method, of class AnalyzeExecutionOrders.
	 */
	@Test
	public void testOrdenateService() {
		System.out.println("ordenateService");
		ExecutionOrder exec1 = new ExecutionOrder("1", "Gabriel", "12", "CAT1", "aXPENSIVE", "2019/06/07", "22:00:00", "abc def", "porto", "4000-009");
		ExecutionOrder exec2 = new ExecutionOrder("1", "Babriel", "16", "CAT1", "bXPENSIVE", "2019/06/08", "22:00:00", "abc def", "porto", "4000-009");
		ExecutionOrder exec3 = new ExecutionOrder("1", "Dabriel", "19", "CAT1", "dXPENSIVE", "2019/06/09", "22:00:00", "abc def", "porto", "4000-009");
		List<ExecutionOrder> expResult = new ArrayList<>();
		expResult.add(exec1);
		expResult.add(exec2);
		expResult.add(exec3);
		AnalyzeExecutionOrders instance = new AnalyzeExecutionOrders();
		List<ExecutionOrder> result = instance.ordenateName(expResult);
		assertEquals(expResult, result);
	}

	/**
	 * Test of ordenateAddress method, of class AnalyzeExecutionOrders.
	 */
	@Test
	public void testOrdenateAddress() {
		System.out.println("ordenateAddress");
		ExecutionOrder exec1 = new ExecutionOrder("1", "Gabriel", "12", "CAT1", "EXPENSIVE", "2019/06/07", "22:00:00", "a", "porto", "4000-009");
		ExecutionOrder exec2 = new ExecutionOrder("1", "Babriel", "16", "CAT1", "EXPENSIVE", "2019/06/08", "22:00:00", "b", "porto", "4000-009");
		ExecutionOrder exec3 = new ExecutionOrder("1", "Dabriel", "19", "CAT1", "EXPENSIVE", "2019/06/09", "22:00:00", "c", "porto", "4000-009");
		List<ExecutionOrder> expResult = new ArrayList<>();
		expResult.add(exec1);
		expResult.add(exec2);
		expResult.add(exec3);
		AnalyzeExecutionOrders instance = new AnalyzeExecutionOrders();
		List<ExecutionOrder> result = instance.ordenateName(expResult);
		assertEquals(expResult, result);
	}
	
}

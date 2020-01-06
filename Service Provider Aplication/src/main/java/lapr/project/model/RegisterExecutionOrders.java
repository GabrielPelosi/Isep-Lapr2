/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pelosi
 */
public class RegisterExecutionOrders {

	private final Set<ExecutionOrder> listExecutionOrders;

	/**
	 * Constructor method of the RegisterExecutionOrder class
	 */
	public RegisterExecutionOrders() {
		this.listExecutionOrders = new HashSet<>();
	}

	/**
	 * Method get to the list of Execution Orders
	 *
	 * @return The list of Execution Orders
	 */
	public Set<ExecutionOrder> getListExecutionOrders() {
		return listExecutionOrders;
	}

	/**
	 * Method that create one Execution Order
	 *
	 * @param number String number of the Execution Order
	 * @param name String name of the Execution Order
	 * @param distance String distance of the Execution Order
	 * @param category String category of the Execution Order
	 * @param serviceType String Service Type of the Execution Order
	 * @param date String date that will be turned into a Data object of the Execution Order
	 * @param startTime String start Time of the Execution Order
	 * @param address String address of the Execution Order
	 * @param locality String locality of the Execution Order
	 * @param postalCode String postalCode of the Execution Order
	 * @return the Execution Order object
	 */
	public ExecutionOrder newExecutionOrder(String number, String name, String distance, String category, String serviceType, String date, String startTime, String address, String locality, String postalCode) {
		return new ExecutionOrder(number, name, distance, category, serviceType, date, startTime, address, locality, postalCode);
	}

	/**
	 * Method that save the execution order in the Set of this class
	 *
	 * @param exec the execution order that will be saved
	 */
	public void registerExecutionOrder(ExecutionOrder exec) {
//		System.out.println(exec.toString());
		listExecutionOrders.add(exec);
	}

	/**
	 * Method that save the execution orders list in a binary file
	 *
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void saveOrdersToBinaryFile() throws IOException, FileNotFoundException {
		for (ExecutionOrder a : listExecutionOrders) {
			saveOrderToBinary(a);
		}
	}

	/**
	 * Method that save one Execution order in a binary file
	 *
	 * @param a
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void saveOrderToBinary(ExecutionOrder a) throws IOException, FileNotFoundException {
		String fileName = "orders.bin";
		FileOutputStream fo = new FileOutputStream(fileName);
		ObjectOutputStream ob = new ObjectOutputStream(fo);
		ob.writeUnshared(a);
		ob.close();

	}

//	/**
//	 *
//         * @return 
//	 * @throws FileNotFoundException
//	 * @throws IOException
//	 */
//	public List<ExecutionOrder> loadBinaryFile() {
//                List<ExecutionOrder> lp=new ArrayList<>();
//		File fich = new File("orders.bin");
//	
//		try {
//            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fich));
//            lp = (List<ExecutionOrder>) in.readObject();
//            in.close();
//			
//		} catch (IOException ex) {
//			Logger.getLogger(RegisterExecutionOrders.class.getName()).log(Level.SEVERE, null, ex);
//		} catch (ClassNotFoundException ex) {
//			Logger.getLogger(RegisterExecutionOrders.class.getName()).log(Level.SEVERE, null, ex);
//		}
//	return lp;}
}

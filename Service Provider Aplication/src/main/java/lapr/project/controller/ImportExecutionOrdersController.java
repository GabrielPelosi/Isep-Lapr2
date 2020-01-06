/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lapr.project.model.ApplicationServiceProvider;
import lapr.project.model.Company;
import lapr.project.model.ExecutionOrder;
import org.apache.poi.ss.usermodel.Cell;
import lapr.project.model.RegisterExecutionOrders;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Pelosi
 */
public class ImportExecutionOrdersController {

	private static final String DATA_SEPARATOR = ";";

	private RegisterExecutionOrders rEO;
	private Company oCompany;

	/**
	 * The constructor class of the ImportExecutionOrderController
	 */
	public ImportExecutionOrdersController() {
		ApplicationServiceProvider app = ApplicationServiceProvider.getInstance();
		oCompany = app.getEmpresa();
		rEO = oCompany.getRegisterExecutionOrders();
	}

	/**
	 * Method that return the extension of the file inputed
	 *
	 * @param fullName String the full name of the file
	 * @return the String with the file extension
	 */
	public String getExtensionFile(String fullName) {
		String fileName = new File(fullName).getName();
		int dotIndex = fileName.lastIndexOf('.');
		return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
	}

	/**
	 * Method that validate the file extension as our instructions, if the extension does not follow the instructions, an exception will be threw
	 *
	 * @param extensionFile the file extension String
	 */
	public void validateFile(String extensionFile) {
		if (extensionFile.trim().isEmpty()) {
			throw new IllegalArgumentException("You must enter the file format as well! eg: ExecutionORders.xlsx, ExecutionORders.xml, ExecutionOrders.csv");
		}
		if (!(extensionFile.equalsIgnoreCase("csv") || extensionFile.equalsIgnoreCase("xml") || extensionFile.equalsIgnoreCase("xlsx"))) {
			throw new IllegalArgumentException("You must select a compatible file format!");
		}
	}

	/**
	 * Method that read the file as its extension
	 *
	 * @param fileName String with the file name
	 * @param extensionFile String with the file extension
	 * @throws Exception
	 */
	public void readFile(String fileName, String extensionFile) throws Exception {
		if (extensionFile.equalsIgnoreCase("csv")) {
			readInformationFileCSV(fileName);
		} else if (extensionFile.equalsIgnoreCase("xlsx")) {
			readInformationFileXLSX(fileName);
		} else {
			readInformationFileXML(fileName);
		}

	}

	/**
	 * Read the information in one csv file and save as Execution orders in the system
	 *
	 * @param nameFile name of the file String
	 * @throws FileNotFoundException
	 */
	public void readInformationFileCSV(String nameFile) throws FileNotFoundException {
		Scanner in = new Scanner(new File(nameFile));
		while (in.hasNext()) {
			String line = in.nextLine().trim();
			if (line.length() > 0) {
				String[] dados = line.split(DATA_SEPARATOR);
				if (dados.length == 10) {
					rEO.registerExecutionOrder(rEO.newExecutionOrder(dados[0].trim(), dados[1].trim(), dados[2].trim(), dados[3].trim(),
							dados[4].trim(), dados[5].trim(), dados[6].trim(), dados[7].trim(), dados[8].trim(), dados[9].trim()));
				}
			}
		}
	}

	/**
	 * Read the information in one xlsx file and save as Execution orders in the system
	 *
	 * @param fileName name of the file String
	 * @throws Exception
	 */
	public void readInformationFileXLSX(String fileName) throws Exception {
		DataFormatter df = new DataFormatter();
		String[] data = new String[10];
		int i;
		Date[] a = new Date[2];
		File myFile = new File(fileName);
		FileInputStream fis = new FileInputStream(myFile);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);

		Iterator<Row> rowIt = sheet.iterator();

		rowIt.next();

		while (rowIt.hasNext()) {

			i = 0;
			Row row = rowIt.next();
			Iterator<Cell> cellIt = row.cellIterator();

			while (cellIt.hasNext()) {

				Cell cell = cellIt.next();
				if (i == 5) {
					data[i] = df.formatCellValue(cell);
					i++;
					cell = cellIt.next();
				}

				if (i == 6) {
					SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
					if (cell.toString().contains("31-dez-1899")) {
						// System.out.println(getcell.getDateCellValue());
						String timeStamp = formatTime.format(cell.getDateCellValue());
						data[i] = timeStamp;
						i++;
						cell = cellIt.next();
					}

				}
				data[i] = cell.toString();
				i++;

			}

			rEO.registerExecutionOrder(rEO.newExecutionOrder(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(), data[5].trim(), data[6].trim(), data[7].trim(), data[8].trim(), data[9].trim()));

		}

		workbook.close();
		fis.close();

	}

	/**
	 *
	 * @param nameFile
	 */
	public void readInformationFileXML(String nameFile) {

		try {
			File file = new File(nameFile);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder;
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			String number = document.getElementsByTagName("Number").item(0).getTextContent();
			String name = document.getElementsByTagName("ClientName").item(0).getTextContent();
			String distance = document.getElementsByTagName("DistanceToClient").item(0).getTextContent();
			String cat = document.getElementsByTagName("ServiceCategory").item(0).getTextContent();
			String serviceType = document.getElementsByTagName("ServiceType").item(0).getTextContent();
			String startDate = document.getElementsByTagName("StartDate").item(0).getTextContent();
			String startTime = document.getElementsByTagName("StartTime").item(0).getTextContent();
			String address = document.getElementsByTagName("Address").item(0).getTextContent();
			String locality = document.getElementsByTagName("Locality").item(0).getTextContent();
			String postalCode = document.getElementsByTagName("PostalCode").item(0).getTextContent();
						
			rEO.registerExecutionOrder(rEO.newExecutionOrder(number, name, distance, cat, serviceType, startDate, startTime, address, locality, postalCode));
			
			
		} catch (ParserConfigurationException ex) {
			Logger.getLogger(ImportExecutionOrdersController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SAXException ex) {
			Logger.getLogger(ImportExecutionOrdersController.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(ImportExecutionOrdersController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 * Method that save the execution orders in a binary file
	 *
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void saveToBinary() throws IOException, FileNotFoundException {
		rEO.saveOrdersToBinaryFile();
	}
}

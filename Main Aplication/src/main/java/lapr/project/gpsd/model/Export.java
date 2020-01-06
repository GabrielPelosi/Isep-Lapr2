/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import com.sun.media.sound.InvalidFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.apache.poi.ss.usermodel.Cell;
import org.xml.sax.*;
import org.w3c.dom.*;

/**
 *
 * @author Francisco Ferreira
 */
public class Export {

    private static Formatter fOutput;

    private static Workbook workbook;

    private static FileOutputStream fileOut;

    private static FileWriter csvWriter;

    /**
     * Returns true or false if export file (with service execution orders) or not
     *
     * @param peds list of service execution orders
     * @param fileType file type to export
     * @return true or false if export file or not
     */
    public static boolean exportFile(String fileType, List<String[]> data) {
        try {
            String fileTypeLowerCase = fileType.toLowerCase().trim();
            if (fileTypeLowerCase.equals("excel")) {
                return Export.exportFileExcel(data);
            }
            if (fileTypeLowerCase.equals("csv")) {
                return Export.exportFileCSV(data);
            }
            if (fileTypeLowerCase.equals("xml")) {
                return Export.exportFileXML(data);
            }
            return false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Export service execution orders for excel type
     *
     * @param peds list of service execution orders
     * @return true or false if the new file is exported or not
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static boolean exportFileExcel(List<String[]> peds) throws IOException {
        try {
            String[] colums = new String[10];

            colums[0] = "Number";
            colums[1] = "ClientName";
            colums[2] = "DistanceToClient";
            colums[3] = "ServiceCategory";
            colums[4] = "ServiceType";
            colums[5] = "StartDate";
            colums[6] = "StartTime";
            colums[7] = "Address";
            colums[8] = "Locality";
            colums[9] = "PostalCode";

            workbook = new XSSFWorkbook();

            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Service Execution Orders");

            Row headerRow = sheet.createRow(0);

            for (int k = 0; k < colums.length; k++) {
                Cell cell = headerRow.createCell(k);
                cell.setCellValue(colums[k]);
            }

            int rowNum = 1;
            for (String[] ped : peds) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 0; i < ped.length - 1; i++) {
                    row.createCell(i).setCellValue(ped[i]);
                }

                for (int i = 0; i < ped.length - 1; i++) {
                    sheet.autoSizeColumn(i);
                }
            }

            fileOut = new FileOutputStream("ExecutionOrders_" + peds.get(0)[10] + "_UC16.xlsx");
            workbook.write(fileOut);
            return true;
        } catch (InvalidFormatException ife) {
            System.out.println(ife.getMessage());
            return false;
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            return false;
        } finally {
            fileOut.close();
            workbook.close();
        }

    }

    /**
     * Export service execution orders to CSV file
     *
     * @param peds list os service execution orders
     * @return true or false if the new file is exported or not
     */
    public static boolean exportFileCSV(List<String[]> peds) throws IOException {
        try {

            csvWriter = new FileWriter("ExecutionOrders_" + peds.get(0)[10] + "_UC16.csv", true);

            String[] colums = new String[10];

            colums[0] = "Number";
            colums[1] = "ClientName";
            colums[2] = "DistanceToClient";
            colums[3] = "ServiceCategory";
            colums[4] = "ServiceType";
            colums[5] = "StartDate";
            colums[6] = "StartTime";
            colums[7] = "Address";
            colums[8] = "Locality";
            colums[9] = "PostalCode";
            for (String s : colums) {
                csvWriter.append(s);
                csvWriter.append(";");
            }
            csvWriter.append(System.lineSeparator());
            for (String[] ped : peds) {
                for (int i = 0; i < ped.length - 1; i++) {
                    System.out.println(ped[i]);
                    csvWriter.append(ped[i]);
                    csvWriter.append(";");
                }
                csvWriter.append(System.lineSeparator());
            }
            return true;
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            return false;
        } finally {
            csvWriter.flush();
            csvWriter.close();
        }
    }

    /**
     * Export service execution orders for XML file
     *
     * @param peds ist of service execution orders
     * @return true or false if the new file is exported or not
     */
    public static boolean exportFileXML(List<String[]> peds) {
        try {
            String name = "without name";
            int numPed = 0;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Company");
            doc.appendChild(rootElement);
            for (String[] ped : peds){
                for (int i = 0; i < ped.length-1; i++){
            Element element = doc.createElement("Number");
            element.appendChild(doc.createTextNode(ped[i]));
            element.appendChild(doc.createTextNode("numberped"));
            if(i == 1){
               name = ped[i];
            }
            }
//            staff.appendChild(number);
//                Element element =
//            List<Service> serviceList = new ArrayList<>();
//            serviceList = ped.getServico();
//            PostalAddress pA = ped.getMorada();
//            String name = ped.getCl().getNome();
//            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//            Document doc = docBuilder.newDocument();
//            
//            Element rootElement = doc.createElement("Company");
//            doc.appendChild(rootElement);
//
//  
//            Element staff = doc.createElement("ServiceExecutionOrders");
//            rootElement.appendChild(staff);
//
//            Element number = doc.createElement("Number");
//            number.appendChild(doc.createTextNode(Integer.toString(numPed++)));
//            number.appendChild(doc.createTextNode("numberped"));
//            staff.appendChild(number);
//
//            Element clientName = doc.createElement("ClientName");
//            clientName.appendChild(doc.createTextNode(name));
//            clientName.appendChild(doc.createTextNode("name"));
//            staff.appendChild(clientName);
//
//            Element distanceToClient = doc.createElement("DistanceToClient");
//            distanceToClient.appendChild(doc.createTextNode("fazer ainda"));
//            staff.appendChild(distanceToClient);
//
//            Element serviceCategory = doc.createElement("ServiceCategory");
//            serviceCategory.appendChild(doc.createTextNode(ped.CatServ()));
//            serviceCategory.appendChild(doc.createTextNode("servicecategory"));
//            staff.appendChild(serviceCategory);
//
//            Element serviceType = doc.createElement("ServiceType");
//            serviceType.appendChild(doc.createTextNode(serviceList.get(0).getType()));
//            serviceType.appendChild(doc.createTextNode("serviceType"));
//            staff.appendChild(serviceType);
//
//            Element startDate = doc.createElement("StartDate");
//            startDate.appendChild(doc.createTextNode("fazer ainda"));
//            staff.appendChild(startDate);
//
//            Element startTime = doc.createElement("StartTime");
//            startTime.appendChild(doc.createTextNode("fazer ainda"));
//            staff.appendChild(startTime);
//
//            Element address = doc.createElement("Address");
//            address.appendChild(doc.createTextNode((pA.getAddress().toString())));
//            staff.appendChild(address);
//
//            Element locality = doc.createElement("Locality");
//            locality.appendChild(doc.createTextNode(pA.getLocality()));
//            staff.appendChild(locality);
//
//            Element postalCode = doc.createElement("PostalCode");
//            postalCode.appendChild(doc.createTextNode((pA.getPostalCode().toString())));
//            staff.appendChild(postalCode);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("ExecutionOrders_" + name + "_UC16.xml"));

            transformer.transform(source, result);

            System.out.println("File saved!");
            }
            return true;
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
            return false;
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
            return false;
        }

    }
}

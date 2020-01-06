package lapr.project.gpsd.ui.console.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Scanner;
import lapr.project.gpsd.controller.AssignAvailabilityController;
import lapr.project.gpsd.controller.RegisterClientController;
import lapr.project.gpsd.controller.RegisterSPController;
import lapr.project.gpsd.model.Area;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.DailyAvailability;
import lapr.project.gpsd.model.Data;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.PostalCode;
import lapr.project.gpsd.model.RegisterApplication;
import lapr.project.gpsd.model.RegisterArea;
import lapr.project.gpsd.model.RegisterCategory;
import lapr.project.gpsd.model.RegisterClient;
import lapr.project.gpsd.model.RegisterPC;
import lapr.project.gpsd.model.RegisterSP;
import lapr.project.gpsd.model.RegisterService;
import lapr.project.gpsd.model.RegisterServiceType;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.model.ServiceType;
import lapr.project.gpsd.model.Time;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Bootable {

    private final String filePC = System.getProperty("user.dir") + "/bootableFiles/codigopostal_alt_long.csv";
    private final String fileGA = System.getProperty("user.dir") + "/bootableFiles/AreaGeografica_UC5.xlsx";
    private final String fileCat = System.getProperty("user.dir") + "/bootableFiles/Categoria_UC3.xlsx";
    private final String fileServ = System.getProperty("user.dir") + "/bootableFiles/EspecificarServico_UC4.xlsx";
    private final String fileC = System.getProperty("user.dir") + "/bootableFiles/RegistoCiente_UC1.xlsx";
    private final String fileSP = System.getProperty("user.dir") + "/bootableFiles/RegistoPrestadorServico_UC8.xlsx";
    private final String fileASP = System.getProperty("user.dir") + "/bootableFiles/CandidaturaPrestadorServicos_UC2.xlsx";
    private final String fileA = System.getProperty("user.dir") + "/bootableFiles/IndicarDisponibilidade_UC9.xlsx";
    private final String fileSC = System.getProperty("user.dir") + "/bootableFiles/ServicoCompleto_UC13.xlsx";

    private final int NUMBER_OF_POSTAL_CODES = -1;

    public void readAll() {
        try {
            System.out.println("READING POSTAL CODES...");
            lerFicheiroCP(filePC);
            System.out.println("READING AREAS...");
            readGA(fileGA);
            System.out.println("READING CATEGORIES...");
            readCat(fileCat);
            System.out.println("READING SERVICES...");
            readServ(fileServ);
            System.out.println("READING CLIENTS...");
            readC(fileC);
            System.out.println("READING SERVICE PROVIDERS...");
            readSP(fileSP);
            System.out.println("READING APPLICATIONS...");
            readASP(fileASP);
            System.out.println("READING AVAILABILITIES...");
            readA(fileA);
            System.out.println("READING COMPLETED SERVICES...");
            readSC(fileSC);

        } catch (Exception ex) {
            System.out.println("ERROR LOADING: " + ex.getMessage());
        }
    }

    public void lerFicheiroCP(String nomeFich) throws FileNotFoundException {
        boolean order = false;
        int cp1 = 3;
        int cp2 = 4;
        int latNum = 5;
        int lonNum = 6;
        int limit = NUMBER_OF_POSTAL_CODES;
        int counter = 0;

        Scanner in = new Scanner(new File(nomeFich));
        Company e = Utils.getEmpresa();
        RegisterPC c = e.getRegistoCP();

        try {
            while (in.hasNext()) {
                if (counter <= limit || limit == -1) {
                    String linha = in.nextLine().trim();
                    String[] dados = linha.split(";");
                    if (linha.length() > 0) {
                        if (limit > 0) {
                            counter++;
                        }
                        if (!order) {
                            cp1 = localString(dados, "CP4");
                            cp2 = localString(dados, "CP3");
                            latNum = localString(dados, "LATITUDE");
                            lonNum = localString(dados, "LONGITUDE");
                            order = true;
                        } else {
                            double lat = Double.parseDouble(dados[latNum].trim().replace(',', '.'));
                            double lon = Double.parseDouble(dados[lonNum].trim().replace(',', '.'));
                            String pc1 = dados[cp1].trim();
                            String pc2 = dados[cp2].trim();

                            while (pc1.length() < 4) {
                                pc1 = "0" + pc1;
                            }

                            while (pc2.length() < 3) {
                                pc2 = "0" + pc2;
                            }

                            c.registaCP(c.novoCP(String.format("%s-%s", pc1, pc2), lat, lon));
                        }

                    }
                } else {
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            in.close();
            //System.out.println(c.getListCP());
        }
    }

    private static int localString(String[] linha, String dado) throws Exception {
        for (int i = 0; i < linha.length; i++) {
            String exp = linha[i].trim();
            if (exp.equals(dado.trim())) {
                return i;
            }
        }
        throw new Exception();
    }

    private void readGA(String fileName) throws FileNotFoundException, IOException {
        File myFile = new File(fileName);
        FileInputStream fis = new FileInputStream(myFile);
        Company e = Utils.getEmpresa();
        RegisterPC c = e.getRegistoCP();
        RegisterArea a = e.getRegisterArea();

        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIt = sheet.iterator();
        rowIt.next();
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            String[] data = new String[4];
            for (int i = 0; i < 4; i++) {
                Cell cell = row.getCell(i);
                data[i] = cell.toString().trim();
            }
            if (c.getCPById(data[1].trim()) != null) {
                PostalCode pc = c.getCPById(data[1]);
                int radius = (int) Double.parseDouble(data[2].trim());
                double valor = Double.parseDouble(data[3].trim());
                a.registaArea(a.novaArea(data[0].trim(), pc, radius, valor, "XPTO"));
            }
        }
        //System.out.println(a.getAreas());
        workbook.close();
        fis.close();
    }

    private void readCat(String fileName) throws FileNotFoundException, IOException {

        Company e = Utils.getEmpresa();
        RegisterCategory c = e.getRegistoCategoria();

        File myFile = new File(fileName);
        FileInputStream fis = new FileInputStream(myFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIt = sheet.iterator();
        rowIt.next();
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            String[] data = new String[2];

            data[0] = row.getCell(0).toString().trim().substring(0, 1);
            data[1] = row.getCell(2).toString().trim();

            c.registerCategory(c.newCategory(data[0].trim(), data[1].trim()));
        }
        //System.out.println(c.getCategorias());
        workbook.close();
        fis.close();
    }

    private void readServ(String fileName) throws FileNotFoundException, IOException {

        Company e = Utils.getEmpresa();
        RegisterCategory c = e.getRegistoCategoria();
        RegisterService s = e.getRegistoServico();
        RegisterServiceType st = e.getRegisterServiceType();

        File myFile = new File(fileName);
        FileInputStream fis = new FileInputStream(myFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIt = sheet.iterator();
        rowIt.next();
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            String[] data = new String[7];
            if (row.getPhysicalNumberOfCells() < 6) {
                return;
            }
            data[0] = row.getCell(0).toString().trim();
            data[1] = row.getCell(2).toString().trim();
            data[2] = row.getCell(3).toString().trim();
            data[3] = row.getCell(4).toString().trim();
            data[4] = row.getCell(5).toString().trim().substring(0, 1);
            data[6] = row.getCell(1).toString().trim();
            if (row.getPhysicalNumberOfCells() == 7) {
                data[5] = row.getCell(6).toString().trim();
            }
            ServiceType servClass = null;

            if (data[6].equals("FixedService")) {
                servClass = st.getList().get(0);
            } else if (data[6].equals("LimitedService")) {
                servClass = st.getList().get(1);
            } else {
                servClass = st.getList().get(2);
            }

            if (c.getCategoriaById(data[4]) != null) {
                double preco = Double.parseDouble(data[3].trim());
                if (data[6].equals("FixedService")) {
                    Time tempo = new Time("00:" + data[5].substring(0, 2));
                    s.registerService(s.newService(data[0], data[1], data[2], preco, c.getCategoriaById(data[4].trim()), tempo, servClass));
                } else {
                    s.registerService(s.newService(data[0], data[1], data[2], preco, c.getCategoriaById(data[4].trim()), null, servClass));
                }
            }
        }
        //System.out.print(s.getList());
        workbook.close();
        fis.close();
    }

    private void readC(String fileName) throws FileNotFoundException, IOException {
        Company e = Utils.getEmpresa();
        RegisterClient c = e.getRegistoCliente();
        RegisterClientController rcc = new RegisterClientController();

        File myFile = new File(fileName);
        FileInputStream fis = new FileInputStream(myFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIt = sheet.iterator();
        rowIt.next();
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            String[] data = new String[8];
            data[0] = row.getCell(1).toString().trim();
            data[1] = row.getCell(0).toString().trim();
            data[2] = row.getCell(2).toString().trim();
            data[3] = row.getCell(3).toString().trim();
            data[4] = row.getCell(4).toString().trim();
            data[5] = row.getCell(5).toString().trim();
            data[6] = row.getCell(7).toString().trim();
            data[7] = row.getCell(6).toString().trim();
            rcc.newClient(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(), data[5].trim(), data[6].trim(), data[7].trim());
            rcc.registerClient();
        }
        //System.out.println(c.getClients());
        workbook.close();
        fis.close();
    }

    private void readSP(String fileName) throws FileNotFoundException, IOException {
        RegisterSP s = Utils.getEmpresa().getServiceProvidersRegister();
        RegisterCategory rc = Utils.getEmpresa().getRegistoCategoria();
        RegisterPC rpc = Utils.getEmpresa().getRegistoCP();
        RegisterArea ra = Utils.getEmpresa().getRegisterArea();

        File myFile = new File(fileName);
        FileInputStream fis = new FileInputStream(myFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIt = sheet.iterator();
        rowIt.next();
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            String[] data = new String[11];
            data[0] = row.getCell(0).toString().trim().substring(0, row.getCell(0).toString().trim().length() - 2);
            data[1] = row.getCell(1).toString().trim().substring(0, row.getCell(0).toString().trim().length() - 2);
            data[2] = row.getCell(2).toString().trim();
            data[3] = row.getCell(3).toString().trim();
            data[4] = row.getCell(4).toString().trim();
            data[5] = row.getCell(5).toString().trim();
            PostalCode p1 = rpc.getCPById(data[5]);
            PostalAddress temp = new PostalAddress("null", p1, "null");
            ServiceProvider sp = s.novoPrestadorServico(data[0], data[1], data[2], data[3], data[4], temp);
            boolean areaType = false;
            for (int i = 6; i < row.getLastCellNum(); i++) {
                if (row.getCell(i) == null) {
                    areaType = true;
                } else {
                    if (areaType) {
                        Area a = ra.getAreaByName(row.getCell(i).toString().trim());
                        sp.addGeographicArea(a);
                    } else {
                        Category c = rc.getCategoryByName(row.getCell(i).toString().trim());
                        sp.addCategory(c);
                        if (i == 8) {
                            areaType = true;
                        }
                    }
                }
            }
            s.registerServiceProviderFile(sp, "2");

        }
        //System.out.println(s.getServiceProvidersList());
        workbook.close();
        fis.close();
    }

    private void readASP(String fileName) throws FileNotFoundException, IOException, Exception {
        Company e = Utils.getEmpresa();
        RegisterApplication rp = Utils.getEmpresa().getRegisterApplication();
        RegisterCategory rc = Utils.getEmpresa().getRegistoCategoria();

        File myFile = new File(fileName);
        FileInputStream fis = new FileInputStream(myFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIt = sheet.iterator();
        rowIt.next();
        while (rowIt.hasNext()) {
            RegisterSPController c = new RegisterSPController();
            Row row = rowIt.next();
            String[] data = new String[8];
            data[0] = row.getCell(1).toString().trim();
            data[1] = row.getCell(0).toString().trim().substring(0, row.getCell(0).toString().trim().length() - 2).replace(".", "");;
            data[2] = row.getCell(2).toString().trim().substring(0, row.getCell(0).toString().trim().length() - 2).replace(".", "");
            data[3] = row.getCell(3).toString().trim();
            //Addresse
            if (row.getCell(4) != null) {
                data[4] = row.getCell(4).toString().trim();
            } else {
                data[4] = "null";
            }
            if (row.getCell(5) != null) {
                data[5] = row.getCell(5).toString().trim();
            } else {
                data[5] = "null";
            }
            data[6] = row.getCell(6).toString().trim();
            c.addAddress(data[5], data[6], data[4]);

            String habAcad = "";
            String habProf = "";

            for (int i = 7; i < row.getLastCellNum(); i++) {
                if (i > 12) {
                    if (row.getCell(i) != null) {
                        c.addCategory(rc.getCategoryByName(row.getCell(i).toString().trim()));
                    }
                } else if (i > 9) {
                    if (row.getCell(i) != null) {
                        habProf += row.getCell(i).toString().trim() + "; ";
                    }
                } else {
                    if (row.getCell(i) != null) {
                        habAcad += row.getCell(i).toString().trim() + "; ";
                    }
                }
            }
            c.newApplication(data[0], data[1], data[2], data[3], habAcad, habProf);
        }
        System.out.println(rp.getListApplication());
        workbook.close();
        fis.close();
    }

    private void readA(String fileName) throws FileNotFoundException, IOException {
        //AssignAvailabilityController contr = new AssignAvailabilityController();
        RegisterSP rsp = Utils.getEmpresa().getServiceProvidersRegister();
        File myFile = new File(fileName);
        FileInputStream fis = new FileInputStream(myFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIt = sheet.iterator();
        rowIt.next();

        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            String prestS = row.getCell(0).toString().trim().substring(0, row.getCell(0).toString().trim().length() - 2);
            ServiceProvider serv = rsp.getServiceProviderByMechNum(prestS);
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
            for (int i = 2; i < 13; i += 4) {
                if (row.getCell(i) != null && row.getCell(i + 2) != null) {
                    String data1 = df.format(row.getCell(i).getDateCellValue());
                    String d1 = data1.substring(0, 2);
                    String data2 = df.format(row.getCell(i + 2).getDateCellValue());
                    String d2 = data2.substring(0, 2);
                    int b1 = Integer.parseInt(d1);
                    int dif = Integer.parseInt(d2) - b1;

                    String hourISP = formatTime.format(row.getCell(i + 1).getDateCellValue());
                    String hourFSP = formatTime.format(row.getCell(i + 3).getDateCellValue());
                    Time tI = new Time(hourISP);
                    Time tF = new Time(hourFSP);
                    if (!tF.isMaior(tI)) {
                        Data dataI = new Data(data1);
                        Data dataF = new Data(data2);
                        if (!dataI.determinarDiaDaSemana().equals("Domingo")) {
                            serv.addDisponibilidades(new DailyAvailability(dataI, hourISP, "24:00"));
                        }
                        if (!dataF.determinarDiaDaSemana().equals("Domingo")) {
                            serv.addDisponibilidades(new DailyAvailability(dataF, "06:00", hourFSP));
                        }

                    } else {
                        for (int j = 0; j <= dif; j++) {
                            Data data = new Data(String.format("%s%s", b1 + j, data1.substring(2, data1.length())));
                            if (!data.determinarDiaDaSemana().equals("Domingo")) {
                                serv.addDisponibilidades(new DailyAvailability(data, hourISP, hourFSP));
                            }
                        }
                    }
                }
            }
        }
        workbook.close();
        fis.close();

    }

    private void readSC(String fileName) throws FileNotFoundException, IOException{
        
    }
    
}

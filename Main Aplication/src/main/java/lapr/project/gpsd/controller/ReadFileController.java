package lapr.project.gpsd.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import lapr.project.gpsd.model.Area;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.PostalCode;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.model.RegisterArea;
import lapr.project.gpsd.model.RegisterPC;
import lapr.project.gpsd.model.RegisterCategory;
import lapr.project.gpsd.model.RegisterClient;
import lapr.project.gpsd.model.RegisterSP;
import lapr.project.gpsd.model.RegisterService;
import lapr.project.gpsd.model.RegisterServiceType;
import lapr.project.gpsd.model.ServiceType;
import lapr.project.gpsd.model.Time;
import lapr.project.gpsd.ui.console.utils.Utils;

public class ReadFileController {

    public final String SEPARADOR_DADOS = ";";

    /**
     * Lê um ficheiro com os dados dos cliente e instancia-os
     *
     * @param nomeFich nome do ficheiro a ler
     */
    public void lerFicheiroCliente(String nomeFich) throws FileNotFoundException {
        Scanner in = new Scanner(new File(nomeFich));
        RegisterClientController rcc = new RegisterClientController();
        while (in.hasNext()) {
            String linha = in.nextLine().trim();
            if (linha.length() > 0) {
                String[] dados = linha.split(SEPARADOR_DADOS);
                if (dados.length >= 8) {
                    if (rcc.newClient(dados[0].trim(), dados[1].trim(), dados[2].trim(), dados[3].trim(), dados[4].trim(), dados[5].trim(), dados[6].trim(), dados[7].trim())) {
                        rcc.registerClient();
                        Company e = Utils.getEmpresa();
                        RegisterClient c = e.getRegistoCliente();
                        Client temp = c.getClienteByEmail(dados[3].trim());
                        if (dados.length > 8) {
                            int i = 8;
                            while (i + 2 < dados.length) {
                                PostalAddress ep = new PostalAddress(dados[i], dados[i + 1], dados[i + 2]);
                                temp.addPostalAddress(ep);
                                i += 3;
                            }
                        }
                    }
                }
            }
        }
        in.close();
    }

    /**
     * Lê um ficheiro com os dados dos prestador de serviço e instancia-os
     *
     * @param nomeFich nome do ficheiro a ler
     */
    public void lerFicheiroPrestadorServico(String nomeFich) throws FileNotFoundException {
        Scanner in = new Scanner(new File(nomeFich));
        RegisterServiceProviderController rcc = new RegisterServiceProviderController();
        in.nextLine();
		
        Company e = Utils.getEmpresa();
		RegisterArea a = e.getRegisterArea();
        RegisterSP c = e.getServiceProvidersRegister();
        RegisterPC aa = e.getRegistoCP();
        while (in.hasNext()) {
            String linha = in.nextLine().trim();
            if (linha.length() > 0) {
                System.out.println(c.getServiceProvidersList());
                String[] dados = linha.split(";|/");
                System.out.println(dados.length);
                if (dados.length >= 11) {
                    rcc.newServiceProvider(dados[0].trim(), dados[1].trim(), dados[2].trim(), dados[3].trim(),dados[5].trim(),dados[6].trim(),dados[7].trim());
                    rcc.registerServiceProviderFile(dados[4].trim());
                    //Company e = Utils.getEmpresa();
                    //RegisterSP c = e.getServiceProvidersRegister();
                    ServiceProvider prest = c.getPrestadorServicoByEmail(dados[3].trim());
                    if (dados.length > 11) {
                        int i = 8;
                        while (i + 1 < dados.length) {
                            prest.addCategory(new Category(dados[i].trim(), dados[i + 1].trim()));
                            i += 2;
                        }
                        String[] dataGeo = dados[i].split("_");
                        i = 0;
                        //RegisterPC aa = e.getRegistoCP();
                        while (i < dataGeo.length) {
                            PostalCode pc = aa.getCPById(dataGeo[i + 1]);
                            int radius = Integer.parseInt(dataGeo[i + 2].trim());
                            double valor = Double.parseDouble(dataGeo[i + 3].trim());
							Area area = a.novaArea(dataGeo[i], pc, radius, valor, "XPTO");
							a.registaArea(area);
                            prest.addGeographicArea(area);
                            i += 4;
                        }
                    } else {
                        prest.addCategory(new Category(dados[8].trim(), dados[9].trim()));
                        String[] dataGeo = dados[10].split("_");
                        //RegisterPC aa = e.getRegistoCP();
                        PostalCode pc = aa.getCPById(dataGeo[1]);
                        int radius = Integer.parseInt(dataGeo[2].trim());
                        double valor = Double.parseDouble(dataGeo[3].trim());
						
						
						Area area = a.novaArea(dataGeo[0], pc, radius, valor, "XPTO");
                        a.registaArea(area);
						prest.addGeographicArea(area);
                    }

                }
            }
        }
        in.close();
    }

    /**
     * Lê um ficheiro com os dados das categorias e instancia-as
     *
     * @param nomeFich nome do ficheiro a ler
     */
    public void lerFicheiroCategoria(String nomeFich) throws FileNotFoundException {
        Scanner in = new Scanner(new File(nomeFich));
        Company e = Utils.getEmpresa();

        while (in.hasNext()) {
            String linha = in.nextLine().trim();
            if (linha.length() > 0) {
                String[] dados = linha.split(SEPARADOR_DADOS);
                if (dados.length == 2) {
                    RegisterCategory c = e.getRegistoCategoria();
                    c.registerCategory(c.newCategory(dados[0].trim(), dados[1].trim()));
                }
            }

        }
        in.close();
    }

    /**
     * Lê um ficheiro com os dados dos serviços e instancia-os
     *
     * @param nomeFich nome do ficheiro a ler
     */
    public void lerFicheiroServico(String nomeFich) throws FileNotFoundException {
        Scanner in = new Scanner(new File(nomeFich));
        Company e = Utils.getEmpresa();
        RegisterServiceType st = e.getRegisterServiceType();
        try {
            in.nextLine();
            while (in.hasNext()) {
                String linha = in.nextLine().trim();
                if (linha.length() > 0) {
                    String[] dados = linha.split(SEPARADOR_DADOS);
                    if (dados.length == 5) {
                        RegisterCategory c = e.getRegistoCategoria();
                        RegisterService s = e.getRegistoServico();
                        if (c.getCategoriaById(dados[4].trim()) != null) {
                            double preco = Double.parseDouble(dados[3].trim());
                            s.registerService(s.newService(dados[0], dados[1], dados[2], preco, c.getCategoriaById(dados[4].trim()),null, st.getList().get(1)));
                        }
                    } else if (dados.length == 6) {
                        RegisterCategory c = e.getRegistoCategoria();
                        RegisterService s = e.getRegistoServico();
                        if (c.getCategoriaById(dados[4].trim()) != null) {
                            double preco = Double.parseDouble(dados[3].trim());
                            Time tempo = new Time(dados[5]);
                            s.registerService(s.newService(dados[0], dados[1], dados[2], preco, c.getCategoriaById(dados[4].trim()), tempo, st.getList().get(0)));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            in.close();
        }
    }

    /**
     * Lê um ficheiro com os dados dos Códigos Postais e instancia-os
     *
     * @param nomeFich nome do ficheiro a ler
     */
    public void lerFicheiroCP(String nomeFich) throws FileNotFoundException {
        boolean order = false;
        int cp1 = 3;
        int cp2 = 4;
        int latNum = 5;
        int lonNum = 6;
        int limit = 10;
        int counter = 0;

        Scanner in = new Scanner(new File(nomeFich));
        Company e = Utils.getEmpresa();
        RegisterPC c = e.getRegistoCP();

        try {
            while (in.hasNext()) {
                if (counter <= limit) {
                    String linha = in.nextLine().trim();
                    String[] dados = linha.split(SEPARADOR_DADOS);
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

                            while(pc1.length() <4){
                                pc1 = "0"+pc1;
                            }
                            
                            while(pc2.length() <3){
                                pc2 = "0"+pc2;
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
            System.out.println(c.getListCP());
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

    /**
     * Lê um ficheiro com os dados das Áreas Geográficas e instancia-as
     *
     * @param nomeFich nome do ficheiro a ler
     */
    public void lerFicheiroAreaGeografica(String nomeFich) throws FileNotFoundException {
        Scanner in = new Scanner(new File(nomeFich));
        Company e = Utils.getEmpresa();
        RegisterPC c = e.getRegistoCP();
        RegisterArea a = e.getRegisterArea();
        while (in.hasNext()) {
            String linha = in.nextLine().trim();
            if (linha.length() > 0) {
                String[] dados = linha.split(SEPARADOR_DADOS);
                if (dados.length == 4) {
                    if (c.getCPById(dados[1].trim()) != null) {
                        PostalCode pc = c.getCPById(dados[1]);
                        int radius = Integer.parseInt(dados[2].trim());
                        double valor = Double.parseDouble(dados[3].trim());

                        a.registaArea(a.novaArea(dados[0].trim(), pc, radius, valor, "XPTO"));
                    }
                }
            }
        }
        System.out.println(a.getAreas());
        in.close();
    }

    public List<ServiceType> criaTiposServicosSuportados(Properties props) {
        List<ServiceType> listTipos = new ArrayList<ServiceType>();
        // Conhecer quantos TipoServico são suportados
        String qtdTipos = props.getProperty("Empresa.QuantidadeTiposServicoSuportados");
        int qtd = Integer.parseInt(qtdTipos);
        // Por cada tipo suportado criar a instância respetiva
        for (int i = 1; i <= qtd; i++) {
            // Conhecer informação (descrição e classe) da instância a criar
            String desc = props.getProperty("Empresa.TipoServico." + i + ".Designacao");
            String classe = props.getProperty("Empresa.TipoServico." + i + ".Classe");
            // Criar a instância
            ServiceType tipoServ = new ServiceType(desc, classe);
            // Adicionar à lista a devolver
            listTipos.add(tipoServ);
        }
        // Retornar Tipos Suportados
        return listTipos;
    }

    public Properties readProperties() throws IOException {
        File fichConfiguracao = new File("config.properties");
        FileReader reader = new FileReader(fichConfiguracao);
        Properties props = new Properties();
        props.load(reader);
        return props;
    }
}

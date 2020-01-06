package lapr.project.gpsd.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import lapr.project.autorizacao.AuthorizationFacade;
import lapr.project.autorizacao.model.UserSession;
import lapr.project.gpsd.model.Constantes;
import lapr.project.gpsd.model.Company;

public class ApplicationGPSD {

    private final Company oCompany;
    private final AuthorizationFacade oAuthorization;

    private ApplicationGPSD() {
        Properties props = getProperties();
        this.oCompany = new Company(props.getProperty(Constantes.PARAMS_EMPRESA_DESIGNACAO),
                props.getProperty(Constantes.PARAMS_EMPRESA_NIF));
        this.oAuthorization = this.oCompany.getAutorizacaoFacade();
        bootstrap();
    }

    public Company getEmpresa() {
        return this.oCompany;
    }

    public UserSession getSessaoAtual() {
        return this.oAuthorization.getSessaoAtual();
    }

    public boolean doLogin(String strId, String strPwd) {
        return this.oAuthorization.doLogin(strId, strPwd) != null;
    }

    public void doLogout() {
        this.oAuthorization.doLogout();
    }

    private Properties getProperties() {
        Properties props = new Properties();
        // Adiciona propriedades e valores por omissão
        props.setProperty(Constantes.PARAMS_EMPRESA_DESIGNACAO, "Default Lda.");
        props.setProperty(Constantes.PARAMS_EMPRESA_NIF, "Default NIF");
        // Lê as propriedades e valores definidas 
        try {
            InputStream in = new FileInputStream(Constantes.PARAMS_FICHEIRO);
            props.load(in);
            in.close();
        } catch (Exception ex) {

        }
        return props;
    }

    private void bootstrap() {
        this.oAuthorization.registaPapelUtilizador(Constantes.PAPEL_ADMINISTRATIVO);
        this.oAuthorization.registaPapelUtilizador(Constantes.PAPEL_CLIENTE);
        this.oAuthorization.registaPapelUtilizador(Constantes.PAPEL_FRH);
        this.oAuthorization.registaPapelUtilizador(Constantes.PAPEL_PRESTADOR_SERVICO);

        this.oAuthorization.registaUtilizadorComPapel("Administrativo 1", "adm1@esoft.pt", "1", Constantes.PAPEL_ADMINISTRATIVO);
        this.oAuthorization.registaUtilizadorComPapel("Administrativo 2", "adm2@esoft.pt", "12", Constantes.PAPEL_ADMINISTRATIVO);

        this.oAuthorization.registaUtilizadorComPapel("FRH 1", "frh1@esoft.pt", "123", Constantes.PAPEL_FRH);
        this.oAuthorization.registaUtilizadorComPapel("FRH 2", "frh2@esoft.pt", "1234", Constantes.PAPEL_FRH);
        this.oAuthorization.registaUtilizadorComPapel("hr", "hr", "1", Constantes.PAPEL_FRH);
        this.oAuthorization.registaUtilizadorComPapel("1", "1", "1",Constantes.PAPEL_ADMINISTRATIVO);

        this.oAuthorization.registaUtilizadorComPapeis("Martim", "martim@esoft.pt", "123456", new String[]{Constantes.PAPEL_FRH, Constantes.PAPEL_ADMINISTRATIVO});
   }

    // Inspirado em https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static ApplicationGPSD singleton = null;

    public static ApplicationGPSD getInstance() {
        if (singleton == null) {
            synchronized (ApplicationGPSD.class) {
                singleton = new ApplicationGPSD();
            }
        }
        return singleton;
    }

}

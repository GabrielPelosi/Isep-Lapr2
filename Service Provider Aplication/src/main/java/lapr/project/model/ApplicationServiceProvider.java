package lapr.project.model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationServiceProvider {

	private final Company oCompany;

	/**
	 * Constructor method of ApplicationServiceProvider
	 */
	private ApplicationServiceProvider() {
		Properties props = getProperties();
		this.oCompany = new Company(props.getProperty(Constantes.PARAMS_EMPRESA_DESIGNACAO),
				props.getProperty(Constantes.PARAMS_EMPRESA_NIF));
	}

	/**
	 * method get to the company attribute
	 * @return 
	 */
	public Company getEmpresa() {
		return this.oCompany;
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
	// Inspirado em https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
	private static ApplicationServiceProvider singleton = null;

	/**
	 * method get instance, to enter in this class and use their methods
	 * @return ApplicationServiceProvider instance 
	 */
	public static ApplicationServiceProvider getInstance() {
		if (singleton == null) {
			synchronized (ApplicationServiceProvider.class) {
				singleton = new ApplicationServiceProvider();
			}
		}
		return singleton;
	}

}

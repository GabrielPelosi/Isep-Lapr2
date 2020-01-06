package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lapr.project.gpsd.model.Application;

public class RegisterApplication {

    private final Set<Application> listApplication;

	/**
	 * constructor of the RegisterApplication class
	 */
    public RegisterApplication() {
        this.listApplication = new HashSet<>();
    }

	/**
	 * create a new application
	 * @param name String with the name of the candidate
	 * @param nif String with the nif of the candidate
	 * @param pn String with the phone number of the candidate
	 * @param email String with the email of the candidate
	 * @param ah String with the academic habilitation of the candidate
	 * @param ph String with the professional habilitation of the candidate
	 * @param add String with the postal address of the candidate
	 * @param cat List with the categories of the candidate
	 * @return Application instance 
	 */
    public Application newApplication(String name, String nif, String pn, String email, String ah, String ph, PostalAddress add, List<Category> cat) {
        Application a = new Application(name, nif, pn, email, ah, ph, add, cat);
        return a;
    }

	/**
	 * save a given application on the system if it is not already in the list (Set)
	 * @param a Application that will be saved in the system
	 */
    public void registerApplication(Application a) {
        for (Application aa : listApplication) {
            if (aa == a) {
                return;
            }
        }
        listApplication.add(a);
    }

	/**
	 * get one application by the nif
	 * @param nif String with the application nif
	 * @return the application of the given nif, or null if does not exist any application with the given nif
	 */
    public Application getApplicationByNIF(String nif) {
        for (Application a : listApplication) {
            if (a.getNif().equals(nif.trim())) {
                return a;
            }
        }
        return null;
    }

	/**
	 * get the list(Set) of the applications 
	 * @return Set<Applications> applications saved in this class
	 */
    public Set<Application> getListApplication() {
        return listApplication;
    }
    
	/**
	 * get the nifs of all applications saved in this class
	 * @return 
	 */
	public List<String> getListNifApplications(){
		List<String> list = new ArrayList<>();
		for (Application a : listApplication) {
			list.add(a.getNif());
		}
		return list;
	}
    

}

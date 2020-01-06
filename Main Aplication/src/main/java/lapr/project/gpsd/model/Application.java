package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.List;

public class Application {

    String name;
    String nif;
    String pn;
    String email;
    String ah;
    String ph;
    PostalAddress add;
    List<Category> cat;

	/**
	 * Constructor that create the Application instances 
	 * @param name name of the candidate
	 * @param nif nif of the candidate
	 * @param pn phone number of the candidate
	 * @param email email of the candidate
	 * @param ah academic habilitation candidate
	 * @param ph professional habilitation candidate
	 * @param add postal address of the candidate
	 * @param cat category of the candidate
	 */
    public Application(String name, String nif, String pn, String email, String ah, String ph, PostalAddress add, List<Category> cat) {
        this.name = name;
        this.nif = nif;
        this.pn = pn;
        this.email = email;
        this.ah = ah;
        this.ph = ph;
        this.add = add;
        this.cat = new ArrayList<Category>(cat);
    }

	/**
	 * get of the nif attribute
	 * @return String with the nif
	 */
    public String getNif() {
        return nif;
    }

	/**
	 * get of the name attribute
	 * @return String with the name
	 */
    public String getName() {
        return name;
    }

	/**
	 * get of the email attribute
	 * @return String with the email 
	 */
    public String getEmail() {
        return email;
    }

	/**
	 * get of the academic habilitation attribute
	 * @return String with academic habilitation
	 */
    public String getAh() {
        return ah;
    }

	/**
	 * get of the professional habilitation attribute
	 * @return String with professional habilitation
	 */
    public String getPh() {
        return ph;
    }

	/**
	 * get of the postal address instance
	 * @return Postal Address instance
	 */
    public PostalAddress getAdd() {
        return add;
    }

	/**
	 * get the category list
	 * @return List<Category> list with the categories
	 */
    public List<Category> getCat() {
        return cat;
    }

    @Override
    public String toString() {
        return "Application{" + "name=" + name + ", nif=" + nif + ", pn=" + pn + ", email=" + email + ", ah=" + ah + ", ph=" + ph + ", add=" + add + ", cat=" + cat + '}';
    }
    
    

}

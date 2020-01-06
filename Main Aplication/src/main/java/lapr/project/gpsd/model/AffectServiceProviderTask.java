package lapr.project.gpsd.model;

import java.util.TimerTask;
import lapr.project.gpsd.controller.AffectServiceProviderController;

/**
 *
 * @author rickropes
 */
public class AffectServiceProviderTask extends TimerTask{

    private final AffectServiceProviderController arc;
    
    public AffectServiceProviderTask(RegisterRequest rp, RegisterPC rpc, RegisterSP rsp) {
        arc = new AffectServiceProviderController(rp,rpc,rsp);
    }
    
    public void run(){
        System.out.println("VAI");
        arc.affectServiceProvider();
        System.out.println("FINISHED");
    }
    
}

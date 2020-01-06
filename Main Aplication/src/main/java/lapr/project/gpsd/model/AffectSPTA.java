/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

/**
 *
 * @author rickropes
 */
public interface AffectSPTA {
    public String[] getBestServiceProvider(Request rq, ServiceOrder so, RegisterRequest rp, RegisterPC rpc, RegisterSP rsp);
}

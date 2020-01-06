/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.AnalyzeExecutionOrders;
import lapr.project.model.ApplicationServiceProvider;
import lapr.project.model.Company;
import lapr.project.model.ExecutionOrder;
import lapr.project.model.RegisterExecutionOrders;

/**
 *
 * @author Utilizador
 */
public class AnalyzeExecutionOrdersController {

private List<ExecutionOrder> listExecutionOrders;
private final RegisterExecutionOrders register;
private final AnalyzeExecutionOrders analyze;
private final ApplicationServiceProvider app;
private final Company ap;

    public AnalyzeExecutionOrdersController() {
        app=ApplicationServiceProvider.getInstance();
        ap=app.getEmpresa();
        register=ap.getRegisterExecutionOrders();
        analyze=new AnalyzeExecutionOrders();
        listExecutionOrders=new ArrayList<>();
        listExecutionOrders=feed();
        
    }
    
    private List<ExecutionOrder> feed(){
        for (ExecutionOrder a : register.getListExecutionOrders()) {
            listExecutionOrders.add(a);
	}
    return listExecutionOrders;}

    
    public List<ExecutionOrder> analizeAll(){
        listExecutionOrders=analyze.ordenateAll(listExecutionOrders);
    return listExecutionOrders;}
    
    
    public List<ExecutionOrder> analizeName(){
        listExecutionOrders=analyze.ordenateName(listExecutionOrders);
    return listExecutionOrders;}
    
    
    public List<ExecutionOrder> analizeDistance(){
        listExecutionOrders=analyze.ordenateDistance(listExecutionOrders);
    return listExecutionOrders;}
    
    
    public List<ExecutionOrder> analizeCategory(){
        listExecutionOrders=analyze.ordenateCategory(listExecutionOrders);
    return listExecutionOrders;}
    
    
    public List<ExecutionOrder> analizeDate(){
        listExecutionOrders=analyze.ordenateDate(listExecutionOrders);
    return listExecutionOrders;}
    
    
    public List<ExecutionOrder> analizeService(){
        listExecutionOrders=analyze.ordenateService(listExecutionOrders);
    return listExecutionOrders;}
    
    public List<ExecutionOrder> analizeAddress(){
        listExecutionOrders=analyze.ordenateAddress(listExecutionOrders);
    return listExecutionOrders;}
        

}

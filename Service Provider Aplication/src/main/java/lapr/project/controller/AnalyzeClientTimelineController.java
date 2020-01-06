/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.AnalyzeClientTimeline;
import lapr.project.model.ExecutionOrder;

/**
 *
 * @author Utilizador
 */
public class AnalyzeClientTimelineController {
    private AnalyzeClientTimeline time;
    private List<ExecutionOrder> list;

    public AnalyzeClientTimelineController() {
        this.list=new ArrayList<>();
    }
    
    public void AnalyzeOrders(List<ExecutionOrder> clients,String name){
        for(ExecutionOrder a:clients){
            if(name.equals(a.getName())){
                this.list.add(a);
            }
        }
    }
    
    public List<ExecutionOrder> analyzeClient(){
        return  time.ordenateDate(this.list);
    }
}

package lapr.project.gpsd.model;

public class RequestCompleted {

    private Request request;
    private String description = "";
    private String strategy = "";

    public RequestCompleted(Request request) {
        this.request = request;
    }
    
    public RequestCompleted(Request request, String problem, String strategy) {
        this.request = request;
        this.description = description;
        this.strategy = strategy;
    }

    public Request getRequest() {
        return request;
    }

    public String getDescription() {
        return description;
    }

    public String getStrategy() {
        return strategy;
    }
    
    
    @Override
    public String toString() {

        return String.format("%s, %s, %s", request.toString(), description, strategy);
    }
    
    
}

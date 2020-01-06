package lapr.project.gpsd.model;

import java.util.List;

public interface ExternalService {
    public List<ActsOn> getActing(PostalCode pc, int radius);
}

package td.geo.geo_tchad.service;

import org.springframework.stereotype.Service;

@Service 
public class GeoInfoService {
    
    public String getSummary(){
        return "geo_tchad : 23 provinces, 95 départements, 365 communes";
    }
}

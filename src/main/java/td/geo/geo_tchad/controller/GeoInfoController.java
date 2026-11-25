package td.geo.geo_tchad.controller;

import org.springframework.web.bind.annotation.RestController;

import td.geo.geo_tchad.service.GeoInfoService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class GeoInfoController {
    private final GeoInfoService geoInfoService;

    public GeoInfoController(GeoInfoService geoInfoService) {
        this.geoInfoService = geoInfoService;

    }

    @GetMapping("/api/info")
    public String getGeoInfo() {
        return this.geoInfoService.getSummary();
    }
    
}

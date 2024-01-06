package tw.com.ispan.spot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.spot.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressContoller {
	@Autowired
	private AddressService addressService;
	
    @GetMapping("/cities")
    public ResponseEntity<?> findCities() {
    	List<String> cities = addressService.findDistinctCity();
		if(cities==null || cities.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(cities);
		}
	}

    @GetMapping("/roads/{siteId}")
    public ResponseEntity<?> findRoadsBySiteId(@PathVariable(name="siteId") String siteId) {
    	if(siteId==null) {
    		return ResponseEntity.notFound().build();
    	} else {
    		List<String> roads = addressService.findDistinctRoadBySiteId(siteId);
			if(roads==null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok().body(roads);
			}
    	}
	}
    
    @GetMapping("/siteids/{city}")
    public ResponseEntity<?> findSiteIdByCity(@PathVariable(name="city") String city) {
    	if(city==null) {
    		return ResponseEntity.notFound().build();
    	} else {
    		List<String> siteids = addressService.findDistinctSiteIdByCity(city);
			if(siteids==null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok().body(siteids);
			}
    	}
	}
}

package tw.com.ispan.spot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.spot.repository.AddressRepository;

@Service
@Transactional(readOnly = true)
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;
	
	public List<String> findDistinctCity() {
		List<String> cities = addressRepository.findDistinctCity();
		if(cities==null || cities.isEmpty()) {
			return null;
		} else {
			return cities;
		}
	}
	
	public List<String> findDistinctRoadBySiteId(String siteId) {
		if(siteId!=null && siteId.length()!=0) {
			List<String> cities = addressRepository.findDistinctRoadBySiteId(siteId);
			if(cities!=null && !cities.isEmpty()) {
				return cities;
			}
		}
		return null;
	}
	
	public List<String> findDistinctSiteIdByCity(String city) {
		if(city!=null && city.length()!=0) {
			List<String> siteIds = addressRepository.findDistinctSiteIdByCity(city);
			if(siteIds!=null && !siteIds.isEmpty()) {
				return siteIds;
			}
		}
		return null;
	}
}

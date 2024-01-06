package tw.com.ispan.spot.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AddressServiceTests {
	@Autowired
	private AddressService addressService;
	
	@Test
	public void testFindDistinctCity() {
		List<String> cities = addressService.findDistinctCity();
		System.out.println("cities="+cities);
	}
	
	@Test
	public void testFindDistinctRoadBySiteId() {
		List<String> roads = addressService.findDistinctRoadBySiteId("金門縣金城鎮");
		System.out.println("roads="+roads);
	}
	
	@Test
	public void testFindDistinctSiteIdByCity() {
		List<String> siteIds = addressService.findDistinctSiteIdByCity("金門縣");
		System.out.println("siteIds="+siteIds);
	}
}

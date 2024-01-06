package tw.com.ispan.spot.service;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.spot.domain.SpotImages;
import tw.com.ispan.spot.domain.SpotImagesSpot;

@SpringBootTest
public class SpotImagesServiceTests {
	@Autowired
	private SpotImagesService spotImagesService;
	
//	@Test
	public void testFindBySpotId() {
		List<SpotImages> spotImages = spotImagesService.findBySpotId(2);
		if(spotImages!=null) {
			for(SpotImages spotImage : spotImages) {
				System.out.println("spotImage="+spotImage);
			}
		}
	}
	
	@Test
	public void testFindSpotImagesSpot() {
		JSONObject obj = new JSONObject()
				.put("start", 0)
				.put("rows", 10)
				.put("dir", true)
//				.put("sort", "categoryId")
				.put("categoryId", 1)
//				.put("spotTitle", "埕")
//				.put("spotDescription", "熱")
				;
		List<SpotImagesSpot> spotImagesSpots = spotImagesService.findSpotImagesSpot(obj.toString());
		if(spotImagesSpots!=null) {
			for(SpotImagesSpot spotImagesSpot : spotImagesSpots) {
				System.out.println("spotImagesSpot="+spotImagesSpot);
			}
		}
	}
}

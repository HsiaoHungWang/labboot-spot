package tw.com.ispan.spot.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ispan.spot.domain.Categories;
import tw.com.ispan.spot.domain.SpotImages;
import tw.com.ispan.spot.domain.SpotImagesSpot;
import tw.com.ispan.spot.service.CategoriesService;
import tw.com.ispan.spot.service.SpotImagesService;

@RestController
@RequestMapping("/api/spot")
@CrossOrigin()
public class SpotContoller {
	@Autowired
	private CategoriesService categoriesService;

	@Autowired
	private SpotImagesService spotImagesService;

	@GetMapping("/categories")
	public ResponseEntity<?> findCategories() {
		List<Categories> categories = categoriesService.find();
		if (categories == null || categories.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(categories);
		}
	}

	@GetMapping("/spotimages/{spotId}")
	public ResponseEntity<?> findSpotImagesBySpotIdCategories(
			@PathVariable(name = "spotId") Integer spotId) {
		if (spotId == null) {
			return ResponseEntity.notFound().build();
		} else {
			List<SpotImages> spotImages = spotImagesService.findBySpotId(spotId);
			if (spotImages == null) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok().body(spotImages);
			}
		}
	}

	@PostMapping("/spotimages")
	public String findSpotImages(@RequestBody String body) {
		JSONObject responseJson = new JSONObject();

		long count = spotImagesService.countSpotImagesSpot(body);
		responseJson.put("count", count);

		JSONArray array = new JSONArray();
		List<SpotImagesSpot> spotImagesSpots = spotImagesService.findSpotImagesSpot(body);
		if (spotImagesSpots != null && !spotImagesSpots.isEmpty()) {
			for (SpotImagesSpot spotImagesSpot : spotImagesSpots) {
				JSONObject item = new JSONObject()
						.put("spotId", spotImagesSpot.getSpotId())
						.put("categoryId", spotImagesSpot.getCategoryId())
						.put("spotTitle", spotImagesSpot.getSpotTitle())
						.put("spotDescription", spotImagesSpot.getSpotDescription())
						.put("address", spotImagesSpot.getAddress())
						.put("trafficInfo", spotImagesSpot.getTrafficInfo())
						.put("longitude", spotImagesSpot.getLongitude())
						.put("latitude", spotImagesSpot.getLatitude())
						.put("openTime", spotImagesSpot.getOpenTime())
						.put("contactPhone", spotImagesSpot.getContactPhone())
						.put("dateCreated", spotImagesSpot.getDateCreated())
						.put("spotImage", spotImagesSpot.getSpotImage());
				array = array.put(item);
			}
		}

		responseJson.put("list", array);
		return responseJson.toString();
	}
}

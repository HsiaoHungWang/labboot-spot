package tw.com.ispan.spot.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.spot.domain.SpotImages;
import tw.com.ispan.spot.domain.SpotImagesSpot;
import tw.com.ispan.spot.repository.SpotImagesRepository;

@Service
@Transactional(readOnly = true)
public class SpotImagesService {
	@Autowired
	private SpotImagesRepository spotImagesRepository;
	
	public List<SpotImages> findBySpotId(Integer spotId) {
		if(spotId!=null) {
			List<SpotImages> spotImages = spotImagesRepository.findBySpotId(spotId);
			if(spotImages!=null && !spotImages.isEmpty()) {
				return spotImages;
			}
		}
		return null;
	}
	
	public List<SpotImagesSpot> findSpotImagesSpot(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return spotImagesRepository.findSpotImagesSpot(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public long countSpotImagesSpot(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return spotImagesRepository.countSpotImagesSpot(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}

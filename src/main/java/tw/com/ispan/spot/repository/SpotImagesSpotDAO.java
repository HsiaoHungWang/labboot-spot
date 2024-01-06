package tw.com.ispan.spot.repository;

import java.util.List;

import org.json.JSONObject;

import tw.com.ispan.spot.domain.SpotImagesSpot;

public interface SpotImagesSpotDAO {
	
	public abstract long countSpotImagesSpot(JSONObject param);
	public abstract List<SpotImagesSpot> findSpotImagesSpot(JSONObject param);
	
}

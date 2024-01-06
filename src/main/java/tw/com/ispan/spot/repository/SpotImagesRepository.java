package tw.com.ispan.spot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.ispan.spot.domain.SpotImages;

@Repository
public interface SpotImagesRepository
			extends JpaRepository<SpotImages, Integer>, SpotImagesSpotDAO {
	
	@Query("from SpotImages where spotId = :spotid")
	public List<SpotImages> findBySpotId(@Param("spotid") Integer spotId);
	
}

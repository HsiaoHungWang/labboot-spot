package tw.com.ispan.spot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.ispan.spot.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	@Query("select distinct city from Address")
	public List<String> findDistinctCity();
	
	@Query("select distinct siteId from Address where city=:city")
	public List<String> findDistinctSiteIdByCity(@Param("city") String city);
	
	@Query("select distinct road from Address where siteId=:siteId")
	public List<String> findDistinctRoadBySiteId(@Param("siteId") String siteId);

}

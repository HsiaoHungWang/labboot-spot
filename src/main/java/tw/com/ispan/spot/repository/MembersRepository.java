package tw.com.ispan.spot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.com.ispan.spot.domain.Members;

@Repository
public interface MembersRepository extends JpaRepository<Members, Integer> {
	
	@Query("from Members where name = :name")
	public List<Members> findByName(@Param("name") String name);
	
}

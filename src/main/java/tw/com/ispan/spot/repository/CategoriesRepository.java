package tw.com.ispan.spot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ispan.spot.domain.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

}

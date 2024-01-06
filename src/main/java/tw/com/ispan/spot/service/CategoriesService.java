package tw.com.ispan.spot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ispan.spot.domain.Categories;
import tw.com.ispan.spot.repository.CategoriesRepository;

@Service
@Transactional(readOnly = true)
public class CategoriesService {
	@Autowired
	private CategoriesRepository categoriesRepository;
	
	public List<Categories> find() {
		List<Categories> categories = categoriesRepository.findAll();
		if(categories!=null && !categories.isEmpty()) {
			return categories;
		} else {
			return null;
		}
	}
}

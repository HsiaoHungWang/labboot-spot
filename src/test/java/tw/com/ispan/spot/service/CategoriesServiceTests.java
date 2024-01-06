package tw.com.ispan.spot.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.spot.domain.Categories;

@SpringBootTest
public class CategoriesServiceTests {
	@Autowired
	private CategoriesService categoriesService;
	
	@Test
	public void testFind() {
		List<Categories> categories = categoriesService.find();
		System.out.println("categories="+categories);
	}
}

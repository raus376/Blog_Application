package app.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.blog.repositorys.CategoryRepository;
import app.blog.repositorys.UserRepository;

@SpringBootTest
class BlogApplicationResTfulApiApplicationTests {
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private CategoryRepository cRepo;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void repoTest() {
		String name=this.uRepo.getClass().getName();
		System.out.println(name);
		System.out.println(this.uRepo.getClass().getPackageName());
	}

}

package app.blog.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.blog.exceptions.CategoryException;
import app.blog.exceptions.PostException;
import app.blog.exceptions.UserException;
import app.blog.models.Category;
import app.blog.models.Post;
import app.blog.models.User;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer>{

	public List<Post> findByUser(User user) throws UserException;
	
	public List<Post> findByCategory(Category category) throws CategoryException;
	
	List<Post> findByTitleContaining(String title) throws PostException;
}

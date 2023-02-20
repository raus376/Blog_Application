package app.blog.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.blog.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>{

}

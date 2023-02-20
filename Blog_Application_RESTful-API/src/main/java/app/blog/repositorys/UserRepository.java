package app.blog.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.blog.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

}

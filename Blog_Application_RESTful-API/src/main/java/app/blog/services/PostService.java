package app.blog.services;

import java.util.List;

import app.blog.exceptions.CategoryException;
import app.blog.exceptions.PostException;
import app.blog.exceptions.UserException;
import app.blog.models.Post;
import app.blog.payloads.PostDTO;
import app.blog.payloads.PostResponse;

public interface PostService {
	
	public PostDTO createPost(PostDTO post,Integer userId,Integer categoryId) throws PostException,UserException,CategoryException;
	
	public PostDTO updatePost(PostDTO post,Integer postId) throws PostException;
	
	public String deletePost(Integer postId) throws PostException;
	
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sorty) throws PostException;
	
	public PostDTO getPostByPostId(Integer pId) throws PostException;
	
	public List<PostDTO> getPostsByCategory(Integer categoryId) throws CategoryException;

	public List<PostDTO> getpostByUser(Integer userId) throws UserException;
	
	public List<PostDTO> searchPosts(String keyword) throws PostException;
}

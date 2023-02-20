package app.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.blog.exceptions.CategoryException;
import app.blog.exceptions.PostException;
import app.blog.exceptions.UserException;
import app.blog.payloads.PostDTO;
import app.blog.payloads.PostResponse;
import app.blog.services.CategoryService;
import app.blog.services.PostService;
import app.blog.services.UserService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService pService;
	
	@Autowired
	private CategoryService cService;
	
	@Autowired
	private UserService uService;
	

	
	@PostMapping("/createPost/{uId}/{cId}")
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO dto,@PathVariable Integer uId,@PathVariable Integer cId) throws PostException, UserException, CategoryException{
		
		PostDTO newDto=pService.createPost(dto, uId, cId);
		
		return new ResponseEntity<>(newDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllPostByCategoryId{cId}")
	public ResponseEntity<List<PostDTO>> getAllPostByCategoryId(@PathVariable Integer cId) throws CategoryException{
		
		List<PostDTO> newDto=pService.getPostsByCategory(cId);
		
		return new ResponseEntity<>(newDto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllPostByUserId{uId}")
	public ResponseEntity<List<PostDTO>> getAllPostByUserId(@PathVariable Integer uId) throws UserException{
		
		List<PostDTO> newDto=pService.getpostByUser(uId);
		
		return new ResponseEntity<>(newDto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllPost/{pageNumber}/{pageSize}")
	public ResponseEntity<PostResponse> getAllPost(
			@PathVariable Integer pageNumber,@PathVariable Integer pageSize,
			@RequestParam(value="sortBy",defaultValue="postId",required=false) String sortBy
//			@RequestParam(value="pageNumber",defaultValue="1",required=false) Integer pageNumber,
//			@RequestParam(value="pageNumber",defaultValue="5",required=false) Integer pageSize
			) throws  PostException{
		
		PostResponse newDto=pService.getAllPost(pageNumber, pageSize,sortBy);
		
		return new ResponseEntity<>(newDto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllPostByPostId/{pId}")
	public ResponseEntity<PostDTO> getAllPostByPostId(@PathVariable Integer pId) throws  PostException{
		
		PostDTO newDto=pService.getPostByPostId(pId);
		
		return new ResponseEntity<>(newDto,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deletePostByPostId/{pId}")
	public ResponseEntity<String> deletePostByPostId(@PathVariable Integer pId) throws PostException{
		
		String str=pService.deletePost(pId);
		
		return new ResponseEntity<>(str,HttpStatus.OK);
	}
	
	@PutMapping("/updatePost/{pId}")
	public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO dto,@PathVariable Integer pId) throws PostException{
		
	PostDTO dtos=	pService.updatePost(dto, pId);
	
	return new ResponseEntity<>(dtos,HttpStatus.OK);
	}
	
	//searching
	
	

}

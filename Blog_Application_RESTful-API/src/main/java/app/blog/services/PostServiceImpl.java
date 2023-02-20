package app.blog.services;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import app.blog.exceptions.CategoryException;
import app.blog.exceptions.PostException;
import app.blog.exceptions.UserException;
import app.blog.models.Category;
import app.blog.models.Post;
import app.blog.models.User;
import app.blog.payloads.PostDTO;
import app.blog.payloads.PostResponse;
import app.blog.repositorys.CategoryRepository;
import app.blog.repositorys.PostRepository;
import app.blog.repositorys.UserRepository;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository pRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private CategoryRepository cRepo;
	
	@Override
	public PostDTO createPost(PostDTO dto, Integer userId, Integer categoryId)
			throws PostException, UserException, CategoryException {
		 
		User user=uRepo.findById(userId).orElseThrow(()-> new UserException("User not found with userId"));
		
		Category category=cRepo.findById(categoryId).orElseThrow(()->new CategoryException("Category Not found"));
		
//		dto.setCategory(category);
//		dto.setUser(user);
		
		Post post=mapper.map(dto, Post.class);
		post.setCategory(category);
		post.setUser(user);
//		post.setImageName("default.png");
		post.setDate(LocalDateTime.now());
		Post newPost=pRepo.save(post);
		
		return this.mapper.map(newPost, PostDTO.class);
		
		
		
	}
	

	@Override
	public PostDTO updatePost(PostDTO dto,Integer pId) throws PostException {
	
		Post opt=pRepo.findById(pId).orElseThrow(()->new PostException("post not found..."));
		
		opt.setContent(dto.getContent());
		opt.setImageName(dto.getImageName());
		opt.setTitle(dto.getTitle());
		opt.setDate(LocalDateTime.now());
		
		Post p=pRepo.save(opt);
	
		return mapper.map(p, PostDTO.class);
		
	}

	@Override
	public String deletePost(Integer postId) throws PostException {
		
		Post post=pRepo.findById(postId).orElseThrow(()->new PostException("Post not found..."));
		
		pRepo.delete(post);
		
		return  "Deleted Successfully...!";
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy) throws PostException {
		 
       org.springframework.data.domain.Pageable p=PageRequest.of(pageNumber,pageSize,Sort.by(sortBy));
       
       Page<Post> list=pRepo.findAll(p);
       
     List<Post> posts=  list.getContent();
       
	List<PostDTO> dtos=  posts.stream().map(pp->mapper.map(pp, PostDTO.class)).toList();
	
	PostResponse pr=new PostResponse();
	
	pr.setContent(dtos);
	pr.setPageNumber(list.getNumber());
	pr.setPageSize(list.getSize());
	pr.setTotalPages(list.getTotalPages());
	pr.setTotalRecords(list.getTotalElements());
	pr.setLastPage(list.isLast());
	
	return pr;
	}

	@Override
	public PostDTO getPostByPostId(Integer pId) throws PostException {
		
		Post post=pRepo.findById(pId).orElseThrow(()->new PostException("Post not found..."));
	
		return mapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> getPostsByCategory(Integer categoryId) throws CategoryException {
		
	Category Category=	cRepo.findById(categoryId).orElseThrow(()->new CategoryException("Category not found"));
	
	List<Post> list= pRepo.findByCategory(Category);
	
	List<PostDTO> dto=list.stream().map(p->mapper.map(p, PostDTO.class)).toList();
	
   if(dto.size()>0) return dto;
   else throw new CategoryException("Post not available for this post...! be the first one to post.. :) ");
  
	}

	@Override
	public List<PostDTO> getpostByUser(Integer userId) throws UserException {
		
	User user=	uRepo.findById(userId).orElseThrow(()->new UserException("User not present"));
	
    List<Post> list=   pRepo.findByUser(user);

    List<PostDTO> dto=list.stream().map(p->mapper.map(p, PostDTO.class)).toList();
	
    if(dto.size()>0) return dto;
    else throw new UserException("Post not present ...! please post.. :) ");
	}


	@Override
	public List<PostDTO> searchPosts(String keyword) throws PostException {

		List<Post>  list=pRepo.findByTitleContaining(keyword);

		if(list.size()>0) {
			List<PostDTO> dto=list.stream().map((p)->this.mapper.map(list, PostDTO.class)).toList();
			return dto;
		}
		else
			throw new PostException("Post not find with KeyWord: "+keyword);
	}
	

	

	

}

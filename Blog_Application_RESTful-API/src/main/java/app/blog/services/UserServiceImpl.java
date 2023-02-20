package app.blog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.blog.exceptions.UserException;
import app.blog.models.User;
import app.blog.payloads.UserDTO;
import app.blog.repositorys.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public UserDTO registerUser(User user) {
		
		User u=uRepo.save(user);
		UserDTO uDTO=this.userToUserDTO(u);
		
		return uDTO;
	}

	@Override
	public UserDTO updateUser(UserDTO user, Integer userId) throws UserException {
		
		Optional<User> opt=uRepo.findById(userId);
		
		if(opt.isPresent()) {
			
		    User u= opt.get();
		    u.setAbout(user.getAbout());
		    u.setEmail(user.getEmail());
		    u.setName(user.getName());
		    u.setPassword(user.getPassword());
		   
		   User uu= uRepo.save(u);
		   UserDTO dto=this.userToUserDTO(uu);
		     
		     return dto;
		}
		else
			throw new UserException("User not found with userId: "+userId);
	}

	@Override
	public UserDTO getUserById(Integer userId) throws UserException {
		
	 Optional<User> opt=uRepo.findById(userId);
	 
	 if(opt.isPresent()) {
		User user= opt.get();
		UserDTO userdto=this.userToUserDTO(user);
		
		return userdto;
	 }
	 else
		 throw new UserException("User not found with userIdL: "+userId);
	}

	@Override
	public List<UserDTO> getAllUser() throws UserException {
		
		List<User> userList=uRepo.findAll();
		if(userList.size()>0) {
			List<UserDTO> userdtoList=new ArrayList();
		 	userdtoList=userList.stream().map(e->this.userToUserDTO(e)).toList();
			return userdtoList;
		}
		else
			throw new UserException("Not found any User");
	}

	@Override
	public UserDTO deleteUser(Integer userId) throws UserException {
		
		Optional<User> opt=uRepo.findById(userId);
		if(opt.isPresent()) {
			UserDTO userdto=this.userToUserDTO(opt.get());
			uRepo.delete(opt.get());
			return userdto;
		}
		else 
			throw new UserException("User not found with userId: "+userId);
	}
	
	public User dtoToUser(UserDTO userdto) {
		
		User user=mapper.map(userdto, User.class);
//		User user =new User();
//		user.setId(userdto.getId());
//		user.setEmail(userdto.getEmail());
//		user.setAbout(userdto.getAbout());
//		user.setPassword(userdto.getPassword());
//		user.setName(userdto.getName());
		
		return user;
	}
	
	public UserDTO userToUserDTO(User user) {
		
		UserDTO userdto=mapper.map(user, UserDTO.class);
//		UserDTO userdto=new UserDTO();
//		userdto.setAbout(user.getAbout());
//		userdto.setEmail(user.getEmail());
//		userdto.setPassword(user.getPassword());
//		userdto.setName(user.getName());
//		userdto.setId(user.getId());
//  
		return userdto;
		
	}

}

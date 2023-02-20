package app.blog.services;

import java.util.List;

import app.blog.exceptions.UserException;
import app.blog.models.User;
import app.blog.payloads.UserDTO;

public interface UserService {
	
	public UserDTO registerUser(User user);
	
	public UserDTO updateUser(UserDTO user,Integer userId) throws UserException;

	public UserDTO getUserById(Integer userId) throws UserException;
	
	public List<UserDTO> getAllUser() throws UserException;
	
	public UserDTO deleteUser(Integer userId) throws UserException;
}

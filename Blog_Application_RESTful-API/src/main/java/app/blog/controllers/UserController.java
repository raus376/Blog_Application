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
import org.springframework.web.bind.annotation.RestController;

import app.blog.exceptions.UserException;
import app.blog.models.User;
import app.blog.payloads.UserDTO;
import app.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService uService;
	
	@PostMapping("register")
	public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody User user){
		UserDTO dto= uService.registerUser(user);
		
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{uId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO dto,@PathVariable Integer uId) throws UserException{
		
		UserDTO udto=uService.updateUser(dto,uId);
		
		return new ResponseEntity<>(udto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{uId}")
	public ResponseEntity<UserDTO> deleteUserById(@PathVariable Integer uId) throws UserException{
		UserDTO dto=uService.deleteUser(uId);
		
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@GetMapping("/allUser")
	public ResponseEntity<List<UserDTO>> listAllUser() throws UserException{
		List<UserDTO> list=uService.getAllUser();
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/getUser/{uId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Integer uId) throws UserException{
		UserDTO dto=uService.getUserById(uId);
		
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	

}

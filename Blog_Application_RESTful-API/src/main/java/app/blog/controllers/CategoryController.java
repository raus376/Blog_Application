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

import app.blog.exceptions.CategoryException;
import app.blog.models.Category;
import app.blog.payloads.CategoryDTO;
import app.blog.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService cService;
	
	@PostMapping("/registerCategory")
	public ResponseEntity<CategoryDTO> registerCategor(@Valid @RequestBody Category category) throws CategoryException{
		
		CategoryDTO dto=cService.registerCategory(category);
		
		return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateCategory/{cId}")
	public ResponseEntity<CategoryDTO> updateCategor(@Valid @RequestBody CategoryDTO category,@PathVariable Integer cId) throws CategoryException{
		
		CategoryDTO dto=cService.updateCategory(category, cId);
		
		return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getCategory/{cId}")
	public ResponseEntity<CategoryDTO> getCategorByCategoryId(@PathVariable Integer cId) throws CategoryException{
		
		CategoryDTO dto=cService.getCategory(cId);
		
		return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllCategory")
	public ResponseEntity<List<CategoryDTO>> getListOfCategor() throws CategoryException{
		
		List<CategoryDTO> dto=cService.getAllCategory();
		
		return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteCategory/{cId}")
	public ResponseEntity<CategoryDTO> deleteCategorByCategoryId(@PathVariable Integer cId) throws CategoryException{
		
		CategoryDTO dto=cService.deleteCategory(cId);
		
		return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
	}
	
	
	
}

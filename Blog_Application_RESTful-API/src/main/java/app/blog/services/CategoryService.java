package app.blog.services;

import java.util.List;

import app.blog.exceptions.CategoryException;
import app.blog.models.Category;
import app.blog.payloads.CategoryDTO;

public interface CategoryService {
	
	public CategoryDTO registerCategory(Category category) throws CategoryException;

	public CategoryDTO updateCategory(CategoryDTO dto,Integer cId) throws CategoryException;
	
	public CategoryDTO deleteCategory(Integer cId) throws CategoryException;
	
	public CategoryDTO getCategory(Integer cId) throws CategoryException;
	
	public List<CategoryDTO> getAllCategory() throws CategoryException;
}

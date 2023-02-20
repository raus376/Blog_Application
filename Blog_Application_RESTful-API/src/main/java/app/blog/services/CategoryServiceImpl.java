package app.blog.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.blog.exceptions.CategoryException;
import app.blog.models.Category;
import app.blog.payloads.CategoryDTO;
import app.blog.repositorys.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository cRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public CategoryDTO registerCategory(Category category) throws CategoryException {
		
		if(category!=null) {
			
			Category c=cRepo.save(category);
			CategoryDTO dto=this.categoryToDTO(c);
			
			return dto;
		}
		else
			throw new CategoryException("Category should not be null");
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO dto, Integer cId) throws CategoryException {
		
		Optional<Category> opt=cRepo.findById(cId);
		
		if(opt.isPresent()) {
			
			Category category=opt.get();
			category.setCategoryDescription(dto.getCategoryDescription());
			category.setCategoryId(cId);
			category.setCategoryTitle(dto.getCategoryTitle());
			
			Category c=cRepo.save(category);
			CategoryDTO dto1=this.categoryToDTO(c);
			
			return dto1;
			
		}
		else
			throw new CategoryException("Category not found with CategoryId: "+cId);
	}

	@Override
	public CategoryDTO deleteCategory(Integer cId) throws CategoryException {
		
		Optional<Category> opt=cRepo.findById(cId);
		if(opt.isPresent()) {
			Category category=opt.get();
			CategoryDTO dto=this.categoryToDTO(category);
			cRepo.delete(category);
			
			return dto;
		}
		else
			throw new CategoryException("Category not found with CategoryId: "+cId);
	}

	@Override
	public CategoryDTO getCategory(Integer cId) throws CategoryException {
		Optional<Category> opt=cRepo.findById(cId);
		if(opt.isPresent()) {
			Category category=opt.get();
			CategoryDTO dto=this.categoryToDTO(category);
			
			return dto;
		}
		else
			throw new CategoryException("Category not found with CategoryId: "+cId);
	}


	@Override
	public List<CategoryDTO> getAllCategory() throws CategoryException {
		
		List<Category> list=cRepo.findAll();
		
		if(list.size()>0) {
		List<CategoryDTO> dtoList=	list.stream().map(s->this.categoryToDTO(s)).toList();
		
		return dtoList;
		}
		else
			throw new CategoryException("Data not present In database... ");
	}
	
	public CategoryDTO categoryToDTO(Category cateogry) {
		
		CategoryDTO dto=mapper.map(cateogry, CategoryDTO.class);
		
		return dto;
	}
	
	public Category dtoToCategory(CategoryDTO dto) {
		Category category = mapper.map(dto, Category.class);
		
		return category;
	}

}

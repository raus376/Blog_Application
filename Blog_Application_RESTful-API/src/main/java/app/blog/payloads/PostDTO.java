package app.blog.payloads;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import app.blog.models.Category;
import app.blog.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
	
	@Transient
	private Integer postId;

	@NotEmpty
	@Size(min=4,max=100,message="Size should be in between 4 and 100 character")
	private String title;
	
	@NotEmpty
	@Size(min=4,max=100,message="Size should be in between 4 and 100 character")
	private String content;
	
	@NotEmpty
	private String imageName;

	@Transient
	private LocalDateTime date;

	@Transient
	private CategoryDTO category;

	@Transient
	private UserDTO user;

}

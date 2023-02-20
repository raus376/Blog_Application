package app.blog.payloads;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDTO {
	
	private Integer categoryId;
	
	@NotEmpty
	@Column(name="title")
	@Size(min=4,max=14,message="Size should be in between 4 and 14 Characters")
	private String categoryTitle;
	
	@NotEmpty
	@Column(name="description")
	@Size(min=4,max=14,message="Size should be in between 4 and 14 Characters")
	private String categoryDescription;

}

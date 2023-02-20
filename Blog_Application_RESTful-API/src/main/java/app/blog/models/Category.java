package app.blog.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="categories")
@Data
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer categoryId;
	
	@NotEmpty
	@Column(name="title")
	@Size(min=4,max=14,message="Size should be in between 4 and 14 Characters")
	private String categoryTitle;
	
	@NotEmpty
	@Column(name="description")
	@Size(min=4,max=14,message="Size should be in between 4 and 14 Characters")
	private String categoryDescription;
	
	
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Post> posts = new HashSet<>();

	
}

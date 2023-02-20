package app.blog.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer postId;
	
	@Column(name="post_title")
	private String title;
	
	private String content;
	
	private String imageName;
	
	private LocalDateTime date;
	
	@ManyToOne
	@JoinColumn(name="category_Id")
	private Category category;
	

	@ManyToOne
	private User user;
	

}

package app.blog.payloads;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
	
	private Integer id;
	
	@NotEmpty
	@Size(min=4,max=14,message="Size should be in between 4 and 14 character")
	@Column(name="User_name",nullable=false,length=100)
	private String name;
	
	@Email(message="Enter valid Email address")
	private String Email;
	
    @NotEmpty
    @Size(min=4,max=14,message="Size should be in between 4 and 14 character")
    private String password;
    
    @NotEmpty
    private String about;


}

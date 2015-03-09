package demo;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class Moderator {
	private Integer id;
	private String name;
	@NotBlank
	@Email(message="Please enter a valid email address to continue...")
	private String email;
	@NotEmpty
	@NotBlank
	private String password;
	private String created_at;
	
	public void setDetails(Moderator moderator, Integer userId) {
		this.setId(userId);
		this.setName(moderator.getName());
		this.setEmail(moderator.getEmail());
		this.setPassword(moderator.getPassword());
		this.setCreated_at(generateTimestamp());
	}

	private String generateTimestamp() {		 
		return (new Timestamp(new java.util.Date().getTime())).toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
}

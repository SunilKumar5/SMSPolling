package demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;



public class Moderator {

	private int id;
	

	private String name;
	@Email(message="not valid email")
	@NotEmpty(message="email cannot be empty")
	private String email;
	@NotNull(message="password cannot be empty")
	private String password;
	Date date=new Date();
	private String created_at;
	Controller con = new Controller();
	 public String getCreated_at() {
		return created_at;
	}
	 @SuppressWarnings("deprecation")
	public String setCreated_at() {
		return date.toLocaleString();
	}
	 
	ArrayList<Poll> pollobj = new ArrayList<Poll>();
	
	 
//	 public void setArraypollobj(Poll pollobject){
//		 this.pollobj.add(pollobject);
//	 }
//	
	
	public int getId() {
		return id;
	}
	public int setId() {
	//	System.out.println(con.count++);
		return con.count++;
	}
	@ValidateOnExecution
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ValidateOnExecution
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
	public void setdetails(Moderator moderator)
	{
		this.id= this.setId();
		this.name=moderator.name;
		this.email=moderator.email;
		this.password=moderator.password;
		this.created_at=this.setCreated_at();
	}
	public void setDetails_update(Moderator moderator,int s)
	{
		this.id = s;
		this.name=newname;
		this.email=moderator.email;
		this.password=moderator.password;
		this.created_at=this.setCreated_at();
		
	}
	
	
	private String newname;
	public void copyname(String name){
		newname = name;
	}
	
}

package demo;

import java.math.BigDecimal; 
 
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;



//import com.fasterxml.jackson.annotation.JsonView;

//import org.springframework.format.annotation.String;

//import 

/**
 * @author sunil
 *
 */
@SuppressWarnings("unused")
public class PollProxy {
	protected String pollid;
	protected String question;
	Date date=new Date();
	
	protected String started_at;
	protected String expired_at;
	protected String choice[];
	//ArrayList<PollWithResult> pollresult = new ArrayList<PollWithResult>();
public String[] getChoice() {
		return Arrays.copyOf(choice,choice.length);
	}



	public void setChoice(String[] choice) {
		this.choice = Arrays.copyOf(choice, choice.length);
	}


	public String getpollid() {
		return pollid;
	}

	
	
	public void setpollid(String pollid) {
	   this.pollid=pollid;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getStarted_at() {
		return started_at;
	}
	public void setStarted_at(String started_at) {
		this.started_at = started_at;
	}
	public String getExpired_at() {
		return expired_at;
	}
	public void setExpired_at(String expired_at) {
		this.expired_at = expired_at;
	}
	
//public String[] getChoice() {
//		return choice;
//	}
//	public String[] setChoice(String[] choice) {
//		for(int j=0;j < choice.length;j++){
//		this.choice[j] = choice[j];
//          	
//		}
//		return this.choice;
//	}
//	

	public void setFields(Poll poll, String pollid)
	{
		this.choice = poll.getChoice();
		this.pollid =pollid;
		this.expired_at = poll.expired_at;
		this.started_at = poll.started_at;
		this.question = poll.question;
		//this.setChoice(poll.choice);
	}
	
	
	


	
	
	
}


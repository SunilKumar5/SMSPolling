package demo;

import java.math.BigDecimal;  
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;





import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;


//import com.fasterxml.jackson.annotation.JsonView;

//import org.springframework.format.annotation.String;

//import 

/**
 * @author sunil
 *
 */
public class Poll {
	protected String pollid;
	@NotNull(message="routing number cannot be empty")
	protected String question;
	Date date=new Date();
	@NotNull(message="routing number cannot be empty")
	protected String started_at;
	@NotNull(message="routing number cannot be empty")
	protected String expired_at;
	@NotNull(message="routing number cannot be empty")
	protected String choice[];
    private int results[]= {0,0};
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

	
	
	public String setpollid() {
	SecureRandom randnum = new SecureRandom(); 
	this.pollid =new BigInteger(28,randnum).toString(36);
	return pollid;
	}
	@ValidateOnExecution
	public String getQuestion() {
		return question;
	}
	@ValidateOnExecution
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

	public void setFields(Poll poll)
	{
		this.choice = poll.getChoice();
		this.pollid =poll.setpollid();
		this.expired_at = poll.expired_at;
		this.started_at = poll.started_at;
		this.question = poll.question;
		//this.setChoice(poll.choice);
	}
	
	public void setresult(int index){
		if(index==1){
			System.out.println(results[1]);
			int x=results[1];
			System.out.println(x);
			results[1]=x+1;
			System.out.println(results[1]);
		}
		else if(index ==0){
			System.out.println(results[0]);
			int x=results[0];
			System.out.println(results[0]);
			results[0]=x+1;
			System.out.println(results[0]);
		}
		}
	public void setresultdetails(Poll poll){

		this.choice = poll.getChoice();
		this.pollid =poll.setpollid();
		this.expired_at = poll.expired_at;
		this.started_at = poll.started_at;
		this.question = poll.question;
		this.results = poll.getResults();
		
	}



	public int[] getResults() {
		return Arrays.copyOf(results,results.length);
	}



	public void setResults(int[] results) {
		this.results = results;
	}



	
	
	
}

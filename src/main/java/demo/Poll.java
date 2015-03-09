package demo;

import java.util.HashMap;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class Poll {
	private String id;
	@NotBlank
	private String question;
	@NotBlank
	private String started_at;
	@NotBlank
	private String expired_at;
	@NotEmpty
	private String[] choice;
	
	private static Integer tempId = 10043;
	
	private HashMap<String, Poll> polls = new HashMap<String, Poll>();
	
	public void createPoll(Poll poll) {
		this.setId(generatePollId());
		this.setQuestion(poll.getQuestion());
		this.setStarted_at(poll.getStarted_at());
		this.setExpired_at(poll.getExpired_at());
		this.setChoice(poll.getChoice());
		polls.put(this.getId(), poll);
	}
	
	public Poll getPolls(String id) {
		return polls.get(id);
	}

	public String generatePollId(){
		tempId++;
		return Integer.toString(tempId,36); 
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String[] getChoice() {
		return choice;
	}

	public void setChoice(String[] choice) {
		this.choice = choice;
	}
}

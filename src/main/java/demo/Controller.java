package demo;

import java.util.Collection;
import java.util.HashMap;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value="/api/v1/*")
public class Controller {
	private HashMap<Integer,Moderator> users = new HashMap<Integer,Moderator>();
	private HashMap<String,Poll> pollList = new HashMap<String,Poll>();
	private HashMap<Integer,HashMap<String,Poll>> userPolls = new HashMap<Integer, HashMap<String,Poll>>();
	private static Integer userId=1000;
	private HashMap<String,Poll> pollWithOutResult = new HashMap<String,Poll>();
	private HashMap<String,PollProxy> pollWithResult = new HashMap<String,PollProxy>();
	
	// add moderator
	@RequestMapping(value = "/moderators", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Moderator createModerator(@Valid @RequestBody Moderator moderator){
		generateUserId();
		moderator.setDetails(moderator, userId);
		users.put(userId, moderator);
		return moderator;
	}

	// GET moderator for id
	@RequestMapping(value="/moderators/{id}" , method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Moderator getModerator(@PathVariable Integer id){
		return users.get(id);
	}
	
	// update moderator details
	@RequestMapping(value="/moderators/{id}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public Moderator updateModerator(@PathVariable Integer id, @Valid @RequestBody Moderator moderator){
		Moderator temp = users.get(id);
		temp.setEmail(moderator.getEmail());
		temp.setPassword(moderator.getPassword());
		users.put(id, temp);
		return users.get(id);
	}
	
	// add poll for moderator id
	@RequestMapping(value="/moderators/{id}/polls", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Poll createPoll(@PathVariable Integer id, @Valid @RequestBody Poll poll){
		poll.createPoll(poll);
		pollList.put(poll.getId(),poll);
		userPolls.put(id,pollList);
		pollWithOutResult.put(poll.getId(), poll);
		this.addPollResult(poll);
		return poll;
	}
	
	// get poll for id
	@RequestMapping(value="/polls/{poll_id}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Poll getPoll(@PathVariable String poll_id){
		return pollWithOutResult.get(poll_id);
	}
	
	// view polls with result
	@RequestMapping(value="/moderators/{moderator_id}/polls/{poll_id}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public PollProxy getPollWithResult(@PathVariable Integer moderator_id, @PathVariable String poll_id){
		return pollWithResult.get(poll_id);
	}
	
	// get all polls for moderator id
	@RequestMapping(value="/moderators/{moderator_id}/polls", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Collection<PollProxy> getAllPollsForModerator(@PathVariable Integer moderator_id){
//		return userPolls.get(moderator_id).values();
		return pollWithResult.values();
	}
	
	// Deleting a poll
	@RequestMapping(value="/moderators/{moderator_id}/polls/{poll_id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePoll(@PathVariable Integer moderator_id, @PathVariable String poll_id){
		userPolls.get(moderator_id).remove(poll_id);
		pollList.remove(poll_id);
		pollWithOutResult.remove(poll_id);
		pollWithResult.remove(poll_id);
	}
	
	// voting...
	@RequestMapping(value="/polls/{poll_id}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void vote(@PathVariable String poll_id, @RequestParam("choice") int choice_index){
		pollWithResult.get(poll_id).updateVote(choice_index);
	}
	
	// method to generate unique moderator id
	private void generateUserId() {
		userId++;
	}
	
	private void addPollResult(Poll obj){
		PollProxy objResult = new PollProxy();
		objResult.setId(obj.getId());
		objResult.setQuestion(obj.getQuestion());
		objResult.setStarted_at(obj.getStarted_at());
		objResult.setExpired_at(obj.getExpired_at());
		objResult.setChoice(obj.getChoice());
		int[] results = {0,0};
		objResult.setResults(results);
		pollWithResult.put(obj.getId(), objResult);
	}
}

package demo;

import java.io.IOException;
import java.util.ArrayList;  

import javax.validation.Valid;





import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

//import scala.annotation.meta.setter;
@RequestMapping(value = "/api/v1/*")
@RestController
public class Controller {
	
public static int count=0;
//private final ObjectMapper objectMapper = new ObjectMapper();
//public static int pollcounter =1;
	ArrayList<Moderator> modarray = new ArrayList<Moderator>();
   ArrayList<Poll> pollarray2 = new ArrayList<Poll>();
   
   
	@RequestMapping(value = "/moderators", method = RequestMethod.POST)
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	public Moderator createModerator(@Valid @RequestBody Moderator moderator) {
		System.out.println("in method");
		Moderator m = new Moderator();
		
		m.setdetails(moderator);
		//System.out.println(m.setId());
		System.out.println(m.getId()+m.getEmail());
		
		
		modarray.add(m);
		
		return m;
	}

	@RequestMapping(value = "/moderators/{id}", method = RequestMethod.GET)
	public Moderator moderators(@PathVariable int id) {

		System.out.println(modarray.size());
		// Moderator mod= new Moderator();
		for (int j = 0; j < modarray.size(); j++) {

			if (id == modarray.get(j).getId()) {
				// mod = modarray.get(j);
				// System.out.println(mod.getId());
				System.out.println("in loop");
				return modarray.get(j);

			}

		}
		return null;
	}

	@RequestMapping(value = "/moderators/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public Moderator moderators(@PathVariable int id,
			@Valid @RequestBody Moderator moderator) {
		Moderator mod = new Moderator();
		for (int j = 0; j < modarray.size(); j++) {
			mod = modarray.get(j);
			System.out.println(mod.getId());
			if (id ==modarray.get(j).getId()) {
				mod.copyname(mod.getName());
				mod.setDetails_update(moderator,id);
				modarray.set(j, mod);
				return mod;
			}
		}
		return null;
	}
	
	

  @RequestMapping(value = "/moderators/{id}/polls", method = RequestMethod.POST)
   @ResponseStatus(HttpStatus.CREATED)
   public PollProxy createpoll(@PathVariable int id,@Valid  @RequestBody Poll polluser) {
	System.out.println("in post");
 Poll pollobj1 = new Poll();
 PollProxy proxy=new PollProxy();
Moderator mod = new Moderator();
		for(int i=0;i<modarray.size();i++)
		{
			mod =modarray.get(i);
			if(id == mod.getId())
			{
	//			System.out.println(pollobj1.getpollid());
				pollobj1.setFields(polluser);
//				System.out.println(pollobj1.getpollid());
//				pollarray2.add(pollobj1);
//				
				modarray.get(i).pollobj.add(pollobj1);
				pollarray2.add(pollobj1);
				System.out.println(pollobj1.getpollid());
				
				proxy.setFields(polluser,pollobj1.pollid);
				
				
				
				System.out.println(pollarray2.size());
				return proxy;
				//System.out.println(modarray.get(i).pollobj.get(0).getId());
			}
			
			
		}
		return null;
	}
	
	
	@RequestMapping(value ="/polls/{pollid}", method =RequestMethod.GET)
	public PollProxy viewPoll(@PathVariable String pollid) throws IOException{
	
		//ObjectWriter objectWriter = objectMapper.viewWriter(Views.Public.class);
        //return objectWriter.writeValueAsString();
		PollProxy proxy=new PollProxy();
		Poll poll = new Poll();

		System.out.println(pollarray2.size());
		// Moderator mod= new Moderator();
		for (int j = 0; j < pollarray2.size(); j++) {
                 System.out.println(j);
			if (pollid.equals(pollarray2.get(j).getpollid())) {
				
				proxy.setFields(pollarray2.get(j), pollid);
				// mod = modarray.get(j);
				System.out.println("in loop");
				// System.out.println(mod.getId());
				//return pollarray2.get(j);
				return proxy;
				//return objectWriter.w((pollarray2.get(j)));

			}

		}
		return null;
		
		
	}
	
	@RequestMapping(value ="/moderators/{id}/polls/{pollid}", method = RequestMethod.GET)
	
	public Poll viewpollsModer(@PathVariable int id,@PathVariable String pollid){
		for(int i=0;i<modarray.size();i++)
		{
			
			if(id == (modarray.get(i).getId()))				
		{
			
				return viewPoll(pollid, i);				
				
		}
		
		}
		return null;
	}
	
	public Poll viewPoll(String z, int i){
		for(int j=0;j < modarray.get(i).pollobj.size();j++){
			if(z.equals(modarray.get(i).pollobj.get(j).getpollid())){
				System.out.println();
				return modarray.get(i).pollobj.get(j);
				
			}
		}
		return null;
	}
	
	
	@RequestMapping(value="/moderators/{id}/polls", method =RequestMethod.GET)
	public ArrayList<Poll> pollsList (@PathVariable int id)
	{
		
	Moderator mod;
		for(int i=0;i<modarray.size();i++)
		{
			mod=modarray.get(i);
			if(id ==mod.getId())
			{
				for(int j=0;j<mod.pollobj.size();j++){
			    
				return modarray.get(i).pollobj;
			}}
		}
		return null;
	}
	
	@RequestMapping(value ="/moderators/{id}/polls/{pollid}",method =RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletepoll(@PathVariable int id, @PathVariable String pollid)
	{
		System.out.println("in web_details delete");
		
		for(int i=0;i<modarray.size();i++)
		{
			
			if(id ==(modarray.get(i).getId()))
		{
				
			removepoll(pollid,i);
		}
		
		}

	}
	
	public void removepoll(String s, int i)
	{
		for(int j=0;j<modarray.get(i).pollobj.size();j++)
		{
		
			
			if(s.equals(modarray.get(i).pollobj.get(j).getpollid()))
			{
				
				modarray.get(i).pollobj.remove(j);
				
			}	
		}	
	}
	
	@RequestMapping(value ="/polls/{pollid}",method =RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void voteAPoll(@PathVariable String pollid,@RequestParam (value="choice") int choice_index){
		Poll pollobj1 = new Poll();
		System.out.println("in method");
		for (int i=0;i < modarray.size();i++){
			for(int j=0;j<modarray.get(i).pollobj.size();j++){
				System.out.println("in first loop");
				if(pollid.equals(modarray.get(i).pollobj.get(j).pollid)){
					modarray.get(i).pollobj.get(j).setresult(choice_index);
					
					
				}
			}
		}
		
//		for(int j=0;j<pollarray2.size();j++){
//			System.out.println("in second pools");
//			if(pollid.equals(pollarray2.get(j).getpollid())){
//				
//				pollarray2.get(j).setresult(choice_index);
//			}
//		}
		
		
	}
	
	
	
	
}
	

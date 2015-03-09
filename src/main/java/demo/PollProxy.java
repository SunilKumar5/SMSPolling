package demo;

public class PollProxy extends Poll {
	private int[] results = {0,0};

	public int[] getResults() {
		return results;
	}

	public void setResults(int[] results) {
		this.results = results;
	}

	public void updateVote(int choice_index) {
		results[choice_index]=results[choice_index]+1;
	}
}

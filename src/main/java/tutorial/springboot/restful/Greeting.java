package tutorial.springboot.restful;

public class Greeting {

	// unique identifier 
	private final long id;
	// textual representation of the greeting
	private final String content;

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
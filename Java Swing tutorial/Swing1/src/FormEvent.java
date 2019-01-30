import java.util.EventObject;

public class FormEvent extends EventObject {
	
	private String name;
	private String occupation;

	
	// konstruktor 1
	public FormEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	// konstruktor 2
	public FormEvent(Object source, String name, String occupation) {
		super(source);
		
		this.name = name;
		this.occupation = occupation;
	}
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}



}

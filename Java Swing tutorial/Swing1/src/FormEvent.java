import java.util.EventObject;

public class FormEvent extends EventObject {
	
	private String name;
	private String occupation;
	private int ageCategory;
	private String empCat;
	
	// konstruktor 1
	public FormEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	// konstruktor 2
	public FormEvent(Object source, String name, String occupation, int ageCat, String empCat) {
		super(source);
		
		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCat;
		this.empCat = empCat;
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

	public int getAgeCategory() {
		return ageCategory;
	}

	public void setAgeCategory(int ageCategory) {
		this.ageCategory = ageCategory;
	}

	public String getEmpCat() {
		return empCat;
	}

	public void setEmpCat(String empCat) {
		this.empCat = empCat;
	}



}

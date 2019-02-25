package model;

// stosowane do atrybutu empCat klasy Person

public enum EmploymentCategory {
	emplyed("employed"),
	selfEmplyed("self emplyed"),
	unemployed("unemployed"),
	other("other");
	
	private String text;
	
	private EmploymentCategory(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		
		return text;
	}
	
	
	
}

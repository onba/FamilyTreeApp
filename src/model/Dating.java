package model;

public class Dating extends Relationship{

	public Dating(Person male, Person female) throws GayException{
		super(male, female);
	}

	@Override
	public String getName() {
		return "Dating";
	}
	
}

package model;

import java.util.ArrayList;

public abstract class Relationship {
	public Relationship(Person male, Person female) throws GayException{
		if (male.getSex()!=Sex.Male || female.getSex()!=Sex.Female) {
			throw new GayException("Can't be relationship between Persons with the same Sex.");
		}
		this.male = male;
		this.female = female;
		kids = new ArrayList<Person>();
	}
	public abstract String getName();
	public Person getMale() {
		return male;
	}
	public Person getFemale() {
		return female;
	}
	public void AddKid(Person baby) {
		baby.setParents(this);
		kids.add(baby);
	}
	private Person male;
	private Person female;
	private ArrayList<Person> kids;
	
}

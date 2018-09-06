package model;

import java.util.ArrayList;

import model.Exceptions.GayException;
import model.Exceptions.MultipleParentsException;

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
		try {
			baby.addParents(this);
			kids.add(baby);
		}
		catch(MultipleParentsException e) {
			System.err.println("This Kid named " + baby.getName()+" has other parents.");
		}
	}
	private Person male;
	private Person female;
	private ArrayList<Person> kids;
	
}

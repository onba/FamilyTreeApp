package model;

import java.util.ArrayList;
import java.util.Date;

import model.Exceptions.MultipleParentsException;

public class Person {
	public Person(Sex sex, String name, Date birthDay) {
		new Person(sex,name,birthDay,null,null,null);
	}
	public Person(Sex sex, String name, Date birthDay, Date deathDay, Relationship parents, ArrayList<Relationship> kids) {
		this.name = name==null ? "John Doe" : name;
		this.birthDay = birthDay==null ? new Date() : birthDay;
	}
	public Sex getSex() {
		return sex;
	}
	public Relationship getParents() {
		return parents;
	}
	public void addParents(Relationship parents) throws MultipleParentsException{
		if(this.parents==null || this.parents.equals(parents)) {
			this.parents = parents;
		}
		else {
			throw new MultipleParentsException("This Person already has parents");
		}
	}
	public ArrayList<Relationship> getPartners() {
		return partners;
	}
	public void setPartners(ArrayList<Relationship> partners) {
		this.partners = partners;
	}
	public String getName() {
		return name;
	}
	public Person getDaddy() {
		if (parents==null) {
			return null;
			}
		return parents.getMale();
		}
	public Person getMommy() {
		if (parents==null) {
			return null;
			}
		return parents.getFemale();
		}
	public boolean isAlive() {
		if (deathDay==null) {
			return true;
		}
		return false;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public Date getDeathDay() {
		return deathDay;
	}
	private String name;
	private Date birthDay;
	private Date deathDay;
	private Sex sex;
	private Relationship parents;
	private ArrayList<Relationship> partners;
	
}

package model;

import java.util.ArrayList;
import java.util.Date;

import model.Exceptions.GayException;
import model.Exceptions.MultipleParentsException;

public class Person {
	public Person(Sex sex, String name, Date birthDay) {
		this(sex,name,birthDay,null,null);
	}
	public Person(Sex sex, String name, Date birthDay, Date deathDay, Relationship parents) {
		this.sex = sex;
		this.name = name==null ? "John Doe" : name;
		this.birthDay = birthDay==null ? new Date() : birthDay;
		this.deathDay = deathDay;
		if (parents!=null)
			parents.AddKid(this);
		partners = new ArrayList<Relationship>();
	}
	public Sex getSex() {
		return sex;
	}
	public Relationship getParents() {
		return parents;
	}
	public void wed(Relationship rs){
		partners.add(rs);
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
	public int getUpperLevels(int previousLevels) {
		if (parents == null) return previousLevels;
		++previousLevels;
		int motherLevels = getMommy().getUpperLevels(previousLevels);
		int fatherLevels = getDaddy().getUpperLevels(previousLevels);
		return motherLevels>fatherLevels ? motherLevels : fatherLevels;
	}
	public boolean hasChild() {
		for(Relationship rel : partners) {
			if (rel.getKids()!=null) {
				return true;
			}
		}
		return false;
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

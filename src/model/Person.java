package model;

import java.util.ArrayList;
import java.util.Date;

import model.Exceptions.GayException;
import model.Exceptions.MultipleParentsException;

public class Person {
	
	public Person(Sex sex, String name, Date birthDay) {
		this(sex,name,birthDay,null,null);
	}
	
	private int level; //level in the familyTree
	
	public Person(Sex sex, String name, Date birthDay, Date deathDay, Relationship parents) {
		this.sex = sex;
		this.name = name==null ? "John Doe" : name;
		this.birthDay = birthDay==null ? new Date() : birthDay;
		this.deathDay = deathDay;
		if (parents!=null)
			parents.AddKid(this);
		relationShips = new ArrayList<Relationship>();
		level = -1;
	}
	/*public boolean isAlone() {
		return relationShips.
	}*/
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Sex getSex() {
		return sex;
	}
	public Relationship getParents() {
		return parents;
	}
	public int subTreeWidth(int width) {
		ArrayList<Person> lowerLevel = getLowerLevel();
		
		if (lowerLevel.size()>width) width=lowerLevel.size();
		for (Person lowerPerson : lowerLevel) {
			int lowerWidth = lowerPerson.subTreeWidth(width);
			if (lowerWidth>width) width = lowerWidth;
		}
		return  width;
	}
	
	public void wed(Relationship rs){
		relationShips.add(rs);
	}
	
	public void addParents(Relationship parents) throws MultipleParentsException{
		if(this.parents==null || this.parents.equals(parents)) {
			this.parents = parents;
		}
		else {
			throw new MultipleParentsException("This Person already has parents");
		}
	}
	
	public ArrayList<Relationship> getRelationShips() {
		return relationShips;
	}
	
	public ArrayList<Person> getPartners(){
		ArrayList<Person> res = new ArrayList<Person>();
		for(Relationship rel : relationShips) {
			if (sex == Sex.Male)
				res.add(rel.getFemale());
			else
				res.add(rel.getMale());
		}
		return res;
	}
	
	public String getName() {
		return name;
	}
	
	public Person geMalePrimogenitor() {
		Person per = this;
		while(per!=null) {
			per = per.getDaddy();
		}
		return per;
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
	public ArrayList<Person> getLowerLevel(){
		ArrayList<Person> lowerLevel = new ArrayList<Person>();
		for(Relationship rel : relationShips) {
			for (Person kid :rel.getKids()) {
				lowerLevel.add(kid);
				for (Person partner : kid.getPartners()) {
					lowerLevel.add(partner);
				}
			}
		}
		return lowerLevel;
	}
	public int getUpperLevels(int previousLevels) {
		if (parents == null) return previousLevels;
		++previousLevels;
		int motherLevels = getMommy().getUpperLevels(previousLevels);
		int fatherLevels = getDaddy().getUpperLevels(previousLevels);
		return motherLevels>fatherLevels ? motherLevels : fatherLevels;
	}
	public boolean hasChild() {
		for(Relationship rel : relationShips) {
			if (rel.getKids()!=null) {
				return true;
			}
		}
		return false;
	}
	public boolean hasActiveRelationShip() {
		for(Relationship rs : getRelationShips()) {
			if(rs.getActive()) return true;
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
	private ArrayList<Relationship> relationShips;
	
}

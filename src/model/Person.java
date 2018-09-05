package model;

import java.util.ArrayList;
import java.util.Date;

public class Person {
	
	public Sex getSex() {
		return sex;
	}
	public Relationship getParents() {
		return parents;
	}
	public void setParents(Relationship parents) {
		this.parents = parents;
	}
	public ArrayList<Relationship> getPartners() {
		return partners;
	}
	public void setPartners(ArrayList<Relationship> partners) {
		this.partners = partners;
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
	
	private String name;
	private Date birthDay;
	private Date deathDay;
	private Sex sex;
	private Relationship parents;
	private ArrayList<Relationship> partners;
	
}

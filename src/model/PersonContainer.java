package model;

import java.util.ArrayList;

public class PersonContainer {
	private ArrayList<Person> persons;
	private ArrayList<Person> leafs;
	
	public PersonContainer() {
		persons = new ArrayList<Person>();
		leafs = new ArrayList<Person>();
	}
	public void AddMember(Person person) {
		if(!person.hasChild()) {
			leafs.add(person);
			Person mummy = person.getMommy();
			Person daddy = person.getDaddy();
			if (mummy!=null && leafs.contains(mummy)) leafs.remove(mummy);
			if (daddy!=null && leafs.contains(daddy)) leafs.remove(daddy);
		}
		persons.add(person);
	}
	public ArrayList<Person> getLeafs(){
		return leafs;
	}
	public int getLevels() {
		int highestLevel = 1;
		for(Person person : persons){
			if (!person.hasChild()) {
				int actualLevel = person.getUpperLevels(1);
				if(actualLevel>highestLevel) highestLevel = actualLevel;
			}
		}
		return highestLevel;
	}
}

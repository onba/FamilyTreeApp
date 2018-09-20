package model;

import java.util.ArrayList;

public class PersonContainer {
	private ArrayList<Person> persons;
	private ArrayList<Person> leafs;
	private int levels;
	
	public PersonContainer() {
		persons = new ArrayList<Person>();
		leafs = new ArrayList<Person>();
		levels = 0;
	}
	public void AddMember(Person person) throws Exception{
		if(!person.hasChild()) {
			leafs.add(person);
			Person mummy = person.getMommy();
			Person daddy = person.getDaddy();
			if (mummy ==null && daddy ==null) {
				levels = 1;
				person.setLevel(0);
			}
			else {
				if (mummy!=null && leafs.contains(mummy)) leafs.remove(mummy);
				if (daddy!=null && leafs.contains(daddy)) leafs.remove(daddy);
				switch(mummy.getLevel()){
					case -1: 	throw new Exception(mummy.getName()+"'s Treelevel is unknown.");
					case 0: 	increaseLevels();
					default:	person.setLevel(mummy.getLevel()-1);
				}
			}
		}
		else {
			int newLevel = person.getLowerLevel().get(0).getLevel()+1;
			person.setLevel(newLevel);
			if (newLevel>=levels) levels = newLevel+1;
		}
		persons.add(person);
	}
	public String getDatasForTest() {
		String res = new String();
		for(Person person : persons) {
			res+="Name: "+person.getName()+", level: "+person.getLevel()+"\n";
		}
		res+="\nlevels: "+levels;
		return res;
	}
	private void increaseLevels() {
		for(Person person : persons) {
			int newLevel = person.getLevel()+1;
			person.setLevel(newLevel);
			if(newLevel>=levels) levels = newLevel+1;
		}
	}
	public ArrayList<Person> getLeafs(){
		return leafs;
	}
	public ArrayList<Person> getPersons(){
		return persons;
	}
	
	public ArrayList<Integer> countLevelWidths() {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i=0; i<levels;i++) {
			res.add(0);
		}
		for(Person person : persons) {
			int onLevel = person.getLevel();
			res.set(onLevel, res.get(onLevel)+1);
		}
		return res;
	}
	
	public int getLevels() {
		return levels;
	}
}

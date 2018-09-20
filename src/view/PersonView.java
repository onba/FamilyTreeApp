package view;

import java.awt.Font;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Person;
import model.Sex;


public class PersonView {
	public PersonView(Person person, int nodeRadius) {
		this.person = person;
		this.nodeRadius = nodeRadius;
	}
	public void draw(GraphicsContext gc) {
		int nameLength = person.getName().length();
		if (person.getSex()==Sex.Male)
			gc.setFill(Color.BLUE);
		else
			gc.setFill(Color.RED);
		gc.fillOval(x-nodeRadius/2, y-nodeRadius/2, nameLength*nodeRadius/4, nodeRadius);
		gc.setFill(Color.WHITE);
		gc.fillText(person.getName(), x, y);
	}
	private Person person;
	
	private int nodeRadius;
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int calculateX(int canvasWidth, ArrayList<Integer> levelWidths, ArrayList<Integer> donePeopleOnLevel) {
		int levelPersonNumber = levelWidths.get(person.getLevel());
		int personWidth = canvasWidth / levelPersonNumber;
		int donePeople = donePeopleOnLevel.get(person.getLevel());
		
		int res = personWidth * donePeople;
		donePeopleOnLevel.set(person.getLevel(),donePeople+1);
		return res;
	}
	
	public int calculateY(int levelHeight, int allLevel) {
		return levelHeight * (allLevel - person.getLevel());
	}
}

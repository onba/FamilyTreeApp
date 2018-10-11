package view;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Person;
import model.Sex;


public class PersonView {
	public PersonView(Person person, int namePadding) {
		this.person = person;
		this.namePadding = namePadding;
		if (person.getParents()==null) {
			addPV = new AddButtonView();
		}
		if (!person.hasActiveRelationShip()) {
			addRV = new AddButtonView();
		}
		isMouseOn = false;
		refreshTextSize();
	}
	static Color femaleColor = new Color(0.46,0.07,0.07,1);
	static Color maleColor = Color.BLUE;
	static Font font = Font.font("Arial", 20);
	static Color activeColor = Color.valueOf("53FF83");
	
	public void refreshTextSize() {
		final Text text = new Text(person.getName());
        text.setFont(font);
        
		nameWidth = text.getLayoutBounds().getWidth();
		nameHeight = text.getLayoutBounds().getHeight();
	}
	
	public void draw(GraphicsContext gc) {
		
		if (isMouseOn) gc.setFill(activeColor);
		else if (person.getSex()==Sex.Male) gc.setFill(maleColor);
		else	gc.setFill(femaleColor);
		Point2D textBegin = new Point2D(x-nameWidth/2, y+nameHeight/2);
		gc.fillRoundRect(textBegin.getX()-namePadding, textBegin.getY()- 1.5 *nameHeight, nameWidth+namePadding*2, 2.5 *nameHeight,namePadding*2,nameHeight*1.5);
		gc.setFill(Color.WHITE);
		gc.setFont(font);
		gc.fillText(person.getName(), textBegin.getX(), textBegin.getY());
		if (person.getParents()==null) {
			addPV.draw(gc);
		}
		if (!person.hasActiveRelationShip()) {
			addRV.draw(gc);
		}
	}
	private Person person;
	
	private double nameWidth;
	private double nameHeight;
	private int namePadding;
	private int x;
	private int y;
	private AddButtonView addPV;
	private AddButtonView addRV;
	private boolean isMouseOn;
	
	public Person getPerson() {
		return person;
	}
	public double getWidth() {
		return getTextFormat().getLayoutBounds().getWidth();
	}
	public double getHeight() {       
		return getTextFormat().getLayoutBounds().getHeight();
	}
	private Text getTextFormat() {
		final Text text = new Text(person.getName());
        Font font = Font.font("Arial", 20);
        text.setFont(font);
        return text;
	}
	public boolean checkMouseIn(double x, double y) {
		boolean isIn = isOnPersonArea(x, y);
		boolean res = isIn==isMouseOn;
		if (isIn) isMouseOn = true;
		else isMouseOn = false;
		return res;
	}
	private boolean isOnPersonArea(double x, double y) {
		if (addPV==null) return false;
		return addPV.isInArea(x,y);
	}
	
	private boolean isOnAddParentArea(double x, double y) {
		if (addPV==null) return false;
		return addPV.isInArea(x,y);
	}
	private boolean isOnAddRelationArea(double x, double y) {
		if (addRV==null) return false;
		return addRV.isInArea(x,y);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
		if (addPV!=null) {
			addPV.setX(x);
		}
		if (addRV!=null) {
			addRV.setX(person.getSex()==Sex.Male ? x+(int)(nameWidth/2)+namePadding : x-(int)(nameWidth/2)-namePadding);
		}
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
		if (addPV!=null) {
			addPV.setY(y-(int)nameHeight);
		}
		if (addRV!=null) {
			addRV.setY(y+(int)(0.25*nameHeight));
		}
	}
	public int calculateX(int canvasWidth, ArrayList<Integer> levelWidths, ArrayList<Integer> donePeopleOnLevel) {
		int levelPersonNumber = levelWidths.get(person.getLevel());
		int personWidth = canvasWidth / (levelPersonNumber+1);
		int donePeople = donePeopleOnLevel.get(person.getLevel());
		
		int res = personWidth * (donePeople+1);
		donePeopleOnLevel.set(person.getLevel(),donePeople+1);
		return res;
	}
	
	public int calculateY(int levelHeight, int allLevel) {
		return levelHeight * (allLevel - person.getLevel());
	}
}

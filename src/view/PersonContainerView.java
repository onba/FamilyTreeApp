package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.sun.javafx.geom.Point2D;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import model.Marriage;
import model.Person;
import model.PersonContainer;
import model.Sex;
import model.Exceptions.GayException;
import model.Exceptions.MarriageOutOfLifeException;

public class PersonContainerView implements Initializable{
	@FXML
	private Canvas mainCanvas;
	@FXML
	private BorderPane borderPane;
	
	private GraphicsContext gc;
	private PersonContainer pc;
	public static int screenWidth;
	public static int screenHeight;
	
	private ArrayList<PersonView> pViews;
	private ArrayList<Integer> levelWidths;
	
	//environmental variables
	
	public int nodeRadius = 50;
	public double treeThickness = 2;
	
	public int canvasBorder = 5;
	public int screenPadding = 100;
	public int canvasPadding = 100;
	
	public Point2D camera = new Point2D(0,0);
	public double zoom = 1;
	
	private int getCanvasWidth() {
		return screenWidth-2*screenPadding-2*canvasBorder;
	}
	private int getCanvasHeight() {
		return screenHeight-2*screenPadding-2*canvasBorder;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pViews = new ArrayList<PersonView>();
		pc = initalizeModel();
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		screenWidth = (int) primaryScreenBounds.getWidth();
		screenHeight = (int) primaryScreenBounds.getHeight();
		
		borderPane.resize(screenWidth, screenHeight);
		mainCanvas.setWidth(screenWidth-2*screenPadding);
		mainCanvas.setHeight(screenHeight-2*screenPadding);				
		
		
		gc = mainCanvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0,0,getCanvasWidth(),getCanvasHeight());
		gc.setFill(Color.WHITE);
		gc.fillRect(canvasBorder,canvasBorder,getCanvasWidth()-2*canvasBorder,getCanvasHeight()-2*canvasBorder);
		
		drawGraph(gc);
	}
	
	public void drawGraph(GraphicsContext gc) {
		int levelHeight = (getCanvasHeight()-2*canvasPadding)/pc.getLevels();
		int allLevel = pc.getLevels();
		ArrayList<Integer> donePeopleOnLevel = new ArrayList<Integer>();
		for(int i=0;i<pc.getLevels();i++) {
			donePeopleOnLevel.add(0);
		}
		for(PersonView pv : pViews) {
			pv.setX(pv.calculateX(getCanvasWidth()-2*canvasPadding, levelWidths, donePeopleOnLevel)+canvasBorder+canvasPadding);
			pv.setY(pv.calculateY(levelHeight, allLevel)+canvasBorder+canvasPadding);
			pv.draw(gc);
		}
	}
	
	//Generate Example Model -> later need deleted
	public PersonContainer initalizeModel() {
		try {
		pc = new PersonContainer();
		Person kovacsJanos = new Person(Sex.Male,"Kovács János", new Date(1930, 10, 21), new Date(2002,5,27), null);
		initializePersonModelAndView(kovacsJanos);
		Person kovacsJanosne = new Person(Sex.Female,"Kiss Marika", new Date(1935, 12, 2));
		initializePersonModelAndView(kovacsJanosne);
		try {
		Marriage kovacsFamily = new Marriage(kovacsJanos, kovacsJanosne,new Date(1955,8,12),null);
		Person kovacsIstvan = new Person(Sex.Male,"Kovács István", new Date(1958, 1, 1), null, kovacsFamily);
		initializePersonModelAndView(kovacsIstvan);
		Person szaboEtelka = new Person(Sex.Female,"Szabó Etelka", new Date(1966, 2, 23));
		initializePersonModelAndView(szaboEtelka);
		Marriage ifjKovacsFamily = new Marriage(kovacsIstvan, szaboEtelka, new Date(1982, 5, 2), null);
		Person kovacsHajnalka = new Person(Sex.Female,"Kovács Hajnalka", new Date(1990, 7, 30), null, ifjKovacsFamily);
		initializePersonModelAndView(kovacsHajnalka);
		} catch(MarriageOutOfLifeException e) {
			System.out.println(e.getMessage());
		} catch(GayException e) {
			System.out.println(e.getMessage());
		}
		
		levelWidths = pc.countLevelWidths();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Unexpected Error");
			alert.setContentText(e.getMessage());
		}
		return pc;
	}
	
	private void initializePersonModelAndView(Person p) throws Exception{
		pc.AddMember(p);
		pViews.add(new PersonView(p, nodeRadius));
	}
	
	@FXML
	private void onMouseClicked(MouseEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText(getViewDatasForTest());

		alert.showAndWait();
	}
	
	@FXML
	private void onMouseDragged(MouseEvent event) {
		giveAlertWithDatas();
	}
	
	private void giveAlertWithDatas() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText(pc.getDatasForTest());

		alert.showAndWait();
	}
	
	private String getViewDatasForTest() {
		String res = new String();
		for(PersonView pv : pViews) {
			res+="["+pv.getX()+";"+pv.getY()+"]\n";
		}
		res+="\n["+getCanvasHeight()+";"+getCanvasWidth()+"]";
		return res;
	}
}

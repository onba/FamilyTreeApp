package view;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.Marriage;
import model.Person;
import model.PersonContainer;
import model.Sex;
import model.Exceptions.GayException;
import model.Exceptions.MarriageOutOfLifeException;

public class PersonContainerView implements Initializable{
	@FXML
	private Canvas mainCanvas;
	
	private GraphicsContext gc;
	private PersonContainer pc;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		gc = mainCanvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0,0,200,200);
		pc = initalizeModel();
	}
	
	public PersonContainer initalizeModel() {
		PersonContainer pc = new PersonContainer();
		Person kovacsJanos = new Person(Sex.Male,"Kovács János", new Date(1930, 10, 21), new Date(2002,5,27), null);
		pc.AddMember(kovacsJanos);
		Person kovacsJanosne = new Person(Sex.Female,"Kiss Marika", new Date(1935, 12, 2));
		pc.AddMember(kovacsJanosne);
		Person szaboEtelka = new Person(Sex.Female,"Szabó Etelka", new Date(1966, 2, 23));
		pc.AddMember(szaboEtelka);
		try {
		Marriage kovacsFamily = new Marriage(kovacsJanos, kovacsJanosne,new Date(1955,8,12),null);
		Person kovacsIstvan = new Person(Sex.Male,"Kovács István", new Date(1958, 1, 1), null, kovacsFamily);
		pc.AddMember(kovacsIstvan);
		Marriage ifjKovacsFamily = new Marriage(kovacsIstvan, szaboEtelka, new Date(1982, 5, 2), null);
		Person kovacsHajnalka = new Person(Sex.Female,"Kovács Hajnalka", new Date(1990, 7, 30), null, ifjKovacsFamily);
		pc.AddMember(kovacsHajnalka);
		} catch(MarriageOutOfLifeException e) {
			System.out.println(e.getMessage());
		} catch(GayException e) {
			System.out.println(e.getMessage());
		}	
		return pc;
	}
	@FXML
	private void onMouseClicked(MouseEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText("The levels of the tree: "+ pc.getLevels());

		alert.showAndWait();
	}
	
	@FXML
	private void onMouseDragged(MouseEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Look, an Information Dialog");
		String contentText = "The leafs of the tree: ";
		for(Person person: pc.getLeafs()) {
			contentText+=person.getName()+", ";
		}
		alert.setContentText(contentText);

		alert.showAndWait();
	}
}

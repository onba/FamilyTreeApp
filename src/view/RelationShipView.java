package view;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RelationShipView extends RelationView{

	public RelationShipView(PersonView aPersonView, PersonView bPersonView) {
		if (aPersonView.getX()<bPersonView.getX()) {
			APersonView = bPersonView;
			BPersonView = aPersonView;
		}
		else {
			APersonView = aPersonView;
			BPersonView = bPersonView;
		}
	}
	protected static Color RSColor = Color.BLACK;
	
	protected PersonView APersonView;	//Left Person
	protected PersonView BPersonView;	//Right Person
	@Override
	public void draw(GraphicsContext gc) {
		int lineBegin = APersonView.getX() + (int) APersonView.getWidth();
		int lineEnd = BPersonView.getX() - (int) BPersonView.getWidth();
		double textHeight = APersonView.getHeight();
		gc.setStroke(RSColor);
		gc.strokeLine(lineBegin, APersonView.getY()-textHeight/3, lineEnd, APersonView.getY()-textHeight/3);
		gc.strokeLine(lineBegin, APersonView.getY(), lineEnd, APersonView.getY());
		gc.strokeLine(lineBegin, APersonView.getY()+textHeight/3, lineEnd, APersonView.getY()+textHeight/3);		
	}
	public Point2D getMidle() {
		return new Point2D((APersonView.getX()+BPersonView.getX())/2, (APersonView.getY()+BPersonView.getY())/2);
	}

}

package view;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ParentChildRelationView extends RelationView{

	public ParentChildRelationView(PersonView kidView, RelationShipView parentView) {
		KidView = kidView;
		ParentView = parentView;
	}
	
	static private Color relationColor = Color.BLACK;
	
	private PersonView KidView;
	private RelationShipView ParentView;
	@Override
	public void draw(GraphicsContext gc) {
		Point2D relationShipPoint = ParentView.getMidle();
		gc.setStroke(relationColor);
		gc.strokeLine(relationShipPoint.getX(), relationShipPoint.getY(), KidView.getX(), KidView.getY());
		//System.out.println(relationShipPoint.getX()+","+ relationShipPoint.getY()+","+ KidView.getX()+","+ KidView.getY());
	}

}

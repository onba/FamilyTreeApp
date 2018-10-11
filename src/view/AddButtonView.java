package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AddButtonView {
	
	private int x;
	private int y;
	
	static Color bGColor = Color.valueOf("E28228");
	static Color fGColor = Color.valueOf("4247E8");
	
	static double circleRadius = 10;
	static double crossSize = 10;
	
	public AddButtonView() {
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void draw(GraphicsContext gc) {
		gc.setFill(bGColor);
		gc.fillOval(x-circleRadius, y-circleRadius, 2*circleRadius, 2*circleRadius);
		gc.setStroke(fGColor);
		gc.strokeLine(x-crossSize/2.0, y, (x+crossSize/2.0), y);
		gc.strokeLine(x, y-crossSize/2.0, x, (y+crossSize/2.0));
		System.out.println(x-crossSize/2.0+","+ y+","+ (x+crossSize/2.0)+","+ x);
	}
	public boolean isInArea(double mX, double mY) {
		int oX = (int)mX-x;
		int oY = (int)mY-y;
		if (oX*oX+oY*oY<circleRadius*circleRadius) return true;
		return false;
	}
}

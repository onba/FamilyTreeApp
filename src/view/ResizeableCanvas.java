package view;

import javafx.scene.canvas.Canvas;

public class ResizeableCanvas extends Canvas{

	@Override
	public boolean isResizable() {
		return true;
	}

	@Override
	public double prefHeight(double width) {
		// TODO Auto-generated method stub
		return getWidth();
	}

	@Override
	public double prefWidth(double height) {
		// TODO Auto-generated method stub
		return getHeight();
	}

}

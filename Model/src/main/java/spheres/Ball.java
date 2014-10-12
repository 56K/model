package spheres;

import java.awt.Color;
import java.awt.Point;

public class Ball {

	private Point position;
	private Color color;

	public Ball(Point posArgs, Color colorArgs) {
		position = posArgs;
		color = colorArgs;
	}

	public Ball(int xArgs, int yArgs, Color colorArgs) {
		position = new Point(xArgs, yArgs);
		color = colorArgs;
	}

	public Point getPos(){
		return position;
	}

	public void setPos(Point posArgs){
		position = posArgs;
	}
	public void setPos(int xArgs, int yArgs){
		position= new Point(xArgs, yArgs);
	}

	public Color getBallColor(){
		return color;
	}
}

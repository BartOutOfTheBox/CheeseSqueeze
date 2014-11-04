package cheese.squeeze.gameObjects;

import java.util.Map.Entry;

import javax.naming.OperationNotSupportedException;

import com.badlogic.gdx.math.Vector2;

public class HorizontalLine extends Line implements Cloneable{
	
    public HorizontalLine(){
    	super();
    }
    
    
    public HorizontalLine(float y,float x1, float x2){
    	super(new Vector2(x1,y),new Vector2(x2,y));
    }
	

    public void onClick(int screenX, int screenY) {
    	if(getPoint1() != null) {
    		setPoint2(new Vector2(screenX,getY1()));
    		setDrawable(true);
    	}
    	else {
    		setPoint1(new Vector2(screenX,screenY));
    	}
    }
    
    public void onDrag(int screenX, int screenY) {
    	setPoint1(new Vector2(screenX,screenY));
    	setPoint2(new Vector2(screenX,screenY));
    }


	public void update(float delta) {
		if(getPoint1() != null && getPoint2() != null) {
			setDrawable(true);
		}
	}
	
	public HorizontalLine clone() {
		return new HorizontalLine(getY1(),getX1(),getX2());
	}
	
	@Override
	public float getRelativePosition(Vector2 point) {
		return Math.abs(point.x - this.getX1());
	}

	@Override
	public void setNeighbour(Line line) {
		if (!(line instanceof VerticalLine)) {
			super.setNeighbour(line);
		} else {
			float intersection = getRelativePosition(line.getPoint1());
			neighbours.put(intersection, line);
		}
	}
	
//	@Override
//	public Vector2 getNextIntersection(Vector2 from, Vector2 direction) {
//		if (from.equals(getPoint2()))
//			return getPoint1();
//		return getPoint2();
//	}
//	
//	@Override
//	public Line getNeighbour(Vector2 from, Vector2 direction) {
//		return neighbours.get(getRelativePosition(getNextIntersection(from, null)));
//	}
}

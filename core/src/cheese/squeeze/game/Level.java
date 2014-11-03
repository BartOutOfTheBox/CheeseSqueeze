package cheese.squeeze.game;


import cheese.squeeze.gameObjects.Cheese;
import cheese.squeeze.gameObjects.HorizontalLine;
import cheese.squeeze.gameObjects.Trap;
import cheese.squeeze.gameObjects.VerticalLine;

public enum Level {
	
	LEVEL2(4,1,5,0.5f,new HorizontalLine[]{},2), 
	
	LEVEL1(0.4f,new HorizontalLine[]{}
			,new VerticalLine[]{new VerticalLine(new Trap()),new VerticalLine(new Cheese(1)),new VerticalLine(new Trap())}
			,1,LEVEL2);
	
	private int amountTraps;
	private int amountGoals;
	private HorizontalLine[] hlines;
	private int amountVlines;
	private float speed;
	private Level nextLevel;
	private int nbMouse;
	private boolean randomLines = true;
	private VerticalLine[] vlines;
	
	
	Level(float speed, HorizontalLine[] hlines,VerticalLine[] vlines, int nbMouse) {
        this.amountVlines = vlines.length;
        this.hlines = hlines;
        this.speed = speed;
        this.nextLevel = null;
        this.nbMouse = nbMouse;
        this.randomLines = false;
        this.vlines = vlines;
   
	}
	

	Level(float speed, HorizontalLine[] hlines,VerticalLine[] vlines, int nbMouse,Level nextLevel) {
        this.amountVlines = vlines.length;
        this.hlines = hlines;
        this.speed = speed;
        this.nextLevel = null;
        this.nbMouse = nbMouse;
        this.randomLines = false;
        this.vlines = vlines;
        this.nextLevel = nextLevel;
	}
	
	Level(int nbTraps,int nbGoals,int nbVlines,float speed, HorizontalLine[] hlines, int nbMouse) {
        this.amountTraps = nbTraps;
        this.amountGoals = nbGoals;
        this.amountVlines = nbVlines;
        this.hlines = hlines;
        this.speed = speed;
        this.nextLevel = null;
        this.nbMouse = nbMouse;
    }

	Level(int nbTraps,int nbGoals,int nbVlines,float speed, HorizontalLine[] hlines,int nbMouse,Level nextLevel) {
        this.amountTraps = nbTraps;
        this.amountGoals = nbGoals;
        this.amountVlines = nbVlines;
        this.hlines = hlines;
        this.speed = speed;
        this.nextLevel = nextLevel;
        this.nbMouse = nbMouse;
    }

	public int getAmountTraps() {
		return amountTraps;
	}

	public int getAmountGoals() {
		return amountGoals;
	}

	public HorizontalLine[] getHlines() {
		return hlines;
	}

	public int getAmountVlines() {
		return amountVlines;
	}

	public float getSpeed() {
		return speed;
	}

	public boolean isRandomLines() {
		return randomLines;
	}

	public VerticalLine[] getVlines() {
		return vlines;
	}
	
	public int getNbMouse() {
		return nbMouse;
	}

	public Level getNextLevel() {
		return nextLevel;
	}
	
}

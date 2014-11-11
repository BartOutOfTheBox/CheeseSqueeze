package cheese.squeeze.ui;

import cheese.squeeze.helpers.AssetLoader;
import cheese.squeeze.tweenAccessors.SoundAccessor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class SimpleButton {

    private float x, y, width, height;

    private TextureRegion buttonUp;
    private TextureRegion buttonDown;
    
    private float screenX,screenY;

	private Rectangle bounds;

    private boolean isPressed = false;

	private String name;

	private SimpleButtonListener listen;

	/*
    public SimpleButton(float x, float y, float width, float height,
            TextureRegion buttonUp, TextureRegion buttonDown) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;

        bounds = new Rectangle(x, y, width, height);

    }
    */
    
    public SimpleButton(SimpleButtonListener listen, float x, float y, float width, float height,
            TextureRegion buttonUp, TextureRegion buttonDown) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;
        this.listen = listen;
        bounds = new Rectangle(x, y, width, height);

    }
    
    public void scale(float sc) {
    	this.width = width+sc;
    	this.height = height+sc;
    }

    public boolean isClicked(int screenX, int screenY) {
        return isPressed;
    }

    public void draw(SpriteBatch batcher) {
        if (isPressed) {
            batcher.draw(buttonDown, x, y, width, height);
        } else {
            batcher.draw(buttonUp, x, y, width, height);
        }
    }

    public boolean isTouchDown(int screenX, int screenY) {
        if (contains(screenX, screenY)) {
        	playButtonSound();
            isPressed = true;
            listen.pushButtonListener(this);
            this.screenX =screenX;
            this.screenY = screenY;
            return true;
        }

        return false;
    }

    public boolean isTouchUp(int screenX, int screenY) {
        
        // It only counts as a touchUp if the button is in a pressed state.
        if (contains(screenX, screenY) && isPressed) {
            isPressed = false;
            return true;
        }
        
        // Whenever a finger is released, we will cancel any presses.
        isPressed = false;
        return false;
    }
    
    public boolean contains(int screenX, int screenY) {
    	return bounds.contains(screenX, screenY);
	}

	public void drawUp(SpriteBatch batcher) {
    	batcher.draw(buttonUp, x, y, width, height);
    }
    
    public void drawDown(SpriteBatch batcher) {
    	batcher.draw(buttonDown, x, y, width, height);
    }
    
    public boolean isPressed() {
    	return isPressed;
    }
    
    public void setPressed(boolean p) {
    	isPressed = p;
    }
    
    public Rectangle getBounds(){
    	return bounds;
    }
    
    public String getName() {
    	return name;
    }
    
    protected void playButtonSound() {
    	SoundAccessor.play(AssetLoader.buttonSound);
    }
    
    public float getScreenX() {
    	return this.screenX;
    }
    
    public float getScreenY() {
    	return this.screenY;
    }

}

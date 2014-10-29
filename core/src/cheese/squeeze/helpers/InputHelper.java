package cheese.squeeze.helpers;

import java.util.ArrayList;

import cheese.squeeze.game.CSGame;
import cheese.squeeze.gameLogic.GameBoard;
import cheese.squeeze.gameObjects.HorizontalLine;
import cheese.squeeze.gameObjects.VerticalLine;
import cheese.squeeze.screens.GameScreen;
import cheese.squeeze.screens.MenuScreen;
import cheese.squeeze.ui.SimpleButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class InputHelper implements InputProcessor {

	private GameBoard board;
	private HorizontalLine l;
	private HorizontalLine gl;
	boolean touchedDown = false;
	private CSGame game;
	private ArrayList<SimpleButton> buttons;

	public InputHelper(GameBoard board, CSGame game,
			ArrayList<SimpleButton> buttons) {
		l = new HorizontalLine();
		gl = new HorizontalLine();
		this.board = board;
		this.game = game;
		this.buttons = buttons;

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touchButton(screenX, screenY);
		switch (CSGame.currentState) {
		case GAMEOVER:

			break;
		case PLAYING:
			touchDownPlaying(screenX, screenY, pointer, button);
			break;
		case PAUSE:

			break;

		}
		return true;

	}

	public boolean touchDownPlaying(int screenX, int screenY, int pointer,
			int button) {

		if (pointer == 0) {
			if (!touchedDown) {
				board.setClickedPosition(new Vector2(screenX, screenY));
				touchedDown = true;
				gl.onClick(screenX, screenY);
			}

			// gl.onClick(screenX, screenY);
			if (!gl.isdrawable()) {
				gl.onClick(screenX + 1, screenY);
				board.setGesturedLine(gl);
			}
		}
		// l.onClick(screenX,screenY);
		// l.onClick(screenX,screenY);

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		switch (CSGame.currentState) {
		case GAMEOVER:

			break;
		case PLAYING:
			touchUpPlaying(screenX, screenY, pointer, button);
			break;
		case PAUSE:

			break;

		}
		return true;
	}

	public boolean touchUpPlaying(int screenX, int screenY, int pointer,
			int button) {
		if (pointer == 0) {
			if (!board.isClearable(screenX, screenY)) {
				board.addHLine(gl.clone());
				// l.clear();
			}
			if (!Gdx.input.isTouched()) {
				gl.clear();
				touchedDown = false;
			}
		}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (pointer == 0) {
			switch (CSGame.currentState) {
			case GAMEOVER:

				break;
			case PLAYING:
				touchDraggedPlaying(screenX, screenY, pointer);
				break;
			case PAUSE:

				break;
			}
		}
		return true;
	}

	public boolean touchDraggedPlaying(int screenX, int screenY, int pointer) {
		if (pointer == 0) {
			gl.onDrag(screenX, screenY);
			board.setGesturedLineDragged(gl);
		}
		return true;
	}

	private boolean touchButton(int x, int y) {
		Vector2 vec2 = board.unProject(x, y);
		if (buttons.get(0).isClicked((int) vec2.x, (int) vec2.y)) {
			game.setScreen(new MenuScreen(game));
			return true;
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}

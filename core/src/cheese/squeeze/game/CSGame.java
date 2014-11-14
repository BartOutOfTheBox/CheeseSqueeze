package cheese.squeeze.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import cheese.squeeze.helpers.AssetLoader;
import cheese.squeeze.helpers.Timer;
import cheese.squeeze.helpers.TimerFactory;
import cheese.squeeze.screens.GameScreen;
import cheese.squeeze.screens.SplashScreen;
import cheese.squeeze.tweenAccessors.ActionResolver;
import cheese.squeeze.tweenAccessors.MusicAccessor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


/**
 * This class ini the game.
 * @author maxdekoninck
 *
 */
public class CSGame extends Game {

	public static GameState currentState;
	public static Level currentLevel = Level.LEVEL1;
	private ActionResolver action;
	public  final static String version = "1.4";
	public static final String updateURL = "http://camambar.github.io/CheeseSqueeze/";
	public static final String checkVersionURL = "http://camambar.github.io/CheeseSqueeze/version";
	
	public CSGame(ActionResolver action) {
		this.action = action;
		//action.reportAnalytics("test", "cat", 3);
	}

	public CSGame() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		
		//LOAD LEVEL
		/*
		Preferences prefs = Gdx.app.getPreferences("CurrentLevel");
		if (prefs.contains("level")) {
			String l = prefs.getString("level");
			currentLevel = Level.valueOf(l);
		}
		*/
		
		Gdx.app.log("CSGame", "created");
		TimerFactory.getNewTimer(new ReportStatus(GameState.GAMESTART)).start();
		MusicAccessor musicA = new MusicAccessor();
		AssetLoader.loadSplashScreen();
		// set to setScreen(new GameScreen()); to start the game immediatly!
		//setScreen(new GameScreen());

		
		setScreen(new SplashScreen(this));
	}
	
	public ActionResolver getActionResolver() {
		return this.action;
	}
	
    @Override
    public void dispose() {
        super.dispose();
        TimerFactory.stopAll();
        ArrayList<Timer> t = TimerFactory.getTimers();
        HashMap<Level,HashMap<GameState,Integer>> m = Report.map;
		if(action!=null) {
			for(Timer timer : t) {
				action.reportAnalytics(timer.getState().getLevel().toString(), timer.getState().getGameState().toString(), timer.getSeconds());
				System.out.println(timer.getSeconds());
			}
			for(Entry<Level, HashMap<GameState, Integer>> e: Report.map.entrySet()) {
				for (Entry<GameState, Integer> e2 : e.getValue().entrySet()) {
					action.reportAnalytics(e.getKey().toString(),e2.getKey().toString(),e2.getValue());
				}
			}
			
			
		}
        
		Preferences prefs = Gdx.app.getPreferences("CurrentLevel");
		prefs.putString("level", currentLevel.toString());
		prefs.flush();
        
        AssetLoader.dispose();
    }

}

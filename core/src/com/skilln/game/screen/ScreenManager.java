package com.skilln.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import java.util.HashMap;

public class ScreenManager {

    public static HashMap<Integer, Screen> screens = new HashMap<Integer, Screen>();

    public static final int SPLASH = 0;
    public static final int MENU = 1;
    public static final int GAME = 2;

    private static Screen currentScreen;

    private static Game game;

    public static void init(Game game) {
        ScreenManager.game = game;

        screens.put(GAME, new GameScreen());

    }

    public static void setCurrentScreen(int index) {
        game.setScreen(screens.get(index));
        currentScreen = screens.get(index);
    }

    public static Screen getCurrentScreen() {
        return currentScreen;
    }


}

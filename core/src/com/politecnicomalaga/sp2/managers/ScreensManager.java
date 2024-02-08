package com.politecnicomalaga.sp2.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.politecnicomalaga.sp2.view.*;

public class ScreensManager {

    private static ScreensManager singleton;

    private Screen activeScreen;

    public static enum SCREENS {
        GAME_SCREEN,
        SPLASH_SCREEN,
        CREDITS_SCREEN,
        GAMEOVER_SCREEN,
        SETTINGS_SCREEN
    }

    private ScreensManager() {

    }

    public static ScreensManager getSingleton() {
        if (singleton == null) {
            singleton = new ScreensManager();
        }
        return singleton;
    }

    public Screen getScreen(Game aGame, SCREENS screenToGet, String msg) {
        Screen newScreen;
        switch (screenToGet) {
            case GAME_SCREEN: newScreen = new GameScreen(aGame);
                break;
            case GAMEOVER_SCREEN: newScreen = new GameOverScreen(aGame,msg);
                break;
            case SPLASH_SCREEN: newScreen = new SplashScreen(aGame);
                break;
            case CREDITS_SCREEN: newScreen = new CreditsScreen(aGame);
                break;
            case SETTINGS_SCREEN: newScreen = new SettingsScreen(aGame);
                break;
            default: newScreen = new SplashScreen(aGame);
        }
        if (activeScreen != null) activeScreen.dispose();
        activeScreen = newScreen;
        return newScreen;
    }

    public Screen getActiveScreen() {
        return activeScreen;
    }

}

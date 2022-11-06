package com.politecnicomalaga.sp2.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.politecnicomalaga.sp2.view.GameScreen;

public class ScreensManager {

    private static ScreensManager singleton;

    private ScreensManager() {

    }

    public static ScreensManager getSingleton() {
        if (singleton == null) {
            singleton = new ScreensManager();
        }
        return singleton;
    }

    public Screen getScreen(Game aGame) {
        return new GameScreen(aGame);
    }

}

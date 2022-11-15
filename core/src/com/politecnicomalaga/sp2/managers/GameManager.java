package com.politecnicomalaga.sp2.managers;

import com.badlogic.gdx.Gdx;
import com.politecnicomalaga.sp2.GdxSpaceInvaders2;

public class GameManager {

    private float gameTime;
    private static GameManager singleton;

    public static GameManager getSingleton() {
        if (singleton == null) {
            singleton = new GameManager();
        }
        return singleton;
    }

    public float getGameTime() {
        return GdxSpaceInvaders2.getGameTime();
    }
}

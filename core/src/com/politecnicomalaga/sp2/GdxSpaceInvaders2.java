package com.politecnicomalaga.sp2;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.politecnicomalaga.sp2.managers.ScreensManager;
import com.politecnicomalaga.sp2.managers.SettingsManager;

public class GdxSpaceInvaders2 extends Game {
	SpriteBatch batch;
    OrthographicCamera camera;

	private static float fGameTime;

	
	@Override
	public void create () {
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, SettingsManager.SCREEN_WIDTH, SettingsManager.SCREEN_HEIGHT);
		this.setScreen(ScreensManager.getSingleton().getScreen(this, ScreensManager.SCREENS.SPLASH_SCREEN));
        GdxSpaceInvaders2.fGameTime = 0f;
		this.getScreen().show();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		/*batch.begin();
		this.getScreen().render(Gdx.graphics.getDeltaTime());
		batch.end();*/
		super.render();

		GdxSpaceInvaders2.fGameTime += Gdx.graphics.getDeltaTime();
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}

	public static float getGameTime() {
		return GdxSpaceInvaders2.fGameTime;
	}
}

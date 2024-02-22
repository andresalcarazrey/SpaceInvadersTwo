package com.politecnicomalaga.sp2.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.politecnicomalaga.sp2.managers.AssetsManager;
import com.politecnicomalaga.sp2.managers.SettingsManager;
import com.politecnicomalaga.sp2.model.BackgroundParallax;
import com.politecnicomalaga.sp2.model.Battalion;
import com.politecnicomalaga.sp2.model.PlayerSpaceShip;

/**
 * GameScreen Class. Where we play the game and we have the main battle
 * Created by Andrés Alcaraz Rey on 5/11/2022.
 *
 */
public class GameScreen implements Screen {

    private Stage stage;
    private Game game;
    private Battalion empire;
    private PlayerSpaceShip heroShip;
    private NumbersPanel score;
    private SpriteBatch batch;

    public GameScreen(Game aGame) {
        game = aGame;
        score = new NumbersPanel(SettingsManager.SCORE_X,SettingsManager.SCORE_Y, SettingsManager.SCORE_WIDTH);
        score.setData(0);

        stage = new Stage(new ScreenViewport());
        batch = (SpriteBatch)stage.getBatch();

        //Esta orden se puede poner también en el show()
        Gdx.input.setInputProcessor(stage);

        Group bg = new Group();
// the order is important in the following two lines
        stage.addActor(bg);

        //We add the battalion, "the empire"

        empire = new Battalion(game, stage);

        //We add the main player
        heroShip = new PlayerSpaceShip();

        heroShip.addListener(new InputListener() {

            public void clicked(InputEvent event, float x, float y, int pointer, int button) {

            }

            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }


            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                //We set X to the position of the "point" touched, so, the spaceship follows us
                heroShip.setX(event.getStageX());
                heroShip.calculateBodyCircle();
            }
        });
        stage.addActor(heroShip);
        heroShip.setTouchable(Touchable.enabled);

    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen","show");

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();


        batch.begin();
        score.render(batch);
        batch.end();
    }




    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        stage.dispose();
        empire.dispose();
        heroShip.dispose();
        score.dispose();
    }

    public Battalion getBattalion() {
        return empire;
    }

    public void addScore(int scoreToAdd) {
        score.increment(scoreToAdd);
    }

}
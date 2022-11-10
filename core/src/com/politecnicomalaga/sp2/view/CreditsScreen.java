package com.politecnicomalaga.sp2.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.politecnicomalaga.sp2.model.Battalion;
import com.politecnicomalaga.sp2.model.PlayerSpaceShip;

/**
 * CreditsScreen Class. It shows data about the programmers and collaborators
 * Created by Andrés Alcaraz Rey on 10/11/2022.
 *
 */
public class CreditsScreen implements Screen {

    private Stage stage;
    private Game game;


    public CreditsScreen(Game aGame) {
        game = aGame;


        stage = new Stage(new ScreenViewport());

        //Esta orden se puede poner también en el show()
        Gdx.input.setInputProcessor(stage);


    /*
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
            }
        });
        stage.addActor(heroShip);
        heroShip.setTouchable(Touchable.enabled);
*/
    }

    @Override
    public void show() {
        Gdx.app.log("CreditsScreen","show");

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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
    }


}

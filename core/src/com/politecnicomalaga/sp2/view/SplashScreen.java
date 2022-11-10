package com.politecnicomalaga.sp2.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.politecnicomalaga.sp2.GdxSpaceInvaders2;
import com.politecnicomalaga.sp2.managers.AssetsManager;
import com.politecnicomalaga.sp2.managers.ScreensManager;
import com.politecnicomalaga.sp2.model.Battalion;
import com.politecnicomalaga.sp2.model.PlayerSpaceShip;

/**
 * SplashScreen Class. It is showed when the game ends
 * Created by Andrés Alcaraz Rey on 10/11/2022.
 *
 */
public class SplashScreen implements Screen {

    private Stage stage;
    private Game game;

    public SplashScreen(final Game aGame) {
        game = aGame;


        stage = new Stage(new ScreenViewport());

        //Esta orden se puede poner también en el show()
        Gdx.input.setInputProcessor(stage);

        Label title = new Label("Box2D Basic demo", AssetsManager.getTextSkin(),"big-black");
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight()*2/3);
        title.setWidth(Gdx.graphics.getWidth());

        //Añadimos la etiqueta a la pantalla.
        stage.addActor(title);

        //Ahora le toca al botón. Es un botón con texto
        TextButton playButton = new TextButton("Start!", AssetsManager.getTextSkin());
        playButton.setWidth(Gdx.graphics.getWidth()/2);
        playButton.setPosition(Gdx.graphics.getWidth()/2-playButton.getWidth()/2,Gdx.graphics.getHeight()/2-playButton.getHeight()/2);

        //Le añadimos al botón una acción con un listener. MVC
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //Cuando el botón se suelte...
                //Le decimos al juego que coja y asigne una screen nueva, en concreto
                //una GameScreen
                game.setScreen(ScreensManager.getSingleton().getScreen(game, ScreensManager.SCREENS.GAME_SCREEN));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                //Cuando el botón se pulsa no hacermos nada. Pero hay que implementarlo
                return true;
            }
        });
        //El botón también es un actor.
        stage.addActor(playButton);

    }

    @Override
    public void show() {
        Gdx.app.log("SplashScreen","show");

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
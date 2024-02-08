package com.politecnicomalaga.sp2.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.politecnicomalaga.sp2.managers.AssetsManager;
import com.politecnicomalaga.sp2.managers.LanguageManager;
import com.politecnicomalaga.sp2.managers.ScreensManager;
import com.politecnicomalaga.sp2.model.Battalion;
import com.politecnicomalaga.sp2.model.PlayerSpaceShip;

/**
 * GameOverScreen Class. It is showed when the game ends
 * Created by Andrés Alcaraz Rey on 10/11/2022.
 *
 */
public class GameOverScreen implements Screen {

    private Stage stage;
    private Game game;

    public GameOverScreen(Game aGame, String msg) {
        game = aGame;


        stage = new Stage(new ScreenViewport());

        //Esta orden se puede poner también en el show()
        Gdx.input.setInputProcessor(stage);


        Label title = new Label(msg, AssetsManager.getTextSkin(),"big-black");
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight()-title.getHeight()*2);
        title.setWidth(Gdx.graphics.getWidth());

        //Añadimos la etiqueta a la pantalla.
        stage.addActor(title);


        //Ahora le toca a los botones. Son botones con texto
        TextButton continueButton = new TextButton(LanguageManager.getSingleton().getString(LanguageManager.CONTINUE_BUTTON), AssetsManager.getTextSkin());
        continueButton.setWidth(Gdx.graphics.getWidth()/2);
        continueButton.setPosition(Gdx.graphics.getWidth()/2-continueButton.getWidth()/2,Gdx.graphics.getHeight()-continueButton.getHeight()*4);

        //Le añadimos al botón una acción con un listener. MVC
        continueButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //Cuando el botón se suelte...
                //Le decimos al juego que coja y asigne una screen nueva, en concreto
                //una SplashScreen
                game.setScreen(ScreensManager.getSingleton().getScreen(game, ScreensManager.SCREENS.SPLASH_SCREEN,""));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                //Cuando el botón se pulsa no hacermos nada. Pero hay que implementarlo
                return true;
            }
        });
        //El botón también es un actor.
        stage.addActor(continueButton);

    }

    @Override
    public void show() {
        Gdx.app.log("GameOverScreen","show");

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

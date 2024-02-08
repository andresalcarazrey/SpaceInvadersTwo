package com.politecnicomalaga.sp2.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.politecnicomalaga.sp2.managers.AssetsManager;
import com.politecnicomalaga.sp2.managers.GameManager;
import com.politecnicomalaga.sp2.managers.ScreensManager;
import com.politecnicomalaga.sp2.managers.SettingsManager;
import com.politecnicomalaga.sp2.view.GameScreen;

import java.util.Set;

public class EnemyShip extends Actor {

    private Animation<TextureRegion> skin;
    private TextureAtlas atlas;
    private int maxXMovement;
    private float originalX;
    private float velX;
    private float timeToSelfDestruction;
    private boolean bSelfDestruction;


    private Circle body;

    public EnemyShip(int offsetX) {
        super();
        bSelfDestruction = false;
        timeToSelfDestruction = SettingsManager.TIME_TO_SELFDESTRUCTION;
        originalX = -1;
        velX = SettingsManager.ENEMIES_X_VEL;
        maxXMovement = offsetX;
        setBounds(0,0,SettingsManager.ENEMIES_SIZE,SettingsManager.ENEMIES_SIZE);
        double d = Math.random();
        if (d<0.25)
            atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.ATLAS_FILEBLUE));
         else if (d<0.5)
            atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.ATLAS_FILERED));
        else if (d<0.75)
            atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.ATLAS_FILEGREEN));
        else
            atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.ATLAS_FILE));
        skin = new Animation<TextureRegion>(SettingsManager.ENEMY_ANIMATION_VEL, atlas.findRegions(AssetsManager.ENEMY_SPRITES_REGION), Animation.PlayMode.LOOP);
        body = null;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
        TextureRegion currentFrame = skin.getKeyFrame(GameManager.getSingleton().getGameTime(), true);
        batch.draw(currentFrame, this.getX(), this.getY(), SettingsManager.ENEMIES_SIZE, SettingsManager.ENEMIES_SIZE);
    }

    @Override
    public void act(float delta) {
        if (bSelfDestruction) {
            timeToSelfDestruction -= delta;
            if (timeToSelfDestruction < 0f) {
                this.remove();
                this.dispose();
            }
        } else {
            setX(getX() + velX * delta);
            if (Math.abs(getX() - originalX) > maxXMovement) {
                velX = -velX;
            }
            this.calculateBodyCircle();

            //Time to fire? Probability matters...
            if (Math.random() >= SettingsManager.FIRE_PROBABILITY) {
                this.fireBullet();
            }
        }
    }

    @Override
    public void setX(float X) {
        super.setX(X);
        if (originalX == -1) {
            originalX = X;
        }

    }

    public void dispose() {
        if (atlas != null) atlas.dispose();
    }


    public boolean calculateCollisions(HeroBullet hb) {
        boolean result;
        Circle hbBody = hb.getBody();
        if (hbBody != null && body != null) {
            result = body.overlaps(hbBody);
            if (result) {
                //We change to explosion
                skin = new Animation<TextureRegion>(SettingsManager.BULLETS_ANIMATION_VEL, atlas.findRegions(AssetsManager.EXPLOSION_SPRITES_REGION), Animation.PlayMode.LOOP);
                activateSelfDestruction();
            }
            return result;
        } else
            return false;
    }

    public void calculateBodyCircle() {
        body = new Circle(getX()+SettingsManager.MIDENEMIES_SIZE,getY()+SettingsManager.MIDENEMIES_SIZE,SettingsManager.MIDENEMIES_SIZE-SettingsManager.ENEMIES_BODY_AJUST);
    }

    public void fireBullet() {
        GameScreen myScreen = (GameScreen) ScreensManager.getSingleton().getActiveScreen();
        myScreen.getBattalion().fireBullet(this);
    }

    private void activateSelfDestruction() {
        bSelfDestruction = true;
    }
}

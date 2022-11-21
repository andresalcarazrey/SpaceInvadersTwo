package com.politecnicomalaga.sp2.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.politecnicomalaga.sp2.managers.AssetsManager;
import com.politecnicomalaga.sp2.managers.GameManager;
import com.politecnicomalaga.sp2.managers.ScreensManager;
import com.politecnicomalaga.sp2.managers.SettingsManager;
import com.politecnicomalaga.sp2.view.GameScreen;

public class EnemyBullet extends Actor {

    private Animation<TextureRegion> skin;
    private TextureAtlas atlas;
    private EnemyShip myOwner;
    private float velY;
    private Circle body;
    //boolean bEnabled;

    public EnemyBullet(EnemyShip myOwner) {
        super();
        this.myOwner = myOwner;
        body = null;

        //Dims and position
        setBounds(0,0, SettingsManager.ENEMYBULLET_SIZE,SettingsManager.ENEMYBULLET_SIZE);
        setX(myOwner.getX()+SettingsManager.MIDENEMIES_SIZE-SettingsManager.ENEMYBULLET_MIDSIZE);
        setY(myOwner.getY());
        this.calculateBodyCircle();
        velY = -SettingsManager.ENEMYBULLET_VELY;

        atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.ATLAS_FILE));
        skin = new Animation<TextureRegion>(SettingsManager.BULLETS_ANIMATION_VEL, atlas.findRegions(AssetsManager.HEROBULLET_SPRITES_REGION), Animation.PlayMode.LOOP);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion currentFrame = skin.getKeyFrame(GameManager.getSingleton().getGameTime(), true);
        batch.draw(currentFrame, this.getX(), this.getY(), SettingsManager.ENEMYBULLET_SIZE, SettingsManager.ENEMYBULLET_SIZE);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setY(getY()+velY);
        this.calculateBodyCircle();
    }

    public void dispose() {
        if (atlas != null) atlas.dispose();
    }

    public void calculateBodyCircle() {
        body = new Circle(getX()+SettingsManager.ENEMYBULLET_MIDSIZE,getY()+SettingsManager.ENEMYBULLET_MIDSIZE,SettingsManager.ENEMYBULLET_MIDSIZE);
    }


    public Circle getBody() {
        return body;
    }

    public boolean calculateCollision(PlayerSpaceShip player) {
        boolean result;

        Circle playerBody = player.getBody();
        if (playerBody != null && body != null) {
            result = body.overlaps(playerBody);
            if (result) {
                //We change to explosion
                skin = new Animation<TextureRegion>(SettingsManager.BULLETS_ANIMATION_VEL, atlas.findRegions(AssetsManager.EXPLOSION_SPRITES_REGION), Animation.PlayMode.LOOP);
            }
            return result;
        } else
            return false;
    }

}

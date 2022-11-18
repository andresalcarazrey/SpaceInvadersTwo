package com.politecnicomalaga.sp2.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.politecnicomalaga.sp2.managers.AssetsManager;
import com.politecnicomalaga.sp2.managers.GameManager;
import com.politecnicomalaga.sp2.managers.SettingsManager;

import java.util.Set;

public class EnemyShip extends Actor {

    private Animation<TextureRegion> skin;
    private TextureAtlas atlas;

    public EnemyShip() {
        super();
        setBounds(0,0,SettingsManager.ENEMIES_SIZE,SettingsManager.ENEMIES_SIZE);
        atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.ATLAS_FILE));
        skin = new Animation<TextureRegion>(SettingsManager.ENEMY_ANIMATION_VEL, atlas.findRegions(AssetsManager.ENEMY_SPRITES_REGION), Animation.PlayMode.LOOP);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion currentFrame = skin.getKeyFrame(GameManager.getSingleton().getGameTime(), true);
        batch.draw(currentFrame, this.getX(), this.getY(), SettingsManager.ENEMIES_SIZE, SettingsManager.ENEMIES_SIZE);
    }


    public void dispose() {
        if (atlas != null) atlas.dispose();
    }


    public boolean calculateCollisions(HeroBullet hb) {
        Rectangle enemyR, bulletR;

        enemyR = new Rectangle(getX(),getY(),getWidth(),getHeight());
        bulletR = new Rectangle(hb.getX(),hb.getY(),hb.getWidth(),hb.getHeight());
        return enemyR.overlaps(bulletR);
    }
}

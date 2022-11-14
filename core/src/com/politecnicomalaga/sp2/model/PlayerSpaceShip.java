package com.politecnicomalaga.sp2.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.politecnicomalaga.sp2.managers.AssetsManager;
import com.politecnicomalaga.sp2.managers.GameManager;
import com.politecnicomalaga.sp2.managers.SettingsManager;

public class PlayerSpaceShip extends Actor {

    private Animation<TextureRegion> skin;
    private TextureAtlas atlas;

    private Array<HeroBullet> myActiveBullets;

    private Array<HeroBullet> myUsedBullets;
    private float timeToFire;

    public PlayerSpaceShip() {
        super();
        timeToFire = 0f;
        myActiveBullets = new Array<HeroBullet>();
        myUsedBullets = new Array<HeroBullet>();

        atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.ATLAS_FILE));
        skin = new Animation<TextureRegion>(SettingsManager.PLAYER_ANIMATION_VEL, atlas.findRegions(AssetsManager.PLAYER_SPRITES_REGION), Animation.PlayMode.LOOP);
        this.setBounds(0,0,SettingsManager.PLAYER_SIZE,SettingsManager.PLAYER_SIZE);
        this.setX(SettingsManager.SCREEN_WIDTH/2 - SettingsManager.MIDPLAYER_SIZE);
        this.setY(SettingsManager.MIDPLAYER_SIZE);

    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        //We could give the bullets to the stage, or we can draw them ourselves
        //We have to manage the bullets, ative, prepared to reuse, etc. The best option,
        //manage ourselves our bullets.
        for (HeroBullet b: myActiveBullets) {
            b.draw(batch, parentAlpha);
        }

        TextureRegion currentFrame = skin.getKeyFrame(GameManager.getSingleton().getGameTime(), true);
        batch.draw(currentFrame, this.getX(), this.getY(), getWidth(),getHeight());


    }

    @Override
    public void act(float delta) {
        timeToFire += delta;
        if (timeToFire >= SettingsManager.FIRE_PLAYER_TIME) {
            timeToFire = 0f;
            this.fireBullet();
        }

        //Search for bullets to recycle
        if (myActiveBullets.size != 0) {
            if (myActiveBullets.get(0).getY() > SettingsManager.SCREEN_HEIGHT) {
                this.prepareForReuse(myActiveBullets.get(0));
            }
        }

        //Tells the bullets to act. Only active ones
        for (HeroBullet b: myActiveBullets) {
            b.act(delta);
        }
    }


    //Memory dispose
    public void dispose() {
        if (atlas != null) atlas.dispose();
        for (HeroBullet b:myUsedBullets) {
            b.dispose();
        }
        for (HeroBullet b:myActiveBullets) {
            b.dispose();
        }

    }

    public void fireBullet() {
        if (myUsedBullets.size == 0)
            myActiveBullets.add(new HeroBullet(this));
        else {
            //Get the first one...
            HeroBullet usedBullet = myUsedBullets.get(0);

            //change the array index to know where is it and we reactivate it
            myActiveBullets.add(usedBullet);

            //remove from used bullets array pool
            myUsedBullets.removeIndex(0);

            //Change the position
            usedBullet.setX(this.getX()+SettingsManager.MIDPLAYER_SIZE-SettingsManager.MIDHEROBULLET_SIZE);
            usedBullet.setY(this.getY());
        }
    }

    private void prepareForReuse(HeroBullet usedBullet) {
        myActiveBullets.removeIndex(0);
        myUsedBullets.add(usedBullet);
    }
}

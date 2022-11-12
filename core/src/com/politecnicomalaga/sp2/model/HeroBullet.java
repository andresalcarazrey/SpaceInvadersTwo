package com.politecnicomalaga.sp2.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.politecnicomalaga.sp2.managers.AssetsManager;
import com.politecnicomalaga.sp2.managers.GameManager;
import com.politecnicomalaga.sp2.managers.SettingsManager;

public class HeroBullet extends Actor {
    private Animation<TextureRegion> skin;
    private TextureAtlas atlas;
    private PlayerSpaceShip myOwner;
    private float velY;

    public HeroBullet(PlayerSpaceShip myOwner) {
        super();
        this.myOwner = myOwner;

        //Dims and position
        setBounds(0,0,SettingsManager.HEROBULLET_SIZE,SettingsManager.HEROBULLET_SIZE);
        setX(myOwner.getX());
        setY(myOwner.getY());
        velY = SettingsManager.HEROBULLET_VELY;

        atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.ATLAS_FILE));
        skin = new Animation<TextureRegion>(SettingsManager.ENEMY_ANIMATION_VEL, atlas.findRegions(AssetsManager.HEROBULLET_SPRITES_REGION), Animation.PlayMode.LOOP);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion currentFrame = skin.getKeyFrame(GameManager.getSingleton().getGameTime(), true);
        batch.draw(currentFrame, this.getX(), this.getY(), SettingsManager.HEROBULLET_SIZE, SettingsManager.HEROBULLET_SIZE);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setY(getY()+velY);

    }

    public void dispose() {
        if (atlas != null) atlas.dispose();
    }
}
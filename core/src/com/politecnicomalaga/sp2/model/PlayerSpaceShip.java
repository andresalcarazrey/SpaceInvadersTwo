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

public class PlayerSpaceShip extends Actor {

    private Animation<TextureRegion> skin;

    public PlayerSpaceShip() {
        super();
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(AssetsManager.ATLAS_FILE));
        skin = new Animation<TextureRegion>(SettingsManager.PLAYER_ANIMATION_VEL, atlas.findRegions(AssetsManager.PLAYER_SPRITES_REGION), Animation.PlayMode.LOOP);
        this.setBounds(0,0,SettingsManager.PLAYER_SIZE,SettingsManager.PLAYER_SIZE);
        this.setX(SettingsManager.SCREEN_WIDTH/2 - SettingsManager.MIDPLAYER_SIZE);
        this.setY(SettingsManager.MIDPLAYER_SIZE);


    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TextureRegion currentFrame = skin.getKeyFrame(GameManager.getSingleton().getGameTime(), true);
        batch.draw(currentFrame, this.getX(), this.getY(), getWidth(),getHeight());
    }
}

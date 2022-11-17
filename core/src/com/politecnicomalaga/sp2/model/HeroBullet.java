package com.politecnicomalaga.sp2.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.politecnicomalaga.sp2.managers.AssetsManager;
import com.politecnicomalaga.sp2.managers.GameManager;
import com.politecnicomalaga.sp2.managers.ScreensManager;
import com.politecnicomalaga.sp2.managers.SettingsManager;
import com.politecnicomalaga.sp2.view.GameScreen;

public class HeroBullet extends Actor {
    private Animation<TextureRegion> skin;
    private TextureAtlas atlas;
    private PlayerSpaceShip myOwner;
    private float velY;
    boolean bEnabled;

    public HeroBullet(PlayerSpaceShip myOwner) {
        super();
        this.myOwner = myOwner;
        bEnabled = true;

        //Dims and position
        setBounds(0,0,SettingsManager.HEROBULLET_SIZE,SettingsManager.HEROBULLET_SIZE);
        setX(myOwner.getX()+SettingsManager.MIDPLAYER_SIZE-SettingsManager.MIDHEROBULLET_SIZE);
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

        //Now we have to search for collisions....
        //For efficiency, we use squadron Y to avoid extra calculations
        //We give this object to Battalion, it search for the squadron with the more or less same Y
        //and then the enemy with more or less X.

        GameScreen myScreen = (GameScreen)ScreensManager.getSingleton().getActiveScreen();
        if (myScreen.getBattalion().calculateCollisions(this)) {
            //we touch an enemy. The enemy is dead. We have to remove this bullet
            myOwner.removeBullet(this);
            this.dispose();

        }



    }

    public boolean isEnabled() {
        return bEnabled;
    }

    public void setEnabled(){
        bEnabled = true;
    }

    public void setDisabled(){
        bEnabled = false;
    }

    public void dispose() {
        if (atlas != null) atlas.dispose();
    }

}

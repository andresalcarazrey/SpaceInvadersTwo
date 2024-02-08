package com.politecnicomalaga.sp2.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.politecnicomalaga.sp2.managers.ScreensManager;
import com.politecnicomalaga.sp2.managers.SettingsManager;
import com.politecnicomalaga.sp2.view.GameScreen;

import java.util.ArrayList;

public class Battalion {
    private Array<Squadron> squadrons;
    private Array<EnemyBullet> myActiveBullets;
    private Array<EnemyBullet> myUsedBullets;
    private Game game;

    private int totalShips;

    public Battalion(Game game, Stage baseStage) {
        short posY, offsetY;

        this.game = game;

        //Initiate the arraylist
        squadrons = new Array<Squadron>();

        offsetY = SettingsManager.SCREEN_HEIGHT / (2*SettingsManager.SQUADRON_PER_BATTALION);
        posY = (short)(SettingsManager.SCREEN_HEIGHT - offsetY);


        //We have to create all the squadrons
        for (short i = 0; i< SettingsManager.SQUADRON_PER_BATTALION; i++) {
            Squadron newSquad = new Squadron(baseStage, (short)(posY - i*offsetY));
            squadrons.add(newSquad);
        }

        totalShips = SettingsManager.SQUADRON_PER_BATTALION*SettingsManager.ENEMIES_PER_SQUADRON;

        //Bullets
        myActiveBullets = new Array<EnemyBullet>();
        myUsedBullets = new Array<EnemyBullet>();
    }


    //Memory dispose
    public void dispose() {
        for (Squadron sq: squadrons) {
            sq.dispose();
        }

        for (EnemyBullet b:myUsedBullets) {
            b.dispose();
        }
        for (EnemyBullet b:myActiveBullets) {
            b.dispose();
        }
    }

    //Collisions between a bullet and my soldiers
    public boolean calculateCollisions(HeroBullet heroBullet) {
        boolean bResult = false;
        int indexSquad = 0;

        while (!bResult && indexSquad < squadrons.size) {
            bResult = squadrons.get(indexSquad).calculateCollisions(heroBullet);
            indexSquad++;
        }

        if (bResult) { //update score
            GameScreen myScreen = (GameScreen)(ScreensManager.getSingleton().getActiveScreen());
            myScreen.addScore(SettingsManager.ENEMIES_SCORE);
            totalShips--;
        }

        return bResult;
    }

    public void fireBullet(EnemyShip shooter) {
        if (myUsedBullets.size == 0)
            myActiveBullets.add(new EnemyBullet(shooter));
        else {
            //Get the first one...
            EnemyBullet usedBullet = myUsedBullets.get(0);

            //usedBullet.setEnabled();
            //change the array index to know where is it and we reactivate it
            myActiveBullets.add(usedBullet);

            //remove from used bullets array pool
            myUsedBullets.removeIndex(0);

            //Change the position
            usedBullet.setX(shooter.getX()+SettingsManager.MIDENEMIES_SIZE-SettingsManager.ENEMYBULLET_MIDSIZE);
            usedBullet.setY(shooter.getY());
            usedBullet.calculateBodyCircle();
        }
    }

    //Batallion is not an actor. It has the control of all the bullets. But somebody has to tell it
    //to act. Don't forget to call it from stage/screen
    public void act(PlayerSpaceShip player, float delta) {
        //Search for bullets to recycle
        if (myActiveBullets.size != 0) {
            if (myActiveBullets.get(0).getY() < 0) {
                this.prepareForReuse(myActiveBullets.get(0));
            }
        }

        //Tells the bullets to act. Only active ones
        for (EnemyBullet b: myActiveBullets) {
            b.act(delta);
        }

        //Calculate collisions against Player
        boolean bResult = false;
        int index = 0;

        while (!bResult && index < myActiveBullets.size) {
            bResult = myActiveBullets.get(index).calculateCollision(player);
            index++;
        }

        if (bResult) {
            //We are dead...
            //here: change to game over...
            game.setScreen(ScreensManager.getSingleton().getScreen(game, ScreensManager.SCREENS.GAMEOVER_SCREEN));

        }

        //Now time to show "Congrats Screen if we win
        if (this.aliveSpaceShips() == 0) {
            game.setScreen(ScreensManager.getSingleton().getScreen(game, ScreensManager.SCREENS.GAMEOVER_SCREEN));
        }
    }

    public void draw(Batch batch) {
        for (EnemyBullet b:myActiveBullets) {
            b.draw(batch,1f);
        }
    }




    private void prepareForReuse(EnemyBullet usedBullet) {
        myActiveBullets.removeIndex(0);
        myUsedBullets.add(usedBullet);
    }

    public void removeBullet(EnemyBullet bulletToRemove) {
        //We have to search for this bullet in active list and move to used list
        myActiveBullets.removeValue(bulletToRemove,true);
        myUsedBullets.add(bulletToRemove);
    }

    public int aliveSpaceShips() {
        return totalShips;
    }
}

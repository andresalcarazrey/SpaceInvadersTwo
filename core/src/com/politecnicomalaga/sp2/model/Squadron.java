package com.politecnicomalaga.sp2.model;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.politecnicomalaga.sp2.managers.SettingsManager;

public class Squadron {

    private Array<EnemyShip> troops;

    public Squadron(Stage baseStage, short posY) {
        short offsetX, posX;

        //Initiate the arraylist
        troops = new Array<EnemyShip>();

        posX = SettingsManager.SCREEN_WIDTH / (SettingsManager.ENEMIES_PER_SQUADRON + 1);
        offsetX = posX;
        posX -= SettingsManager.MIDENEMIES_SIZE;


        //We have to create all the squadrons
        for (short i=0;i<SettingsManager.ENEMIES_PER_SQUADRON;i++) {
            EnemyShip newEnemy = new EnemyShip();
            newEnemy.setX(posX+i*offsetX);
            newEnemy.setY(posY);
            baseStage.addActor(newEnemy);
        }
    }
}

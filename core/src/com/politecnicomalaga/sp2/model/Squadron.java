package com.politecnicomalaga.sp2.model;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.politecnicomalaga.sp2.managers.SettingsManager;

public class Squadron {

    private Array<EnemyShip> troops;
    private Stage baseStage;

    public Squadron(Stage baseStage, short posY) {
        short offsetX, posX;

        //Initiate the arraylist
        troops = new Array<EnemyShip>();

        this.baseStage = baseStage;
        posX = SettingsManager.SCREEN_WIDTH / (SettingsManager.ENEMIES_PER_SQUADRON + 1);
        offsetX = posX;
        posX -= SettingsManager.MIDENEMIES_SIZE;


        //We have to create all the squadrons
        for (short i=0;i<SettingsManager.ENEMIES_PER_SQUADRON;i++) {
            EnemyShip newEnemy = new EnemyShip(offsetX);
            newEnemy.setX(posX+i*offsetX);
            newEnemy.setY(posY);
            baseStage.addActor(newEnemy);
            troops.add(newEnemy);
        }
    }

    //Memory dispose
    public void dispose() {
        for (EnemyShip ship: troops) {
            ship.dispose();
        }
    }


    //Collisions between a bullet and my soldiers
    public boolean calculateCollisions(HeroBullet heroBullet) {
        boolean bResult = false;
        int indexTroops = 0;

        while (!bResult && indexTroops < this.troops.size) {
            bResult = troops.get(indexTroops).calculateCollisions(heroBullet);
            indexTroops++;
        }

        if (bResult) {
            //We have a collision. We need to remove the enemy from the list.
            EnemyShip deadEnemy = troops.removeIndex(indexTroops-1);
            deadEnemy.remove();
            deadEnemy.dispose();

        }
        return bResult;
    }
}

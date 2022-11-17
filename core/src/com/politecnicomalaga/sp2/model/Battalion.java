package com.politecnicomalaga.sp2.model;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.politecnicomalaga.sp2.managers.SettingsManager;

import java.util.ArrayList;

public class Battalion {
    private Array<Squadron> squadrons;

    public Battalion(Stage baseStage) {
        short posY, offsetY;

        //Initiate the arraylist
        squadrons = new Array<Squadron>();
        offsetY = SettingsManager.SCREEN_HEIGHT / (2*SettingsManager.SQUADRON_PER_BATTALION);
        posY = (short)(SettingsManager.SCREEN_HEIGHT - offsetY);


        //We have to create all the squadrons
        for (short i = 0; i< SettingsManager.SQUADRON_PER_BATTALION; i++) {
            Squadron newSquad = new Squadron(baseStage, (short)(posY - i*offsetY));
        }
    }


    //Memory dispose
    public void dispose() {
        for (Squadron sq: squadrons) {
            sq.dispose();
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

        return bResult;
    }
}

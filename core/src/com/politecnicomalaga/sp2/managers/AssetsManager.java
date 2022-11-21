package com.politecnicomalaga.sp2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetsManager {
    //Constantes
    public static final String NUMBERS_SPRITES = "numbers/digito";
    public static final String NUMBERS_EXT_SPRITES =".png";
    public static final String ENEMY_SPRITES_REGION ="enemy";
    public static final String HEROBULLET_SPRITES_REGION ="herobullets";
    public static final String ENEMYBULLET_SPRITES_REGION ="enemybullets";
    public static final String ATLAS_FILE ="sp2.atlas";
    public static final String PLAYER_SPRITES_REGION ="player";
    public static final String EXPLOSION_SPRITES_REGION ="explosion";


    private static Skin textSkin;

    public static Skin getTextSkin() {
        //Carga la fuente para poner textos en la splash screen
        if (textSkin == null) {
            textSkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        }
        return textSkin;
    }

}

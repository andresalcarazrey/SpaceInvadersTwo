package com.politecnicomalaga.sp2.managers;

public class LanguageManager {

    //Language Available
    public static enum Lang {
        SPANISH,
        ENGLISH
    }

    private static final String[] spanishTexts = {"Empezar", "Configuracion", "Creditos","Español","Inglés","Space Invaders 2","Idioma"};
    private static final String[] englishTexts = {"Start", "Settings", "Credits","Spanish", "English", "Space Invaders 2", "Language"};

    //UI Components used in the game
    public static final int START_BUTTON = 0;
    public static final int SETTINGS_BUTTON = 1;
    public static final int CREDITS_BUTTON = 2;
    public static final int SPANISH_BUTTON = 3;
    public static final int ENGLISH_BUTTON = 4;
    public static final int SPLASH_LABEL = 5;
    public static final int SETTINGS_LABEL = 6;


    //Active Language
    private Lang activeLanguage;
    private String[] activeTexts;

    //SINGLETON
    private static LanguageManager singleton;

    private LanguageManager() {
        activeLanguage = Lang.SPANISH;
        activeTexts = spanishTexts;
    }


    public static LanguageManager getSingleton() {
        if (singleton == null) {
            singleton = new LanguageManager();
        }
        return singleton;
    }


    //Get Strings in different languages
    public String getString(int uiDescriptor) {
        return activeTexts[uiDescriptor];
    }


    //Change Language
    public void setActiveLanguage(Lang newLang) {
        activeLanguage = newLang;
        if (newLang == Lang.SPANISH)
            activeTexts = spanishTexts;
        else
            activeTexts = englishTexts;
    }
}

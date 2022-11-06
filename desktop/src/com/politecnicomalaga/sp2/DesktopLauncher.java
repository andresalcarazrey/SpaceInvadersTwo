package com.politecnicomalaga.sp2;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.politecnicomalaga.sp2.GdxSpaceInvaders2;
import com.politecnicomalaga.sp2.managers.SettingsManager;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Space Invaders 2");
		config.setWindowedMode(SettingsManager.SCREEN_WIDTH,SettingsManager.SCREEN_HEIGHT);
		new Lwjgl3Application(new GdxSpaceInvaders2(), config);
	}
}

package com.politecnicomalaga.sp2;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.politecnicomalaga.sp2.GdxSpaceInvaders2;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		//Activamos el hw para control por movimiento
		config.useAccelerometer = true;
		config.useCompass = true;


		initialize(new GdxSpaceInvaders2(), config);
	}
}

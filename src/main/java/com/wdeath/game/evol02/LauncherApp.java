package com.wdeath.game.evol02;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.math.RandomXS128;

import java.util.Random;

public class LauncherApp {

    public static Random rnd = new RandomXS128(1);

    public static void main(String[] args) {
        LwjglApplicationConfiguration cnf = new LwjglApplicationConfiguration();
        cnf.width = 800;
        cnf.height = 600;
        cnf.title = "Evol 02";
        cnf.resizable = false;

        MainGame game = new MainGame();
        LwjglApplication app = new LwjglApplication(game, cnf);
    }
}

package com.wdeath.game.evol02.world.entity;

import com.badlogic.gdx.utils.Pool;
import com.wdeath.game.evol02.LauncherApp;
import com.wdeath.game.evol02.mind.MindEngine;
import com.wdeath.game.evol02.world.World;

public class Bot implements Pool.Poolable {

    public static int MAX_HEALTH = 100, MAX_FOOD = 100;

    public static final int UP = 0, UR = 1, RIGHT = 2, DR = 3, DOWN = 4, DL = 5, LEFT = 6, UL = 7;

    public static final int[][] dirs = new int[8][2];

    static{
        dirs[0][0] = 0;
        dirs[0][1] = 1;

        dirs[1][0] = 1;
        dirs[1][1] = 1;

        dirs[2][0] = 1;
        dirs[2][1] = 0;

        dirs[3][0] = 1;
        dirs[3][1] = -1;

        dirs[4][0] = 0;
        dirs[4][1] = -1;

        dirs[5][0] = -1;
        dirs[5][1] = -1;

        dirs[6][0] = -1;
        dirs[6][1] = 0;

        dirs[7][0] = 1;
        dirs[7][1] = -1;
    }

    public int x, y;
    public int health;
    public MindEngine mind;
    public World world;
    public float rC, gC, bC;
    public int dir;
    public int food;

    public Bot(){
        mind = new MindEngine(this);
        reset();
    }

    public void process(){
        mind.process();
        health -= 1;
        death();
        mutation();
    }

    private void mutation(){
        if(health < MAX_HEALTH && food < MAX_FOOD)
            return;
        if(health >= MAX_HEALTH){
            health /= 2;
        }
        if(food >= MAX_FOOD){
            food /= 2;
        }
        int mi = LauncherApp.rnd.nextInt(MindEngine.MAX_LENGTH_MIND);
        int a = LauncherApp.rnd.nextInt(MindEngine.MAX_LENGTH_MIND);
        mind.arguments[mi] = a;
    }

    private void death(){
        if(health > 0)
            return;
        world.removeBot(this);
    }

    public int[] getDir(int dirL){
        dirL += dir;
        if(dirL >= 8)
            dirL = dirL - 8;
        return dirs[dirL];
    }

    public void move(int xd, int yd){
        setPosition(x + xd, y + yd);
    }

    public void setPosition(int x, int y){
        if (!world.isFree(x, y))
            return;
        world.setPositionBot(this, x, y);
    }

    public void toRed(){
        rC += 0.1;
        gC += -0.05;
        bC += -0.05;

//        rC = 1;
//        gC = 0;
//        bC = 0;
    }

    public void toGreen(){
        rC += -0.05;
        gC += 0.1;
        bC += -0.05;
//        rC = 0;
//        gC = 1;
//        bC = 0;
    }

    public void toBlue(){
        rC += -0.05;
        gC += -0.05;
        bC += 0.1;
//        rC = 0;
//        gC = 0;
//        bC = 1;
    }

    @Override
    public void reset() {
        health = 20;
        food = 5;
        dir = UP;
        rC = 1f/3;
        gC = 1f/3;
        bC = 1f/3;
    }
}

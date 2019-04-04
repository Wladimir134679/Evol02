package com.wdeath.game.evol02.mind;

import com.wdeath.game.evol02.LauncherApp;
import com.wdeath.game.evol02.world.entity.Bot;

import java.util.Random;

public class MindEngine {

    public static int MAX_LENGTH_MIND = 64;

    public int[] commands;
    public int[] arguments;

    public int utk;
    public Bot bot;

    public boolean stop;

    public MindEngine(Bot bot){
        this.bot = bot;

        commands = new int[MAX_LENGTH_MIND];
        arguments = new int[MAX_LENGTH_MIND];
        utk = 0;

    }

    public void process(){
        stop = false;
        for(int i = 0; i < 15 && !stop; i++){

            MindCommand command = MindCommands.minds.get(commands[getUTK()]);
            if(command == null){
                step(commands[getUTK()]);
            }else{
                command.process(this);
            }

            utk++;
        }
    }

    public void cloneMind(MindEngine mind, float s){
        for(int mi = 0; mi < MAX_LENGTH_MIND; mi++){
            mind.commands[mi] = this.commands[mi];
            mind.arguments[mi] = this.arguments[mi];
        }
        Random rnd = LauncherApp.rnd;
        if(rnd.nextFloat() < s){
            boolean b = rnd.nextBoolean();
            if(b) {
                int mi = rnd.nextInt(MAX_LENGTH_MIND);
                int p = rnd.nextInt(MAX_LENGTH_MIND);
                mind.commands[mi] = p;
            }else {
                int mia = rnd.nextInt(MAX_LENGTH_MIND);
                int pa = rnd.nextInt(MAX_LENGTH_MIND);
                mind.arguments[mia] = pa;
            }
        }
    }

    public void step(int u){
        utk += u;
        utk = getUTKW(utk);
    }

    public int getUTK(){
        return getUTKW(utk);
    }

    public int getUTKW(int u){
        if(u < 0)
            return MAX_LENGTH_MIND + u;
        if(u >= MAX_LENGTH_MIND)
            return u - MAX_LENGTH_MIND * (int)Math.floor(u / (float)MAX_LENGTH_MIND);
        return u;
    }

    public void stop(){
        stop = true;
    }
}

package com.wdeath.game.evol02.world;

public class ThreadRenderWorld {

    private Thread thread;
    private World world;
    private boolean run, sleep;
    public float timeUpdate, timeStart;

    public ThreadRenderWorld(World world) {
        this.world = world;
        thread = new Thread(this::run);
    }

    public void start(){
        sleep = false;
        run = true;
        thread.start();
    }

    private void run(){
        timeStart = 0;
        timeUpdate = 0;
        while(run){
            timeStart = System.nanoTime();
            world.process();
            timeUpdate = (System.nanoTime() - timeStart) / 1_000_000_000f;

            sleep(10);
            while(sleep){
                sleep(1000/30);
            }
        }
    }

    private void sleep(long i){
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

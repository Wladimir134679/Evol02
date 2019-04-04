package com.wdeath.game.evol02.world;

import com.wdeath.game.evol02.world.entity.Bot;

import java.util.ArrayList;

public class World {

    public static int WIDTH_CELL = 8, HEIGHT_CELL = 8;

    public final int w, h;

    private ArrayList<Bot> bots;
    private Bot[][] botsMap;
    private int inter = 0;
    public long tik = 0;

    public World(int w, int h){
        this.w = w;
        this.h = h;
        botsMap = new Bot[w][h];
        bots = new ArrayList<>();
    }

    public void addBot(Bot b){
        b.world = this;
        bots.add(b);
        botsMap[b.x][b.y] = b;
    }

    public void removeBot(Bot b){
        PoolBot.pool.free(b);
        bots.remove(b);
        botsMap[b.x][b.y] = null;
    }

    public Bot getBot(int x, int y){
        x = getXW(x);
        y = getYW(y);
        return botsMap[x][y];
    }

    public void setPositionBot(Bot bot, int x, int y){
        x = getXW(x);
        y = getYW(y);
        botsMap[bot.x][bot.y] = null;
        bot.x = x;
        bot.y = y;
        botsMap[x][y] = bot;
    }

    public boolean isFree(int x, int y){
        return getBot(x, y) == null;
    }

    public void process(){
        tik++;
        inter = 0;
        for(inter = 0; inter < bots.size(); inter++){
            Bot bot = bots.get(inter);
            bot.process();
        }
    }

    public int getXW(int u){
        if(u < 0)
            return w + u;
        if(u >= w)
            return u - w * (int)Math.floor(u / (float)w);
        return u;
    }

    public int getYW(int u){
        if(u < 0)
            return h + u;
        if(u >= h)
            return u - h * (int)Math.floor(u / (float)h);
        return u;
    }

    public Bot[][] getBotsMap(){
        return botsMap;
    }
}

package com.wdeath.game.evol02.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.wdeath.game.evol02.world.entity.Bot;

import java.util.Arrays;

public class WorldDraw {

    public World world;
    public Bot[][] botsMap;

    public WorldDraw(World world) {
        this.world = world;
        botsMap = new Bot[world.w][world.h];

    }

    public void draw(OrthographicCamera camera, ShapeRenderer shape){
        int wc = (int)(camera.viewportWidth * camera.zoom) / World.WIDTH_CELL + 10;
        int hc = (int)(camera.viewportHeight * camera.zoom) / World.HEIGHT_CELL + 10;
        int x = (int)(camera.position.x / World.WIDTH_CELL  - wc/2f) - 1;
        int y = (int)(camera.position.y / World.HEIGHT_CELL - hc/2f) - 1;

//        for(int yi = 0; yi < world.h; yi++){
//            for(int xi = 0; xi < world.w; xi++){
//                botsMap[xi][yi] = world.getBotsMap()[xi][yi];
//            }
//        }

        shape.begin(ShapeRenderer.ShapeType.Filled);
        for(int xi = x; xi < x + wc; xi++){
            for(int yi = y; yi < y + hc; yi++){
                drawBot(camera, shape, xi, yi);
            }
        }
        shape.end();
    }

    public void drawBot(OrthographicCamera camera, ShapeRenderer shape, int x, int y){
        if(x < 0 || y < 0 || x >= world.w || y >= world.h)
            return;
        Bot bot = world.getBot(x, y);//[x][y];
        if (bot == null)
            return;
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
            shape.setColor(colorFood(bot));
        }else if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            shape.setColor(colorHealth(bot));
        }else{
            shape.setColor(bot.rC, bot.gC, bot.bC, 1);
        }
        shape.rect(x * World.WIDTH_CELL, y * World.HEIGHT_CELL, World.WIDTH_CELL, World.HEIGHT_CELL);
    }

    public Color colorFood(Bot bot){
        Color color = new Color();
        float p = bot.food / (float)Bot.MAX_FOOD;
        if(p > 1)
            p = 1;
        if(p < 0)
            p = 0;
        color.r = 1 - p;
        color.g = p;
        return color;
    }

    public Color colorHealth(Bot bot){
        Color color = new Color();
        float p = bot.health / (float)Bot.MAX_HEALTH;
        if(p > 1)
            p = 1;
        if(p < 0)
            p = 0;
        color.r = 1 - p;
        color.g = p;
        return color;
    }
}

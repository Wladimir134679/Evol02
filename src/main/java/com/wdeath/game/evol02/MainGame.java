package com.wdeath.game.evol02;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.wdeath.game.evol02.mind.MindCommands;
import com.wdeath.game.evol02.mind.MindEngine;
import com.wdeath.game.evol02.world.PoolBot;
import com.wdeath.game.evol02.world.ThreadRenderWorld;
import com.wdeath.game.evol02.world.World;
import com.wdeath.game.evol02.world.WorldDraw;
import com.wdeath.game.evol02.world.entity.Bot;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class MainGame extends Game implements InputProcessor {

    ShapeRenderer shape;
    OrthographicCamera camera;
    SpriteBatch batchGui;
    BitmapFont font;

    public World world;
    public WorldDraw worldDraw;
    public ThreadRenderWorld threadRenderWorld;

    @Override
    public void create() {
        MindCommands.init();
        PoolBot.init();

        shape = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batchGui = new SpriteBatch();
        font = new BitmapFont();

        world = new World(100, 100);
        worldDraw = new WorldDraw(world);

        Random rnd = new Random(1);
        for(int x = 0; x < world.w; x++){
            for(int y = 0; y < world.h; y++){
                if(rnd.nextFloat() < 0.75f)
                    continue;
                Bot b = PoolBot.pool.obtain();
                b.x = x;
                b.y = y;
                for(int mi = 0; mi < b.mind.commands.length; mi++){
                    b.mind.commands[mi] = rnd.nextInt(MindEngine.MAX_LENGTH_MIND);
                    b.mind.arguments[mi] = rnd.nextInt(MindEngine.MAX_LENGTH_MIND);
                }
                world.addBot(b);
            }
        }

        threadRenderWorld = new ThreadRenderWorld(world);
        threadRenderWorld.start();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        camera.update();
        shape.setProjectionMatrix(camera.combined);
        worldDraw.draw(camera, shape);

        batchGui.begin();
        String timeUp = String.valueOf("timeUp: " + threadRenderWorld.timeUpdate);
        font.draw(batchGui, timeUp, 5, 50);
        batchGui.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        camera.position.x -= Gdx.input.getDeltaX() * camera.zoom;
        camera.position.y += Gdx.input.getDeltaY() * camera.zoom;
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if(amount > 0)
            camera.zoom += 0.05f;
        if(amount < 0)
            camera.zoom -= 0.05f;
        return false;
    }
}

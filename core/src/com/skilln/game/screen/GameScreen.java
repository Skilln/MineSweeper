package com.skilln.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.skilln.game.stage.GameStage;
import com.skilln.game.values.Values;

public class GameScreen implements Screen {

    private static final String TAG = "GAME_SCREEN";

    private GameStage gameStage;
    private OrthographicCamera camera;

    private SpriteBatch batch;
    private FitViewport viewport;

    public GameScreen () {

    }

    @Override
    public void show() {

        camera = new OrthographicCamera(Values.GAME_WIDTH, Values.GAME_HEIGHT);
        camera.position.set(camera.viewportWidth , camera.viewportHeight, 0);

        batch = new SpriteBatch();
        viewport = new FitViewport(Values.GAME_WIDTH, Values.GAME_HEIGHT);

        gameStage = new GameStage(viewport, batch);

        gameStage.start(10, 10,  9);

        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void render(float delta) {
        gameStage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        gameStage.dispose();
    }
}

package com.skilln.game.io.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.skilln.game.values.Strings;

public class AtlasLoader {

    //LOG
    public static final String TAG = "ATLAS_LOADER";

    private AssetManager assetManager = null;

    public AtlasLoader() {
        Gdx.app.log(TAG, "Initialised");

        assetManager = new AssetManager();

        loadAtlas(Strings.LOGO_ATLAS);
    }

    private void loadAtlas(String atlasName) {
        assetManager.load(atlasName, TextureAtlas.class);

        assetManager.update();

        assetManager.finishLoading();

        Gdx.app.log(TAG, "Loaded : " + atlasName);
    }

    private void loadTexture(String texutureName) {
        assetManager.load(texutureName, Texture.class);

        assetManager.update();

        assetManager.finishLoading();

        Gdx.app.log(TAG, "Loaded : " + texutureName);
    }

    public <T> T get(String path) {
        return assetManager.get(path);
    }

}

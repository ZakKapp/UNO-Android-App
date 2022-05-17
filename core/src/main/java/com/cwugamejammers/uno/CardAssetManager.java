package com.cwugamejammers.uno;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class CardAssetManager {
    public final AssetManager manager = new AssetManager();



    public void loadImages(){
        manager.load("cards/B0.jpeg", Texture.class);
        manager.load("cards/B1.jpeg", Texture.class);
        manager.load("cards/B2.jpeg", Texture.class);
        manager.load("cards/B3.jpeg", Texture.class);
        manager.load("cards/B4.jpeg", Texture.class);
        manager.load("cards/B5.jpeg", Texture.class);
        manager.load("cards/B6.jpeg", Texture.class);
        manager.load("cards/B7.jpeg", Texture.class);
        manager.load("cards/B8.jpeg", Texture.class);
        manager.load("cards/B9.jpeg", Texture.class);
        manager.load("cards/B10.jpeg", Texture.class);
        manager.load("cards/B11.jpeg", Texture.class);
        manager.load("cards/B12.jpeg", Texture.class);

        manager.load("cards/G0.jpeg", Texture.class);
        manager.load("cards/G1.jpeg", Texture.class);
        manager.load("cards/G2.jpeg", Texture.class);
        manager.load("cards/G3.jpeg", Texture.class);
        manager.load("cards/G4.jpeg", Texture.class);
        manager.load("cards/G5.jpeg", Texture.class);
        manager.load("cards/G6.jpeg", Texture.class);
        manager.load("cards/G7.jpeg", Texture.class);
        manager.load("cards/G8.jpeg", Texture.class);
        manager.load("cards/G9.jpeg", Texture.class);
        manager.load("cards/G10.jpeg", Texture.class);
        manager.load("cards/G11.jpeg", Texture.class);
        manager.load("cards/G12.jpeg", Texture.class);

        manager.load("cards/R0.jpeg", Texture.class);
        manager.load("cards/R1.jpeg", Texture.class);
        manager.load("cards/R2.jpeg", Texture.class);
        manager.load("cards/R3.jpeg", Texture.class);
        manager.load("cards/R4.jpeg", Texture.class);
        manager.load("cards/R5.jpeg", Texture.class);
        manager.load("cards/R6.jpeg", Texture.class);
        manager.load("cards/R7.jpeg", Texture.class);
        manager.load("cards/R8.jpeg", Texture.class);
        manager.load("cards/R9.jpeg", Texture.class);
        manager.load("cards/R10.jpeg", Texture.class);
        manager.load("cards/R11.jpeg", Texture.class);
        manager.load("cards/R12.jpeg", Texture.class);

        manager.load("cards/Y0.jpeg", Texture.class);
        manager.load("cards/Y1.jpeg", Texture.class);
        manager.load("cards/Y2.jpeg", Texture.class);
        manager.load("cards/Y3.jpeg", Texture.class);
        manager.load("cards/Y4.jpeg", Texture.class);
        manager.load("cards/Y5.jpeg", Texture.class);
        manager.load("cards/Y6.jpeg", Texture.class);
        manager.load("cards/Y7.jpeg", Texture.class);
        manager.load("cards/Y8.jpeg", Texture.class);
        manager.load("cards/Y9.jpeg", Texture.class);
        manager.load("cards/Y10.jpeg", Texture.class);
        manager.load("cards/Y11.jpeg", Texture.class);
        manager.load("cards/Y12.jpeg", Texture.class);

        manager.load("cards/W13.jpeg", Texture.class);
        manager.load("cards/W13B.jpeg", Texture.class);
        manager.load("cards/W13G.jpeg", Texture.class);
        manager.load("cards/W13R.jpeg", Texture.class);
        manager.load("cards/W13Y.jpeg", Texture.class);

        manager.load("cards/W14.jpeg", Texture.class);
        manager.load("cards/W14B.jpeg", Texture.class);
        manager.load("cards/W14G.jpeg", Texture.class);
        manager.load("cards/W14R.jpeg", Texture.class);
        manager.load("cards/W14Y.jpeg", Texture.class);

        manager.load("cards/CardBack.jpeg", Texture.class);


    }

    public void dispose(){
        manager.dispose();
    }

    public void finishLoading(){
        manager.finishLoading();
    }
}

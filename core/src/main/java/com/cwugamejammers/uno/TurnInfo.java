package com.cwugamejammers.uno;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

public class TurnInfo {

    String info;
    Rectangle rect;
    Color fontColor = new Color(Color.WHITE);
    float fontScale = 3f;

    public TurnInfo(float x, float y, float width, float height){
        rect = new Rectangle(x, y, width, height);
    }


    public void pdraw(Batch batch, BitmapFont font){
        info = "Your Turn";
        font.setColor(fontColor);
        font.getData().setScale(fontScale);
        font.draw(batch, info, rect.x, rect.y);
    }

    public void draw(Batch batch, BitmapFont font){
        info = "Opponents turn";
        font.setColor(fontColor);
        font.getData().setScale(fontScale);
        font.draw(batch, info, rect.x, rect.y);
    }

}

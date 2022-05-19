package com.cwugamejammers.uno;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PlayerInfo {
    String info;
    String name;
    String cardsLeft;
    Rectangle rect;
    Color fontColor = new Color(Color.WHITE);
    float fontScale = 1.85f;



    public PlayerInfo(String name, int cardsLeft, float x, float y, float width, float height){
        this.name = name;
        this.cardsLeft = Integer.toString(cardsLeft);
        rect = new Rectangle(x, y, width, height);
    }

    public void updateCard(int cardsLeft){
        this.cardsLeft = Integer.toString(cardsLeft);
    }


    public void setIsTurn(){
        fontColor = Color.BLACK;
    }

    public void setNotTurn(){
        fontColor = Color.WHITE;
    }

    public void draw(Batch batch, BitmapFont font){
        info = name + "\n" + "Cards Left:" + cardsLeft;
        font.setColor(fontColor);
        font.getData().setScale(fontScale);
        font.draw(batch, info, rect.x, rect.y);
    }

    public void pDraw(Batch batch, BitmapFont font){
        info = "Cards Left:" + cardsLeft;
        font.setColor(fontColor);
        font.getData().setScale(fontScale);
        font.draw(batch, info, rect.x, rect.y);
    }


}

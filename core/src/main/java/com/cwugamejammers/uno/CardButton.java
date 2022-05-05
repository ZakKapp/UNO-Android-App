package com.cwugamejammers.uno;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public class CardButton {

    private Texture texture;

    Rectangle rect;

    Bound bounds;

    String imageName;

    public CardButton (Texture t, float x, float y, float w, float h, Card card){
        texture = t;
        rect = new Rectangle(x,y,w,h);
        bounds = new Bound(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        String imageName = card.getColor() + String.valueOf(card.getNumber()) + ".PNG";
    }

    public CardButton (float x, float y, float w, float h){
        rect = new Rectangle(x,y,w,h);
    }



    public boolean collision (float x, float y){
        return rect.contains(x, (Gdx.graphics.getHeight() - y));
    }


    public void setBounds(float minX, float minY, float maxX, float maxY){
        bounds = new Bound(minX, minY, maxX, maxY);
    }

    public void bounds(){
        if (rect.x < bounds.minX){
            rect.x = bounds.minX;
        }
        if (rect.x > bounds.maxX){
            rect.x = bounds.maxX;
        }

        if (rect.y < bounds.minY){
            rect.y = bounds.minY;
        }
        if (rect.y > bounds.maxY){
            rect.y = bounds.maxY;
        }

    }


    public void reposition(float x, float y) {
        this.rect.x = x;
        this.rect.y = y;
    }

    public void resize(float width, float height){
        this.rect.width = width;
        this.rect.height = height;
    }


    public float getx(){
        return rect.x;
    }

    public float gety(){
        return rect.y;
    }

    public float getWidth(){
        return rect.getWidth();
    }

    public float getHeight(){
        return rect.getHeight();
    }

    public void draw(Batch b){
        b.draw(texture, rect.x, rect.y, rect.width, rect.height);
    }

    public void dispose(){
        texture.dispose();
    }

}

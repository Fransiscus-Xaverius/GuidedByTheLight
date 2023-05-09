/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guidedbythelight;

import processing.core.*;

/**
 *
 * @author Frans
 */
public abstract class GameObjects {
    
    //Sprite Position
    protected int x;
    protected int y;
    
    //Game Object Sprite
    private PImage sprite;

    public GameObjects(int x, int y, PImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public GameObjects(int x, int y) {
        this.x = x;
        this.y = y;
    }

    
    
    //Getter Setter Methods for Class
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public PImage getSprite() {
        return sprite;
    }

    public void setSprite(PImage sprite) {
        this.sprite = sprite;
    }
    

    
}

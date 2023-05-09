/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guidedbythelight;

import java.util.ArrayList;
import processing.core.*;

/**
 *
 * @author Frans
 */
public abstract class CharacterObject {
    //Sprite Position
    public int x;
    public int y;
    
    //Game Object Sprite
    protected PImage[] idle;
    protected PImage[] attack1;
    protected PImage[] attack2;
    protected PImage[] walk;

    public CharacterObject(int x, int y, PImage[] idle, PImage[] attack1, PImage[] attack2, PImage[] walk) {
        this.x = x;
        this.y = y;
        this.idle = idle;
        this.attack1 = attack1;
        this.attack2 = attack2;
        this.walk = walk;
    }

    public CharacterObject(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public abstract void drawIdle(PApplet app, int t);
    
    public abstract void drawWalk(PApplet app, int f);
    
    public abstract boolean drawAttack1(PApplet app, int f, ArrayList<CharacterObject> enemies);
    
    public abstract void drawAttack2(PApplet app, int f);
    
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

    public PImage[] getIdle() {
        return idle;
    }

    public void setIdle(PImage idle[]) {
        this.idle = idle;
    }
    
    public void setWalk(PImage walk[]){
        this.walk = walk;
    }    
    
    public void setAttack1(PImage attack1[]){
        this.attack1 = attack1;
    }
    
}

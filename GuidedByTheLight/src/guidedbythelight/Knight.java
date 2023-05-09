/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guidedbythelight;

import java.util.ArrayList;
import processing.core.*;

/**
 *
 * @author LENOVO
 */
public class Knight extends CharacterObject{

    public Knight(int x, int y, PImage[] idle, PImage[] attack1, PImage[] attack2, PImage[] walk) {
        super(x, y, idle, attack1, attack2, walk);
    }

    public Knight(int x, int y) {
        super(x, y);
    }
    
    public int vx,vy;
    
    public void update(boolean left,boolean right,boolean up,boolean down){
        vx = vy = 0;
        if(left){
            vx = -5;
        }
        if(right){
            vx = 5;
        }
        if(up){
            vy = -5;
        }
        if(down){
            vy = 5;
        }
        if(up&&down){
            vy = 0;
        }
        if(left&&right){
            vx = 0;
        }
        x+=vx;
        y+=vy;
    }
    
    private int t = 0;
    
    @Override
    public void drawIdle(PApplet app, int f){
        if(f==-1) {t=0; f = 0;}
        if(f%10==0) t++;
        if(t==6) t=0;
        app.image(idle[t],x,y);
    }
    
    @Override
    public void drawWalk(PApplet app, int f){
        if(f==-1) {t=0; f = 0;}
        if(f%10==0) t++;
        if(t==8) t=0;
        app.image(walk[t],x,y);
    }
    
    @Override
    public boolean drawAttack1(PApplet app, int f, ArrayList<CharacterObject> enemies){
        if(f==-1) {t=0; f = 0;}
        if(f%10==0) t++;
        if(t==8) t=0;
        app.image(walk[t],x,y);
        return false;
    }
    
    @Override
    public void drawAttack2(PApplet app, int f){
        if(f==-1) {t=0; f = 0;}
        if(f%10==0) t++;
        if(t==8) t=0;
        app.image(walk[t],x,y);
    }
    
}

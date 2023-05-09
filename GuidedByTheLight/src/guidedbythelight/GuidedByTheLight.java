/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package guidedbythelight;

import java.awt.Color;
import java.io.*;
import java.util.*;
import processing.core.*;
import processing.data.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Frans
 */
public class GuidedByTheLight extends PApplet{
    
    //resolution
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    //sprite pixel size
    public static final int SPRITESIZE = 20;
    
    //Health bar
    public static final int TOPBAR = 80;
    
    //FPS Values
    public static final int FPS = 60;
    
    //Assets
    public  PImage bg;
    public PImage title;
    
    //Character variables
    public Enchantress enchantress;
    
    public void settings(){
        size(WIDTH,HEIGHT);
    }
    
    public InputStream in;
    
    //Ran at setup. Before the game.
    PImage[] idle_1;
    PImage[] walk_1;
    GUIButton b;
    String bip = "src/assets/sound/MainTheme.wav";
    public PlayMusic pm = new PlayMusic(bip);
    public void setup(){
        //set FPS
        frameRate(FPS);
        b = new GUIButton(400,400,500,100, new Color(255,255,0));
        //declare Character objects
        enchantress = new Enchantress(10,10);
        
        bg = loadImage("src/assets/background/darkbg.png");
        title = loadImage("src/assets/background/title.png");
        
        idle_1 = new PImage[5];
        for(int i =0;i<5;i++){
            idle_1[i] = loadImage("src/assets/playablecharacter/Enchantress/idle/"+(i+1)+".png");
        }
        enchantress.setIdle(idle_1);
        pm.PlayMusic();
        
        //set bgm
   
        
    }
    
    public int f = 0;
    public int t = 0;
    
    public boolean rectOver = false;
    
    public void draw(){    
        update(mouseX, mouseY, b);
        background(bg);
        image(title, 120, 200);
        enchantress.drawIdle(this, f);
        f++;
        
        // draw rectangle button
        fill(255,245,248);
        stroke(255);
        rect(b.x,b.y,b.width,b.height);
        
        //draw button text
        textSize(30);
        fill(0);
        text("Start Game", 560, 450);
    }
    
    void update(int x, int y, GUIButton b) {
        if ( overRect(b.x, b.y, b.width, b.height) ) {
          rectOver = true;
        } else {
          rectOver = false;
        }
    }
    
    boolean overRect(int x, int y, int width, int height)  {
        if (mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height) {
            return true;
        } else {
            return false;
        }   
    }
    
    public void mousePressed(){
        if(rectOver){
            String[] args = {"MainGameRun"};
            PApplet.runSketch(args, new MainGame());
            surface.setVisible(false);
            pm.stop();
        }
    }
    
    public static void main(String[] args) {
        PApplet.main("guidedbythelight.GuidedByTheLight");
    }
    
}

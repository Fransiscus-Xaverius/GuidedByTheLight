/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guidedbythelight;

import static guidedbythelight.GuidedByTheLight.HEIGHT;
import static guidedbythelight.GuidedByTheLight.WIDTH;
import processing.core.*;
import java.util.ArrayList;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author LENOVO
 */
public class MainGame extends PApplet{
     //resolution
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    
    //Music Player
    String bip = "src/assets/sound/overworld.wav";
    public PlayMusic pm;

    //sprite pixel size
    public static final int SPRITESIZE = 20;
    
    //Health bar
    public static final int TOPBAR = 80;
    
    //FPS Values
    public static final int FPS = 60;
    
    //Assets
    public  PImage bg;
    
    //Character variables
    public Enchantress enchantress;
    public PImage[] idle_1;
    public PImage[] walk_1;
    public PImage[] attack_1;
    public PImage[] attack_2;
    boolean running, idle, attacking_1, attacking_2;
    boolean left, right, up, down;
    
    public Knight knight;
    public PImage[] idle_2;
    public PImage[] walk_2;
    
    //ArrayList of Enemies
    ArrayList<CharacterObject> enemies;
    
    public void setup(){
        running = attacking_1 = attacking_2 = false;
        enemies = new ArrayList<>();
        idle = true;
        frameRate(FPS);
        bg = loadImage("src/assets/background/Overworld.jpeg");
        //declare Character objects
        enchantress = new Enchantress(165,165);
        knight = new Knight(400,400);
        left = right = up = down = false;
        //load idle animation
        idle_1 = new PImage[5];
        walk_1 = new PImage[8];
        idle_2 = new PImage[6];
        walk_2 = new PImage[8];
        attack_1 = new PImage[6];
        for(int i =0;i<5;i++){
            idle_1[i] = loadImage("src/assets/playablecharacter/Enchantress/idle/"+(i+1)+".png");
        }
        enchantress.setIdle(idle_1);
        for(int i =0;i<8;i++){
            walk_1[i] = loadImage("src/assets/playablecharacter/Enchantress/walk/"+(i+1)+".png");
        }
        enchantress.setWalk(walk_1);
        for (int i = 0; i < 6; i++) {
            attack_1[i] = loadImage("src/assets/playablecharacter/Enchantress/attack1/"+(i+1)+".png");
        }
        enchantress.setAttack1(attack_1);
        
        for(int i =0;i<6;i++){
            idle_2[i] = loadImage("src/assets/playablecharacter/Knight/idle/"+(i+1)+".png");
        }
        knight.setIdle(idle_2);
        
        enemies.add(knight);
        
        //Start Music
        pm = new PlayMusic(bip);
        pm.PlayMusic();
    }
    
    public void keyPressed(){
        if(key=='w'){ up = true; running = true; idle = false;}
        if(key=='s'){ down = true; running = true; idle = false;}
        if(key=='a'){ left = true; running = true; idle = false;}
        if(key=='d'){ right = true; running = true; idle = false;}
        if(key==','){ attacking_1 = true; }
        if(key=='.'){ attacking_2 = true; }
    }
    
    public void keyReleased(){
        running = attacking_1 = false;
        idle = true;
        f = -1;
        if(key=='w'){ up = false;}
        if(key=='s'){ down = false;}
        if(key=='a'){ left = false;}
        if(key=='d'){ right = false;}
    }
    
    public void settings(){
        size(WIDTH,HEIGHT);
    }
    
    public int f = 0;
    
    boolean inactive = false;
    
    public void change(){
        surface.setVisible(false);
        this.pm.stop();
    }
    
    public void invokeBattle(){
        String[] args = {"InvokedBattle"};
            PApplet.runSketch(args, new battleMain());
    }
    
    public void draw(){
        background(bg);
        enchantress.update(left, right, up, down);
        if(attacking_1){
            boolean invoke_battle = enchantress.drawAttack1(this, f, enemies);
            if(invoke_battle&&!inactive){
                change();
                invokeBattle();
                inactive = true;
            }
        }
        else if(idle){
            enchantress.drawIdle(this, f);
        }
        else if(running){
            enchantress.drawWalk(this, f);
        }
        
        knight.drawIdle(this, f);
        f++;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package guidedbythelight;
import static guidedbythelight.MainGame.FPS;
import static guidedbythelight.MainGame.HEIGHT;
import static guidedbythelight.MainGame.WIDTH;
import java.awt.Color;
import processing.core.*;
import java.util.ArrayList;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author LENOVO
 */
public class battleMain extends PApplet{
    //resolution
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    
    //Music Player
    String bip = "src/assets/sound/vsKnight.wav";
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
    
    boolean chooseMove = true;
    boolean isAttacking = false;
    boolean isDefending = false;
    boolean isFleeing = false;
    boolean isUsingItem = false;
    
    public Knight knight;
    public PImage[] idle_2;
    public PImage[] walk_2;
    
    //ArrayList of Enemies
    ArrayList<CharacterObject> enemies;

    public void setup(){
         b = new GUIButton(100,450,200,50, new Color(255,255,0));
        running = attacking_1 = attacking_2 = false;
        enemies = new ArrayList<>();
        idle = true;
        frameRate(FPS);
        bg = loadImage("src/assets/background/battlebg_1.png");
        
        //declare Character objects
        enchantress = new Enchantress(200,300);
        knight = new Knight(1000,300);
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
    
    public void settings(){
        size(WIDTH,HEIGHT);
    }
    
    int f = 0;
    //attack button
    GUIButton b;
    
    public boolean rectOverAttack = false;
    public boolean rectOverItems = false;
    public boolean rectOverDefend = false;
    public boolean rectOverFlee = false;
    
    boolean overRect(int x, int y, int width, int height)  {
        if (mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height) {
            return true;
        } else {
            return false;
        }   
    }
    
    void update(int x, int y, GUIButton b) {
        //Button Registry on press.
        //Not a good implementation but this will do.
        if ( overRect(b.x, b.y, b.width, b.height) ) {
          rectOverAttack = true;
        }
        else{
            rectOverAttack = false;
        }
        if(overRect((b.x+250), b.y, b.width, b.height)){
          rectOverDefend = true;
        }
        else{
            rectOverDefend = false;
        }
        if(overRect(b.x, (b.y+75), b.width, b.height)){
            rectOverItems = true;
        } 
        else{
            rectOverItems = false;
        }
        if(overRect((b.x+250), (b.y+75), b.width, b.height)){
            rectOverFlee = true;
        }
        else{
            rectOverFlee = false;
        }
    }
    
    public void mousePressed(){
        if(rectOverAttack){
            isAttacking = true;
            chooseMove = false;
        }
        else if(rectOverDefend){
            isDefending = true;
            chooseMove = false;
        }
        else if(rectOverItems){
            isUsingItem = true;
            chooseMove = false;
        }
        else if(rectOverFlee){
            isFleeing = true;
            chooseMove = false;
        }
    }
    
    int c = 0;
    
    public void draw(){
        update(mouseX, mouseY, b);
        background(bg);
        
        if(chooseMove){
            enchantress.drawIdle(this, f);
            knight.drawIdle(this, f);
        }
        else if(isAttacking){
            textSize(30);
            fill(248);
            text("The Enchantress Attacks..", 120, 450);
            if(c>85){
                f = -1;
                c = 0;
                enchantress.x = 200;
                isAttacking = false;
                chooseMove = true;
                System.out.println(f+",");
            }
            else if(c>70){
                enchantress.drawAttack1(this, f, enemies);
                f= -1;
                System.out.println(c+"");
                c++;
            }
            else{
                enchantress.x+=10;
                enchantress.drawWalk(this, f);
                c++;
                f= -1;
            }
        }
        
        if(chooseMove){
            //Draw Attack button
            fill(255,245,248);
            stroke(255, 245, 248);
            rect(b.x,b.y,b.width,b.height);

            //Draw Defend Button
            fill(255,245,248);
            stroke(255, 245, 248);
            rect((b.x+250),b.y,b.width,b.height);

            //Draw Items Button
            fill(255,245,248);
            stroke(255, 245, 248);
            rect(b.x,(b.y+75),b.width,b.height);

            //Draw Flee Button
            fill(255,245,248);
            stroke(255, 245, 248);
            rect((b.x+250),(b.y+75),b.width,b.height);
        }
        
        //Draw Enemy
        knight.drawIdle(this, f);
        f++;
    }
    
}

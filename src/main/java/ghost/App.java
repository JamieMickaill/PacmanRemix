package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.io.File;



/**
 * Main Application Class for the game
 */

public class App extends PApplet {

    /**
     * Width of game screen
     */
    public static final int WIDTH = 448;

    /**
     * Height of game screen
     */
    public static final int HEIGHT = 576;

    //GameLogic is performed here
    private GameManager gameLogic;

    //Stores sprites
    private ArrayList<PImage> spriteList;

    // private SoundFile file;

    /**
     * App class constructor
     * Instantiates game logic object and 
     * initializes configuration of gameLogic from config file
     */
    public App() {
        
        this.gameLogic = new GameManager();

        this.gameLogic.initConfig();     
        // file = new SoundFile(this, "pacman.wav");
        // file.loop();   
    }

    /**
     * Setup method loads game objects
     * gameLogic object parses map file specified in config file
     * game Sprites are stored in an ArrayList
     */
    public void setup() {

        frameRate(60);

        //parse map
        this.gameLogic.parseMap(this);


        //load player sprites 
        this.spriteList = this.gameLogic.loadSpriteList(this);
        
    }

    /**
     * Game Settings
     */
    public void settings() {
        size(WIDTH, HEIGHT);
        // this.gameLogic.PlaySnippet();
    }

    /**
     * Draw method
     * Responsible for drawing game objects to screen, 
     * e.g. PImage P.Graphics
     * Responsible for ticking frames of game - 60FPS
     */
    public void draw() { 
        
        //background
        background(0, 0, 0);
        
        //checks Win/Lose conditions have been met
        gameLogic.gameStatusCheck();
       
        //Updates player/ghost sprites
        gameLogic.updateSprite(this.spriteList);

        //If game has not been won or lost, check game started
        if(!(gameLogic.checkWinLose())){
        
        //if game has begun, tick
        if(gameLogic.getStartedGame()){

            gameLogic.tickAll();
            }
        
        //perform move
        gameLogic.performCurrentMove();

        //draw all sprites
        gameLogic.drawAll(this);

        }else{
            //game Ended - display result, reset and respawn
            gameLogic.endGame(gameLogic.getGameOver(), 
            this,this.spriteList, 1000);
        }
    }

    /**
     * KeyPressed takes user input 
     * and calls methods to move the game character
     */
    public void keyPressed(){
        gameLogic.setStartedGame(true);
        //to be abstracted - moved to game manager VVVVVVVV

        if (keyCode == UP){
            gameLogic.setMove(1);           
        }
        if (keyCode == DOWN){
            gameLogic.setMove(2);           
        }
        if (keyCode == RIGHT){
            gameLogic.setMove(4); 
        }
        if (keyCode == LEFT){
            gameLogic.setMove(3);   
        }
        if (keyCode == 32){
            gameLogic.setDebugMode();
        }
    }

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        PApplet.main("ghost.App");
    }

}

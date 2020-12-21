//GameManager 

package ghost;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random; 
import java.io.File;
import java.util.HashMap;
import java.io.FileNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
* Game manager class - responsible for all game logic
*/ 

public class GameManager extends PApplet{
        public static final int WIDTH = 448;
        public static final int HEIGHT = 576;

        private int currTileX;
        private int currTileY;
        private int snaccCount;
        private int timeCount;
        private int ghostTimeCount;
        private int frightenedTimeCount;
        private int endGameCount;
        private int ShroomTimeCount;
        private int ShroomTimeCountLimit;
        private int dir;

        private long speed;
        private long OGspeed;
        private long lives;
        private long frightenedLength;

        private String map;

        private boolean startedGame;
        private boolean raverOpen;
        private boolean ghostMode;
        private boolean debugMode;
        private boolean shroomMode;
        private boolean frightenedMode;
        private boolean gameOver;
        private boolean gameWin;
        private boolean moveUp;
        private boolean moveDown;
        private boolean moveLeft;
        private boolean moveRight;

        private Raver raver;

        private ArrayList<Ghost> ghosts;
        private ArrayList<Snacc> snaccs;
        private ArrayList<Tile> tiles;
        private ArrayList<Life> livesLeft;
        private ArrayList<Integer> modeLengthList;

        GameManager(){
            this.ghosts = new ArrayList<Ghost>();
            this.tiles = new ArrayList<Tile>();
            this.snaccs = new ArrayList<Snacc>();
            this.livesLeft = new ArrayList<Life>();
            this.modeLengthList = new ArrayList<Integer>();
            this.raver = null;
            this.snaccCount = 1;
            this.timeCount = 0;
            this.ShroomTimeCount = 0;
            this.ShroomTimeCountLimit = 10;
            this.dir=0;
            this.currTileX = 0;
            this.currTileY = 0;
            this.startedGame = false;
            this.raverOpen = false;
            this.ghostMode = false;
            this.debugMode = false;
            this.frightenedMode = false;
            this.gameOver = false;
            this.gameWin = false;
            this.moveUp = false;
            this.moveDown = false;
            this.moveLeft = false;
            this.moveRight = false;
}

    /**
    *initConfig Method - loads in config json file 
    from root folder and updates game variables
    *Loads in map, speed, lives, ghost Chase/Scatter mode lengths
    @exception IOException
    @exception ParseException
    */     

    public void initConfig(){

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("src/config.json")) {

            JSONObject jsonObject = (JSONObject)
            parser.parse(reader);

            this.map = (String) jsonObject.get("map");
            this.speed = (long) jsonObject.get("speed");
            this.OGspeed = (long) jsonObject.get("speed");
            this.lives = (long) jsonObject.get("lives");
            this.frightenedLength = (long) jsonObject.get("frightenedLength");

            JSONArray modeLengths = (JSONArray) jsonObject.get("modeLengths");

            Iterator iterator = modeLengths.iterator();

            while (iterator.hasNext()) {
                String s = iterator.next().toString();
                int i = Integer.parseInt(s);
                modeLengthList.add(i);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    /**
    *setTimeCount method - 
    increments time variable by 1, 
    used for tracking time for time-dependent methods
    */
    
    public void setTimeCount(){
        this.timeCount++;
    }

   

   /**
    *getTimeCount method - returns tick count for timeCount
    * @return int value of current time count
    */ 

    public int getTimeCount(){
        return this.timeCount;
    }

    
    /**
    *setfrightenedTimeCount method - 
    sets frightened time variable to specified int @i, 
    used for tracking time for time-dependent methods
     * @param i
     */ 

    public void setfrightenedTimeCount(int i){
        this.frightenedTimeCount = i;
    }

    

    /**
    *getfrightenedTimeCount method - 
    sets frightened time variable to specified int @i, 
    used for tracking time for time-dependent methods
    @return int value of frightened Time
    */ 

    public int getfrightenedTimeCount(){
        return this.frightenedTimeCount;
    }


    /**
    *setShroomTimeCount method - 
    sets ShroomTimeCount time variable to specified int @i,
     used for tracking time for time-dependent methods
     * @param i
    */ 
    public void setShroomTimeCount(int i){
        this.ShroomTimeCount = i;
    }

    /**
    *setShroomModeOn method - 
    sets Shroom Mode on - when ShroomSnacc eaten 
    Ghosts will begin flashing colours and eating fruit
    */ 
    public void setShroomModeOn(){
        this.shroomMode = true;
    }

    /**
    *set ChaseMode method - 
    sets Chase Mode on - pursue the player!
    */ 
    public void setChaseModeOn(){
        this.ghostMode = true;
    }
    

    /**
    *get debugMode method - 
    sets Chase Mode on - pursue the player!
    */ 
    public boolean getDebugMode(){
        return this.debugMode;
    }


    
    /*** 
    *getShroomTimeCount method - sets ShroomTimeCount time variable 
    to specified int @i, 
    used for tracking time for time-dependent methods
    @return int value of frightened Time
    */ 
    public int getShroomTimeCount(){
        return this.ShroomTimeCount;
    }

    /**
    *setghostTimeCount method - increments ghost time variable, 
    used for tracking time for ghost mode time-dependent methods
    */ 
    public void setghostTimeCount(){
        this.ghostTimeCount++;
    }

    
    /**
    *setghostTimeCountVal method - 
    sets ghost time variable to specified int @i, 
    used for tracking time for ghost mode time-dependent methods
     * @param i
     */ 
    public void setghostTimeCountVal(int i){
        this.ghostTimeCount = i;
    }

    /*** 
    *getghostTimeCount method - gets ghost time variable, 
    used for tracking time for ghost mode time-dependent methods
    *@return int value of ghost Time
    */ 
    public int getghostTimeCount(){
        return this.ghostTimeCount;
    }

    
    /**setRaverOpen 
     * - sets whether player sprite is currently in open-mouth mode @t
     * @param t
    */ 
    public void setRaverOpen(boolean t){
        this.raverOpen = t;
    }

    
    /**getRaverOpen - returns whether players mouth is currently open
    @return boolean whether players mouth is open
    */ 
    public boolean getRaverOpen(){
        return this.raverOpen;

    }

    
    /**getRaver - returns player instance 
    *@return Raver instance of current player
    */ 
    public Raver getRaver(){
        return this.raver;
    }

    
    /**getRaverX - returns player X coordinate 
    *@return Raver x coordinate int value
    */ 
    public int getRaverX(){
        return this.raver.getX();
    }

    /**getRaverY - returns player X coordinate 
     * @return int
     */ 
    public int getRaverY(){
        return this.raver.getY();
    }

    
    /**getRaverX - returns endgame sequence timer tick count 
    *@return int - returns endgame sequence timer tick count
    */ 
    public int getEndGameCount(){
        return this.endGameCount;
    }

    
    /**getDirX - returns current players movement direction as an integer 
    *@return int - returns current players movement direction as an integer 
    */ 
    public int getDirMove(){
        return this.dir;
    }

    
    /**setRaverSprite - updates image based 
    * @param takes in PImage
    */ 
    public void setRaverSprite(PImage sprite){
        this.raver.setSprite(sprite);
    }

    
    /**getRaverSprite - updates image based 
     * @return PImage
     */ 
    public PImage getRaverSprite(){
        return this.raver.getSprite();
    }

    /**resetRaverDirection - sets player direction to default right
    */ 
    public void resetRaverDirection(){
        this.raver.resetDirection();}

    /**moveRaver - sets player direction to default right
     * @param dir
     * @param speed
     * @return boolean  whether player move mas successful
    */
    public boolean moveRaver(int dir, long speed){
        if(dir == 1){
            return this.raver.moveUp(speed);

        }else if(dir == 2){
            return this.raver.moveDown(speed);

        }else if(dir == 3){
            return this.raver.moveLeft(speed);

        }else if(dir == 4){
            return this.raver.moveRight(speed);

        }else 
            return false;
    }


    /**gameStatusCheck - checks game booleans to see 
     * whether player has died or game has been won or lost.
    *respawns player and ghosts if player died.
    */ 
    public void gameStatusCheck(){

        if(!(raver.checkAlive())){

            //reset game values
            this.startedGame = false;
            this.ghostTimeCount = 0;
            this.ShroomTimeCount = 0;
            this.ghostMode = false;
            this.shroomMode = false;
            this.speed = this.OGspeed;

             //reduce lives
            this.lives -=1;
            if (this.lives !=0){
            this.lostLife();

            //respawn all sprites
            this.raver.respawn();
            for(Ghost ghost: this.ghosts){
                ghost.respawn();
                ghost.resetLastMove();
            }

        }else{
            this.gameOver=true;   
            }
        }
    }

    
    /**checkWinLose - returns boolean value whether 
     * game has been won or lost (checks gameOver and gameWin)
    * @return boolean whether game has ended or not
    */ 
    public boolean checkWinLose(){
        if(gameOver){
            return true;
        }
        else if(this.snaccCount == 0){
            gameWin = true;
            return true;
        }
        else return false;
    }

    /**tickRaver - calls tick method on player
    */ 
    public void tickRaver(){
        this.raver.tick();
    }

    /**tickGhosts - calls tick method on ghost
    * updates ghostMode (scatter or chase) depending on 
    * ghost Time count and specified ghost times imported in config
    */ 
    public void tickGhosts(){
        int i = 0;

        //check time passed to increment mode
        if(this.ghostTimeCount == (modeLengthList.get(i)*100)){
            i++;
            this.ghostMode = (!(this.ghostMode));
            this.ghostTimeCount = 0;

            //if at end of modelist - restart
            if (i+1 == modeLengthList.size()){
                i = 0;
                this.ghostTimeCount = 0;
            }
        }

        //tick ghosts, parsing in mode variable and speed
        for(Ghost ghost : this.ghosts){
            ghost.tick(this.ghostMode, this.frightenedMode, 
            this.shroomMode, this.speed, this.raver);
        }
    }

    /**tickSnaccs - calls tick method on all Snacc objects
    * checks timers for snacc-related game modes
    * counts snaccs and stores in snaccCount
    */ 
    public void tickSnaccs(){

        checkFrightened();
        frightenedTimer();
        tripTimer();
        

        //count snaccs
        int snaccCounting = 0;
        for(Snacc snacc : snaccs){
            if (snacc.getIsEdible()){
                snaccCounting ++;
            }   
        }

        this.snaccCount = snaccCounting;

        }
                
/**checkFrightened - 
 * checks all snaccs for whether ThiccSnacc or ShroomSnacc has been eaten
* if ThiccSnacc (SuperFruit) has been eated, 
* set player invincibility ON and game frightenedMode ON
* if ShroomSnacc eaten, increase game speed.
*/  
    public void checkFrightened(){
        for(Snacc snacc : this.snaccs){
            if(snacc.checkThiccSnaccAte()){
                this.frightenedMode = true;
                this.raver.setInvincibleOn();
            }
            if(snacc.checkShroomSnaccAte()){
                this.shroomMode = true;
                this.speed = 2;
            }
    }
}

    /**setFrightened - sets game frightenedMode to true.
    */         
    public void setFrightened(){
        this.frightenedMode = true;
    }


    /**getFrightened - gets game frightenedMode.
    @return boolean value whether frightenedMode is currently active.
    */    
    public boolean getFrightenedMode(){
        return this.frightenedMode;
    }

    /**frightenedTimer - checks current frightenedTime count and 
    * compares to the length of Frightened time specified in config
    * Activates/Disables frightenedMode based on time passed.
    */    
    public void frightenedTimer(){
        if(this.frightenedMode){

            //check timeCount 
            //if frightenedmode time has elapsed - unspook ghosts,
            //turn off player invincibility and reset timer
            if(frightenedTimeCount / 100 == frightenedLength){

                this.frightenedMode = false;
                this.frightenedTimeCount = 0;
                this.raver.setInvincibleOff();

                for(Ghost ghost : this.ghosts){
                    ghost.spookOn();
                
            }
            
        }else{this.frightenedTimeCount +=1;}
        }
    }


    /**tripTimer - checks current ShroomTimeCount 
     * and compares to the length of ShroomTimeCountValue
    * Activates/Disables ShroomMode based on time passed.
    */  
    public void tripTimer(){
        if(this.shroomMode){
            //check time - 
            //if frightenedmode time has elapsed 
            //- sober up ghosts, reset speed and reset timer
            if(ShroomTimeCount / 100 == ShroomTimeCountLimit){

                this.shroomMode = false;
                this.ShroomTimeCount = 0;
                this.speed = 1;

                for(Ghost ghost : this.ghosts){
                    ghost.shroomOff();
                }

        }else{
            this.ShroomTimeCount +=1;}
        }
    }


    /**drawAll - runs PApplet draw for all sprite PImages within the game 
    *@param PApplet object required for draw
    */    
    public void drawAll(PApplet p){

        for(Tile tile : this.tiles){
            tile.draw(p);
        }

        for(Snacc snacc : this.snaccs){
            snacc.draw(p);
        }

        for(Ghost ghost : this.ghosts){

            if(this.debugMode){
                ghost.drawTargetLine(this.ghostMode, this.raver, p);
            }
            
            ghost.draw(p);
            
        }

        this.raver.draw(p);
        this.drawLives(p);
    }

    /** getGhosts - gets ArrayList of Ghost objects
    @return ArrayList<Ghost> containing all ghost objects parsed from map
    */    
    public ArrayList<Ghost> getGhosts(){
        return this.ghosts;
    }
   

   /**getTiles - gets ArrayList of Tile objects
    @return ArrayList<Tile> containing all Tile (wall) objects parsed from map
    */    
    public ArrayList<Tile> getTiles(){
        return this.tiles;
    }   

   /**addRaver - updates game Player object
   *@Param Raver object instance
    */    
    public void addRaver(Raver r){
        this.raver = r;
    }

    
    /**addTile - 
     * adds given tile instance to tiles ArrayList containing all Tiles
    *@Param t Tile object instance
    */    
    public void addTile(Tile t){
        this.tiles.add(t);
    }

   
    /**addGhost - 
     * adds given ghost instance to ghosts ArrayList containing all Ghosts
    *@Param g Ghost object g
    */  
    public void addGhost(Ghost g){
        this.ghosts.add(g);
    }

    /**addSnacc - 
     * adds given snacc instance to snacc ArrayList containing all Snaccs
    *@Param s Snacc object s
    */  
    public void addSnacc(Snacc s){
        this.snaccs.add(s);
    }

    /**eatAllSnaccs - 
     * loops through all snacc instances and calls snaccEaten method, 
     * updating Eaten boolean for all snaccs
    */  
    public void eatAllSnaccs(){

        for(Snacc snacc: this.snaccs){
            snacc.snaccEaten();
        }

    }

    /**getSnaccs - gets ArrayList of Snacc objects
    @return ArrayList<Snacc> containing all snacc objects parsed from map
    */   
    public ArrayList<Snacc> getSnaccs(){
        return this.snaccs;
    }


    /**getStartedGame - gets boolean whether game has started
    @return Boolean - True if game has started
    */   
    public boolean getStartedGame(){
        return this.startedGame;
    }


    /**getGhostMode - gets boolean whether ChaseMode is active
    @return Boolean - True if Chase mode active
    */   
    public boolean getGhostMode(){
        return this.ghostMode;
    }
    
    /**setStartedGame - sets boolean whether game has started
    @param Boolean - True if game has started
    */   
    public void setStartedGame(boolean val){
        this.startedGame = val;
    }

    /**setDebugMode - toggles debugMode on/off
    */ 
    public void setDebugMode(){
        this.debugMode = !this.debugMode;
    }


    /**getSpeed - retrieves speed value loaded from config file
    @return long speed value read from config
    */ 
    public long getSpeed(){
        return this.speed;
    }


    /**getMap - 
     * retrieves map file string directory value loaded from config file
    @return String map file directory read from config
    */  
    public String getMap(){
        return this.map;
    }

    
    /**loadLives - creates live objects to be drawn to display
     * @param image
     */ 
    public void loadLives(PImage image){
        int lifeX = 10;
        int lifeY = 576-32;

        for(int i=0; i < lives; i++){
            livesLeft.add(new Life(lifeX, lifeY, image));
            lifeX += 32;
        }

    }

    /**drawLives - draws all life objects
     * @param p
     */ 
    public void drawLives(PApplet p){

            for(Life life : livesLeft){

                life.draw(p);

            }
        
    }

    /**lostLife() - removes a life if player dies
    */ 
    public void lostLife(){

        if(this.lives>0){

            int livesInt = (int) this.lives;

        this.livesLeft.get(livesInt).oof();

        }
    }


    /**getCurrTileX - integer value of X coordinate which 
     * increments as map objects are parsed
    *@return int value of current X tile being created
    */ 
    public int getCurrTileX(){
        return this.currTileX;
    }

    /**getCurrTileY - integer value of Y coordinate which 
     * increments as map objects are parsed
    *@return int value of current Y tile being created
    */ 
    public int getCurrTileY(){
        return this.currTileY;
    }


    /**incCurrTileX - integer value of X coordinate is 
     * incremented by provided integer
    *@param int value to increment X value tile being created
    */ 
    public void incCurrTileX(int inc){
        this.currTileX+=inc;
    }

    /**resetCurrTileX - resets integer value of X coordinate to zero - 
     * used for next line of map to be parsed
    */ 
    public void resetCurrTileX(){
        this.currTileX=0;
    }

    /**incCurrTileY - 
     * integer value of Y coordinate is incremented by provided integer
    *@param inc int value to increment Y value tile being created
    */ 
    public void incCurrTileY(int inc){
        this.currTileY+=inc;
    }

    /**parseMap - 
     * reads characters from map config file, 
     * constructs game object instances based on characters read from array
    *@param p PApplet object instance  - needed in order to load PImage objects
    */ 
    public void parseMap(PApplet p) {

        HashMap<Character,String> tileMap = new HashMap<Character,String>();
        tileMap.put('0' , null);
        tileMap.put('1', "src/main/resources/horizontal.png");
        tileMap.put('2', "src/main/resources/vertical.png");
        tileMap.put('3', "src/main/resources/upLeft.png");
        tileMap.put('4', "src/main/resources/upRight.png");
        tileMap.put('5', "src/main/resources/downLeft.png");
        tileMap.put('6', "src/main/resources/downRight.png");
        tileMap.put('7', "src/main/resources/fruit.png");
        tileMap.put('8', "src/main/resources/superFruit.png");
        tileMap.put('s', "src/main/resources/shroom.png");
        tileMap.put('p', "src/main/resources/playerRight.png");
        tileMap.put('c', "src/main/resources/chaser.png");
        tileMap.put('a', "src/main/resources/ambusher.png");
        tileMap.put('w', "src/main/resources/whim.png");
        tileMap.put('i', "src/main/resources/ignorant.png");

        //scan in map and create objects
        try{
            File mapInFile = new File(this.getMap());
            Scanner mapInput = new Scanner(mapInFile);

            while(mapInput.hasNextLine()){

                String line = mapInput.nextLine();
                line = line.trim();
                char[] tilesToParse = line.toCharArray();

                for(char tileChar: tilesToParse){

                    if (tileChar != '0' && tileChar != 'p' &&
                     tileChar != 'c' && tileChar != 'a' &&
                     tileChar != 'w' && tileChar != 'i' && tileChar != 's'&&
                      tileChar != '7' && tileChar != '8'){

                    this.addTile(new Tile(this.getCurrTileX(), 
                    this.getCurrTileY(), 
                    p.loadImage(tileMap.get(tileChar)),tileChar));

                    }   

                    else if(tileChar == 'p'){

                        this.addRaver(new Raver(this.getCurrTileX(), 
                        this.getCurrTileY(), 
                        p.loadImage(tileMap.get(tileChar)), this.getGhosts(), 
                        this.getSnaccs(), this.getTiles()));

                    }

                    else if(tileChar == 'c'){

                        this.addGhost(new Chaser(this.getCurrTileX(), 
                        this.getCurrTileY(), 
                        p.loadImage(tileMap.get(tileChar)), this.getTiles(),
                        this.getSnaccs(), this.raver));

                    }

                    else if(tileChar == 'a'){

                        this.addGhost(new Ambusher(this.getCurrTileX(), 
                        this.getCurrTileY(), 
                        p.loadImage(tileMap.get(tileChar)), this.getTiles(),
                        this.getSnaccs(), this.raver));

                    }

                    else if(tileChar == 'w'){

                        this.addGhost(new Whim(this.getCurrTileX(), 
                        this.getCurrTileY(), 
                        p.loadImage(tileMap.get(tileChar)), this.getTiles(),
                        this.getSnaccs(), this.raver));

                    }

                    else if(tileChar == 'i'){

                        this.addGhost(new Ignorant(this.getCurrTileX(), 
                        this.getCurrTileY(), 
                        p.loadImage(tileMap.get(tileChar)), this.getTiles(),
                        this.getSnaccs(), this.raver));

                    }

                    else if(tileChar == '7'){

                        this.addSnacc(new Snacc(this.getCurrTileX(), 
                        this.getCurrTileY(), 
                        p.loadImage(tileMap.get(tileChar))));

                        this.snaccCount +=1;

                    }

                    else if(tileChar == '8'){

                        this.addSnacc(new ThiccSnacc(this.getCurrTileX(), 
                        this.getCurrTileY(), 
                        p.loadImage(tileMap.get(tileChar))));

                        this.snaccCount +=1;
                    }

                    else if(tileChar == 's'){

                        this.addSnacc(new ShroomSnacc(this.getCurrTileX(), 
                        this.getCurrTileY(), 
                        p.loadImage(tileMap.get(tileChar))));

                        this.snaccCount +=1;
                    }

                    this.incCurrTileX(16);
                }

                this.resetCurrTileX();
                this.incCurrTileY(16);

            }
            }catch(FileNotFoundException f){
                System.out.println("Config not found");
        }

        //load player lives sprites
        loadLives(p.loadImage(tileMap.get('p')));

        //set chaser for whim
        for(Ghost w: ghosts){
            if(w instanceof Whim){
                for(Ghost c: ghosts){
                    if(c instanceof Chaser){
                        w.setChaser(c);
                    }
            }
        }
    }
}

    /**resetFrightened - sets frightenedMode back to false;
    */ 
    public void resetFrightened(){
        this.frightenedMode = false;
    }


    /*** updateSprite - checks gameMode booleans and update game sprites
     * @param spriteList list of PImage sprites
     */ 
    public void updateSprite(ArrayList<PImage> spriteList){

        if(this.getStartedGame()){

            //if frightened mode, set all ghost sprites to frightened
            if(frightenedMode){
                for(Ghost g : ghosts){
                    g.setSprite(spriteList.get(spriteList.size()-5));
                }

            //if shroom mode, set all sprites to change at random
            }else if(shroomMode){

                Random ran = new Random(); 
                int SpriteOffset = ran.nextInt(3);

                for(Ghost g : ghosts){
                    if(g instanceof Ambusher){
                        g.setSprite(spriteList.get(spriteList.size()-1-SpriteOffset));
                    }
                    else if(g instanceof Chaser){
                        g.setSprite(spriteList.get(spriteList.size()-1-SpriteOffset));
                    }
                    else if(g instanceof Whim){
                        g.setSprite(spriteList.get(spriteList.size()-1-SpriteOffset));
                    }
                    else if(g instanceof Ignorant){
                        g.setSprite(spriteList.get(spriteList.size()-1-SpriteOffset));
                    }

                }

            //If normal mode - set normal sprites
            }else if(!(frightenedMode)){

                for(Ghost g : ghosts){
                    if(g instanceof Ambusher){
                        g.setSprite(spriteList.get(spriteList.size()-2));
                    }
                    else if(g instanceof Chaser){
                        g.setSprite(spriteList.get(spriteList.size()-1));
                    }
                    else if(g instanceof Whim){
                        g.setSprite(spriteList.get(spriteList.size()-4));
                    }
                    else if(g instanceof Ignorant){
                        g.setSprite(spriteList.get(spriteList.size()-3));
                    }

                }
            }

            //Open and close player mouth every 5 ticks
            if (this.getTimeCount() % 5 == 0){
                this.setRaverOpen(!(this.getRaverOpen()));
            }
            if (this.getRaverOpen()){
                this.setRaverSprite(
                    spriteList.get(this.getRaver().getDirection()));
            } else {
                this.setRaverSprite(spriteList.get(0));
            }
        }
    }

    
    /*** 
     * @return boolean
     */ 
    public boolean getGameOver(){
        return this.gameOver;
    }

    
    /*** 
     * @return boolean
     */ 
    public boolean getWin(){
        return this.gameWin;
    }

    
    public void setGameOver(){
        this.gameOver = true;
    }

    /*** 
     * sets current game player alive = false
     */ 
    public void killPlayer(){
        this.raver.oof();
    }
    
    /*** resetSprite - sets player sprite back to initial position
     * @param spriteList
     */ 
    public void resetSprite(ArrayList<PImage> spriteList){

    this.setRaverSprite(spriteList.get(1));
    }


    /*** endGame Method - displays endgame screen based on boolean @lose, 
     * for length of time defined by @msWaitBeforeRestart, 
     * resets all game variables and restarts game
     * @param lose - true if player lives depleted
     * @param p - PApplet object
     * @param spriteList - List of PImage sprites
     * @param msWaitBeforeRestart - length of time before game restarts
        */ 
    public void endGame(boolean lose, PApplet p, 
    ArrayList<PImage> spriteList, int msWaitBeforeRestart) {

        //check endgame count
        if(endGameCount < msWaitBeforeRestart){

            //set font
            p.g.textFont(p.createFont("PressStart2P-Regular.ttf", 20));

            //print endgame screen
            if(lose){ 
                p.g.fill(255, 255, 255);
                p.g.background(0);
                p.g.textAlign(CENTER,CENTER);
                p.g.text("GAME OVER", 220, 250);

            }else{
                p.g.fill(255, 255, 255);
                p.g.background(0);
                p.g.textAlign(CENTER,CENTER);
                p.g.text("YOU WIN", 220, 250);
            }
            //increment counter
            this.endGameCount+=1;
        }
        else{

            //respawn all sprites
            this.initConfig();
            this.resetFrightened();
            this.updateSprite(spriteList);
            this.resetSprite(spriteList);

            
            //respawn
            this.raver.respawn();
            this.raver.resetDirection();
            this.frightenedTimeCount = 0;

            for(Ghost ghost: this.ghosts){
                ghost.respawn();
                ghost.resetLastMove();
            }

            for(Snacc snacc: this.snaccs){
                snacc.snaccRespawn();
            }

            for(Life waka: this.livesLeft){
                waka.revive();
            }

            //reset all game variables and modes,
            this.gameOver = false;
            this.frightenedMode = false;
            this.shroomMode = false;
            this.ShroomTimeCount = 0;
            this.startedGame = false;
            this.ghostTimeCount = 0;
            this.ghostMode = false;
            this.gameOver = false;
            this.gameWin = false;
            this.endGameCount = 0;
            this.snaccCount = 1;
        }
    }


    /*** loadSpriteList 
     * - loads list of images within root folder and returns a list of images
     * @param p
     */ 
    public ArrayList<PImage> loadSpriteList(PApplet p){
        ArrayList<PImage> spriteList = new ArrayList<PImage>();
        spriteList.add(p.loadImage("src/main/resources/playerClosed.png"));
        spriteList.add(p.loadImage("src/main/resources/playerRight.png"));
        spriteList.add(p.loadImage("src/main/resources/playerLeft.png"));
        spriteList.add(p.loadImage("src/main/resources/playerDown.png"));
        spriteList.add(p.loadImage("src/main/resources/playerUp.png"));
        spriteList.add(p.loadImage("src/main/resources/frightened.png"));
        spriteList.add(p.loadImage("src/main/resources/whim.png"));
        spriteList.add(p.loadImage("src/main/resources/ignorant.png"));
        spriteList.add(p.loadImage("src/main/resources/ambusher.png"));
        spriteList.add(p.loadImage("src/main/resources/chaser.png"));
        return spriteList;

    }

    /*** performCurrentMove - sets game Move booleans based on user input 
     * - will continue to attempt move until successful or new input
     */ 
    public void performCurrentMove(){        
        if(this.getDirMove() == 1){
            //keep trying to move up until successful
            this.moveRaver(1,this.getSpeed());
        }

        if(this.getDirMove() == 4){
            this.moveRaver(4,this.getSpeed());
        }

        if(this.getDirMove() == 3){
            this.moveRaver(3,this.getSpeed());
        }

        if(this.getDirMove() == 2){
            this.moveRaver(2,this.getSpeed());
            }

        }


    /*** setMove method takes a direction 
     * determined by user input @dir and updates game movement booleans 
     *  1=UP 2=Down 3=LEft 4=Right
     * @param dir
     */ 
    public void setMove (int dir){

        if(dir == 1){
            this.moveUp = true;
            this.moveRight = false;
            this.moveLeft = false;
            this.moveDown = false;
            this.dir = 1;
        }

        else if(dir == 2){
            this.moveUp = false;
            this.moveRight = false;
            this.moveLeft = false;
            this.moveDown = true;
            this.dir = 2;
        }
            
        else if(dir == 3){
            this.moveUp = false;
            this.moveRight = false;
            this.moveDown = false;
            this.moveLeft = true;
            this.dir = 3;
        }

        else if(dir == 4){
            this.moveUp = false;
            this.moveRight = true;
            this.moveLeft = false;
            this.moveDown = false;
            this.dir = 4;
        }

    }

    /*** tickAll - calls tick method on all game objects 
     * - updating their positions and checking booleans.
     */ 
    public void tickAll(){
        this.tickRaver();
        this.tickSnaccs();
        this.tickGhosts();
        this.setTimeCount();
        this.setghostTimeCount();
    }

    public void PlaySnippet() {
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/pacman.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            Thread.sleep(10000); // looping as long as this thread is alive
        }catch(Exception e){
            System.out.println("Audio problem");
            System.out.println(e);
        }
    }
}


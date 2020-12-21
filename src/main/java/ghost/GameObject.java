package ghost;

import processing.core.PImage;
import processing.core.PApplet;
import java.util.List;


/** GameObject is an abstract class which is inherited by all game objects 
*
*/

public abstract class GameObject extends PApplet{

    protected int time;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int direction;
    protected int startPositionX;
    protected int startPositionY;
    protected int lastMove;

    protected long yVelocity;
    protected long xVelocity;

    protected PImage sprite;

    protected List<Tile> tiles;

    protected boolean goingUp;
    protected boolean goingLeft;
    protected boolean goingRight;
    protected boolean goingDown;
    protected boolean alive;


    /**Main constructor
    * @param int x
    * @param int y
    * @param PImage sprite
    * @param List<Tile> tiles list of tiles objects
    */
    public GameObject (int x, int y, PImage sprite, List<Tile> tiles){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.startPositionX = x;
        this.startPositionY = y;
        this.yVelocity = 0;
        this.xVelocity = 0;
        this.tiles = tiles;
        this.time = 0;
        this.direction = 0;
        this.width = 16;
        this.height = 16;
        this.lastMove = 0;
        this.alive = true;
        
    }

    /**Test constructor 
    * @param int x
    * @param int y
    * @param PImage sprite tiles list of ghost objects
    */
    public GameObject (int x, int y, PImage sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.startPositionX = x;
        this.startPositionY = y;
        this.yVelocity = 0;
        this.xVelocity = 0;
        this.time = 0;
        this.direction = 0;
        this.width = 16;
        this.height = 16;
        this.lastMove = 0;
        this.alive = true;
        
    }
    
    /**Test constructor - no PImage passed in
    *
    */
    public GameObject (int x, int y){
        this.x = x;
        this.y = y;
        this.startPositionX = x;
        this.startPositionY = y;
        this.yVelocity = 0;
        this.xVelocity = 0;
        this.time = 0;
        this.direction = 0;
        this.width = 16;
        this.height = 16;
        this.lastMove = 0;
        this.alive = true;
    }
    
    /*** 
     * @return int
     */
    public int getX(){
        return this.x;
    }

    
    /*** 
     * @return int
     */
    public int getY(){
        return this.y;
    }

    
    /*** 
     * @return int
     */
    public int getWidth(){
        return this.width;
    }
    
    /*** 
     * @return int
     */
    public int getHeight(){
        return this.height;
    }

    
    /*** 
     * @return int
     */
    public int getDirection(){
        return this.direction;
    }

    
    /*** 
     * @param i
     */
    public void setDirection(int i){
        this.direction = i;
    }

    public void resetDirection(){
        this.direction = 1;
    }

    /*** 
     * @param i
     */
    public void setLastMove(int i){
        this.lastMove = i;
    }
    
    /*** 
     * @return int
     */
    public int getLastMove(){
        return this.lastMove;
    }

    
    /*** getXv() gets Xvelocity
     * @return long
     */
    public long getXv(){
        return this.xVelocity;
    }

    
    /*** getYv() gets Yvelocity
     * @return long
     */
    public long getYv(){
        return this.yVelocity;
    }

    /***  oof() sets object alive = false
     */
    public void oof() {
        this.alive = false;
    }

    /***  revive() sets this.alive = true
     */
    public void revive() {
        this.alive = true;
    }
    
    
    /*** 
     * @return boolean
     */
    boolean checkAlive(){
        return this.alive;
    }

    
    /*** 
     * @param app
     */
    // public abstract void respawn();

    //pass in PApplet app
    public abstract void draw(PApplet app);
    

    /*** wallCollision() resets player velocity
     */
    public void wallCollision(){
        this.xVelocity = 0;
        this.yVelocity = 0;
    }

    
    /*** Adjusts player coordinates 
     * based on whether movement in provided direction is possible
     *  based on collision checks
     * @param speed
     * @return boolean whether move was successful
     */
    //move player up!
    public boolean moveUp(long speed){

        if(!(this.checkWallCollision(Math.toIntExact(this.x), 
        Math.toIntExact(this.y-8)))){

        if(this.y > 0){
        this.yVelocity = 1*speed;
        this.xVelocity = 0;
        this.direction = 4;
        this.lastMove = 4;
        return true;

        }else{

            return false;

        }
    }
    return false;
    }

    
    /*** Adjusts player coordinates based on whether movement 
     * in provided direction is possible based on collision checks
     * @param speed
     * @return boolean whether move was successful
     */
    //move player down!
    public boolean moveDown(long speed){
        
        if(!(this.checkWallCollision(Math.toIntExact(this.x), 
        Math.toIntExact(this.y+8)))){

            if(this.y < 576){
                this.yVelocity = -1*speed;
                this.xVelocity = 0;
                this.direction = 3;
                this.lastMove = 3;
            return true;
        }else{
            return false;
        }
    }return false;}

    
    /*** Adjusts player coordinates based on whether movement in 
     * provided direction is possible based on collision checks
     * @param speed
     * @return boolean whether move was successful
     */
    //move player right!
    public boolean moveRight(long speed){
        if(!(this.checkWallCollision(Math.toIntExact(this.x +8), 
        Math.toIntExact(this.y)))){
            if(this.x > 0){
                this.xVelocity = -1*speed;
                this.yVelocity = 0;
                this.direction = 1;
                this.lastMove = 1;
                return true;
            }else{
                return false;
            }
    }return false;}

    
    /*** Adjusts player coordinates based on whether movement in 
     * provided direction is possible based on collision checks
     * @param speed
     * @return boolean whether move was successful
     */
    //move player left!
    public boolean moveLeft(long speed){
        if(!(this.checkWallCollision(Math.toIntExact(this.x -8), 
        Math.toIntExact(this.y)))){
            if(this.x < 448){
                this.xVelocity = 1*speed;
                this.yVelocity = 0;
                this.direction = 2;
                this.lastMove = 2;
                return true;
            }else{
                return false;
            }
        }return false;
    }



    /*** wallCollision() iterates through wall/Tile 
     * objects and performs ABC collision check
     * If collision - Player velocity is reset
     * @param futureX
     * @param futureY
     * @return boolean
     */
    public boolean checkWallCollision(int futureX, int futureY) {
        if(futureX%8 == 0 || futureY%8==0){
        for(Tile tile : tiles){

            int tileLeft = tile.getX();
            int tileRight = tile.getX() + tile.getWidth();
            int tileTop = tile.getY();
            int tileBottom = tile.getY() + tile.getHeight();
            int raverRight = futureX + 16;
            int raverLeft = futureX;
            int raverTop = futureY;
            int raverBottom = futureY + 16;
        
            
        //ABCollision 
        if (raverRight > tileLeft && raverLeft < tileRight 
        && raverBottom > tileTop && raverTop < tileBottom ){
            return true;
            }
        
        }   
        }
        return false;
    }

    /***setSprite() Sets sprite to provided @sprite
     * @param sprite
     */
    public void setSprite(PImage sprite){
        
        this.sprite = sprite;
    }


    /*** Gets sprite
     * @return PImage
     */
    public PImage getSprite(){
            
        return this.sprite;
    }

}

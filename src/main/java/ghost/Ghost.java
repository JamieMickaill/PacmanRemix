package ghost;

import processing.core.PImage;
import processing.core.PApplet;
import java.util.List;
import java.util.Random;

/**Ghost extends GameObject 
 * - Abstract class for ghost types to be created from
*
*/
public abstract class Ghost  extends GameObject{

    protected int time;
    protected int aimX;
    protected int aimY;
    protected int xDistance;
    protected int yDistance;
    protected int corner;
    protected Raver raver;
    protected Ghost chaser;
    protected List<Snacc> snaccs;
    protected boolean frightened;
    protected boolean tripping;


    /**Main constructor
    * @param int x
    * @param int y
    * @param PImage sprite
    * @param List<Tile> tiles list of tiles objects
    * @param List<Snacc> snaccs list of snacc objects
    * @param Raver (Waka) Player instance
    */
    public Ghost (int x, int y, PImage sprite, 
    List<Tile> tiles,List<Snacc> snaccs, Raver raver){
        super(x, y, sprite, tiles);
        this.time = 0;
        this.aimX = 0;
        this.aimY = 0;
        this.xDistance = 0;
        this.yDistance = 0;
        this.raver = raver;
        this.frightened = false;
        this.chaser = null;
        this.snaccs = snaccs;
    }

    /**Secondary test constructor
    * @param int x
    * @param int y
    * @param PImage sprite
    * @param List<Tile> tiles list of tiles objects
    * @param Raver (Waka) Player instance
    */
    public Ghost (int x, int y, PImage sprite, 
    List<Tile> tiles, Raver raver){
        super(x, y, sprite, tiles);
        this.time = 0;
        this.aimX = 0;
        this.aimY = 0;
        this.xDistance = 0;
        this.yDistance = 0;
        this.raver = raver;
        this.frightened = false;
        this.chaser = null;
 
    }

    /**Test constructor - no PImage passed in
    *
    */
    public Ghost(int x, int y){
        super(x, y);
       
        this.aimX = 0;
        this.aimY = 0;
        this.xDistance = 0;
        this.yDistance = 0;
        this.lastMove = 0;
        this.frightened = false;
        this.chaser = null;
 
    }

    /*** respawn() resets all player variables 
     * (veloctity, coordinates, alive, invincibility, direction)
     */
    public void respawn(){
        this.x = this.startPositionX;
        this.y = this.startPositionY;
        this.frightened = false;
        this.alive = true;
        
    }
    
    /*** Sets chaser - used for Whim
     * @param c
     */
    public void setChaser(Ghost c){
        this.chaser = c;
    }

    
    /*** getAimX returns current AIM X coordinate point of Ghost
     * @return int
     */
    public int getAimX(){
        return this.aimX;
    }
    
    /*** getAimY returns current AIM X coordinate point of Ghost
     * @return int
     */
    public int getAimY(){
        return this.aimY;
    }

    
    /*** tick() method - 
     * takes in ghostMode variables and performs movements based on current mode
     * Scatter mode - ghosts go to their respective corners
     * Chase mode - ghosts target Player in their own 
     * individual ways defined within subclass getDir()
     * FrightenedMode - movements made at random
     * ShroomMode - Ghosts will collect fruit
     * @param ghostMode
     * @param frightened
     * @param shroomMode
     * @param speed
     * @param raver
     */
    public void tick(boolean ghostMode, boolean frightened, 
    boolean shroomMode, long speed, Raver raver) {

    //if alive
    if(alive){           
            //check mode
            if(shroomMode){
                shroomModeActive(speed);
            }

            if(frightened){
                frightenedMode(speed);}

            //if scattermode
            else if((!(ghostMode))){
                scatterMode(ghostMode, speed, raver);

            //if CHASE mode    
            }else if (ghostMode){
                chaseMode(ghostMode, speed, raver);}


            if(!(this.checkWallCollision(Math.toIntExact(this.x - xVelocity),
            Math.toIntExact(this.y -yVelocity)))){
                this.y -= this.yVelocity;
                this.x -= this.xVelocity;
                }else{
                    this.wallCollision();
                }

        this.time++;
    }
}



    /*** tick() method overridden for tests 
     * - takes in ghostMode variables and performs movements based on current mode
     * Scatter mode - ghosts go to their respective corners
     * Chase mode - ghosts target 
     * Player in their own individual ways defined within subclass getDir()
     * FrightenedMode - movements made at random
     * @param ghostMode
     * @param frightened
     * @param speed
     * @param raver
        */
    public void tick(boolean ghostMode, 
    boolean frightened, long speed, Raver raver) {

        //if alive
        if(alive){           
                //check mode


                if(frightened){
                    frightenedMode(speed);}    
                //if scattermode
                else if((!(ghostMode))){
                    scatterMode(ghostMode, speed, raver);
                //if CHASE mode    
                }else if (ghostMode){
                    chaseMode(ghostMode, speed, raver);}


                if(!(this.checkWallCollision(Math.toIntExact(this.x - xVelocity), 
                Math.toIntExact(this.y -yVelocity)))){
                    this.y -= this.yVelocity;
                    this.x -= this.xVelocity;
                    }else{
                        this.wallCollision();
                    }

            this.time++;
        }
    }


    
    /*** Draw() method draws all objects to screen
     * @param app
     */
    //pass in PApplet app
    public void draw(PApplet app) {
        if(alive){
        //Handles graphics
        app.image(this.sprite, this.x-5, this.y-5);
        }

    }

    /*** spookOn method turns frightened mode on
     */
    public void spookOn(){
        this.frightened = true;
    }

    /*** spookOn method turns frightened mode off
     */
    public void spookOff(){


        this.frightened = false;
    }

    
    /*** getSpooked() gets whether ghost object is scared
     * @return boolean
     */
    public boolean getSpooked(){
        return this.frightened;
    }


    /*** spookOn method turns shroomMode mode on
     */
    public void shroomOn(){
        this.tripping = true;
    }

    /*** spookOff method turns shroomMode mode on
     */
    public void shroomOff(){
        this.tripping = false;
    }

    
    /*** getSpooked() gets whether ghost object is tripping
     * @return boolean
     */
    public boolean getShroomMode(){
        return this.tripping;
    }


    
    /*** Scattermode() ghosts go to their respective corner 
     * - movement selected based on X and Y distance to AIM (corner)
     * 
     * @param ghostMode ChaserMode
     * @param speed gameSpeed
     * @param raver Current player
     */
    public void scatterMode(boolean ghostMode, long speed, Raver raver){

    int corner = getCorner();
    int axis = getAxis(raver);
    makeMove(axis, corner, speed);

    }
        
    
    /*** Sets AIMX and AIMY coordinates based on corner to Aim for 
     * @return int corner 1topleft, 2topright, 3bottomleft, 4bottomright
     */
    public int getCorner(){
        if(this.corner == 1){
            this.aimX =0;
            this.aimY = 0;
        }
        else if(this.corner == 2){
            this.aimX =448;
            this.aimY = 0;

        }else if(this.corner == 3){
            this.aimX =0;
            this.aimY = 576;
            
        }else if(this.corner == 4){
            this.aimX =448;
            this.aimY = 576;
            
        }

        return this.corner;
    }

    
    /*** Calculates which axis move should be made first
     * (selects X (Horizontal = 0) or Y (Vertical = 1) distance is longer to AIMX)
     * @param raver
     * @return int (Horizontal = 0) or Y (Vertical = 1)
     */
    public int getAxis(Raver raver){

    //calculate distance from current to destination
    this.xDistance = Math.abs(this.aimX - (this.x));
    this.yDistance = Math.abs(this.aimY - (this.y));

    //check whether xDistance or yDistance is longer
    //if xDistance is longer then we move horizontally
    if(this.xDistance > this.yDistance){
    return 1;
    }else{
        return 0;
        }
    }

    
    /*** Chase mode - ghosts target Player in their own 
     * individual ways defined within subclass getDir()
     * @param ghostMode
     * @param speed
     * @param raver
     */
    public void chaseMode( boolean ghostMode,long speed,Raver raver){

        int dir = getDir(raver);   
        //upleft = 1, upright = 2, downleft = 3, downright = 4
        int axis = getAxis(raver);   
        //horizontal=1 or vertical=0 priority  
        makeMove(axis, dir, speed);

    }

    
    /*** Each ghost is required to define its own 
     * individual method of determining next direction
     */
    public abstract int getDir(Raver raver);
    

    
    /*** Makes ghost Movement in prioritized order 
     * based on ghosts current AIM x and y coordinates.
     * @param axis
     * @param corner
     * @param speed
     */
    public void makeMove(int axis, int corner, long speed){

    //vertical distance is furthest from target
    if(axis==0){
    //top right therefore we must go up as priority
    if(corner==2){
        //check if we can go up
        if((!(checkWallCollision(this.x, this.y-8))) && this.lastMove !=3){
            this.moveUp(speed);
        //if not, we can try go right
        }else if((!(checkWallCollision(this.x+8, this.y)))  && this.lastMove !=2){
            this.moveRight(speed);}
        //try go down
        else if((!(checkWallCollision(this.x, this.y+8))) && this.lastMove != 4 && this.lastMove !=3){
            this.moveDown(speed);}
        //left is last resort
        else  if((!(checkWallCollision(this.x-8, this.y))) && this.lastMove != 1 && this.lastMove !=2){
            this.moveLeft(speed);}
    }
    else if(corner==1){
        //check if we can go up
        if((!(checkWallCollision(this.x, this.y-8))) && this.lastMove !=3){
            this.moveUp(speed);
        //if not, we can try go left
        }else if((!(checkWallCollision(this.x-8, this.y))) && this.lastMove != 1 ){
            this.moveLeft(speed);}
        //try go down
        else if((!(checkWallCollision(this.x, this.y+8))) && this.lastMove != 4 && this.lastMove !=3){
            this.moveDown(speed);}
        //right is last resort
        else  if((!(checkWallCollision(this.x+8, this.y))) && this.lastMove != 1 && this.lastMove !=2){
            this.moveRight(speed);}
    }
    else if(corner==3){
        //check if we can go down
        if((!(checkWallCollision(this.x, this.y+8))) && this.lastMove != 4 ){
            this.moveDown(speed);
        //if not, we can try go left
        }else if((!(checkWallCollision(this.x-8, this.y))) && this.lastMove != 1 ){
            this.moveLeft(speed);}
        //try go up
        else if((!(checkWallCollision(this.x, this.y-8))) && this.lastMove != 4 && this.lastMove !=3){
            this.moveUp(speed);}
        //right is last resort
        else  if((!(checkWallCollision(this.x+8, this.y))) && this.lastMove != 1 && this.lastMove !=2){
            this.moveRight(speed);}
    }
    else if(corner==4){
        //check if we can go down
        if((!(checkWallCollision(this.x, this.y+8))) && this.lastMove != 4){
            this.moveDown(speed);
        //if not, we can try go right
        }else if((!(checkWallCollision(this.x+8, this.y)))  && this.lastMove !=2){
            this.moveRight(speed);}
        //try go up
        else if((!(checkWallCollision(this.x, this.y-8))) && this.lastMove != 4 && this.lastMove !=3){
            this.moveUp(speed);}
        //left is last resort
        else  if((!(checkWallCollision(this.x-8, this.y))) && this.lastMove != 1 && this.lastMove !=2){
            this.moveLeft(speed);}
    }

    }else if(axis==1){ //horizontal priority
    //top right therefore we must go up as priority
    if(corner==2){
        // we can try go right
        if((!(checkWallCollision(this.x+8, this.y)))  && this.lastMove !=2){
            this.moveRight(speed);}
        //check if we can go up
        else if((!(checkWallCollision(this.x, this.y-8)))  && this.lastMove !=3){
            this.moveUp(speed);}
        //left is next resort
        else  if((!(checkWallCollision(this.x-8, this.y))) && this.lastMove != 1 && this.lastMove !=2){
            this.moveLeft(speed);}
        //try go down as last resort
        else if((!(checkWallCollision(this.x, this.y+8))) && this.lastMove != 4 && this.lastMove !=3){
            this.moveDown(speed);}

    }
    else if(corner==1){                //if not, we can try go left
        if((!(checkWallCollision(this.x-8, this.y))) && this.lastMove != 1){
            this.moveLeft(speed);}
        //check if we can go up
        else if((!(checkWallCollision(this.x, this.y-8))) && this.lastMove !=3){
            this.moveUp(speed);}
        //right is next resort
        else  if((!(checkWallCollision(this.x+8, this.y))) && this.lastMove != 1 && this.lastMove !=2){
            this.moveRight(speed);}
        //try go down as last resort
        else if((!(checkWallCollision(this.x, this.y+8))) && this.lastMove != 4 && this.lastMove !=3){
            this.moveDown(speed);}

    }
    else if(corner==3){                //bottom left
        if((!(checkWallCollision(this.x-8, this.y))) && this.lastMove != 1){
            this.moveLeft(speed);}
        //check if we can go down
        else if((!(checkWallCollision(this.x, this.y+8))) && this.lastMove != 4){
            this.moveDown(speed);}
        //right is next resort
        else  if((!(checkWallCollision(this.x+8, this.y))) && this.lastMove != 1 && this.lastMove !=2){
            this.moveRight(speed);}
        //try up down as last resort
        else if((!(checkWallCollision(this.x, this.y-8))) && this.lastMove != 4 && this.lastMove !=3){
            this.moveUp(speed);}

    }
    else if(corner==4){                //bottom right
        if((!(checkWallCollision(this.x+8, this.y)))  && this.lastMove !=2){
            this.moveRight(speed);}
        //check if we can go down
        else if((!(checkWallCollision(this.x, this.y+8))) && this.lastMove != 4 ){
            this.moveDown(speed);}
        //left is next resort
        else  if((!(checkWallCollision(this.x-8, this.y))) && this.lastMove != 1 && this.lastMove !=2){
            this.moveLeft(speed);}
        //try up down as last resort
        else if((!(checkWallCollision(this.x, this.y-8))) && this.lastMove != 4 && this.lastMove !=3){
            this.moveUp(speed);}

    }

    }

    }

    
    /*** drawTargetLine() draws debug line between 
     * Ghost and AIM x/y coordinates when called
     * @param ghostMode
     * @param raver
     * @param p
     */
    public void drawTargetLine(boolean ghostMode, Raver raver, PApplet p) {

    if(this.alive){
        p.g.line(this.aimX, this.aimY, this.x, this.y);
        p.g.stroke(126);

        }
    }

    /*** FrightenedMode - movements made at random
     * @param speed
     */
    public void frightenedMode(long speed){
        Random randInt = new Random();
        int axis = randInt.nextInt(2) ;
        int dir = randInt.nextInt(4)+1;
        this.aimX = randInt.nextInt(448);
        this.aimY = randInt.nextInt(556);
        makeMove(axis, dir, speed);
    }

    /*** resetLastMove - Sets last move to 0 
     * - prevents movement issues at respawn
     */
    public void resetLastMove(){
        this.lastMove = 0;
    }

    /*** ghostBusted() - Kills ghost 
     * - alive=false, this.x and y reset, frightened mode reset
     */
    public void ghostBusted(){
        this.frightened = false;
        this.x =0;
        this.y=0;
        this.oof();       

    }
    

    /*** shroomModeActive 
     * - Ghosts begin collecting fruit and flashing between sprites
     * @param speed
     */
    public void shroomModeActive(long speed){
        this.checkSnaccCollision();
    }


    /*** snaccTime() - calls eat method on provided fruit
     * @param snacc
     */
    public void snaccTime(Snacc snacc) {
        snacc.snaccEaten();
    }

    /*** checkSnaccCollision() 
     * iterates through Fruit objects and performs ABC collision check
     * If collision - Fruit is eaten
     */
    public void checkSnaccCollision() {
            
        for(Snacc snacc : this.snaccs){

            int snaccCenterX = snacc.getX()+8;
            int snaccCenterY = snacc.getY()-8;
            int raverCenterX = this.x+8;
            int raverCenterY = this.y-8;

            //ABCollision 
            if (raverCenterX == snaccCenterX  
            && raverCenterY == snaccCenterY ){
                this.snaccTime(snacc);
            }

        }
        
}}
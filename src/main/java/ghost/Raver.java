package ghost;

import processing.core.PImage;
import processing.core.PApplet;
import java.util.List;

/**Raver (Waka) extends GameObject - Main class for player object (Waka)
*
*/
public class Raver extends GameObject{

    private boolean invincible;
    private List<Ghost> ghosts;
    private List<Snacc> snaccs;

    /**Test constructor - no PImage passed in
    *
    */
    public Raver(int x, int y){
        super(x, y);
        this.x = x;
        this.y = y;
        this.direction = 1;          
    
        }

    /**Test constructor 
    * @param int x
    * @param int y
    * @param List<Ghost> tiles list of ghost objects
    * @param List<Snacc> snaccs list of snacc objects
    * @param List<Tile> tiles list of tiles objects
    */
    public Raver(int x, int y, List<Ghost> ghosts, List<Snacc> snaccs, List<Tile> tiles){
        super(x, y);
        this.x = x;
        this.y = y;
        this.ghosts = ghosts;
        this.snaccs = snaccs;
        this.tiles = tiles;
 
    }

        /**Main constructor
        * @param int x
        * @param int y
        * @param PImage sprite
        * @param List<Ghost> tiles list of ghost objects
        * @param List<Tile> tiles list of tiles objects
        * @param List<Snacc> snaccs list of snacc objects
        */
    public Raver(int x, int y, PImage sprite, List<Ghost> ghosts, List<Snacc> snaccs, List<Tile> tiles){
        super(x, y, sprite, tiles);
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.ghosts = ghosts;
        this.snaccs = snaccs;
        this.tiles = tiles;
        this.direction = 1;


    }


   /**Tick() method - updates player position if alive, based on movement velocity and wall collision checks
    *
    */
    public void tick() {
        if(this.alive){

            if(!(this.checkWallCollision(Math.toIntExact(this.x - xVelocity), Math.toIntExact(this.y -yVelocity)))){
            this.y -= this.yVelocity;
            this.x -= this.xVelocity;
            }else{
                this.wallCollision();
            }

            this.checkGhostCollision();
            this.checkSnaccCollision();

    }
    }

    
    /*** Draw() method draws object to screen if alive
     * @param app
     */
    //pass in PApplet app
    public void draw(PApplet app) {
        //Handles graphics
        if(this.alive) { 
            app.image(this.sprite, this.x-5, this.y-5);
            }
    }

    /*** snaccTime() calls eat method on Fruit object
     * @param snacc
     */
    public void snaccTime(Snacc snacc) {
        snacc.snaccEaten();
    }

    /*** checkGhostCollision() iterates through ghost objects 
     * and performs ABC collision check
     * If collision - 
     * Player or Ghost dies depending on (Player.getInvincible)
     */
    public void checkGhostCollision() {

        if(this.x%8 == 0 || this.y%8==0){

            for(Ghost ghost : ghosts){

                int ghostLeft = ghost.getX();
                int ghostRight = ghost.getX() + ghost.getWidth();
                int ghostTop = ghost.getY();
                int ghostBottom = ghost.getY() + ghost.getHeight();
                int raverRight = this.x + this.width;
                int raverLeft = this.x;
                int raverTop = this.y;
                int raverBottom = this.y + this.height;
            

                //ABCollision 
                if (raverRight > ghostLeft && raverLeft < ghostRight 
                && raverBottom > ghostTop && raverTop < ghostBottom ){
                    if(this.invincible){
                        ghost.ghostBusted();

                    }else{
                    this.direction = 1;
                    this.oof();
                    return;
                }}
                
            }}

        }

    /*** checkSnaccCollision() 
     * iterates through Fruit objects and performs ABC collision check
     * If collision - Fruit is eaten
     */
    public void checkSnaccCollision() {
        
        for(Snacc snacc : this.snaccs){

            //collision for supersnacks is different

            int snaccCenterX = snacc.getX()+8;
            int snaccCenterY = snacc.getY()-8;
            int raverCenterX = this.x+8;
            int raverCenterY = this.y-8;




            //ABCollision 
            if (raverCenterX == snaccCenterX  && raverCenterY == snaccCenterY ){
                this.snaccTime(snacc);
                //maybe dont return?
                // return;
            }


        }
        



    }


    void setInvincibleOn(){
        this.invincible = true;
    }

    
    /*** checkInvincible() checks invincible mode
     * @return boolean
     */
    boolean checkInvincible(){
        return this.invincible;
    }

    /*** toggle invincibility - used for ghost mode.
     * @return boolean
     */
    void setInvincibleOff(){
        this.invincible = false;;
    }

    /*** respawn() resets all player variables 
     * (veloctity, coordinates, alive, invincibility, direction)
     */
    void respawn(){
        this.xVelocity = 0;
        this.yVelocity = 0;
        this.direction = 1;
        this.x = this.startPositionX;
        this.y = this.startPositionY;
        this.invincible = false;
        this.alive = true;
        
    }



}
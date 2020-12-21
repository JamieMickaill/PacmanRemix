package ghost;

import processing.core.PImage;
import java.util.List;


/**Ambusher extends Ghost 
 * - Class for Ambusher ghost type to be created from
* Ambusher gets direction for chaseMode 
based on Raver (Waka) current position 
+ 2 tiles in Waka direction
*
*/
public class Ambusher extends Ghost{
    
    /**Main constructor
    * @param int x
    * @param int y
    * @param PImage sprite
    * @param List<Tile> tiles list of tiles objects
    * @param List<Snacc> snaccs list of snacc objects
    * @param Raver (Waka) Player instance
    */
    Ambusher(int x, int y, PImage sprite, List<Tile> tiles, 
    List<Snacc> snaccs, Raver raver){
        super(x, y, sprite, tiles, snaccs, raver);
        this.corner = 2;
    }

    /**Secondary test constructor
    * @param int x
    * @param int y
    * @param PImage sprite
    * @param List<Tile> tiles list of tiles objects
    * @param Raver (Waka) Player instance
    */
    Ambusher(int x, int y, PImage sprite, List<Tile> tiles, Raver raver){
        super(x, y, sprite, tiles, raver);
        this.corner = 2;
    }

    /**Test constructor - no PImage passed in
    *
    */
    Ambusher(int x, int y){
        super(x, y);
        this.corner = 2;
    }
    
    
    /*** getDir gets direction for chased based on Raver (Waka) 
     * current position + 2 tiles in direction of player 
     * @param raver
     * @return int
     */
    public int getDir(Raver raver){

            int direction = raver.getDirection();
            //1=right, 2=left, 3=down, 4=up

            //player going right - ambush him!
            if(direction == 1){
                this.aimX = raver.getX() + 4*16;
                this.aimY = raver.getY();
            }
            //player going left - ambush him!
            if(direction == 2){
                this.aimX = raver.getX() - 4*16;
                this.aimY = raver.getY();
            }

            //player going down - ambush him!
            if(direction == 3){
                this.aimX = raver.getX();
                this.aimY = raver.getY() + 4*16;
            }

            //player going up - ambush him!
            if(direction == 4){
                this.aimX = raver.getX();
                this.aimY = raver.getY() - 4*16;
            }

            
            //calculate distance from current to destination
            this.xDistance = this.aimX - (this.x);
            this.yDistance = this.aimY - (this.y);
    
            //positive x value means going right
            if (this.xDistance>0){
                this.goingRight = true;
                this.goingLeft = false;
            }else{
                this.goingRight = false;
                this.goingLeft = true;
            }
    
            //positive y value means going down
            if (this.yDistance>0){
                this.goingDown = true;
                this.goingUp = false;
            }else{
                this.goingDown = false;
                this.goingUp = true;
            }
    
            if(this.goingUp && this.goingLeft){
                return 1;
            }
            else if(this.goingUp && this.goingRight){
                return 2;
            }
            else if(this.goingDown && this.goingLeft){
                return 3;
            }
            else if(this.goingDown && this.goingRight){
                return 4;
            }

            else{return 0;}
    
        }

            


        
}
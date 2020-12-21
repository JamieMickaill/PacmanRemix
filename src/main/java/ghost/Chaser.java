package ghost;

import processing.core.PImage;

import java.util.List;




/**Chaser extends Ghost - 
 * Class for Chaser ghost type to be created from
* Chaser gets direction for chaseMode 
based on Raver (Waka) current position
*
*/
public class Chaser extends Ghost{
    
    /**Main constructor
    * @param int x
    * @param int y
    * @param PImage sprite
    * @param List<Tile> tiles list of tiles objects
    * @param List<Snacc> snaccs list of snacc objects
    * @param Raver (Waka) Player instance
    */
    Chaser(int x, int y, PImage sprite, List<Tile> tiles, 
    List<Snacc> snaccs, Raver raver){
        super(x, y, sprite, tiles, snaccs, raver);
        this.corner = 1;
    }

    /**Secondary test constructor
    * @param int x
    * @param int y
    * @param PImage sprite
    * @param List<Tile> tiles list of tiles objects
    * @param Raver (Waka) Player instance
    */
    Chaser(int x, int y, PImage sprite, List<Tile> tiles, Raver raver){
        super(x, y, sprite, tiles, raver);
        this.corner = 1;
    }

    /**Test constructor - no PImage passed in
    *
    */
    Chaser(int x, int y){
        super(x, y);
        this.corner = 1;
    }

    /*** getDir gets direction for 
     * chaseMode based on Raver (Waka) current position
     * @param raver
     * @return int
     */
    public int getDir(Raver raver){
            
            this.aimX = raver.getX();
            this.aimY = raver.getY();
            
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
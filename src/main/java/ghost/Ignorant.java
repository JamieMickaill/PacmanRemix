package ghost;

import processing.core.PImage;
import java.util.List;


/**Ignorant extends Ghost - 
 * Class for Ignorant ghost type to be created from
* Ignorant gets direction for chaseMode gets direction 
* based on Raver (Waka) current position 
 * - if distance greater than 8 tiles away - aims for corner
*/
public class Ignorant extends Ghost{
    private boolean ignorant;
    
    /**Main constructor
    * @param int x
    * @param int y
    * @param PImage sprite
    * @param List<Tile> tiles list of tiles objects
    * @param List<Snacc> snaccs list of snacc objects
    * @param Raver (Waka) Player instance
    */
    Ignorant(int x, int y, PImage sprite, List<Tile> tiles, 
    List<Snacc> snaccs, Raver raver){
        super(x, y, sprite, tiles, snaccs, raver);
        this.corner = 3;
        this.ignorant = false;
    }

    /**Secondary test constructor
    * @param int x
    * @param int y
    * @param PImage sprite
    * @param List<Tile> tiles list of tiles objects
    * @param Raver (Waka) Player instance
    */
    Ignorant(int x, int y, PImage sprite, List<Tile> tiles, Raver raver){
        super(x, y, sprite, tiles, raver);
        this.corner = 3;
        this.ignorant = false;
    }

    /**Test constructor - no PImage passed in
    *
    */
    Ignorant(int x, int y){
        super(x, y);
        this.corner = 3;
    }

    
    /*** getDir gets direction for chased based on Raver (Waka) current position 
     * - if distance greater than 8 tiles away - aims for corner
     * @param raver
     * @return int
     */
    public int getDir(Raver raver){

            //check if more than 8 units away from raver (ignorant/not)
            this.aimX = raver.getX();
            this.aimY = raver.getY();
            //calculate distance from current to destination
            this.xDistance = this.aimX - (this.x);
            this.yDistance = this.aimY - (this.y);

            //convert to double
            double xDistance = this.xDistance;
            double yDistance = this.xDistance;

            //zdistance is sqrt(x^2 + y^2)
            double zDistance =  
            Math.sqrt( Math.pow(Math.abs(xDistance), 2) +
             Math.pow(Math.abs(yDistance), 2));

            if((zDistance/16) > 8){
                ignorant = false;


            }else if ((zDistance/16) < 8){
                ignorant = true;
            }

            if(!(ignorant)){

                this.aimX = raver.getX();
                this.aimY = raver.getY();
        
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
    
        //if ignorant mode active - goes to corner
        }else if(ignorant){

            int corner = getCorner();
            
            return corner;
        }else{
            return 0;
        }

    }
}

            


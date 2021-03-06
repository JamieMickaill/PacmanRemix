package ghost;

import processing.core.PImage;

import java.util.List;

/**Whim extends Ghost 
 * - Class for Whim ghost type to be created from
* Whim gets direction for chaseMode based on Chaser current aim
*/
public class Whim extends Ghost{

    /**Main constructor
    * @param int x
    * @param int y
    * @param PImage sprite
    * @param List<Tile> tiles list of tiles objects
    * @param List<Snacc> snaccs list of snacc objects
    * @param Raver (Waka) Player instance
    */

    Whim(int x, int y, PImage sprite, List<Tile> tiles, 
    List<Snacc> snaccs, Raver raver){
        super(x, y, sprite, tiles, snaccs, raver);
        this.corner = 4;

    }

    /**Secondary test constructor
    * @param int x
    * @param int y
    * @param PImage sprite
    * @param List<Tile> tiles list of tiles objects
    * @param Raver (Waka) Player instance
    */
    Whim(int x, int y, PImage sprite, List<Tile> tiles, Raver raver){
        super(x, y, sprite, tiles, raver);
        this.corner = 4;

    }

    /**Test constructor - no PImage passed in
    *
    */
    Whim(int x, int y){
        super(x, y);
        this.corner = 4;
    }
    
    /*** getDir gets direction for chaser 
     * based on Chaser current aim
     * @param raver
     * @return int
     */
    public int getDir(Raver raver){


            int tempAimX = 0;
            int tempAimY = 0;

            int direction = raver.getDirection();
            //1=right, 2=left, 3=down, 4=up

            //player going right - ambush him!
            if(direction == 1){
                tempAimX = raver.getX() + 2*16;
                tempAimY = raver.getY();
            }
            //player going left - ambush him!
            if(direction == 2){
                tempAimX = raver.getX() - 2*16;
                tempAimY = raver.getY();
            }

            //player going down - ambush him!
            if(direction == 3){
                tempAimX = raver.getX();
                tempAimY = raver.getY() + 2*16;
            }

            //player going up - ambush him!
            if(direction == 4){
                tempAimX = raver.getX();
                tempAimY = raver.getY() - 2*16;
            }

            //calculate distance between Chaser 
            //coordinates and current this coordinates
            int DistanceBetweenChaserXandthisX = 
            (this.x - this.chaser.getX());
            int DistanceBetweenChaserYandthisY = 
            (this.y - this.chaser.getY());

            int chaserToRaverFuturePositionDistanceX = 
            tempAimX - chaser.getX();
            int chaserToRaveFuturePositionrDistanceY = 
            tempAimY - chaser.getY();

            this.aimX = (this.x - DistanceBetweenChaserXandthisX) +
             2*(chaserToRaverFuturePositionDistanceX);

            this.aimY = (this.y - DistanceBetweenChaserYandthisY) +
             2*(chaserToRaveFuturePositionrDistanceY);
        
            
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
    
            //calculate prioritized direction
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
            else{
                return 0;}
    
        }

            


        
}

 

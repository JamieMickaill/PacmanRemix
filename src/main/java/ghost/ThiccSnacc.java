package ghost;

import processing.core.PImage;



/**
*ThiccSnacc (SuperFruit) extends Snacc 
- when collected by player causes ghosts to enter Frightened Mode
*/

public class ThiccSnacc extends Snacc{
   

    /**Test constructor - no PImage passed in
    *
    */

    public ThiccSnacc(int x, int y){
        super(x,y);
        //thicc = Superfruit
        this.thicc = true;
    }

   /**Main constructor 
    * - takes x coordinate, y coordinate, PImage sprite
    * @param int x
    * @param int y
    * @param PImage sprite
    */
    
    public ThiccSnacc(int x, int y, PImage sprite){
        super(x,y,sprite);
        this.thicc = true;
    }
}
    

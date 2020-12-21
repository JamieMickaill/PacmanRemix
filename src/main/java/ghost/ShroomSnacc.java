package ghost;

import processing.core.PImage;



/**ShroomSnacc (Extention) extends Snacc 
 * - when collected by player causes ghosts to enter ShroomMode,
* Ghosts will begin eating fruit and their sprites will flash,
* gamespeed increases to 2x
*/
public class ShroomSnacc extends Snacc{
  
    /**Test constructor - no PImage passed in
    *
    */
    public ShroomSnacc(int x, int y){
        super(x,y);
        this.thicc = false;
        this.shroom = true;
        this.used = false;
    }


   /**Main constructor 
    * - takes x coordinate, y coordinate, PImage sprite
    * @param int x
    * @param int y
    * @param PImage sprite
    */
    public ShroomSnacc(int x, int y, PImage sprite){
        super(x,y,sprite);
        this.shroom = true;
        this.thicc = false;

    }}

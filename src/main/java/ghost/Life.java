package ghost;

import processing.core.PImage;
import processing.core.PApplet;



/**
*Life extends GameObject 
- creates Life objects which represent 
player lives and are drawn to screen
*/

public class Life extends GameObject{

    /**Main constructor
    * @param int x
    * @param int y
    * @param PImage sprite
    */
     public Life(int x, int y, PImage sprite){
         super(x,y,sprite);
     }
    
    /**Test constructor - no PImage passed in
    *
    */
     public Life(int x, int y){
        super(x,y);
    }


    
    /*** Draw method - draws sprite to screen
     * @param app
     */
    //pass in PApplet app
    public void draw(PApplet app) {
        //Handles graphics
        if(this.alive){
            app.image(this.sprite, this.x, this.y);
        }
    }
    
}
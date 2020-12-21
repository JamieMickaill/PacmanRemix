package ghost;

import processing.core.PImage;
import processing.core.PApplet;



/**EdibleObject extends GameObject 
 * - abstract class for all collectable/edible game objects
*
*/
public abstract class EdibleObject extends GameObject{
    //current location
    protected int x;
    protected int y;
    protected PImage sprite;
    protected boolean snaccAte;
    protected boolean used;

    /**Test constructor - no PImage passed in
    *
    */
    public EdibleObject(int x, int y){
        super(x,y);
        this.x = x;
        this.y = y;
        this.snaccAte = false;     
        this.used = false;
        this.height = 16;
        this.width = 16;
        
    }

   /**Main constructor 
    * - takes x coordinate, y coordinate, PImage sprite
    * @param int x
    * @param int y
    * @param PImage sprite
    */
    public EdibleObject(int x, int y, PImage sprite){
        super(x,y,sprite);
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.snaccAte = false;
        this.used = false;
        this.height = sprite.height;
        this.width = sprite.width;
        
    }
    
   /**getIsEdible() Returns whether object has been collected/eaten
    * @return boolean  
    */
    public boolean getIsEdible(){
        return (!(this.snaccAte));
    }

   /**snaccEaten() Sets object collected/eaten
    */
    public void snaccEaten(){
        this.snaccAte = true;
    }

    
   /*snaccRespawn() 
   Sets object collected/eaten = false and used = false
    */
    public void snaccRespawn(){
        this.snaccAte = false;
        this.used = false;
    }

  
    /** Draw method
     * @param app
     */
    //pass in PApplet app
    public void draw(PApplet app) {
        //Handles graphics
        if(!(this.snaccAte)){
            app.image(this.sprite, this.x, this.y);
        }
    }


}
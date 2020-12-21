package ghost;

import processing.core.PImage;


/**Snacc (Fruit) extends EdibleObject 
 * - collectable by object - requires all to be eaten to win game
*
*/
public class Snacc extends EdibleObject{

     protected boolean thicc;
     protected boolean used;
     protected boolean shroom;
    

   /**Test constructor - no PImage passed in
    *
    */
    public Snacc(int x, int y){
        super(x,y);
        this.thicc = false;
        this.shroom = false;
        this.used = false;
    }

   /**Main constructor - takes x coordinate, y coordinate, PImage sprite
    * @param int x
    * @param int y
    * @param PImage sprite
    */
    public Snacc(int x, int y, PImage sprite){
        super(x,y,sprite);
        this.used = false;
        
    }
        
    /**getThicc() returnstrue if this is thiccSnacc (SuperFruit) 
     * @return boolean
     */
    public boolean getThicc(){
        return this.thicc;
    }
        


    /**checkThiccSnaccAte() 
     * returns true if thiccSnacc (SuperFruit) has been eaten,
     *  updates "used" variable to true - fruits abilities activated
     * @return boolean true 
     * if special fruit object is eaten and effects are to be activated
     */
    public boolean checkThiccSnaccAte(){
        if(this.snaccAte && this.thicc && (!(this.used)) ){
            this.used = true;
            return true;}
            else return false;
        }

     
    /**checkShroomSnaccAte() 
     * returns true if ShroomSnacc (Extension fruit) has been eaten,
     *  updates "used" variable to true - fruits abilities activated
     * @return boolean true if ShroomSnacc 
     * (Extension fruit) object is eaten and effects are to be activated
     */
    public boolean checkShroomSnaccAte(){
        if(this.snaccAte && this.shroom && (!(this.used)) ){
            this.used = true;
            return true;}
            else return false;
        }}

 
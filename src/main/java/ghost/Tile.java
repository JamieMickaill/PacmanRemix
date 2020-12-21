package ghost;

import processing.core.PImage;
import processing.core.PApplet;



/**Tile extends GameObject - Wall objects parsed in from map file
*
*/
    public class Tile extends GameObject{
        private char type;
        private PImage sprite;
        
        /**Main constructor - takes x coordinate, y coordinate, PImage sprite
        * @param int x
        * @param int y
        * @param PImage sprite
        */
        public Tile(int x, int y, PImage sprite, char type){
            super(x,y,sprite);
            this.sprite = sprite;
            this.type = type;
        }

        /**Test constructor - no PImage passed in
        *
        */
        public Tile(int x, int y){
            super(x,y);

        }

        
        /*** Draws sprites to screen
         * @param app
         */
        //pass in PApplet app
        public void draw(PApplet app) {
        //Handles graphics
            app.image(this.sprite, this.x, this.y);

        }

    }

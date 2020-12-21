//GameManagerTest
package ghost;

import processing.core.PImage;
import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class GameManagerTest {

    @Test 
    public void simpleTest() {
        GameManager testGame = new GameManager();
        assertNotNull(testGame);
    }


@Test 
public void getTimeCount() {
    GameManager testGame = new GameManager();
    assertEquals(testGame.getTimeCount(),0);
}

@Test 
public void setTimeCount() {
    GameManager testGame = new GameManager();
    testGame.setTimeCount();
    assertEquals(testGame.getTimeCount(),1);
}

@Test 
public void getGhostTimeCount() {
    GameManager testGame = new GameManager();
    assertEquals(testGame.getghostTimeCount(),0);
}

@Test 
public void setGhostTimeCount() {
    GameManager testGame = new GameManager();
    testGame.setghostTimeCount();
    assertEquals(testGame.getghostTimeCount(),1);
}

@Test 
public void getDirMove() {
    GameManager testGame = new GameManager();
    assertEquals(testGame.getDirMove(),0);
}

@Test 
public void setDebugMode() {
    GameManager testGame = new GameManager();
    assertEquals(testGame.getDebugMode(),false);
    testGame.setDebugMode();
    assertEquals(testGame.getDebugMode(),true);
}


@Test 
public void setRaverOpenTest() {
    GameManager testGame = new GameManager();
    testGame.setRaverOpen(true);
    assertEquals(testGame.getRaverOpen(),true);
}

@Test 
public void parseMapTest() {

    App app = new App();
        PApplet.runSketch(new String[] {"App"}, app);
        app.setup();

    GameManager testGame = new GameManager();
    testGame.initConfig();
    testGame.parseMap(app);
    assertNotNull(testGame.getRaver());
}

  

@Test 
public void setSpriteTest(){
    App app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    app.setup();
    PImage i = app.loadImage("src/main/resources/chaser.png");

    GameManager testGame = new GameManager();
    testGame.initConfig();
    testGame.parseMap(app);

    testGame.setRaverSprite(i);
    assertEquals(testGame.getRaverSprite(), i);

}

@Test 
public void tickAllTest(){
    App app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    app.setup();
    PImage i = app.loadImage("src/main/resources/chaser.png");

    GameManager testGame = new GameManager();
    testGame.initConfig();
    testGame.parseMap(app);
    testGame.tickAll();
    ArrayList<PImage> spriteList = testGame.loadSpriteList(app);
    testGame.setStartedGame(true);
    testGame.updateSprite(spriteList);


    assertEquals(testGame.getSpeed(), 1);
    assertEquals(testGame.getMap(), "src/map.txt");
    assertEquals(testGame.getStartedGame(), true);
    assertEquals(testGame.checkWinLose(), false);
}


@Test 
public void tickAllTestFrightened(){
    App app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    app.setup();
    PImage i = app.loadImage("src/main/resources/chaser.png");

    GameManager testGame = new GameManager();
    testGame.setStartedGame(true);
    testGame.initConfig();
    testGame.parseMap(app);
    testGame.setFrightened();
    testGame.setTimeCount();

    assertEquals(testGame.getFrightenedMode(), true);



    testGame.tickAll();
 
    ArrayList<PImage> spriteList = testGame.loadSpriteList(app);
    testGame.setStartedGame(true);
    testGame.updateSprite(spriteList);

    testGame.resetFrightened();

    assertEquals(testGame.getFrightenedMode(), false);


    assertEquals(testGame.getSpeed(), 1);
    assertEquals(testGame.getMap(), "src/map.txt");
    assertEquals(testGame.getStartedGame(), true);
    assertEquals(testGame.checkWinLose(), false);
}


@Test 
public void tickAllTestGhostMode(){
    App app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    app.setup();
    PImage i = app.loadImage("src/main/resources/chaser.png");

    GameManager testGame = new GameManager();
    testGame.setStartedGame(true);
    testGame.initConfig();
    testGame.parseMap(app);
    testGame.setFrightened();
    testGame.setTimeCount();

    //check ghostmode switch branch
    testGame.setghostTimeCountVal(700);
    assertEquals(testGame.getghostTimeCount(), 700);

    //check frightened mode over switch branch
    testGame.setfrightenedTimeCount(1000);

    //check time incremented
    assertEquals(testGame.getfrightenedTimeCount(), 1000);
    
    testGame.frightenedTimer();

    //check time decremented
    assertEquals(testGame.getfrightenedTimeCount(), 0);

    testGame.tickAll();
    testGame.tickAll();
    testGame.tickAll();
    testGame.tickAll();
    testGame.tickAll();
    testGame.tickAll();
    testGame.tickAll();
    testGame.tickAll();
    testGame.tickAll();
    testGame.tickAll();
    testGame.tickAll();

    //mode should turn off/on when time reached
    assertEquals(testGame.getFrightenedMode(), false);
    assertEquals(testGame.getGhostMode(), true);

    ArrayList<PImage> spriteList = testGame.loadSpriteList(app);
    testGame.setStartedGame(true);
    testGame.updateSprite(spriteList);

    
}

@Test 
public void testEndGame(){
    App app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    app.setup();
    PImage i = app.loadImage("src/main/resources/chaser.png");

    GameManager testGame = new GameManager();
    testGame.initConfig();
    testGame.parseMap(app);
    testGame.tickAll();
    ArrayList<PImage> spriteList = testGame.loadSpriteList(app);
    testGame.setStartedGame(true);
    testGame.updateSprite(spriteList);
    testGame.setFrightened();
    testGame.setGameOver();
    assertEquals(testGame.getGameOver(), true);
    assertEquals(testGame.checkWinLose(), true);

    testGame.endGame(true, app, spriteList,0);

    //test game restarted
    assertEquals(testGame.getStartedGame(), false);
    assertEquals(testGame.checkWinLose(), false);
    assertEquals(testGame.getGameOver(), false);
}

@Test 
public void testEndGame2(){
    App app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    app.setup();
    PImage i = app.loadImage("src/main/resources/chaser.png");

    GameManager testGame = new GameManager();
    testGame.initConfig();
    testGame.parseMap(app);
    testGame.tickAll();
    ArrayList<PImage> spriteList = testGame.loadSpriteList(app);
    testGame.setStartedGame(true);
    testGame.updateSprite(spriteList);
    testGame.setFrightened();
    testGame.killPlayer();
    testGame.gameStatusCheck();
    testGame.killPlayer();
    testGame.gameStatusCheck();
    testGame.killPlayer();
    testGame.gameStatusCheck();
    assertEquals(testGame.getGameOver(), true);
    assertEquals(testGame.checkWinLose(), true);

    assertEquals(testGame.getGameOver(), true);
    assertEquals(testGame.checkWinLose(), true);

    testGame.endGame(true, app, spriteList,0);

    assertEquals(testGame.getStartedGame(), false);
    assertEquals(testGame.checkWinLose(), false);
    assertEquals(testGame.getGameOver(), false);
}

@Test 
public void testEndGameWin(){
    App app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    app.setup();
    PImage i = app.loadImage("src/main/resources/chaser.png");

    GameManager testGame = new GameManager();
    testGame.initConfig();
    testGame.parseMap(app);
    testGame.tickAll();
    ArrayList<PImage> spriteList = testGame.loadSpriteList(app);
    testGame.setStartedGame(true);
    testGame.updateSprite(spriteList);

    testGame.eatAllSnaccs();
    testGame.tickAll();
    assertEquals(testGame.checkWinLose(), true);
    assertEquals(testGame.getWin(), true);
    assertEquals(testGame.getGameOver(), false);


    testGame.endGame(true, app, spriteList,0);

    assertEquals(testGame.getStartedGame(), false);
    assertEquals(testGame.checkWinLose(), false);
    assertEquals(testGame.getGameOver(), false);
}


@Test 
public void testEndGameWinScreen(){
    App app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    app.setup();
    PImage i = app.loadImage("src/main/resources/chaser.png");

    GameManager testGame = new GameManager();
    testGame.initConfig();
    testGame.parseMap(app);
    testGame.tickAll();
    ArrayList<PImage> spriteList = testGame.loadSpriteList(app);
    testGame.setStartedGame(true);
    testGame.updateSprite(spriteList);
    testGame.eatAllSnaccs();
    testGame.tickAll();
    
    assertEquals(testGame.checkWinLose(), true);
    assertEquals(testGame.getWin(), true);
    assertEquals(testGame.getGameOver(), false);

    testGame.endGame(false, app, spriteList,100);

    assertEquals(testGame.getEndGameCount()>0, true);

}

@Test 
public void testEndGameLose(){
    App app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    app.setup();
    PImage i = app.loadImage("src/main/resources/chaser.png");

    GameManager testGame = new GameManager();
    testGame.initConfig();
    testGame.parseMap(app);
    testGame.tickAll();
    ArrayList<PImage> spriteList = testGame.loadSpriteList(app);
    testGame.setStartedGame(true);
    testGame.updateSprite(spriteList);
    testGame.killPlayer();
    testGame.tickAll();
    testGame.gameStatusCheck();
    testGame.killPlayer();
    testGame.tickAll();
    testGame.gameStatusCheck();
    testGame.killPlayer();
    testGame.tickAll();
    testGame.gameStatusCheck();


    
    assertEquals(testGame.checkWinLose(), true);
    assertEquals(testGame.getWin(), false);
    assertEquals(testGame.getGameOver(), true);

    testGame.endGame(true, app, spriteList, 100);
    //100ms delay in game restar - variables wont update

    assertEquals(testGame.getGameOver(), true);

}

@Test 
public void testMoveRaver(){
    App app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    app.setup();
    PImage i = app.loadImage("src/main/resources/chaser.png");

    GameManager testGame = new GameManager();
    testGame.initConfig();
    testGame.parseMap(app);
    int originalX = testGame.getRaverX();
    int originalY = testGame.getRaverY();

    testGame.setStartedGame(true);
    testGame.tickAll();
    ArrayList<PImage> spriteList = testGame.loadSpriteList(app);

    testGame.updateSprite(spriteList);

    //test up
    testGame.setMove(1);
    assertEquals(testGame.getDirMove(), 1); 
    testGame.tickAll();
    testGame.performCurrentMove();
    testGame.tickAll();

    //will not change coordinate due to test map wall
    assertEquals(testGame.getRaverY(), originalY);

    //test reset move
    testGame.resetRaverDirection();
    assertEquals(testGame.getDirMove(), 1); 

    //test down
    testGame.setMove(2);
    assertEquals(testGame.getDirMove(), 2); 
    testGame.performCurrentMove();
    testGame.tickAll();

    //will not change coordinate due to test map wall
    assertEquals(testGame.getRaverY(), originalY);

    testGame.resetRaverDirection();

    //test left
    testGame.setMove(3);
    assertEquals(testGame.getDirMove(), 3); 
    testGame.performCurrentMove();
    testGame.tickAll();

    //will change coordinate - no wall
    assertEquals(testGame.getRaverX(), originalX-1);

    testGame.resetRaverDirection();

    //test right
    testGame.setMove(4);
    assertEquals(testGame.getDirMove(), 4); 
    testGame.performCurrentMove();
    testGame.tickAll();
    //will change coordinate - no wall
    assertEquals(testGame.getRaverX(), originalX);
}


@Test 
public void testMoveRaverUpdateSprite(){
    App app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    app.setup();
    PImage i = app.loadImage("src/main/resources/chaser.png");

    GameManager testGame = new GameManager();
    testGame.initConfig();
    testGame.parseMap(app);
    int originalX = testGame.getRaverX();
    int originalY = testGame.getRaverY();

    testGame.setStartedGame(true);
    testGame.tickAll();
    ArrayList<PImage> spriteList = testGame.loadSpriteList(app);

    testGame.setTimeCount();
    testGame.updateSprite(spriteList);
    testGame.setTimeCount();
    testGame.updateSprite(spriteList);
    testGame.setTimeCount();
    testGame.updateSprite(spriteList);
    testGame.setTimeCount();
    testGame.updateSprite(spriteList);
    testGame.setTimeCount();
    testGame.updateSprite(spriteList);
    testGame.setTimeCount();
    testGame.updateSprite(spriteList);

    //test up
    testGame.setMove(1);
    assertEquals(testGame.getDirMove(), 1); 
    testGame.tickAll();
    testGame.performCurrentMove();
    testGame.tickAll();

    //will not change coordinate due to test map wall
    assertEquals(testGame.getRaverY(), originalY);

    //test reset move
    testGame.resetRaverDirection();
    assertEquals(testGame.getDirMove(), 1); 

    //test down
    testGame.setMove(2);
    assertEquals(testGame.getDirMove(), 2); 
    testGame.performCurrentMove();
    testGame.tickAll();

    //will not change coordinate due to test map wall
    assertEquals(testGame.getRaverY(), originalY);

    testGame.resetRaverDirection();

    //test left
    testGame.setMove(3);
    assertEquals(testGame.getDirMove(), 3); 
    testGame.performCurrentMove();
    testGame.tickAll();

    //will change coordinate - no wall
    assertEquals(testGame.getRaverX(), originalX-1);

    testGame.resetRaverDirection();

    //test right
    testGame.setMove(4);
    assertEquals(testGame.getDirMove(), 4); 
    testGame.performCurrentMove();
    testGame.tickAll();
    //will change coordinate - no wall
    assertEquals(testGame.getRaverX(), originalX);
}



@Test 
public void thiccSnaccCollisionFrightenedModeEnabledTest(){
    App app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    app.setup();
    PImage i = app.loadImage("src/main/resources/chaser.png");

    GameManager testGame = new GameManager();
    testGame.initConfig();
    testGame.parseMap(app);
    testGame.tickAll();
    ArrayList<PImage> spriteList = testGame.loadSpriteList(app);
    testGame.setStartedGame(true);
    testGame.updateSprite(spriteList);

    testGame.eatAllSnaccs();
    testGame.checkFrightened();
    testGame.tickAll();
    assertEquals(testGame.getFrightenedMode(), true);


}



}
/*
 * This Java source file was generated by the Gradle 'init' task.
 */



package ghost;

import processing.core.PImage;
import processing.core.PApplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class GhostTest {
    @Test 
    public void constructorTest() {
        Chaser testSpook = new Chaser(220,225);
        assertNotNull(testSpook);
    }

    @Test 
    public void constructorTest2() {
        Chaser testSpook = new Chaser(220,225,null, null, null);
        assertNotNull(testSpook);
    }

    @Test 
    public void constructorTestA() {
        Ambusher testSpook = new Ambusher(220,225);
        assertNotNull(testSpook);
    }

    @Test 
    public void constructorTest2A() {
        Ambusher testSpook = new Ambusher(220,225,null, null, null);
        assertNotNull(testSpook);
    }

    @Test 
    public void constructorTestI() {
        Ignorant testSpook = new Ignorant(220,225);
        assertNotNull(testSpook);
    }

    @Test 
    public void constructorTest2I() {
        Ignorant testSpook = new Ignorant(220,225,null, null, null);
        assertNotNull(testSpook);
    }

    @Test 
    public void constructorTestW() {
        Whim testSpook = new Whim(220,225);
        assertNotNull(testSpook);
    }

    @Test 
    public void constructorTest2W() {
        Whim testSpook = new Whim(220,225,null, null, null);
        assertNotNull(testSpook);
    }



    @Test 
    public void getXTest() {
        Chaser Spook = new Chaser(220,225);
        assertEquals(Spook.getX(), 220);
    }

    @Test 
    public void getYTest() {
        Chaser Spook = new Chaser(220,225);
        assertEquals(Spook.getY(), 225);
    }


    @Test 
    public void getAimXTest() {
        Chaser Spook = new Chaser(220,225);
        assertEquals(Spook.getAimX(), 0);
    }

    @Test 
    public void getAimYTest() {
        Chaser Spook = new Chaser(220,225);
        assertEquals(Spook.getAimY(), 0);
    }

    @Test 
    public void getWidthTest() {
        Chaser Spook = new Chaser(220,225);
        assertEquals(Spook.getWidth(), 16);
    }

    @Test 
    public void getHeightTest() {
        Chaser Spook = new Chaser(220,225);
        assertEquals(Spook.getHeight(), 16);
    }

    @Test 
    public void getXVelocityTest() {
        Chaser Spook = new Chaser(220,225);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void getYVelocityTest() {
        Chaser Spook = new Chaser(220,225);
        assertEquals(Spook.getYv(), 0);
    }

    @Test 
    public void getLastMoveTest() {
        Chaser Spook = new Chaser(220,225);
        assertEquals(Spook.getLastMove(), 0);
    }

    @Test 
    public void setLastMoveTest() {
        Chaser Spook = new Chaser(220,225);
        Spook.setLastMove(1);
        assertEquals(Spook.getLastMove(), 1);
    }

    @Test 
    public void resetLastMoveTest() {
        Chaser Spook = new Chaser(220,225);
        Spook.setLastMove(1);
        Spook.resetLastMove();
        assertEquals(Spook.getLastMove(), 0);
    }




    @Test 
    public void getDirectionTest() {
        Chaser Spook = new Chaser(220,225);
        assertEquals(Spook.getDirection(), 0);
    }

    @Test 
    public void setDirection() {
        Chaser Spook = new Chaser(220,225);
        Spook.setDirection(4);
        assertEquals(Spook.getDirection(), 4);
    }

    @Test 
    public void resetDirection() {
        Chaser Spook = new Chaser(220,225);
        Spook.setDirection(4);
        Spook.resetDirection();
        assertEquals(Spook.getDirection(), 1);
    }

    @Test 
    public void testMoveUp() {
        Chaser Spook = new Chaser(220,225,null,null,null);
        long speed = 1;
        Spook.moveUp(speed);
        assertEquals(Spook.getDirection(), 4);
        assertEquals(Spook.getYv(), 1);
        assertEquals(Spook.getXv(), 0);

    }

    @Test 
    public void testMoveDown() {
        Chaser Spook = new Chaser(220,225,null,null,null);
        long speed = 1;
        Spook.moveDown(speed);
        assertEquals(Spook.getDirection(), 3);
        assertEquals(Spook.getYv(), -1);
        assertEquals(Spook.getXv(), 0);

    }

    @Test 
    public void testMoveLeft() {
        Chaser Spook = new Chaser(220,225,null,null,null);
        long speed = 1;
        Spook.moveLeft(speed);
        assertEquals(Spook.getDirection(), 2);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 1);

    }

    @Test 
    public void testMoveRight() {
        Chaser Spook = new Chaser(220,225,null,null,null);
        long speed = 1;
        Spook.moveRight(speed);
        assertEquals(Spook.getDirection(), 1);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), -1);

    }

    @Test 
    public void getAlivetest() {
        Chaser Spook = new Chaser(220,225);
        assertEquals(Spook.checkAlive(), true);
    }

    @Test 
    public void getDeadTest() {
        Chaser Spook = new Chaser(220,225);
        Spook.oof();
        assertEquals(Spook.checkAlive(), false);
    }

    @Test 
    public void respawnTest() {
        Chaser Spook = new Chaser(220,225);
        Spook.oof();
        Spook.spookOn();
        Spook.respawn();
        assertEquals(Spook.checkAlive(), true);
        assertEquals(Spook.getSpooked(), false);
    }

    
    @Test 
    public void getFrightenedTest() {
        Chaser Spook = new Chaser(220,225);
        assertEquals(Spook.getSpooked(), false);
    }

    @Test 
    public void getFrightenedOnTest() {
        Chaser Spook = new Chaser(220,225);
        Spook.spookOn();
        assertEquals(Spook.getSpooked(), true);
    }

    @Test 
    public void getFrightenedOffTest() {
        Chaser Spook = new Chaser(220,225);
        Spook.spookOn();
        Spook.spookOff();
        assertEquals(Spook.getSpooked(), false);
    }

    @Test 
    public void getTrippingTest() {
        Chaser Spook = new Chaser(220,225);
        assertEquals(Spook.getShroomMode(), false);
    }

    @Test 
    public void getTrippingTestOn() {
        Chaser Spook = new Chaser(220,225);
        Spook.shroomOn();
        assertEquals(Spook.getShroomMode(), true);
    }

    @Test 
    public void getTrippingTestOff() {
        Chaser Spook = new Chaser(220,225);
        Spook.shroomOn();
        Spook.shroomOff();
        assertEquals(Spook.getShroomMode(), false);
    }


    //below is testing of all ghost movement branches (repetitive)

    @Test 
    public void tickTestChaserScatter() {
        Tile walTile = new Tile(8,8);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(8,8, null, null, null);

        Chaser Spook = new Chaser(440,8, null, tilesTest, testBoi );
        long speed = 1;
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 1);
    }

    @Test 
    public void tickTestChaserScatter2() {
        Tile walTile = new Tile(8,8);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(8,8, null, null, null);

        Chaser Spook = new Chaser(440,8, null, tilesTest, testBoi );
        long speed = 1;
        Spook.setLastMove(1);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 1);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestChaserScatter3() {
        Tile walTile = new Tile(432,8);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(8,8, null, null, null);

        Chaser Spook = new Chaser(440,8, null, tilesTest, testBoi );
        long speed = 1;
        Spook.setLastMove(3);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }

    
    @Test 
    public void tickTestChaserScatter8() {
        Tile walTile = new Tile(440,8);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(8,8, null, null, null);

        Chaser Spook = new Chaser(440,16, null, tilesTest, testBoi );
        long speed = 1;
        Spook.setLastMove(1);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }

    
    @Test 
    public void tickTestChaserScatter4() {
        Tile walTile = new Tile(8,8);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(8,8, null, null, null);

        Chaser Spook = new Chaser(16,552, null, tilesTest, testBoi );
        long speed = 1;
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 1);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestChaserScatter5() {
        Tile walTile = new Tile(8,8);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(8,8, null, null, null);

        Chaser Spook = new Chaser(16,552, null, tilesTest, testBoi );
        long speed = 1;
        Spook.setLastMove(3);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 1);
    }

    @Test 
    public void tickTestChaserScatter6() {
        Tile walTile = new Tile(8,552);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(8,8, null, null, null);

        Chaser Spook = new Chaser(16,552, null, tilesTest, testBoi );
        long speed = 1;
        Spook.setLastMove(3);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestChaserScatter7() {
        Tile walTile = new Tile(8,544);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(8,8, null, null, null);

        Chaser Spook = new Chaser(8,552, null, tilesTest, testBoi );
        long speed = 1;
        Spook.setLastMove(1);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestChaserFrightened() {
        Tile walTile = new Tile(8,8);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(8,8, null, null, null);

        Chaser Spook = new Chaser(440,8, null, tilesTest, testBoi );
        Spook.spookOn();
        long speed = 1;
        Spook.tick(false, true,speed,testBoi);
        assertEquals(Spook.getSpooked(), true);
    }

    @Test 
    public void tickTestChaserChase() {
        Tile walTile = new Tile(8,8);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);
        Raver testBoi = new Raver(440,560, null, null, null);
        Chaser Spook = new Chaser(440,8, null, tilesTest, testBoi );
        long speed = 1;
        Spook.tick(true, false,speed,testBoi);
        assertEquals(Spook.getYv(), -1);
        assertEquals(Spook.getXv(), 0);
    }




    @Test 
    public void tickTestAmbusherScatter() {
        Tile walTile = new Tile(80,80);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);

        Ambusher Spook = new Ambusher(160,160, null, tilesTest, testBoi );
        long speed = 1;
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), -1);
    }

    
    @Test 
    public void tickTestAmbusherScatter2() {
        Tile walTile = new Tile(80,80);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);

        Ambusher Spook = new Ambusher(160,160, null, tilesTest, testBoi );
        long speed = 1;
        Spook.setLastMove(2);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 1);
        assertEquals(Spook.getXv(), 0);
    }

    
    @Test 
    public void tickTestAmbusherScatter3() {
        Tile walTile = new Tile(160,152);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);

        Ambusher Spook = new Ambusher(160,160, null, tilesTest, testBoi );
        long speed = 1;
        Spook.setLastMove(2);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestAmbusherScatter4() {
        Tile walTile = new Tile(80,80);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);

        Ambusher Spook = new Ambusher(440,560, null, tilesTest, testBoi );
        long speed = 1;
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 1);
        assertEquals(Spook.getXv(), 0);
    }

    
    @Test 
    public void tickTestAmbusherScatter5() {
        Tile walTile = new Tile(80,80);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);

        Ambusher Spook = new Ambusher(432,552, null, tilesTest, testBoi );
        long speed = 1;
        Spook.setLastMove(3);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), -1);
    }

    
    @Test 
    public void tickTestAmbusherScatter6() {
        Tile walTile = new Tile(432,544);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);


        Ambusher Spook = new Ambusher(432,552, null, tilesTest, testBoi );
        long speed = 1;
        Spook.setLastMove(2);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestAmbusherScatter7() {
        Tile walTile = new Tile(440,544);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);


        Ambusher Spook = new Ambusher(432,544, null, tilesTest, testBoi );
        long speed = 1;
        Spook.setLastMove(4);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }


    @Test 
    public void tickTestAmbusherChase() {
        Tile walTile = new Tile(80,80);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(0,8, null, null, null);
        Ambusher Spook = new Ambusher(160,160, null, tilesTest, testBoi );
        long speed = 1;
        Spook.tick(true, false,speed,testBoi);
        assertEquals(Spook.getYv(), 1);
        assertEquals(Spook.getXv(), 0);
    }



    @Test 
    public void tickTestWhimScatter() {
        Tile walTile = new Tile(80,80);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);

        Whim Spook = new Whim(0,0, null, tilesTest, testBoi );
        long speed = 1;
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), -1);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestWhimScatter2() {
        Tile walTile = new Tile(80,80);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);

        Whim Spook = new Whim(32,32, null, tilesTest, testBoi );
        
        long speed = 1;
        Spook.setLastMove(4);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), -1);
    }

    @Test 
    public void tickTestWhimScatter3() {
        Tile walTile = new Tile(40,32);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);

        Whim Spook = new Whim(32,32, null, tilesTest, testBoi );
        
        long speed = 1;
        Spook.setLastMove(4);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }


    @Test 
    public void tickTestWhimScatter4() {
        Tile walTile = new Tile(80,80);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(0,0, null, null, null);

        Whim Spook = new Whim(0,504, null, tilesTest, testBoi );
        long speed = 1;
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestWhimScatter5() {
        Tile walTile = new Tile(80,80);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(0,0, null, null, null);

        Whim Spook = new Whim(0,504, null, tilesTest, testBoi );
        
        long speed = 1;
        Spook.setLastMove(4);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestWhimScatter6() {
        Tile walTile = new Tile(0,512);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);


        Raver testBoi = new Raver(0,0, null, null, null);

        Whim Spook = new Whim(0,504, null, tilesTest, testBoi );
        
        long speed = 1;
        Spook.setLastMove(2);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestWhimScatter7() {
        Tile walTile = new Tile(8,512);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);


        Raver testBoi = new Raver(0,0, null, null, null);

        Whim Spook = new Whim(0,512, null, tilesTest, testBoi );
        
        long speed = 1;
        Spook.setLastMove(4);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestWhimScatter8() {
        Tile walTile = new Tile(8,512);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);


        Raver testBoi = new Raver(0,0, null, null, null);

        Whim Spook = new Whim(0,512, null, tilesTest, testBoi );
        
        long speed = 1;
        Spook.setLastMove(2);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestWhimScatter9() {
        Tile walTile = new Tile(400,0);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);


        Raver testBoi = new Raver(0,0, null, null, null);

        Whim Spook = new Whim(400,8, null, tilesTest, testBoi );
        
        long speed = 1;
        Spook.setLastMove(2);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }



    @Test 
    public void tickTestIgnorantScatter() {
        Tile walTile = new Tile(80,80);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);

        Ignorant Spook = new Ignorant(440,560, null, tilesTest, testBoi );
        long speed = 1;
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 1);
    }

    @Test 
    public void tickTestIgnorantScatter2() {
        Tile walTile = new Tile(80,80);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);

        Ignorant Spook = new Ignorant(432,554, null, tilesTest, testBoi );
        Spook.setLastMove(1);
        long speed = 1;
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), -1);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestIgnorantScatter3() {
        Tile walTile = new Tile(440,560);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);

        Ignorant Spook = new Ignorant(440,552, null, tilesTest, testBoi );
        long speed = 1;
        Spook.setLastMove(1);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestIgnorantScatter8() {
        Tile walTile = new Tile(432,552);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);

        Ignorant Spook = new Ignorant(440,552, null, tilesTest, testBoi );
        long speed = 1;
        Spook.setLastMove(4);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestIgnorantChase2() {
        Tile walTile = new Tile(0,0);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,552, null, null, null);

        Ignorant Spook = new Ignorant(440,8, null, tilesTest, testBoi );
        long speed = 1;
        for(int i = 0; i < 2000; i++){
        Spook.tick(true, false,speed,testBoi);
        }
        assertNotEquals(Spook.getX(), 440);
        assertNotEquals(Spook.getY(), 8);

    }

    @Test 
    public void tickTestChaserChase2() {
        Tile walTile = new Tile(0,0);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,552, null, null, null);

        Chaser Spook = new Chaser(440,8, null, tilesTest, testBoi );
        long speed = 1;
        for(int i = 0; i < 2000; i++){
        Spook.tick(true, false,speed,testBoi);
        }
        assertEquals(Spook.getXv(), 0);
        assertEquals(Spook.getYv(), -1);

    }


    @Test 
    public void tickTestIgnorantScatter4() {
        Tile walTile = new Tile(80,80);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);

        Ignorant Spook = new Ignorant(0,0, null, tilesTest, testBoi );
        long speed = 1;
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), -1);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestIgnorantScatter5() {
        Tile walTile = new Tile(80,80);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);

        Ignorant Spook = new Ignorant(0,0, null, tilesTest, testBoi );
        Spook.setLastMove(4);
        long speed = 1;
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 1);
    }

    @Test 
    public void tickTestIgnorantScatter6() {
        Tile walTile = new Tile(8,16);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);

        Ignorant Spook = new Ignorant(16,16, null, tilesTest, testBoi );
        long speed = 1;
        Spook.setLastMove(1);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestIgnorantScatter7() {
        Tile walTile = new Tile(80,80);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(440,8, null, null, null);

        Ignorant Spook = new Ignorant(88,80, null, tilesTest, testBoi );
        long speed = 1;
        Spook.setLastMove(4);
        Spook.tick(false, false,speed,testBoi);
        assertEquals(Spook.getYv(), 0);
        assertEquals(Spook.getXv(), 0);
    }

    @Test 
    public void tickTestWhimChaser() {
        Tile walTile = new Tile(400,0);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);


        Raver testBoi = new Raver(0,0, null, null, null);
        Chaser SpookChase = new Chaser(440,8, null, tilesTest, testBoi );
        Whim Spook = new Whim(400,8, null, tilesTest, testBoi );
        Spook.setChaser(SpookChase);
        
        assertEquals(Spook.getDir(testBoi), 1);

    }

    @Test 
    public void tickTestWhimChaser1() {
        Tile walTile = new Tile(400,0);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);


        Raver testBoi = new Raver(0,0, null, null, null);
        testBoi.setDirection(1);
        Chaser SpookChase = new Chaser(440,8, null, tilesTest, testBoi );
        Whim Spook = new Whim(400,8, null, tilesTest, testBoi );
        Spook.setChaser(SpookChase);
        
        assertEquals(Spook.getDir(testBoi), 1);

    }

    @Test 
    public void tickTestWhimChaser2() {
        Tile walTile = new Tile(400,0);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);


        Raver testBoi = new Raver(0,0, null, null, null);
        testBoi.setDirection(2);
        Chaser SpookChase = new Chaser(440,8, null, tilesTest, testBoi );
        Whim Spook = new Whim(400,8, null, tilesTest, testBoi );
        Spook.setChaser(SpookChase);
        
        assertEquals(Spook.getDir(testBoi), 1);

    }

    @Test 
    public void tickTestWhimChaser3() {
        Tile walTile = new Tile(400,0);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);


        Raver testBoi = new Raver(0,0, null, null, null);
        testBoi.setDirection(3);
        Chaser SpookChase = new Chaser(440,8, null, tilesTest, testBoi );
        Whim Spook = new Whim(400,8, null, tilesTest, testBoi );
        Spook.setChaser(SpookChase);
        
        assertEquals(Spook.getDir(testBoi), 3);

    }

    @Test 
    public void tickTestWhimChaser4() {
        Tile walTile = new Tile(400,0);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);


        Raver testBoi = new Raver(0,0, null, null, null);
        testBoi.setDirection(4);
        Chaser SpookChase = new Chaser(440,8, null, tilesTest, testBoi );
        Whim Spook = new Whim(400,8, null, tilesTest, testBoi );
        Spook.setChaser(SpookChase);
        
        assertEquals(Spook.getDir(testBoi), 1);

    }


    
    @Test 
    public void tickTestAmbusher() {
        Tile walTile = new Tile(400,0);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);


        Raver testBoi = new Raver(0,0, null, null, null);
        testBoi.setDirection(1);
        Ambusher Spook = new Ambusher(400,8, null, tilesTest, testBoi );
        
        assertEquals(Spook.getDir(testBoi), 1);

    }

    @Test 
    public void tickTestAmbusher2() {
        Tile walTile = new Tile(400,0);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(0,0, null, null, null);
        testBoi.setDirection(2);
        Ambusher Spook = new Ambusher(400,8, null, tilesTest, testBoi );
        
        assertEquals(Spook.getDir(testBoi), 1);

    }


    @Test 
    public void tickTestAmbusher3() {
        Tile walTile = new Tile(400,0);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(0,0, null, null, null);
        testBoi.setDirection(3);
        Ambusher Spook = new Ambusher(400,8, null, tilesTest, testBoi );
        
        assertEquals(Spook.getDir(testBoi), 3);

    }


    @Test 
    public void tickTestAmbusher4() {
        Tile walTile = new Tile(400,0);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(0,0, null, null, null);
        testBoi.setDirection(4);
        Ambusher Spook = new Ambusher(400,8, null, tilesTest, testBoi );
        
        assertEquals(Spook.getDir(testBoi), 1);

    }

   
    @Test 
    public void tickTestIgnorant() {
        Tile walTile = new Tile(400,0);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);


        Raver testBoi = new Raver(0,0, null, null, null);
        Ignorant Spook = new Ignorant(400,8, null, tilesTest, testBoi );
        
        assertEquals(Spook.getDir(testBoi), 1);

    }

    @Test 
    public void tickTestIgnorant2() {
        Tile walTile = new Tile(400,0);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(0,0, null, null, null);
        Ignorant Spook = new Ignorant(400,8, null, tilesTest, testBoi );
        
        assertEquals(Spook.getDir(testBoi), 1);

    }


    @Test 
    public void tickTestIgnorant3() {
        Tile walTile = new Tile(400,0);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(0,0, null, null, null);
        Ignorant Spook = new Ignorant(400,8, null, tilesTest, testBoi );
        
        assertEquals(Spook.getDir(testBoi), 1);

    }


    @Test 
    public void tickTestIgnorant4() {
        Tile walTile = new Tile(400,0);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(0,0, null, null, null);
        Ignorant Spook = new Ignorant(400,8, null, tilesTest, testBoi );
        
        assertEquals(Spook.getDir(testBoi), 1);

    }

    @Test 
    public void tickTestIgnorant5() {
        Tile walTile = new Tile(400,0);
        ArrayList<Tile> tilesTest = new ArrayList<Tile>();
        tilesTest.add(walTile);

        Raver testBoi = new Raver(0,0, null, null, null);
        Ignorant Spook = new Ignorant(0,16, null, tilesTest, testBoi );
        
        assertEquals(Spook.getDir(testBoi), 3);

    }

    
@Test 
public void tickAllTestGhostMovement(){
    App app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    app.setup();
    PImage i = app.loadImage("src/main/resources/chaser.png");

    GameManager testGame = new GameManager();
    testGame.setStartedGame(true);
    testGame.initConfig();
    testGame.parseMap(app);
    ArrayList<PImage> spriteList = testGame.loadSpriteList(app);
    testGame.setStartedGame(true);


    int testTick = 2000;
    for(int j = 0; j < testTick; j++){
        testGame.gameStatusCheck();
        testGame.updateSprite(spriteList);
        testGame.tickAll();
        testGame.performCurrentMove();
    }

    
}

 
@Test 
public void tickAllTestGhostMovementFrightened(){
    App app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    app.setup();
    PImage i = app.loadImage("src/main/resources/chaser.png");

    GameManager testGame = new GameManager();
    testGame.setStartedGame(true);
    testGame.initConfig();
    testGame.parseMap(app);
    ArrayList<PImage> spriteList = testGame.loadSpriteList(app);
    testGame.setStartedGame(true);
    testGame.setFrightened();


    int testTick = 2000;
    for(int j = 0; j < testTick; j++){
        testGame.gameStatusCheck();
        testGame.updateSprite(spriteList);
        testGame.tickAll();
        testGame.performCurrentMove();
    }

    
}

@Test 
public void tickAllTestGhostMovementChase(){
    App app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    app.setup();
    PImage i = app.loadImage("src/main/resources/chaser.png");

    GameManager testGame = new GameManager();
    testGame.setStartedGame(true);
    testGame.initConfig();
    testGame.parseMap(app);
    ArrayList<PImage> spriteList = testGame.loadSpriteList(app);
    testGame.setStartedGame(true);
    testGame.setChaseModeOn();


    int testTick = 2000;
    for(int j = 0; j < testTick; j++){
        testGame.gameStatusCheck();
        testGame.updateSprite(spriteList);
        testGame.tickAll();
        testGame.performCurrentMove();
    }

    
}

@Test 
public void tickAllTestGhostMovementTripping(){
    App app = new App();
    PApplet.runSketch(new String[] {"App"}, app);
    app.setup();
    PImage i = app.loadImage("src/main/resources/chaser.png");

    GameManager testGame = new GameManager();
    testGame.setStartedGame(true);
    testGame.initConfig();
    testGame.parseMap(app);
    ArrayList<PImage> spriteList = testGame.loadSpriteList(app);
    testGame.setStartedGame(true);
    testGame.setShroomModeOn();


    int testTick = 2000;
    for(int j = 0; j < testTick; j++){
        testGame.gameStatusCheck();
        testGame.updateSprite(spriteList);
        testGame.tickAll();
        testGame.performCurrentMove();
    }

    
}

}

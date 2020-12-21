import processing.core.PImage;

@startuml
scale 1


class App{
    + WIDTH : int
    + Height : int
    - gameLogic : GameManager
    - spriteList : ArrayList<PImage>

    + draw()
    + keyPressed()
    + main(java.lang.String[] args) 
    + settings()
    + setup()
}

class GameManager{
    + WIDTH : int
    + Height : int

    - dir : int
    - currTileX : int
    - currTileY: int
    - snaccCount : int
    - timeCount : int
    - ghostTimeCount : int
    - frightenedTimeCount : int
    - endGameCount : int
    - ShroomTimeCount : int
    - ShroomTimeCountLimit : int

    - speed : long 
    - OGspeed : long 
    - lives : long 
    - frightenedLength : long 

    - map : String 

    -  startedGame : boolean
    -  raverOpen : boolean
    -  ghostMode : boolean
    -  debugMode : boolean
    -  shroomMode : boolean
    -  frightenedMode : boolean
    -  gameOver : boolean
    -  gameWin : boolean
    -  moveUp : boolean
    -  moveDown : boolean
    -  moveLeft : boolean
    -  moveRight : boolean

    - ghosts : ArrayList<Ghost> 
    - snaccs : ArrayList<Snacc> 
    - tiles : ArrayList<Tile> 
    - livesLeft : ArrayList<Life> 
    - modeLengthList : ArrayList<Integer>

    - raver : Raver

    + addGhost(Ghost g)
    + addRaver(Raver r)
    + addSnacc(Snacc s)
    + addTile(Tile t)
    + checkFrightened()
    + checkWinLose() : boolean
    + drawAll(processing.core.PApplet p)
    + drawLives(processing.core.PApplet p)
    + eatAllSnaccs()
    + endGame(boolean lose, PApplet p, java.util.ArrayList<PImage> spriteList, int msWaitBeforeRestart)
    + frightenedTimer()
    + gameStatusCheck()
    + getCurrTileX() : int
    + 	getCurrTileY() : int
    + 	getDirMove() : int
    + 	getEndGameCount() : int
    + getFrightenedMode() : boolean
    +  getfrightenedTimeCount() : int
    + 	getGameOver() : boolean
    + getGhostMode() : boolean
    +  getGhosts() : ArrayList<Ghost>
    + 	getghostTimeCount() : int
    + 	getMap() : String
    + 	getRaver() :Raver
    + getRaverOpen() : boolean
    + getRaverSprite() : PImage
    +  	getRaverX() : int 
    + 	getRaverY() : int 
    + getShroomTimeCount() : int
    + getSnaccs() : ArrayList<Snacc>
    + getSpeed() : long
    + getStartedGame() : boolean
    + getTiles() : ArrayList<Tile>
    + getTimeCount() : int 
    + getWin(() : boolean
    + incCurrTileX(int inc) 
    + incCurrTileY(int inc)
    + 	initConfig()
    + 	killPlayer()
    + 	loadLives(PImage image)
    +  loadSpriteList(PApplet p) : ArrayList<PImage>
    + lostLife()
    + moveRaver(int dir, long speed) : boolean
    + parseMap(processing.core.PApplet p)
    + performCurrentMove()
    + 	resetCurrTileX()
    + resetFrightened()
    + 	resetRaverDirection()
    + resetSprite(java.util.ArrayList<PImage> spriteList)
    + 	setDebugMode()
    + 	setFrightened()
    + setfrightenedTimeCount(int i)
    + 	setGameOver() 
    + setghostTimeCount()
    + setghostTimeCountVal(int i)
    + setMove(int dir)
    + setRaverOpen(boolean t)
    + setRaverSprite(PImage sprite)
    + 	setShroomTimeCount(int i)
    + 	setStartedGame(boolean val)
    + 	setTimeCount()
    + tickAll()
    + 	tickGhosts()
    + 	tickRaver()
    + 	tickSnaccs()
    + 	tripTimer()
    + updateSprite(ArrayList<PImage> spriteList)



}

abstract class GameObject {
    # alive : boolean
# 	direction : int
#   goingDown :boolean
#   goingLeft : boolean
# 	goingRight : boolean
# 	goingUp : boolean
# 	height : int
# 	lastMove : int
# 	sprite : processing.core.PImage
# 	startPositionX : int
# 	startPositionY : int
# 	tiles : java.util.List<Tile>
# 	time : int
# 	width : int
# 	x : int
# 	xVelocity : long
# 	y : int
# 	yVelocity : long

+ checkWallCollision(int futureX, int futureY) : boolean
+ 	abstract //draw(processing.core.PApplet app)  //
+ 	getDirection() : int
+ 	getHeight() : int
+ 	getLastMove() : int
+ 	getSprite() : PImage
+ 	getWidth() : int
+ 	getX() : int
+   getXv() : long
+ 	getY() : int
+   getYv() : long
+ moveDown(long speed) : boolean
+ moveUp(long speed) : boolean
+ moveLeft(long speed) : boolean
+ moveRight(long speed) : boolean
+ oof()
+ revive()
+ 	resetDirection() 
+ setDirection(int i) 
+ 	setLastMove(int i) 
+ setSprite(PImage sprite)
+ wallCollision()



}

abstract class EdibleObject {
    # 	snaccAte : boolean
    # 	used : boolean
    
    + draw(PApplet app)
    + 	getIsEdible() : boolean
    + 	snaccEaten() : boolean
    + 	snaccRespawn() 
}

class Raver {
    + checkGhostCollision()
    + checkSnaccCollision()
    + draw(processing.core.PApplet app)
    + snaccTime(Snacc snacc)
    + tick()
    
}

class Life {
    + draw(processing.core.PApplet app)
    
}

class Tile {
    + draw(processing.core.PApplet app)
    
}

abstract class Ghost {
    # 	aimX : int
    # 	aimY : int
    # 	chaser  : Ghost 
    # 	corner : int
    # 	frightened : boolean
    # 	raver : Raver
    # 	snaccs : List<Snacc>
    # 	time : int
    # 	tripping : boolean
    # 	xDistance : int
    # 	yDistance : int
    
    + 	chaseMode(boolean ghostMode, long speed, Raver raver)
    + 	checkSnaccCollision()
    + 	draw(processing.core.PApplet app)
    + drawTargetLine(boolean ghostMode, Raver raver, processing.core.PApplet p)
    + frightenedMode(long speed)
    + 	getAimX() : int
    + 	getAimy() : int
    + getAxis(Raver raver): int
    + 	getCorner() : int
    + abstract 	// getDir(Raver raver) : Integer //
    + 	getShroomMode() : boolean
    + 	getSpooked() : boolean
    + 	ghostBusted()
    + makeMove(int axis, int corner, long speed)
    + 	resetLastMove()
    + 	respawn() 
    + scatterMode(boolean ghostMode, long speed, Raver raver)
    + 	setChaser(Ghost c)
    + 	shroomModeActive(long speed)
    + shroomOff()
    + shroomOn()
    + 	snaccTime(Snacc snacc)
    + 	spookOff()
    + 	spookOn()
    + 	tick(boolean ghostMode, boolean frightened, boolean shroomMode, long speed, Raver raver)


    
}

class Whim {
    + getDir(Raver raver) : int
    
}

class Chaser {
    + getDir(Raver raver) : int
    
}

class Ignorant {
    -  ignorant: boolean
    + getDir(Raver raver) : int
    
    
}

class Ambusher {
    + getDir(Raver raver) : int
    
}

class Snacc {
    # 	shroom : boolean
    # 	thicc : boolean

    + getThicc() : boolean
     + checkThiccSnaccAte() : boolean
    +  checkShroomSnaccAte() : boolean
    
}

class ThiccSnacc {
    
}

class ShroomSnacc {
    
}

GameObject <|-- EdibleObject
GameObject <|-- Ghost
GameObject <|-- Raver
GameObject <|-- Life
GameObject <|-- Tile
Ghost <|-- Whim
Ghost <|-- Ignorant
Ghost <|-- Ambusher
Ghost <|-- Chaser
EdibleObject <|-- Snacc
Snacc <|-- ThiccSnacc
Snacc <|-- ShroomSnacc
@enduml
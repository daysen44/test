package com.example.cs2340a.dungenCrawler.model;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;


import java.util.ArrayList;

/**
 * This is a (^pseudo static^) class that holds all the information about the current set up of
 * the game.
 * ^NOTE^ The class itself is not static because this is a top-level class.
 * As it needs to be accessible by many other files, it can't be nested into another class and
 * must, itself, be a top-level class.
 * All of the fields/attributes and methods in GameConfigTest are static. This is so they
 * belong to the class rather than an instance of the class (which we don't have)
 * !!!!!!DO NOT - create an instance of this class. That is not its purpose!!!!!!!!!
 *
 */
public class GameConfig {

    //Attributes -----------------------------------------------------------
    private static Player player;
    private static int avatar;
    private static double difficultyNum;
    private static DifficultyEnum difficulty;

    private static int healthPoints;
    private static Room currRoom;
    private static int resID;
    private static Background bg;
    private static Resources res;

    private static EnemyFactory factory;
    private static Enemy enemy1;
    private static Enemy enemy2;
    // List of current Enemy Observers
    private static ArrayList<Enemy> observerList = new ArrayList<>();


    //Methods -----------------------------------------------------------

    /**
     * Method used when player goes through a doorway and switches rooms.
     * It updates the currRom attribute to the room the player is entering.
     * It calls switchEnemies() to update the enemies that spawn in the new room.
     * @param roomID the room the player is coming from. the previous room
     *               this may have to change if/when the room layout is not linear.
     */
    public static void switchRoom(int roomID) {
        System.out.println("in switchRooms");
        if (roomID == 1) {
            switchEnemies(1);
            currRoom = new RoomTwo("room2", currRoom.getInitialPlayerX(),
                    currRoom.getInitialPlayerY(),
                    currRoom.getDoorwayTopY(), currRoom.getDoorwayBottomY(),
                    currRoom.getDoorwayLeftX(), currRoom.getDoorwayRightX(),
                    currRoom.getBackground(), 2);
            player.setX(30);
            player.setY(400);

        } else if (roomID == 2) {
            switchEnemies(2);
            currRoom = new RoomThree("room3", currRoom.getInitialPlayerX(),
                    currRoom.getInitialPlayerY(),
                    currRoom.getDoorwayTopY(), currRoom.getDoorwayBottomY(),
                    currRoom.getDoorwayLeftX(), currRoom.getDoorwayRightX(),
                    currRoom.getBackground(), 3);
            player.setX(800);
            player.setY(800);
        }
    }

    /**
     * Method used to update the enemies on screen to match the room.
     * @param roomID the room the player is coming from. the previous room
     *               this may have to change if/when the room layout is not linear.
     */
    public static void switchEnemies(int roomID) {
        System.out.println("in switchEnemies");
        removeObserver(enemy1);
        removeObserver(enemy2);
        System.out.println("after clear - observerList size: " + observerList.size());
        switch (roomID) {
        case 1:
            System.out.println("in switch case 1");
            //sets room 2 enemies, replacing room1 enemies
            enemy1 = factory.spawnZombie(res);
            System.out.println("after first enemy change");
            enemy2 = factory.spawnVampire(res);
            break;
        case 2:
            System.out.println("in switch case 2");
            //sets room 3 enemies, replacing room2 enemies
            enemy1 = factory.spawnGhost(res);
            enemy2 = factory.spawnVampire(res);
            break;
        default:
            //sets room 1 enemies for default
            enemy1 = factory.spawnBat(res);
            enemy2 = factory.spawnGhost(res);
        }
        addObserver(enemy1);
        addObserver(enemy2);
        System.out.println("after adds - observerList size: " + observerList.size());
        System.out.println("after switch statement in switchEnemies()");
    }
    public static void drawEnemies(Canvas canvas, Resources resources) {
        System.out.println("Drawing enemies!");
        enemy1.draw(canvas, resources);
        enemy2.draw(canvas, resources);
    }
    public static void drawHP(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(50);
        canvas.drawText("HP: " + healthPoints, 1700, 50, paint);
    }


    //Observable methods
    public static void addObserver(Enemy enemy) {
        observerList.add(enemy);
    }
    public static void removeObserver(Enemy enemy) {
        observerList.remove(enemy);
    }
    public static void notifyObservers() {
        System.out.println("notifying observers --- easy");
        System.out.println("Enemy List Size: " + observerList.size());

        for (Enemy observer : observerList) {
            System.out.println("call update");
            observer.update(getPlayer());
            observer.checkCollision(getPlayer());
        }
    }


    //getters and setters -----------------------------------------------------------
    //      Difficulty
    public static void setDifficulty(DifficultyEnum difficultyE) {
        difficulty = difficultyE;
        switch(difficultyE) {
        case EASY:
            difficultyNum = 1.0;
            factory = new EasyEnemyFactory();
            healthPoints = 500;
            break;
        case MEDIUM:
            difficultyNum = 0.75;
            factory = new MediumEnemyFactory();
            healthPoints = 300;
            break;
        case HARD:
            difficultyNum = 0.5;
            factory = new HardEnemyFactory();
            healthPoints = 100;
            break;
        default:
            difficultyNum = 1.0;
            factory = new EasyEnemyFactory();
            healthPoints = 1000;
            break;
        }
    }

    //      Background
    public static void setBackground(Background pbg) {
        bg = pbg;
    }

    //      singleton Player
    public static void createPlayer(String playerName, int screenX, int screenY, Resources res) {
        player = new Player(playerName, difficultyNum, screenX, screenY,
                res, avatar);
    }
    public static Player getPlayer() {
        return player;
    }

    //      Avatar
    public static void setAvatar(int avatarID) {
        avatar = avatarID;
    }

    //      Health Points
    public static int getHealthPoints() {
        return healthPoints;
    }
    public static void setHealthPoints(int hp) {
        healthPoints = hp;
    }

    //      CurrRoom
    public static void setCurrRoom(Room room) {
        currRoom = room;
    }
    public static Room getCurrRoom() {
        return currRoom;
    }

    //    EnemyFactory  -- factory set when difficulty is set
    /* not needed for functionality
    public static void setFactory(EnemyFactory factoryType) {
        factory = factoryType;
    } */

    //      enemy1 and enemy2
    public static void setEnemies(Enemy e1, Enemy e2) {
        enemy1 = e1;
        enemy2 = e2;
    }
    public static Enemy getEnemy1() {
        return enemy1;
    }
    public static Enemy getEnemy2() {
        return enemy2;
    }

    //      res
    public static void setRes(Resources resource) {
        res = resource;
    }


    //      numEnemies



}

package com.example.cs2340a.dungenCrawler.model;

import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.MotionEvent;

public interface MovementStrategy extends Parcelable, KeyEvent.Callback {
    /*
    This interface should be used as a blueprint for all Moveable objects in the game.
     */
    //boolean isMoving = false;
    int X = 0;
    int Y = 0;
    int WIDTH = 0;
    int HEIGHT = 0;

    boolean onKey(KeyEvent event);
    boolean onTouchLogic(MotionEvent event, Player player, boolean onTouch);
    boolean isMovingUp();
    boolean isMovingDown();
    boolean isMovingLeft();
    boolean isMovingRight();
    void setUp(boolean up);
}

package com.example.cs2340a.dungenCrawler.model;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.cs2340a.R;

public class CollisionMap implements Parcelable, IDrawable, Collidable {
    /*
    This class is made to create the proper collision map based on the room the player is currently
    in. In the GameView class, there will need to be a method called in the update() function to
    check and see if Player is intersecting the CollisionMap and prevent Player from moving past
    the collided area (keep the x and y values in place). >>should be done using Observer pattern**
    */

    private Rect topBorder;
    private Rect bottomBorder;
    private Rect leftBorder;
    private Rect rightBorder;
    private int topBorderCollisionMark = 50;
    private int bottomBorderCollisionMark = 800;
    private int leftBorderCollisionMark = 50;
    private int rightBorderCollisionMark = 2050;
    private Rect doorway1;
    private Rect doorway2;
    private Rect doorway3;
    private Rect doorway4;
    private int roomID;

    public CollisionMap(int roomID) {
        this.roomID = roomID;

        topBorder = new Rect(0, 0, 2200, 50);
        bottomBorder = new Rect(0, 975, 2200, 1080);
        leftBorder = new Rect(0, 0, 50, 1080);
        rightBorder = new Rect(2175, 0, 2300, 1080);

        if (roomID == 1) {
            doorway1 = new Rect(2050, 350, 2300, 650);
        } else if (roomID == 2) {
            doorway1 = new Rect(750, 0, 1000, 100);
        } else if (roomID == 3) {
            doorway1 = new Rect(2050, 550, 2300, 850);
        }
    }

    protected CollisionMap(Parcel in) {
        topBorder = in.readParcelable(Rect.class.getClassLoader());
        bottomBorder = in.readParcelable(Rect.class.getClassLoader());
        leftBorder = in.readParcelable(Rect.class.getClassLoader());
        rightBorder = in.readParcelable(Rect.class.getClassLoader());
        doorway1 = in.readParcelable(Rect.class.getClassLoader());
        roomID = in.readInt();
    }

    public static final Creator<CollisionMap> CREATOR = new Creator<CollisionMap>() {
        @Override
        public CollisionMap createFromParcel(Parcel in) {
            return new CollisionMap(in);
        }

        @Override
        public CollisionMap[] newArray(int size) {
            return new CollisionMap[size];
        }
    };

    public int getRoomID() {
        return roomID; }
    public Rect getTopBorder() {
        return topBorder; } // Call in draw() method in GameView
    public Rect getBottomBorder() {
        return bottomBorder; } // Call in draw() method in GameView
    public Rect getLeftBorder() {
        return leftBorder; } // Call in draw() method in GameView
    public Rect getRightBorder() {
        return rightBorder; } // Call in draw() method in GameView
    public int getTopBorderCollisionMark() {
        return topBorderCollisionMark;
    }
    public int getBottomBorderCollisionMark() {
        return bottomBorderCollisionMark;
    }
    public int getLeftBorderCollisionMark() {
        return leftBorderCollisionMark;
    }
    public int getRightBorderCollisionMark() {
        return rightBorderCollisionMark;
    }
    public Rect getDoorway1() {
        return doorway1; }

    public static final int TILE_WIDTH_PIXELS = 83;
    public static final int TILE_HEIGHT_PIXELS = 84;
    public static final int NUM_ROW_TILES = 13;
    public static final int NUM_COL_TILES = 29;

    private int[][] collisionMap;

    @Override
    public void draw(Canvas canvas, Resources resources) {
        Paint paint = new Paint();
        Paint whitePaint = new Paint(R.color.white);
        canvas.drawRect(topBorder, paint);
        canvas.drawRect(bottomBorder, paint);
        canvas.drawRect(leftBorder, paint);
        canvas.drawRect(rightBorder, paint);
        canvas.drawRect(doorway1, whitePaint);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(topBorder, i);
        parcel.writeParcelable(bottomBorder, i);
        parcel.writeParcelable(leftBorder, i);
        parcel.writeParcelable(rightBorder, i);
        parcel.writeParcelable(doorway1, i);
        parcel.writeInt(roomID);
    }

    //constructor
    // >> edit to take in room id and create collision map accordingly*******
    /*
    public CollisionMap() {
        initializeCollisionMap();
    }

    private void initializeCollisionMap() {

    }

    public int[][] getCollisionMap() {
        return collisionMap;
    }

    static final int WALKABLE = 0;
    static final int BLOCKED = 1;

    @Override
    public void draw(Canvas canvas, Resources resources) {

    }
    /*
    private void initializeCollisionMap() {
        collisionMap = new int[][] {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1}

        };
    }
     */
}

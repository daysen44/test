package com.example.cs2340a.dungenCrawler.model;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.util.Log;
import android.view.KeyEvent;


import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

public class PlayerMovement extends Activity implements MovementStrategy, View.OnKeyListener {
    /*
    This class is meant to represent the Player's movement and control it. Unfortunately,
    Player movement is currently only controlled in the GameRoom1ViewModel. This is the only way
    I could get this to work. So right now, this class does nothing.
     */
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;

    private Room currRoom;
    private int x;
    private int y;
    private int prevX;
    private int prevY;
    private int width;
    private int height;
    private Bitmap avatar;
    public PlayerMovement(int screenX, int screenY, Resources res, int resID) {

        avatar = BitmapFactory.decodeResource(res, resID);

        width = avatar.getWidth();
        height = avatar.getHeight();

        width /= 4;
        height /= 4;

        avatar = Bitmap.createScaledBitmap(avatar, width, height, false);
    }

    protected PlayerMovement(Parcel in) {
        isMovingUp = in.readByte() != 0;
        isMovingDown = in.readByte() != 0;
        isMovingLeft = in.readByte() != 0;
        isMovingRight = in.readByte() != 0;
        x = in.readInt();
        y = in.readInt();
        width = in.readInt();
        height = in.readInt();
        avatar = in.readParcelable(Bitmap.class.getClassLoader());
        currRoom = in.readParcelable(Room.class.getClassLoader());
    }

    public static final Creator<PlayerMovement> CREATOR = new Creator<PlayerMovement>() {
        @Override
        public PlayerMovement createFromParcel(Parcel in) {
            return new PlayerMovement(in);
        }

        @Override
        public PlayerMovement[] newArray(int size) {
            return new PlayerMovement[size];
        }
    };
    public boolean isMovingUp() {
        return isMovingUp;
    }
    public boolean isMovingDown() {
        return isMovingDown;
    }
    public boolean isMovingLeft() {
        return isMovingLeft;
    }
    public boolean isMovingRight() {
        return isMovingRight;
    }
    public void setUp(boolean up) {
        this.isMovingUp = up;
    }

    @Override
    public boolean onKey(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            System.out.println("ActionDown...");
            switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_W:
                System.out.println("W down");
                isMovingUp = true;
                System.out.println("isMovingUp: " + isMovingUp);
                break;
            case KeyEvent.KEYCODE_A:
                isMovingLeft = true;
                break;
            case KeyEvent.KEYCODE_S:
                isMovingDown = true;
                break;
            case KeyEvent.KEYCODE_D:
                isMovingRight = true;
                break;
            default:
                System.out.println("W down");
                isMovingUp = true;
                System.out.println("isMovingUp: " + isMovingUp);
                break;
            }
        }

        return false;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeByte((byte) (isMovingUp ? 1 : 0));
        parcel.writeByte((byte) (isMovingDown ? 1 : 0));
        parcel.writeByte((byte) (isMovingLeft ? 1 : 0));
        parcel.writeByte((byte) (isMovingRight ? 1 : 0));
        parcel.writeInt(x);
        parcel.writeInt(y);
        parcel.writeInt(width);
        parcel.writeInt(height);
        parcel.writeParcelable(avatar, i);
        parcel.writeParcelable(currRoom, i);
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            System.out.println("ActionDown...");
            switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_W:
                System.out.println("W down");
                isMovingUp = true;
                System.out.println("isMovingUp: " + isMovingUp);
                break;
            case KeyEvent.KEYCODE_A:
                isMovingLeft = true;
                break;
            case KeyEvent.KEYCODE_S:
                isMovingDown = true;
                break;
            case KeyEvent.KEYCODE_D:
                isMovingRight = true;
                break;
            default:
                System.out.println("W down");
                isMovingUp = true;
                System.out.println("isMovingUp: " + isMovingUp);
                break;
            }
        }

        return false;
    }

    public boolean onTouchLogic(MotionEvent event, Player player, boolean onTouch) {
        System.out.println("onTouchLogic");
        Log.d("in onTouchEvent", "");
        // player.setX((int) event.getX());
        // player.setY((int) event.getY());
        // System.out.println("Event Action: " + event.getAction());
        /*
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("ACTION DOWN");
                Log.d("in [0] actionDown", "");
                return true;
            case MotionEvent.ACTION_MOVE:
                System.out.println("ACTION MOVE");
                Log.d("in [0] actionMove", "");
                player.setX((int) event.getX());
                player.setY((int) event.getX());
                return true;

         */
        /*
                prevX = player.getX();
                prevY = player.getY();
                Log.d("x:" + x + " y:" + y, "");
                // Log.d("door|" + currRoom.getDoorwayLeftX() + ", " +
                currRoom.getDoorwayBottomY(), "");
                //checking for player collision with doorway
                Log.d("checking if doorway", "");
                if (x >= currRoom.getDoorwayLeftX() && x <= currRoom.getDoorwayRightX()) {
                    if (y >= currRoom.getDoorwayTopY() && y <= currRoom.getDoorwayBottomY()) {
                        player.setX(x);
                        player.setY(y);
                        // pause();
                        Log.d("paused-------------", "");
                    }
                } else if (x < 20) {
                    Log.d("left: " + x + "," + y, "");
                    //player.setX(prevX);
                } else if (x > 2090) {
                    Log.d("right: " + x + "," + y, "");
                    //player.setX(prevX);
                } else if (y < 3) {
                    Log.d("top: " + x + "," + y, "");
                    //player.setY(prevY);
                } else if (y > 810) {
                    Log.d("bottom: " + x + "," + y, "");
                    //player.setY(prevY);
                } else { // there is no collision
                    player.setX(x);
                    player.setY(y);
                }

                //Log.d("post-(" + player.getX() + "," + player.getY(), "");
                return true;
            default:
                player.setX(x);
                player.setY(y);
                return true;
         */
        return onTouch;
        // return super.onTouchEvent(event);
    }
}

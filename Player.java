package com.example.cs2340a.dungenCrawler.model;

import android.content.res.Resources;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.cs2340a.R;


public class Player implements Parcelable, IDrawable {

    //******* MAKE SINGLETON *********

    //attributes
    private String playerName;
    private int avatarID;
    private double difficulty;
    private int healthPoints;
    private Score score;
    private Rect collisionShape;
    private int x = 1200;
    private int y = 540;
    private int collisionOffsetX = 30;
    private int collisionOffsetY = 70;
    private int screenX;
    private int screenY;
    private int width = 74;
    private int height = 74; // Defaults for room1
    private MovementStrategy movement;
    private Bitmap sprite;

    //temporary basic public constructor

    public Player(String name, double difficulty, int screenX, int screenY, Resources res,
                  int avaID) {
        this.playerName = name;
        this.difficulty = difficulty;
        this.healthPoints = (int) (100 * difficulty);
        this.avatarID = avaID;
        score = new Score();
        this.screenX = screenX;
        this.screenY = screenY;

        movement = new PlayerMovement(screenX, screenY, res, avaID);
        collisionShape = new Rect(x + collisionOffsetX, y + collisionOffsetY,
                (x + collisionOffsetX) + width, (y + collisionOffsetY) + height);
    }

    protected Player(Parcel in) {
        playerName = in.readString();
        difficulty = in.readDouble();
        healthPoints = in.readInt();
        avatarID = in.readInt();
        screenX = in.readInt();
        screenY = in.readInt();
        movement = in.readParcelable(PlayerMovement.class.getClassLoader());
        score = in.readParcelable(Score.class.getClassLoader());
        collisionShape = in.readParcelable(Rect.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(playerName);
        dest.writeDouble(difficulty);
        dest.writeInt(healthPoints);
        dest.writeInt(avatarID);
        dest.writeInt(screenX);
        dest.writeInt(screenY);
        dest.writeParcelable(movement, 0);
        dest.writeParcelable((Parcelable) score, 0);
        dest.writeParcelable((Parcelable) collisionShape, 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    //getters and setters
    public String getPlayerName() {
        return playerName;
    }
    public int getAvatarID() {
        return avatarID;
    }
    public double getDifficulty() {
        return difficulty;
    }
    public String getDifficultyTitle() {
        if (difficulty == 0.5) {
            return "Hard";
        } else if (difficulty == 0.75) {
            return "Medium";
        } else {
            return "Easy";
        }
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public String getHealthString() {
        return "HP: " + healthPoints;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getScreenX() {
        return screenX;
    }
    public int getScreenY() {
        return screenY;
    }
    public Score getScore() {
        return score;
    }
    public MovementStrategy getMovement() {
        return movement;
    }
    public Rect getCollisionShape() {
        return collisionShape;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }
    public void setAvatarID(int avaID) {
        this.avatarID = avaID;
    }
    public void setDifficulty(double diff) {
        this.difficulty = diff;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setCollisionShape(Rect collisionShape) {
        this.collisionShape = collisionShape;
    }
    public void setMovement(MovementStrategy movement) {
        this.movement = movement;
    }
    public void setHealthPoints(int i) {
        this.healthPoints = i;
    }

    public void updateHealthPoints(int change) {
        this.healthPoints += change;
        //if negative number is passed in, health points are subtracted
        //if positive number is passed in, health points are added
    }

    public void update() {
        setCollisionShape(new Rect(x + collisionOffsetX, y + collisionOffsetY,
                (x + collisionOffsetX) + width, (y + collisionOffsetY) + height));
    }

    @Override
    public void draw(Canvas canvas, Resources resources) {
        Paint paint = new Paint();
        paint.setTextSize(50);
        canvas.drawText(playerName, 50, 50, paint);
        canvas.drawText(getDifficultyTitle(), 500, 50, paint);
        canvas.drawText(getHealthString(), 2000, 50, paint);
        score.draw(canvas, resources);
        canvas.drawRect(collisionShape, paint);

        if (getAvatarID() == R.drawable.player1) {
            sprite = BitmapFactory.decodeResource(resources, R.drawable.player1);
            canvas.drawBitmap(sprite, x - 24, y, paint);
        } else if (avatarID == R.drawable.player2) {
            sprite = BitmapFactory.decodeResource(resources, R.drawable.player2);
            canvas.drawBitmap(sprite, x - 24, y, paint);
        } else if (avatarID == R.drawable.player3) {
            sprite = BitmapFactory.decodeResource(resources, R.drawable.player3);
            canvas.drawBitmap(sprite, x - 24, y, paint);
        }
    }
}

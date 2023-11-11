package com.example.cs2340a.dungenCrawler.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class Background implements Parcelable, IDrawable {

    private int x;
    private int y;
    private Bitmap background;
    private int resID;
    private Point point;

    public Background(int screenX, int screenY, Resources res, int resID) {
        background = BitmapFactory.decodeResource(res, resID);
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);
        this.resID = resID;
    }
    public Background(Point point, Resources res, int resID) {
        background = BitmapFactory.decodeResource(res, resID);
        background = Bitmap.createScaledBitmap(background, point.x, point.y, false);
        this.resID = resID;
        this.point = point;
    }

    protected Background(Parcel in) {
        resID = in.readInt();
        point = in.readParcelable(Point.class.getClassLoader());
    }

    public void createBitmap(Point point, Resources res, int resID) {
        background = BitmapFactory.decodeResource(res, resID);
        background = Bitmap.createScaledBitmap(background, point.x, point.y, false);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setBitmap(Bitmap bitmap) {
        background = bitmap; }
    public Bitmap getBackground() {

        return background;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getResID() {
        return resID; }
    public Point getPoint() {
        return point; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(resID);
        parcel.writeParcelable(point, i);
    }

    public static final Creator<Background> CREATOR = new Creator<Background>() {
        @Override
        public Background createFromParcel(Parcel in) {
            return new Background(in);
        }

        @Override
        public Background[] newArray(int size) {
            return new Background[size];
        }
    };

    @Override
    public void draw(Canvas canvas, Resources resources) {
        Paint paint = new Paint();
        canvas.drawBitmap(background, x, y, paint);
    }
}

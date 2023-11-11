package com.example.cs2340a.dungenCrawler.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Parcel;

import androidx.annotation.NonNull;

public class Bat extends Enemy implements IDrawable {
    private Bitmap sprite;
    public Bat(Resources res, int resID, int speed, int attackPower) {
        super(res, resID, speed, attackPower);
        this.sprite = BitmapFactory.decodeResource(res, resID);
        this.sprite = Bitmap.createBitmap(sprite);
        System.out.println("bat made");
    }
    protected Bat(Parcel in) {
        super(in);
        sprite = in.readParcelable(Bitmap.class.getClassLoader());
    }

    @Override
    public void draw(Canvas canvas, Resources resources) {
        Paint paint = new Paint();
        canvas.drawRect(getCollisionShape(), paint);
        canvas.drawBitmap(sprite, getX(), getY(), paint);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(sprite, i);
    }
    public static final Creator<Bat> CREATOR = new Creator<Bat>() {
        @Override
        public Bat createFromParcel(Parcel in) {
            return new Bat(in);
        }

        @Override
        public Bat[] newArray(int size) {
            return new Bat[size];
        }
    };
}
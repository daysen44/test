package com.example.cs2340a.dungenCrawler.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;

import androidx.annotation.NonNull;

public class Zombie extends Enemy implements IDrawable {
    private Bitmap sprite;
    //private Drawable drawable;
    public Zombie(Resources res, int resID, int speed, int attackPower) {
        super(res, resID, speed, attackPower);
        this.sprite = BitmapFactory.decodeResource(res, resID);
        //this.drawable = res.getDrawable(resID);
        //this.sprite = drawableToBitmap(drawable);
        if (sprite == null) {
            System.out.println("Failure to decodeResource: " + sprite);
        }
        this.sprite = Bitmap.createBitmap(sprite);
    }

    /*
    private static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
     */

    protected Zombie(Parcel in) {
        super(in);
        sprite = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public void attack() {
        // Zombie attack logic
        // eat method
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
    public static final Creator<Zombie> CREATOR = new Creator<Zombie>() {
        @Override
        public Zombie createFromParcel(Parcel in) {
            return new Zombie(in);
        }

        @Override
        public Zombie[] newArray(int size) {
            return new Zombie[size];
        }
    };
}
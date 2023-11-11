package com.example.cs2340a.dungenCrawler.model;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;


public class Score implements Parcelable, IDrawable {
    private int score;
    private String scoreText;

    public Score(int currScore) {
        score = currScore;
    }

    public Score() {
        this(0);
    }

    protected Score(Parcel in) {
        score = in.readInt();
    }

    public int getScore() {
        return score; }
    public void setScore(int score) {
        this.score = score; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(score);
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

    @Override
    public void draw(Canvas canvas, Resources resources) {
        Paint paint = new Paint();
        paint.setTextSize(50);
        canvas.drawText("Score: " + score, 1500, 1000, paint);
    }
}

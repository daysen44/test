package com.example.cs2340a.dungenCrawler.model;

import android.content.res.Resources;
import android.os.Parcel;

import androidx.annotation.NonNull;

import com.example.cs2340a.R;

public class EasyEnemyFactory extends EnemyFactory {
    private int EasyAttackPower = 1;

    public Enemy spawnBat(Resources res) {
        Enemy enemy;
        enemy = new Bat(res, R.drawable.bat, 10, EasyAttackPower);
        return enemy;
    }
    public Enemy spawnGhost(Resources res) {
        Enemy enemy;
        enemy = new Ghost(res, R.drawable.ghost, 5, EasyAttackPower);
        return enemy;
    }
    public Enemy spawnVampire(Resources res) {
        Enemy enemy;
        enemy = new Vampire(res, R.drawable.vampire, 10, EasyAttackPower);
        return enemy;
    }
    public Enemy spawnZombie(Resources res) {
        Enemy enemy;
        enemy = new Zombie(res, R.drawable.zombie, 5, EasyAttackPower);
        return enemy;
    }


    //other stuff
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

    }
    public static final Creator<EasyEnemyFactory> CREATOR = new Creator<EasyEnemyFactory>() {
        @Override
        public EasyEnemyFactory createFromParcel(Parcel in) {
            return new EasyEnemyFactory();
        }

        @Override
        public EasyEnemyFactory[] newArray(int size) {
            return new EasyEnemyFactory[size];
        }
    };
}
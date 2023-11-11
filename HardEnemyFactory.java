package com.example.cs2340a.dungenCrawler.model;

import android.content.res.Resources;
import android.os.Parcel;
import androidx.annotation.NonNull;

import com.example.cs2340a.R;

public class HardEnemyFactory extends EnemyFactory {
    private int HardAttackPower = 5;

    @Override
    public Enemy spawnBat(Resources res) {
        Enemy enemy;
        enemy = new Bat(res, R.drawable.bat, 25, HardAttackPower);
        return enemy;
    }
    public Enemy spawnGhost(Resources res) {
        Enemy enemy;
        enemy = new Ghost(res, R.drawable.ghost, 20, HardAttackPower);
        return enemy;
    }
    public Enemy spawnVampire(Resources res) {
        Enemy enemy;
        enemy = new Vampire(res, R.drawable.vampire, 25, HardAttackPower);
        return enemy;
    }

    public Enemy spawnZombie(Resources res) {
        Enemy enemy;
        enemy = new Zombie(res, R.drawable.zombie, 20, HardAttackPower);
        return enemy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

    }

    public static final Creator<HardEnemyFactory> CREATOR = new Creator<HardEnemyFactory>() {
        @Override
        public HardEnemyFactory createFromParcel(Parcel in) {
            return new HardEnemyFactory();
        }

        @Override
        public HardEnemyFactory[] newArray(int size) {
            return new HardEnemyFactory[size];
        }
    };
    // do it by difficulty
    // could then implement difficulty interface
    // use those details to inform decisions in that difficulty
    // what difficulty
    // method spawn enemy
    // if its made in this hard difficulty it would do this
    // amount of damage to health points
    // player and enemy implement difficulty
    // decouples things rather than being an attribute of player


    // game loop class that has update draw run sleep implements runnable
    // that's what we instantiate in game room model
}
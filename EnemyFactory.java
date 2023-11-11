package com.example.cs2340a.dungenCrawler.model;

import android.content.res.Resources;
import android.os.Parcelable;

public abstract class EnemyFactory implements Parcelable {
    public abstract Enemy spawnBat(Resources res);
    public abstract Enemy spawnGhost(Resources res);
    public abstract Enemy spawnVampire(Resources res);
    public abstract Enemy spawnZombie(Resources res);
}
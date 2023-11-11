package com.example.cs2340a.dungenCrawler.model;

import com.example.cs2340a.dungenCrawler.view.PositionObserver;

import java.util.ArrayList;
import java.util.List;

// this class is the Subject/Observable for the Observer Pattern
public class PlayerPosition {
    //fields
    private List<PositionObserver> observers = new ArrayList<>();
    private int playerX;
    private int playerY;

    //methods
    public void addObserver(PositionObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PositionObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (PositionObserver observer : observers) {
            observer.onPositionChanged(playerX, playerY);
        }
    }

    public void updatePosition(int x, int y) {
        playerX = x;
        playerY = y;
        notifyObservers();
    }
}

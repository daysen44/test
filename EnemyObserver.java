package com.example.cs2340a.dungenCrawler.model;

interface EnemyObserver {
    public void update(Player player);
    public void checkCollision(Player player);
}

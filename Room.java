package com.example.cs2340a.dungenCrawler.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public abstract class Room implements Parcelable, IDrawable {
    private CollisionMap collisionMap;
    private String name; // A name or identifier for the room
    private int roomID;
    private Room[] connectedRooms; // An array of rooms that are connected to this room
    private Room connectedRoom; //the one room that is connected (sprint 3 - only one room
    private Background bg;
    private int difficulty;
    // connected)
    private int initialPlayerX; // Initial player X-coordinate in the room
    private int initialPlayerY; // Initial player Y-coordinate in the room
    private int doorwayTopY;
    private int doorwayBottomY;
    private int doorwayLeftX;
    private int doorwayRightX;


    public Room(String name, int initialPlayerX, int initialPlayerY, int y1, int y2, int x1,
                int x2, Background bg, int roomID) {
        this.name = name;
        this.roomID = roomID;
        this.initialPlayerX = initialPlayerX;
        this.initialPlayerY = initialPlayerY;
        this.doorwayTopY = y1;
        this.doorwayBottomY = y2;
        this.doorwayLeftX = x1;
        this.doorwayRightX = x2;
        this.bg = bg;

        collisionMap = new CollisionMap(roomID);
    }
    protected Room(Parcel in) {
        name = in.readString();
        roomID = in.readInt();
        initialPlayerX = in.readInt();
        initialPlayerY = in.readInt();
        doorwayTopY = in.readInt();
        doorwayBottomY = in.readInt();
        doorwayLeftX = in.readInt();
        doorwayRightX = in.readInt();
        bg = in.readParcelable(Background.class.getClassLoader());
        collisionMap = in.readParcelable(CollisionMap.class.getClassLoader());
    }

    // Getters
    public Room[] getConnectedRooms() {
        return connectedRooms;
    }
    public Room getConnectedRoom() {
        return connectedRoom;
    }
    public CollisionMap getCollisionMap() {
        return collisionMap;
    }
    public Background getBackground() {
        return bg;
    }
    public int getInitialPlayerX() {
        return initialPlayerX;
    }
    public int getInitialPlayerY() {
        return initialPlayerY;
    }
    public int getDoorwayTopY() {
        return doorwayTopY;
    }
    public int getDoorwayBottomY() {
        return doorwayBottomY;
    }
    public int getDoorwayLeftX() {
        return doorwayLeftX;
    }
    public int getDoorwayRightX() {
        return doorwayRightX;
    }
    public int getRoomID() {
        return roomID;
    }

    //setters
    public void setConnectedRooms(Room[] connectedRooms) {
        this.connectedRooms = connectedRooms;
    }
    public void setConnectedRoom(Room connectedRoom) {
        this.connectedRoom = connectedRoom;
    }
    public void setCollisionMap(CollisionMap collisionMap) {
        this.collisionMap = collisionMap; }
    public void setBg(Background bg) {
        this.bg = bg; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(roomID);
        parcel.writeInt(initialPlayerX);
        parcel.writeInt(initialPlayerY);
        parcel.writeInt(doorwayTopY);
        parcel.writeInt(doorwayBottomY);
        parcel.writeInt(doorwayLeftX);
        parcel.writeInt(doorwayRightX);
        parcel.writeParcelable(bg, i);
        parcel.writeParcelable(collisionMap, i);
    }


    // Additional methods to handle room-specific behavior, such as rendering
}


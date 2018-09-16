package com.villa.deimer.hackathon.models.entities;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class User {

    @DatabaseField(generatedId = true)
    private int code;

    @SerializedName("id")
    @DatabaseField(canBeNull = false)
    private int user_id;

    @SerializedName("namec")
    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField()
    private int totalPoints;

    @DatabaseField()
    private String expirationPoints;

    public User() {}
    public User(int user_id, String name, int totalPoints, String expirationPoints) {
        this.user_id = user_id;
        this.name = name;
        this.totalPoints = totalPoints;
        this.expirationPoints = expirationPoints;
    }

    //Getters
    public int getCode() {
        return code;
    }
    public int getUser_id() {
        return user_id;
    }
    public String getName() {
        return name;
    }
    public int getTotalPoints() {
        return totalPoints;
    }
    public String getExpirationPoints() {
        return expirationPoints;
    }

    //Setters
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
    public void setExpirationPoints(String expirationPoints) {
        this.expirationPoints = expirationPoints;
    }

    //ToString
    @Override
    public String toString() {
        return "User{" +
                "code=" + code +
                ", user_id='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", totalPoints='" + totalPoints + '\'' +
                ", expirationPoints='" + expirationPoints + '\'' +
                '}';
    }
}
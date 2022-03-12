package entities;

import java.sql.Timestamp;
import java.util.Objects;

public class Pack {
    private long id;
    private String name;
    private Timestamp createdOn;
    private Timestamp lastUpdated;
    private long userId;

    public Pack(long id, String name, Timestamp createdOn, Timestamp lastUpdated, long userId) {
        this.id = id;
        this.name = name;
        this.createdOn = createdOn;
        this.lastUpdated = lastUpdated;
        this.userId = userId;
    }

    public Pack(String name, long userId) {
        this.name = name;
        this.userId = userId;
    }

    public Pack() {
    }

    public Pack(String name, Timestamp createdOn, Timestamp lastUpdated, long userId) {
        this.name = name;
        this.createdOn = createdOn;
        this.lastUpdated = lastUpdated;
        this.userId = userId;
    }

    @Override
    public String toString(){
        return Long.toString(id)+"\t"+name+"\t"+createdOn.toString()+"\t"+lastUpdated.toString()+"\t"+Long.toString(userId);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pack pack = (Pack) o;
        return id == pack.id && createdOn.equals(pack.createdOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdOn);
    }


    //GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    //
}

package pis.lab2.entities;

import java.util.Objects;

public class User {


    private long id;
    private String username;
    private String passwordHash;

    public User() {
    }

    public User(long id, String username, String password_hash) {
        this.id = id;
        this.username = username;
        this.passwordHash = password_hash;
    }

    public User(String username, String password_hash) {
        this.username = username;
        this.passwordHash = password_hash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return Long.toString(id)+"\t"+username+"\t"+passwordHash;

    }
}

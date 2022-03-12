package game.www.repo.model;

public class User {

    private final long id;
    private final String username;

    public User(long userId, String username) {
        this.id = userId;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}

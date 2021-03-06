package game.www.api;

import game.www.repo.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private static final List<User> USERS = Arrays.asList(
            new User(1, "KepBuran"),
            new User(2, "MultiSmith"),
            new User(3, "mnb3000")
    );

    @GetMapping("{userId}")
    public User getUser(@PathVariable("userId") Long userId) {
        return USERS.stream()
                .filter(user -> userId.equals(user.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User "+ userId + " does not exist"));
    }

}

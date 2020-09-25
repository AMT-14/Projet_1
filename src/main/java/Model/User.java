package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
//@AllArgsConstructor
public class User {
    private int id;
    private String username;

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }
}

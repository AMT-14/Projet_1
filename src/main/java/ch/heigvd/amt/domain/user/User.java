package ch.heigvd.amt.domain.user;

import ch.heigvd.amt.domain.IEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class User implements IEntity<User, UserId> {
    private UserId id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    @EqualsAndHashCode.Exclude
    private String encryptedPassword;

    public boolean authenticate(String clearTextPassword) {
        return clearTextPassword.toUpperCase().equals(encryptedPassword);
    }

    @Override
    public User deepClone() {
        return this.toBuilder()
                .id(new UserId(id.asString()))
                .build();
    }

    public static class UserBuilder {
        public UserBuilder clearTextPassword(String clearTextPassword){
            if (clearTextPassword == null || clearTextPassword.isEmpty()){
                throw new java.lang.IllegalArgumentException("Password is required, put one not empty");
            }
            encryptedPassword = clearTextPassword.toUpperCase();
            return this;
        }
        public User build(){
            if (id == null) {
                id = new UserId();
            }

            if(username == null || username.isEmpty()){
                throw new java.lang.IllegalArgumentException("Username is required, put one not empty");
            }
            if(encryptedPassword == null || encryptedPassword.isEmpty()){
                throw new java.lang.IllegalArgumentException("Password is required, put one not empty");
            }
            if(firstName == null || firstName.isEmpty()){
                throw new java.lang.IllegalArgumentException("First name is required, put one not empty");
            }
            if(lastName == null || lastName.isEmpty()){
                throw new java.lang.IllegalArgumentException("Last name is required, put one not empty");
            }
            if(email == null || email.isEmpty()){
                throw new java.lang.IllegalArgumentException("Email is required, put one not empty");
            }

            User newUser = new User(id, username, email, firstName, lastName, encryptedPassword);
            return newUser;
        }
    }
}

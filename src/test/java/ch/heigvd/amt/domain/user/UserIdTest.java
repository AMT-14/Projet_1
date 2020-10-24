package ch.heigvd.amt.domain.user;

import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserIdTest {

    public UUID uuid =  UUID.randomUUID();
    public String test = uuid.toString();


    @Test
    public void testIdGenerated(){

        assertNotNull(new UserId());
    }

    @Test
    public void testIdConstructorStringWithInput(){
        UserId uid1 = new UserId(test);
        assertNotNull(uid1);
    }

    @Test
    public void testIdConstructorUUIDWithInput(){
        UserId uid1 = new UserId(uuid);
        assertNotNull(uid1);
    }

    @Test
    public void testIdConstructorUUIDWithNull(){
        assertThrows(NullPointerException.class, () -> {
            UUID uuidN = null ;
            UserId uid1 = new UserId(uuidN);
        });
    }

    @Test
    public void testIdConstructorEquivalent(){
        UserId uid1 = new UserId(test);
        UserId uid2 = new UserId(uuid);
        assertEquals(uid1, uid2);
        assertEquals(uid1.asString(), uid2.asString());
    }


}

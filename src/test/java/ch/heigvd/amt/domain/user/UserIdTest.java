package ch.heigvd.amt.domain.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserIdTest {

    @Test
    public void testIdGenerated(){

        assertNotNull(new UserId());
    }

    @ParameterizedTest
    @ValueSource (ints = {0, 9999})
    public void testIdConstructorStringWithInput(int id){
        String sid = Integer.toString(id);
        UserId uid1 = new UserId(sid);
        assertNotNull(uid1);
    }

    @ParameterizedTest
    @ValueSource (ints = {0, 9999})
    public void testIdConstructorUUIDWithInput(int id){
        String sid = Integer.toString(id);
        UUID uuid = UUID.fromString(sid);
        UserId uid1 = new UserId(uuid);
        assertNotNull(uid1);
    }

    @Test
    public void testIdConstructorUUIDWithNull(){
        assertThrows(NullPointerException.class, () -> {
            UUID uuid = null ;
            UserId uid1 = new UserId(uuid);
        });
    }

    @ParameterizedTest
    @ValueSource (ints = {0, 9999})
    public void testIdConstructorEquivalent(int id){
        String sid = Integer.toString(id);
        UserId uid1 = new UserId(sid);

        UUID uuid = UUID.fromString(sid);
        UserId uid2 = new UserId(uuid);

        assertEquals(uid1, uid2);
        assertEquals(uid1.asString(), uid2.asString());
    }


}

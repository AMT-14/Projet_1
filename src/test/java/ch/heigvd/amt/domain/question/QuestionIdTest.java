package ch.heigvd.amt.domain.question;


import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionIdTest {


    public UUID uuid =  UUID.randomUUID();
    public String test = uuid.toString();


    @Test
    public void testIdGenerated(){
        assertNotNull(new QuestionId());
    }

    @Test
    public void testIdConstructorStringWithInput(){
        QuestionId uid1 = new QuestionId(test);
        assertNotNull(uid1);
    }

    @Test
    public void testIdConstructorUUIDWithInput(){
        QuestionId uid1 = new QuestionId(uuid);
        assertNotNull(uid1);
    }

    @Test
    public void testIdConstructorUUIDWithNull(){
        assertThrows(NullPointerException.class, () -> {
            UUID uuidN = null ;
            QuestionId uid1 = new QuestionId(uuidN);
        });
    }

    @Test
    public void testIdConstructorEquivalent(){
        QuestionId uid1 = new QuestionId(test);
        QuestionId uid2 = new QuestionId(uuid);

        assertEquals(uid1, uid2);
        assertEquals(uid1.asString(), uid2.asString());
    }
}

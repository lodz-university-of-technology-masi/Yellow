package edu.pl.masi.yellow.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class LoginTokenTest {
    @Test
    public void InvalidToken() {
        LoginToken token = new LoginToken("", "");
        assertFalse(token.isValid());
    }

    @Test
    public void ValidTokenDecomposition() {
        LoginToken token = new LoginToken("exampleUserName", "testPassword");

        assertEquals("exampleUserName", token.getUserName());
        assertEquals("testPassword", token.getUserPassword());
        assertEquals("exampleUserName:testPassword", token.getAuthToken());
        assertTrue(token.isValid());
    }

    @Test
    public void CanTokenToJSONSerialization() {
        LoginToken token = new LoginToken("exampleUserName", "testPassword");
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValueAsString(token);
        } catch (JsonProcessingException e) {
            fail();
        }
    }
}

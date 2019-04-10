package edu.pl.masi.yellow.entity;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class UserEntityTest {
    @Test
    public void DefaultUserRoleIsClient() {
        UserEntity entity = new UserEntity("Jan", "qwert1");
        assertEquals("user", entity.getRole());
    }

    @Test
    public void UserDataAreSetDuringConstruction() {
        UserEntity entity = new UserEntity("Jan", "qwert1");
        assertEquals("Jan", entity.getUsername());
        assertEquals("qwert1", entity.getPassword());
    }
}

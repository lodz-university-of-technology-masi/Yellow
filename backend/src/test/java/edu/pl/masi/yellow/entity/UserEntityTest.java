package edu.pl.masi.yellow.entity;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class UserEntityTest {
    @Test
    public void CheckDefaultUserRole() {
        UserEntity entity = new UserEntity("Jan", "qwert1");
        assertEquals(UserEntity.UserRole.USER, entity.getRole());
    }

    @Test
    public void UserDataIsSetDuringConstruction() {
        UserEntity entity = new UserEntity("Jan", "qwert1");
        assertEquals("Jan", entity.getUsername());
        assertEquals("qwert1", entity.getPassword());
    }
}

package edu.pl.masi.yellow.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestEntityTest {
    @Test
    public void CanBuildTest() {
        UserEntity userEntity = new UserEntity();
        TestEntity testEntity = new TestEntity("Simple Test Entity", userEntity);

        assertEquals(userEntity, testEntity.getOwner());
        assertEquals("Simple Test Entity", testEntity.getTestname());
    }

    @Test
    public void CanBuildEmptyTest() {
        UserEntity userEntity = new UserEntity();
        TestEntity testEntity = new TestEntity();

        testEntity.setOwner(userEntity);
        testEntity.setTestname("Simple Test Entity");

        assertEquals(userEntity, testEntity.getOwner());
        assertEquals("Simple Test Entity", testEntity.getTestname());
    }
}

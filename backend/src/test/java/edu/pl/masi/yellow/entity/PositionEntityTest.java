package edu.pl.masi.yellow.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PositionEntityTest {
    @Test
    public void CanCreatePositionWithId() {
        PositionEntity positionEntity = new PositionEntity();

        positionEntity.setId(1);
        assertEquals(1, positionEntity.getId());
    }

    @Test
    public void CanSetPositionName() {
        PositionEntity positionEntity = new PositionEntity();

        positionEntity.setPositionName("Test");
        assertEquals("Test", positionEntity.getPositionName());
    }

    @Test
    public void CanSetPositionActivity() {
        PositionEntity positionEntity = new PositionEntity();

        positionEntity.setActive(true);
        assertTrue(positionEntity.isActive());
    }

    @Test
    public void CanSetPostionList() {
        PositionEntity positionEntity = new PositionEntity();

        List<TestEntity> list = new ArrayList<>();
        positionEntity.setTestEntities(list);

        assertTrue(positionEntity.getTestEntities().isEmpty());
        assertEquals(list, positionEntity.getTestEntities());
    }

    @Test
    public void CanAddToList() {
        PositionEntity positionEntity = new PositionEntity();

        List<TestEntity> list = new ArrayList<>();
        positionEntity.setTestEntities(list);

        positionEntity.addTest(new TestEntity());

        assertFalse(positionEntity.getTestEntities().isEmpty());
        assertFalse(list.isEmpty());
        assertEquals(list, positionEntity.getTestEntities());
    }
}

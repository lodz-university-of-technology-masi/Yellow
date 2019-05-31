package edu.pl.masi.yellow.manager;

import edu.pl.masi.yellow.controller.api.v1.manage.Positions;
import edu.pl.masi.yellow.entity.PositionEntity;
import edu.pl.masi.yellow.repository.PositionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class PositionManagerTest {
    @Mock
    PositionRepository positionRepository;

    PositionManager positionManager;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);

        this.positionManager = new PositionManager();
        this.positionManager.setPositionRepository(positionRepository);
    }

    @Test
    public void CanGetAllPositions() {
        this.positionManager.getAllPositions();
        verify(this.positionRepository).findAll();
    }

    @Test
    public void CanActivatePosition() {
        PositionEntity position = new PositionEntity();
        when(this.positionRepository.findById(1)).thenReturn(position);

        position.setActive(false);
        this.positionManager.activatePositionById(1);
        assertTrue(position.isActive());
    }

    @Test
    public void CanDeactivatePosition() {
        PositionEntity position = new PositionEntity();
        when(this.positionRepository.findById(1)).thenReturn(position);

        position.setActive(true);
        this.positionManager.deactivatePositionById(1);
        assertFalse(position.isActive());
    }

    @Test
    public void CanCreateNewPosition() {
        when(this.positionRepository.save(any(PositionEntity.class)))
                .then(x -> {
                    assertEquals("newPositionName", ((PositionEntity) x.getArgument(0)).getPositionName());
                    return null;
                });

        this.positionManager.createNewPosition("newPositionName");
    }

    @Test
    public void CanDeletePosition() {
        when(this.positionRepository.findById(1)).thenReturn(new PositionEntity());
        this.positionManager.deletePositionById(1);

        verify(this.positionRepository).findById(1);
    }
}

package edu.pl.masi.yellow.manager;

import edu.pl.masi.yellow.entity.PositionEntity;
import edu.pl.masi.yellow.entity.TestEntity;
import edu.pl.masi.yellow.model.response.GenericResponse;
import edu.pl.masi.yellow.repository.PositionRepository;
import edu.pl.masi.yellow.repository.TestRepository;
import edu.pl.masi.yellow.utils.exceptions.ResourceNotFoundException;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PositionManager {
    private PositionRepository positionRepository;
    private TestRepository testRepository;

    public GenericResponse activatePositionById(int positionId) {
        PositionEntity positionEntity = this.positionRepository.findById(positionId);

        if (positionEntity == null)
            return new GenericResponse("Error: Position with given id does not exist!");

        positionEntity.setActive(true);
        positionRepository.save(positionEntity);
        return new GenericResponse("Successfully activated position");
    }

    public GenericResponse deactivatePositionById(int positionId) {
        PositionEntity positionEntity = this.positionRepository.findById(positionId);

        if (positionEntity == null)
            return new GenericResponse("Error: Position with given id does not exist!");

        positionEntity.setActive(false);
        positionRepository.save(positionEntity);
        return new GenericResponse("Successfully deactivated position");
    }

    public PositionEntity createNewPosition(String positionName) {
        PositionEntity positionEntity = new PositionEntity();
        positionEntity.setPositionName(positionName);

        return this.positionRepository.save(positionEntity);
    }

    public GenericResponse deletePositionById(int positionId) {
        PositionEntity positionEntity = this.positionRepository.findById(positionId);

        if (positionEntity == null)
            return new GenericResponse("Error: Position with given id does not exist!");

        this.positionRepository.delete(positionEntity);
        return new GenericResponse("Successfully deleted position");
    }

    public GenericResponse addTestToPosition(int positionId, int testId) {
        PositionEntity positionEntity = this.positionRepository.findById(positionId);
        TestEntity testEntity = this.testRepository.findById(testId);

        if (positionEntity == null || testEntity == null)
            throw new ResourceNotFoundException();

        positionEntity.addTest(testEntity);
        positionRepository.save(positionEntity);

        return new GenericResponse("Added test to position");
    }

    public GenericResponse removeTestFromPosition(int positionId, int testId) {
        PositionEntity positionEntity = this.positionRepository.findById(positionId);
        TestEntity testEntity = this.testRepository.findById(testId);

        if (positionEntity == null || testEntity == null)
            throw new ResourceNotFoundException();

        if (positionEntity.getTestEntities().contains(testEntity)) {
            positionEntity.removeTest(testEntity);
            positionRepository.save(positionEntity);
        }

        return new GenericResponse("Added test to position");
    }

    public List<PositionEntity> getAllPositions() {
        return this.positionRepository.findAll();
    }

    @Autowired
    public void setPositionRepository(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Autowired
    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

}

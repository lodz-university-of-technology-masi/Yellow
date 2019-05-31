package edu.pl.masi.yellow.manager;

import edu.pl.masi.yellow.entity.PositionEntity;
import edu.pl.masi.yellow.model.response.GenericResponse;
import edu.pl.masi.yellow.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PositionManager {
    private PositionRepository positionRepository;

    public GenericResponse activatePositionById(int positionId) {
        PositionEntity positionEntity = this.positionRepository.findById(positionId);

        if (positionEntity == null)
            return new GenericResponse("Error: Position with given id does not exist!");

        positionEntity.setActive(true);
        return new GenericResponse("Successfully activated position");
    }

    public GenericResponse deactivatePositionById(int positionId) {
        PositionEntity positionEntity = this.positionRepository.findById(positionId);

        if (positionEntity == null)
            return new GenericResponse("Error: Position with given id does not exist!");

        positionEntity.setActive(false);
        return new GenericResponse("Successfully deactivated position");
    }

    public GenericResponse createNewPosition(String positionName) {
        PositionEntity positionEntity = new PositionEntity();
        positionEntity.setPositionName(positionName);

        this.positionRepository.save(positionEntity);
        return new GenericResponse("Successfully created position with name " + positionName);
    }

    public GenericResponse deletePositionById(int positionId) {
        PositionEntity positionEntity = this.positionRepository.findById(positionId);

        if (positionEntity == null)
            return new GenericResponse("Error: Position with given id does not exist!");

        this.positionRepository.delete(positionEntity);
        return new GenericResponse("Successfully deleted position");
    }

    public List<PositionEntity> getAllPositions() {
        return this.positionRepository.findAll();
    }

    @Autowired
    public void setPositionRepository(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

}

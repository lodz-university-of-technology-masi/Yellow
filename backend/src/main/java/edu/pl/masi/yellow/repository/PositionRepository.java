package edu.pl.masi.yellow.repository;

import edu.pl.masi.yellow.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository @Transactional
public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
    PositionEntity findById(int id);
}

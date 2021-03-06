package edu.pl.masi.yellow.repository;

import edu.pl.masi.yellow.entity.TestEntity;
import edu.pl.masi.yellow.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TestRepository extends JpaRepository<TestEntity, Long> {
    @Query("SELECT t from TestEntity t WHERE t.owner = :redactor")
    List<TestEntity> findByRedactor(@Param("redactor") UserEntity redactor);

    TestEntity findById(int id);
}

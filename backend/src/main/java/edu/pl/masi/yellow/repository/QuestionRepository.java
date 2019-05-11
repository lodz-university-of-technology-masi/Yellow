package edu.pl.masi.yellow.repository;

import edu.pl.masi.yellow.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    @Query("SELECT q FROM QuestionEntity q WHERE u.id = :testId")
    QuestionEntity findByTest(@Param("testId") Long testId);
}

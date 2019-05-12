package edu.pl.masi.yellow.repository;

import edu.pl.masi.yellow.entity.QuestionEntity;
import edu.pl.masi.yellow.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    @Query("SELECT q FROM QuestionEntity q WHERE q.test = :test")
    List<QuestionEntity> findByTest(@Param("test") TestEntity testEntity);
}

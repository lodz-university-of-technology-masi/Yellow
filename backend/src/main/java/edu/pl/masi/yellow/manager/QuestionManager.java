package edu.pl.masi.yellow.manager;

import edu.pl.masi.yellow.entity.QuestionEntity;
import edu.pl.masi.yellow.entity.TestEntity;
import edu.pl.masi.yellow.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionManager {
    private QuestionRepository questionRepository;

    public QuestionManager() {

    }

    List<QuestionEntity> getAllQuestionsByTest(TestEntity test) {
        return this.questionRepository.findByTest(test);
    }

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
}

package edu.pl.masi.yellow.manager;

import edu.pl.masi.yellow.entity.QuestionEntity;
import edu.pl.masi.yellow.entity.TestEntity;
import edu.pl.masi.yellow.model.request.QuestionAddRequest;
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

    public List<QuestionEntity> getAllQuestionsByTest(TestEntity test) {
        return this.questionRepository.findByTest(test);
    }

    public void removeQuestion(QuestionEntity question) {
        this.questionRepository.delete(question);
    }

    public void addQuestionToTest(TestEntity test, QuestionAddRequest questionRequest) {
        QuestionEntity question = new QuestionEntity(questionRequest);
        question.setTest(test);

        if (question.getNumber() == -1) {
           question.setNumber(this.getAllQuestionsByTest(test).stream()
                   .map(q -> q.getNumber()).max(Integer::compareTo).orElse(0) + 1);
        }

        questionRepository.save(question);
    }

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
}

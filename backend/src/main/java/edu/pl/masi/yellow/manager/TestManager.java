package edu.pl.masi.yellow.manager;

import edu.pl.masi.yellow.entity.TestEntity;
import edu.pl.masi.yellow.entity.UserEntity;
import edu.pl.masi.yellow.model.response.TestDefResponse;
import edu.pl.masi.yellow.repository.TestRepository;
import edu.pl.masi.yellow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestManager {
    private TestRepository testRepository;
    private UserRepository userRepository;
    private QuestionManager questionManager;

    public TestManager() {

    }

    public List<TestDefResponse> getAllTestsByRedactor(int redactorId) {
        UserEntity redactorEntity = userRepository.findById(redactorId);

        if (redactorEntity == null)
            return new ArrayList<>();

        List<TestEntity> redactorTests = testRepository.findByRedactor(redactorEntity);

        return redactorTests.stream().map(e -> new TestDefResponse(e,
                this.questionManager.getAllQuestionsByTest(e)))
                .collect(Collectors.toList());

    }

    public TestDefResponse getTestById(int testId) {
        TestEntity test = testRepository.findById(testId);

        if (test == null)
            return null;

        return new TestDefResponse(test, this.questionManager.getAllQuestionsByTest(test));
    }

    @Autowired
    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setQuestionManager(QuestionManager questionManager) {
        this.questionManager = questionManager;
    }
}

package edu.pl.masi.yellow.manager;

import edu.pl.masi.yellow.entity.TestEntity;
import edu.pl.masi.yellow.entity.UserEntity;
import edu.pl.masi.yellow.model.request.QuestionAddRequest;
import edu.pl.masi.yellow.model.response.GenericResponse;
import edu.pl.masi.yellow.model.response.TestDefResponse;
import edu.pl.masi.yellow.repository.QuestionRepository;
import edu.pl.masi.yellow.repository.TestRepository;
import edu.pl.masi.yellow.repository.UserRepository;
import edu.pl.masi.yellow.utils.exception.ResourceNotFoundException;
import edu.pl.masi.yellow.utils.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new ResourceNotFoundException();

        List<TestEntity> redactorTests = testRepository.findByRedactor(redactorEntity);

        return redactorTests.stream().map(e -> new TestDefResponse(e,
                this.questionManager.getAllQuestionsByTest(e)))
                .collect(Collectors.toList());

    }

    public TestDefResponse getTestById(int testId) {
        TestEntity test = testRepository.findById(testId);

        if (test == null)
            throw new ResourceNotFoundException();

        return new TestDefResponse(test, this.questionManager.getAllQuestionsByTest(test));
    }

    public GenericResponse removeQuestionFromTest(String requesterName, int testId, int questionId) {
        UserEntity requesterUserEntity = userRepository.findByUsername(requesterName);
        TestEntity test = testRepository.findById(testId);

        if (requesterUserEntity == null || test == null)
            throw new ResourceNotFoundException();

        if (requesterUserEntity.getRole().equals(UserEntity.UserRole.MODERATOR) || test.getOwner().equals(requesterUserEntity)) {
            this.questionManager.getAllQuestionsByTest(test).stream().filter(q -> q.getId() == questionId)
                    .forEach(q -> this.questionManager.removeQuestion(q));
            return new GenericResponse("Successfully removed question from test");
        } else {
            throw new ForbiddenException();
        }
    }

    public GenericResponse addQuestionToTest(String requesterName, int testId, QuestionAddRequest request) {
        UserEntity requesterUserEntity = userRepository.findByUsername(requesterName);
        TestEntity test = testRepository.findById(testId);

        if (requesterUserEntity == null || test == null)
            throw new ResourceNotFoundException();

        if (requesterUserEntity.getRole().equals(UserEntity.UserRole.MODERATOR) || test.getOwner().equals(requesterUserEntity)) {
            this.questionManager.addQuestionToTest(test, request);
            return new GenericResponse("Added new question to test");
        } else {
            throw new ForbiddenException();
        }
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

package edu.pl.masi.yellow.manager;

import edu.pl.masi.yellow.entity.*;
import edu.pl.masi.yellow.model.request.TestSolutionRequest;
import edu.pl.masi.yellow.model.response.GenericResponse;
import edu.pl.masi.yellow.repository.*;
import edu.pl.masi.yellow.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AnswerManager {
    private UserRepository userRepository;
    private TestRepository testRepository;
    private PositionRepository positionRepository;
    private SolutionRepositiory solutionRepositiory;
    private QuestionRepository questionRepository;

    public GenericResponse answerTest(String name, TestSolutionRequest solutionRequest) {
        SolutionEntity solution = new SolutionEntity();
        UserEntity userEntity = userRepository.findByUsername(name);
        TestEntity testEntity = testRepository.findById(solutionRequest.testId);
        PositionEntity positionEntity = positionRepository.findById(solutionRequest.positionId);

        if (userEntity == null || testEntity == null || positionEntity == null)
            throw new ResourceNotFoundException();

        solution.setLanguage(solutionRequest.language);
        solution.setPositionEntity(positionEntity);
        solution.setTestEntity(testEntity);
        solution.setUserEntity(userEntity);

        solution.setListOfAnswers(solutionRequest.answerList.stream()
                .map(a -> new AnswerEntity(questionRepository.findById(a.questionId).orElse(null),
                        a.answerString)).collect(Collectors.toList()));

        solutionRepositiory.save(solution);
        return new GenericResponse("Solution to test saved");
    }

    @Autowired
    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Autowired
    public void setPositionRepository(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Autowired
    public void setSolutionRepositiory(SolutionRepositiory solutionRepositiory) {
        this.solutionRepositiory = solutionRepositiory;
    }

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

package edu.pl.masi.yellow.manager;

import edu.pl.masi.yellow.entity.*;
import edu.pl.masi.yellow.model.request.TestSolutionRequest;
import edu.pl.masi.yellow.model.response.AnswerResponse;
import edu.pl.masi.yellow.model.response.GenericResponse;
import edu.pl.masi.yellow.model.response.TestSolutionResponse;
import edu.pl.masi.yellow.repository.*;
import edu.pl.masi.yellow.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerManager {
    private UserRepository userRepository;
    private TestRepository testRepository;
    private PositionRepository positionRepository;
    private SolutionRepositiory solutionRepositiory;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

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

        solution.getListOfAnswers().stream().forEach(a -> this.answerRepository.save(a));

        solutionRepositiory.save(solution);
        return new GenericResponse("Solution to test saved");
    }

    public List<TestSolutionResponse> getAnswerTest(String redactorName) {
        return this.solutionRepositiory.findAll().stream().filter(s -> s.getTestEntity()
                .getOwner().getUsername().equals(redactorName)).map(s -> getResponse(s))
                .collect(Collectors.toList());
    }

    public GenericResponse acceptAnswer(int answerId) {
        AnswerEntity entity = this.answerRepository.findById(answerId).orElseThrow(ResourceNotFoundException::new);

        entity.setAccepted(true);

        this.answerRepository.save(entity);
        return new GenericResponse("Accepted answer");
    }

    public GenericResponse refuseAnswer(int answerId) {
        AnswerEntity entity = this.answerRepository.findById(answerId).orElseThrow(ResourceNotFoundException::new);

        entity.setAccepted(false);

        this.answerRepository.save(entity);
        return new GenericResponse("Answer refused");
    }

    private TestSolutionResponse getResponse(SolutionEntity entity) {
        TestSolutionResponse response = new TestSolutionResponse();
        response.id = entity.getId();
        response.positionId = entity.getPositionEntity().getId();
        response.testId = entity.getTestEntity().getId();
        response.language = entity.getLanguage();
        response.userId = entity.getUserEntity().getId();

        response.answerList = entity.getListOfAnswers().stream().map(a -> {
            AnswerResponse answer = new AnswerResponse();
            answer.answerId = a.getId();
            answer.questionId = a.getQuestionEntity().getId();
            answer.answer = a.getAnswer();
            answer.score = a.isAccepted();

            return answer;
        }).collect(Collectors.toList());

        return response;
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

    @Autowired
    public void setAnswerRepository(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }
}

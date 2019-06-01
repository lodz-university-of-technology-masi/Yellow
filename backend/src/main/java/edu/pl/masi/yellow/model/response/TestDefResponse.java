package edu.pl.masi.yellow.model.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pl.masi.yellow.entity.QuestionEntity;
import edu.pl.masi.yellow.entity.TestEntity;
import edu.pl.masi.yellow.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TestDefResponse {
    @JsonProperty("testId")
    private int testId;

    @JsonProperty("testName")
    private String testName;

    @JsonProperty("testOwner")
    private UserEntity testOwner;

    @JsonProperty("questions")
    private List<QuestionDefResponse> questionList;

    public TestDefResponse(TestEntity test, List<QuestionEntity> questions) {
        this.testId = test.getId();
        this.testName = test.getTestname();
        this.testOwner = test.getOwner();

        this.questionList = questions.stream().map(TestDefResponse::createQuestionDefResponse)
                .collect(Collectors.toList());
    }

    private static QuestionDefResponse createQuestionDefResponse(QuestionEntity questionEntity) {
        QuestionDefResponse response = null;
        switch (questionEntity.getType()) {
            case CHOICE:
                response = new QuestionDefChoiceResponse(questionEntity);
                break;
            case SCALE:
                response = new QuestionDefScaleResponse(questionEntity);
                break;
            default:
                response = new QuestionDefResponse(questionEntity);

        }
        return response;
    }

    @JsonGetter("language")
    public List<String> getTestLanguage() {
        return  questionList.stream().map(q -> q.getLang())
                .distinct().collect(Collectors.toList());
    }
}

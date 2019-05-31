package edu.pl.masi.yellow.entity;

import static org.junit.Assert.*;

import edu.pl.masi.yellow.model.request.QuestionAddRequest;
import org.junit.Test;

public class QuestionEntityTest {
    @Test
    public void CanCreateQuestionEntity() {
        QuestionEntity questionEntity = new QuestionEntity(0,
                "PL", "Desc", QuestionEntity.QuestionType.CHOICE,
                "a|b|c|e", new TestEntity());
        assertEquals(0, questionEntity.getNumber());
        assertEquals("PL", questionEntity.getLanguage());
        assertEquals("Desc", questionEntity.getDescription());
        assertEquals(QuestionEntity.QuestionType.CHOICE, questionEntity.getType());
        assertEquals("a|b|c|e", questionEntity.getMetadata());
    }

    @Test
    public void CanCreateEmptyEntity() {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setTest(new TestEntity());
        questionEntity.setNumber(0);
        questionEntity.setLanguage("EN");
        questionEntity.setDescription("Description");
        questionEntity.setType(QuestionEntity.QuestionType.CHOICE);
        questionEntity.setMetadata("asdfg");

        assertEquals(0, questionEntity.getNumber());
        assertEquals("EN", questionEntity.getLanguage());
        assertEquals("Description", questionEntity.getDescription());
        assertEquals(QuestionEntity.QuestionType.CHOICE, questionEntity.getType());
        assertEquals("asdfg", questionEntity.getMetadata());
    }

    @Test
    public void CanBeConvertedToString() {
        QuestionEntity questionEntity = new QuestionEntity(0,
                "PL", "Desc", QuestionEntity.QuestionType.CHOICE,
                "a|b|c|e", new TestEntity());

        assertEquals("0;W;PL;Desc;", questionEntity.toString());
    }

    @Test
    public void CanByBuildFromAddRequest() {
        QuestionAddRequest addRequest = new QuestionAddRequest();

        addRequest.setQuestionNumber(1);
        addRequest.setQuestionDesc("QuestionDesc");
        addRequest.setQuestionLang("PL");
        addRequest.setQuestionData("asdfgh|asdfg");
        addRequest.setType(QuestionEntity.QuestionType.CHOICE);

        QuestionEntity questionEntity = new QuestionEntity(addRequest);

        assertEquals(1, questionEntity.getNumber());
        assertEquals("PL", questionEntity.getLanguage());
        assertEquals("QuestionDesc", questionEntity.getDescription());
        assertEquals(QuestionEntity.QuestionType.CHOICE, questionEntity.getType());
        assertEquals("asdfgh|asdfg", questionEntity.getMetadata());
    }
}

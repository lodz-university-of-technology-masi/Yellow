package edu.pl.masi.yellow.utils;

import edu.pl.masi.yellow.entity.QuestionEntity;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class QuestionHelperTest {
    @Test
    public void CanCreateQuestionTypeFromString_1() throws IOException {
        assertEquals(QuestionEntity.QuestionType.CHOICE, QuestionHelper.questionCharToType("W"));
    }

    @Test
    public void CanCreateQuestionTypeFromString_2() throws IOException {
        assertEquals(QuestionEntity.QuestionType.OPEN, QuestionHelper.questionCharToType("O"));
    }

    @Test
    public void CanCreateQuestionTypeFromString_3() throws IOException {
        assertEquals(QuestionEntity.QuestionType.NUMBER, QuestionHelper.questionCharToType("L"));
    }

    @Test
    public void CanCreateQuestionTypeFromString_4() throws IOException {
        assertEquals(QuestionEntity.QuestionType.SCALE, QuestionHelper.questionCharToType("S"));
    }

    @Test(expected = IOException.class)
    public void CanCreateQuestionTypeFromString_5() throws IOException {
        assertEquals(QuestionEntity.QuestionType.CHOICE, QuestionHelper.questionCharToType("Z"));
    }
}

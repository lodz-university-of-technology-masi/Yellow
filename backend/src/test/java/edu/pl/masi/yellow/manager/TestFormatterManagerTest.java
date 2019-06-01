package edu.pl.masi.yellow.manager;

import edu.pl.masi.yellow.entity.QuestionEntity;
import edu.pl.masi.yellow.entity.TestEntity;
import edu.pl.masi.yellow.entity.UserEntity;
import edu.pl.masi.yellow.repository.QuestionRepository;
import edu.pl.masi.yellow.repository.TestRepository;
import edu.pl.masi.yellow.repository.UserRepository;
import edu.pl.masi.yellow.utils.exceptions.ForbiddenException;
import edu.pl.masi.yellow.utils.exceptions.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class TestFormatterManagerTest {
    @Mock
    private TestRepository testRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private QuestionManager questionManager;

    @Mock
    private TestManager testManager;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private PrintWriter printWriter;

    private TestFormatterManager testFormatterManager;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        this.testFormatterManager = new TestFormatterManager();

        this.testFormatterManager.setQuestionManager(questionManager);
        this.testFormatterManager.setQuestionRepository(questionRepository);
        this.testFormatterManager.setUserRepository(userRepository);
        this.testFormatterManager.setTestManager(testManager);
        this.testFormatterManager.setTestRepository(testRepository);
    }

    @Test
    public void CanGetCSVResponseAsOwner() throws IOException {
        UserEntity userEntity = new UserEntity("myUser", "password");
        TestEntity test = new TestEntity("simple test", userEntity);

        when(testRepository.findById(0)).thenReturn(test);
        when(questionManager.getAllQuestionsByTest(test)).thenReturn(getSimpleQuestions());
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
        when(printWriter.printf(anyString())).thenReturn(printWriter);

        this.testFormatterManager.getCSVResponse("myUser", 0, httpServletResponse);
        verify(printWriter, times(4)).print(anyString());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void NotFoundExceptionWhenTestNotFound() {
        this.testFormatterManager.getCSVResponse("test", 0, null);
    }

    @Test(expected = ForbiddenException.class)
    public void ForbiddenWhenCSVResponseAsNotAnOwner() throws IOException {
        UserEntity userEntity = new UserEntity("myUser", "password");
        TestEntity test = new TestEntity("simple test", userEntity);

        when(testRepository.findById(0)).thenReturn(test);
        when(questionManager.getAllQuestionsByTest(test)).thenReturn(getSimpleQuestions());
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
        when(printWriter.printf(anyString())).thenReturn(printWriter);

        this.testFormatterManager.getCSVResponse("myUsera", 0, httpServletResponse);
        verify(printWriter, times(4)).print(anyString());

        fail();
    }

    @Test
    public void CanCreatePDFFromTest() {
        UserEntity userEntity = new UserEntity("myUser", "password");
        TestEntity test = new TestEntity("simple test", userEntity);

        when(testRepository.findById(0)).thenReturn(test);
        when(questionManager.getAllQuestionsByTest(test)).thenReturn(getSimpleQuestions());

        this.testFormatterManager.getPDFResponse("myUser", 0);
    }

    @Test(expected = ForbiddenException.class)
    public void CannotGeneratePDFWhenNotAnOwner() {
        UserEntity userEntity = new UserEntity("myUser", "password");
        TestEntity test = new TestEntity("simple test", userEntity);

        when(testRepository.findById(0)).thenReturn(test);
        when(questionManager.getAllQuestionsByTest(test)).thenReturn(getSimpleQuestions());

        this.testFormatterManager.getPDFResponse("myUsera", 0);
    }

    @Test
    public void CanCreateNewTestByUploadCSV() {
        UserEntity userEntity = new UserEntity("myUser", "password");

        when(userRepository.findByUsername("myUser")).thenReturn(userEntity);
        when(questionRepository.save(any())).then((answer) -> {
           QuestionEntity q = answer.getArgument(0);

           assertEquals("DESC", q.getDescription());
           assertEquals("PL", q.getLanguage());
           assertEquals(1, q.getNumber());

           return q;
        });

        assertEquals("Successfully uploaded test file", this.testFormatterManager.uploadCSV(
                "myUser", "1;W;PL;DESC;3;A;B;C", "name").getStatus());
    }


    private List<QuestionEntity> getSimpleQuestions() {
        List<QuestionEntity> list = new ArrayList<>();
        list.add(new QuestionEntity(0,
                "PL", "Desc1", QuestionEntity.QuestionType.CHOICE,
                "a|b|c|e", new TestEntity()));
        list.add(new QuestionEntity(0,
                "PL", "Desc2", QuestionEntity.QuestionType.OPEN,
                "|", new TestEntity()));
        list.add(new QuestionEntity(0,
                "PL", "Desc3", QuestionEntity.QuestionType.SCALE,
                "0|10", new TestEntity()));
        list.add(new QuestionEntity(0,
                "PL", "Desc4", QuestionEntity.QuestionType.CHOICE,
                "a|b|c|e", new TestEntity()));
        return list;
    }

}

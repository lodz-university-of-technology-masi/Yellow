package edu.pl.masi.yellow.manager;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import edu.pl.masi.yellow.entity.QuestionEntity;
import edu.pl.masi.yellow.entity.TestEntity;
import edu.pl.masi.yellow.repository.TestRepository;
import edu.pl.masi.yellow.utils.QuestionToPDFWriter;
import edu.pl.masi.yellow.utils.exceptions.ResourceNotFoundException;
import edu.pl.masi.yellow.utils.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class TestFormatterManager {
    private TestRepository testRepository;
    private QuestionManager questionManager;

    public void getCSVResponse(String name, int testId, HttpServletResponse response) {
        TestEntity test = testRepository.findById(testId);
        if (test == null)
            throw new ResourceNotFoundException();

        if (test.getOwner().getUsername().equals(name)) {
            writeCSVResponse(test, response);
        } else {
            throw new ForbiddenException();
        }
    }

    public ResponseEntity<byte[]> getPDFResponse(String name, int testId) {
        TestEntity test = testRepository.findById(testId);
        if (test == null)
            throw new ResourceNotFoundException();

        if (test.getOwner().getUsername().equals(name)) {
            return writePDFResponse(test);
        } else {
            throw new ForbiddenException();
        }
    }


    private void writeCSVResponse(TestEntity test, HttpServletResponse response) {
        response.setContentType("text/plain; charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            this.questionManager.getAllQuestionsByTest(test).stream()
                    .map(q -> q.toString() + this.getAnswers(q) + "\n")
                    .forEach(line -> writer.print(line));
        } catch (IOException e) {
            throw new ResourceNotFoundException();
        }
    }

    private ResponseEntity<byte[]> writePDFResponse(TestEntity test) {
        try {
            Document result = new Document();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(result, outputStream);

            result.open();

            QuestionToPDFWriter.writeTest(result, test,
                    this.questionManager.getAllQuestionsByTest(test));

            result.close();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData(test.getTestname() + ".pdf",
                    test.getTestname() + ".pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
        } catch (DocumentException e) {
            throw new ForbiddenException();
        }
    }

    private String getAnswers(QuestionEntity q) {
        switch (q.getType()) {
            case OPEN:
            case NUMBER:
                return "|;";
            case CHOICE:
                String[] answers = q.getMetadata().split("\\|");
                return answers.length + ";" + String.join(";", answers) + ";";
            case SCALE:
                return String.join(";", q.getMetadata().split("\\|")) + ";";
        }
        return "";
    }

    @Autowired
    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Autowired
    public void setQuestionManager(QuestionManager questionManager) {
        this.questionManager = questionManager;
    }
}

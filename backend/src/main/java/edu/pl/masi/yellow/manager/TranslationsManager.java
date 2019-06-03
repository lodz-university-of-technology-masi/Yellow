package edu.pl.masi.yellow.manager;

import edu.pl.masi.yellow.ConfigVariables;
import edu.pl.masi.yellow.entity.QuestionEntity;
import edu.pl.masi.yellow.entity.TestEntity;
import edu.pl.masi.yellow.model.request.TranslationRequest;
import edu.pl.masi.yellow.model.response.GenericResponse;
import edu.pl.masi.yellow.repository.QuestionRepository;
import edu.pl.masi.yellow.repository.TestRepository;
import edu.pl.masi.yellow.utils.exceptions.ForbiddenException;
import edu.pl.masi.yellow.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TranslationsManager {
    private TestRepository testRepository;
    private QuestionRepository questionRepository;

    private String key = ConfigVariables.translationKey;

    public GenericResponse translateTest(String name, TranslationRequest request) {
        TestEntity testEntity = testRepository.findById(request.testId);

        if (testEntity == null)
            throw new ResourceNotFoundException();

        if (!testEntity.getOwner().getUsername().equals(name))
            throw new ForbiddenException();

        List<QuestionEntity> questionEntitiesSource = questionRepository.findByTest(testEntity)
                .stream().filter(q -> q.getLanguage().equals(request.fromLang)).collect(Collectors.toList());

        List<QuestionEntity> questionEntitiesDest = questionRepository.findByTest(testEntity)
                .stream().filter(q -> q.getLanguage().equals(request.toLang)).collect(Collectors.toList());

        questionEntitiesSource.stream().filter(q -> questionEntitiesDest.stream()
                                                .map(qd -> qd.getNumber())
                                                .noneMatch(qd -> qd.equals(q.getNumber())))
                .map(q -> translateQuestion(q, request.fromLang, request.toLang))
                .forEach(q -> questionRepository.save(q));

        return new GenericResponse("Test translated");

    }

    private QuestionEntity translateQuestion(QuestionEntity questionEntity, String from, String to) {
        QuestionEntity resultQuestion = new QuestionEntity();

        resultQuestion.setLanguage(to.toUpperCase());
        resultQuestion.setNumber(questionEntity.getNumber());
        resultQuestion.setTest(questionEntity.getTest());
        resultQuestion.setType(questionEntity.getType());

        resultQuestion.setDescription(
                getTranslation(questionEntity.getDescription(), from, to));
        if (questionEntity.getType().equals(QuestionEntity.QuestionType.CHOICE)) {
            String[] choices = questionEntity.getMetadata().split("\\|");
            String[] results = new String[choices.length];

            for (int i = 0; i < choices.length; ++i) {
                results[i] = getTranslation(choices[i], from, to);
            }

            resultQuestion.setMetadata(String.join("|", results));
        } else {
            resultQuestion.setMetadata(questionEntity.getMetadata());
        }

        return resultQuestion;
    }

    private String getTranslation(String text, String from, String to) {
        String language = from.toLowerCase() + "-" + to.toLowerCase();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpEntity<String> request = new HttpEntity<>("text=" + URLEncoder.encode(text, "UTF-8"),
                    headers);
            String answer = restTemplate.postForObject("https://translate.yandex.net/api/v1.5/tr/translate?"
                    + "key=" + key + "&lang=" + language, request, String.class);
            return parseResponse(answer);
        } catch (UnsupportedEncodingException e) {
            return text;
        }
    }

    public static String parseResponse(String response) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            System.out.println(response);

            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(new InputSource(new StringReader(response)));

            System.out.println(document.getDocumentElement().getNodeName());

            XPath xPath = XPathFactory.newInstance().newXPath();
            String answer = (String) xPath.evaluate("/Translation/text/text()", document, XPathConstants.STRING);

            return answer;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    @Autowired
    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }
{

    }
    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

}

package edu.pl.masi.yellow.controller.api.v1.manage;

import edu.pl.masi.yellow.manager.TestManager;
import edu.pl.masi.yellow.model.response.TestDefResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Tests {
    private TestManager testManager;

    @RequestMapping(value = "/api/v1/manage/tests/redactor/{id}",
                    method = RequestMethod.GET)
    public List<TestDefResponse> getAllTestsByRedactor(
            @PathVariable("id") int redactorId) {
        return testManager.getAllTestsByRedactor(redactorId);
    }

    @RequestMapping(value = "/api/v1/manage/tests/id/{id}")
    public TestDefResponse getTestById(@PathVariable("id") int testId) {
        return testManager.getTestById(testId);
    }

    @Autowired
    public void setTestManager(TestManager testManager) {
        this.testManager = testManager;
    }
}

package edu.pl.masi.yellow.utils;

import edu.pl.masi.yellow.utils.exceptions.ForbiddenException;
import edu.pl.masi.yellow.utils.exceptions.ResourceNotFoundException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExceptionTest {
    @Test(expected = ResourceNotFoundException.class)
    public void ThisIsDummyTestForBCHToGTFO_1() {
        ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException();

        assertNotNull(resourceNotFoundException);
        throw resourceNotFoundException;
    }

    @Test(expected = ForbiddenException.class)
    public void ThisIsDummyTestForBCHToGTFO_2() {
        ForbiddenException exception = new ForbiddenException();

        assertNotNull(exception);
        throw exception;
    }
}

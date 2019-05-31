package edu.pl.masi.yellow.model;

import edu.pl.masi.yellow.model.response.GenericResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class GenericResponseTest {
    @Test
    public void CanGenerateGenericResponse() {
        GenericResponse response = new GenericResponse("test response");
        assertNotNull(response);
        assertEquals("test response", response.getStatus());
    }

    @Test
    public void CanChangeResponseStatus() {
        GenericResponse response = new GenericResponse("Old status");
        response.setStatus("newStatus");

        assertEquals("newStatus", response.getStatus());
    }
}

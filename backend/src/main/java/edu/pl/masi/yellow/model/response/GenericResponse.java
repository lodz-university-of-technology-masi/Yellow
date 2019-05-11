package edu.pl.masi.yellow.model.response;

public class GenericResponse {
    private String status;

    public GenericResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

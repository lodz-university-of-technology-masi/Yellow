package edu.pl.masi.yellow.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pl.masi.yellow.entity.QuestionEntity;

public class QuestionDefScaleResponse extends QuestionDefResponse {
    @JsonProperty("minVal")
    private int minimumValue;

    @JsonProperty("maxVal")
    private int maximumValue;

    public QuestionDefScaleResponse(QuestionEntity entity) {
        super(entity);

        String[] meta = entity.getMetadata().split("\\|");

        try {
            this.maximumValue = Integer.valueOf(meta[1]);
            this.minimumValue = Integer.valueOf(meta[0]);
        } catch (Exception e) {
            this.maximumValue = 10;
            this.minimumValue = 0;
        }
    }
}

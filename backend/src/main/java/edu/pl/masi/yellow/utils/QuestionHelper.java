package edu.pl.masi.yellow.utils;

import edu.pl.masi.yellow.entity.QuestionEntity;

import java.io.IOException;

public class QuestionHelper {
    public static QuestionEntity fromCSVLine(String line) throws IOException {
        QuestionEntity questionEntity = new QuestionEntity();
        String values[] = line.split(";");

        questionEntity.setNumber(Integer.valueOf(values[0]));
        questionEntity.setType(questionCharToType(values[1]));

        questionEntity.setLanguage(values[2]);
        questionEntity.setDescription(values[3]);

        String metadata = buildQuestionMetadate(values, questionEntity.getType());

        questionEntity.setMetadata(metadata);

        return questionEntity;
    }

    public static QuestionEntity.QuestionType questionCharToType(String type) throws IOException {
        if (type.length() > 1)
            throw new IOException("Unkonown question type");

        switch (type.charAt(0)) {
            case 'O':
                return QuestionEntity.QuestionType.OPEN;
            case 'W':
                return QuestionEntity.QuestionType.CHOICE;
            case 'S':
                return QuestionEntity.QuestionType.SCALE;
            case 'L':
                return QuestionEntity.QuestionType.NUMBER;
        }

        throw new IOException("Unkonown question type");
    }

    private static String buildQuestionMetadate(String[] values, QuestionEntity.QuestionType type) throws IOException {
        StringBuilder builder = new StringBuilder();

        if (type.equals(QuestionEntity.QuestionType.CHOICE)) {
            int numberOfAnswer = Integer.valueOf(values[4]);
            if (numberOfAnswer != values.length - 5)
                throw new IOException("Missing delimiter | for possible answer");

            for (int i = 5; i < values.length - 1; ++i)
                builder.append(values[i]).append("|");
            builder.append(values[values.length - 1]);

        } else if(type.equals(QuestionEntity.QuestionType.SCALE)) {
            builder.append("0|10");
        }

        return builder.toString();
    }
}

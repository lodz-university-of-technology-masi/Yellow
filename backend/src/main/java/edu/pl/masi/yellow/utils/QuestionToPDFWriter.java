package edu.pl.masi.yellow.utils;

import com.itextpdf.text.*;
import edu.pl.masi.yellow.entity.QuestionEntity;
import edu.pl.masi.yellow.entity.TestEntity;
import edu.pl.masi.yellow.utils.exceptions.ResourceNotFoundException;

import java.util.List;

public class QuestionToPDFWriter {
    private static final Font headerFont = FontFactory.getFont(FontFactory.COURIER, 32, BaseColor.BLACK);
    private static final Font sectionFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 16, BaseColor.BLACK);
    private static final Font textFont = FontFactory.getFont(FontFactory.COURIER, 11, BaseColor.BLACK);

    public static void writeTest(Document document, TestEntity test, List<QuestionEntity> questions) {
        try {
            document.setMargins(0.5f, 0.5f, 50.0f, 50.0f);
            document.add(new Paragraph(test.getTestname(), headerFont));

            questions.forEach(q -> writeQuestion(document, q));


        } catch (DocumentException e) {
            throw new ResourceNotFoundException();
        }
    }

    private static void writeQuestion(Document document, QuestionEntity q) {
        try {
            document.add(new Paragraph(q.getDescription(), sectionFont));

            if (q.getType().equals(QuestionEntity.QuestionType.CHOICE)) {
                for (String s : q.getMetadata().split("\\|")) {
                    document.add(new Paragraph("- " + s, textFont));
                }
            }

        } catch (DocumentException e) {
            throw new ResourceNotFoundException();
        }
    }
}

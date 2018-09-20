package it.gattifederico.demoDTO.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.gattifederico.demoDTO.model.Exam;

public interface ExamProjection {

    Exam getExam();

    String getTitle();

    String getDescription();

    default String getQualcosa() {
        return this.getDescription().concat(" iiii");
    }
}

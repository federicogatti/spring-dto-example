package it.gattifederico.demoDTO.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.gattifederico.demoDTO.model.Exam;
import it.gattifederico.demoDTO.model.projection.ExamProjection;

public class ExamDTO implements ExamProjection {

    @JsonIgnore
    private Exam exam;

    public ExamDTO(ExamProjection examProjection) {
        this.exam = examProjection.getExam();
    }

    @Override
    public Exam getExam() {
        return exam;
    }

    @Override
    public String getTitle() {
        return exam.getTitle();
    }

    @Override
    public String getDescription() {
        return exam.getDescription();
    }

}

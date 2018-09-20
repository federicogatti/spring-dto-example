package it.gattifederico.demoDTO.repository;

import it.gattifederico.demoDTO.model.Exam;
import it.gattifederico.demoDTO.model.projection.ExamProjection;
import it.gattifederico.demoDTO.model.projection.ExamProjection1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Override
    @RestResource(exported = false)
    <S extends Exam> S save(S s);

    @RestResource(exported = false)
    @Query("select e as exam from Exam as e")
    Collection<ExamProjection> findAllCustom();

    @RestResource(exported = false)
    Collection<ExamProjection1> findByTitle(String title);
}
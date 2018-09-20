package it.gattifederico.demoDTO.rest;

import it.gattifederico.demoDTO.model.Exam;
import it.gattifederico.demoDTO.model.dto.ExamDTO;
import it.gattifederico.demoDTO.model.projection.ExamProjection;
import it.gattifederico.demoDTO.model.dto.ExamCreationDTO;
import it.gattifederico.demoDTO.model.dto.ExamUpdateDTO;
import it.gattifederico.demoDTO.repository.ExamRepository;
import it.gattifederico.demoDTO.util.DTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RepositoryRestController
@RequestMapping("/exams")
public class ExamController {

    @NonNull
    private ExamRepository examRepository;

    @NonNull
    private RepositoryEntityLinks links;

    @NonNull
    private PagedResourcesAssembler<ExamProjection> assembler;

    /*public ExamController(ExamRepository examRepository,
                          RepositoryEntityLinks repositoryEntityLinks) {
        this.examRepository = examRepository;
        this.links = repositoryEntityLinks;
    }*/

    @GetMapping("/projections")
    public ResponseEntity<?> getExams() {
        Collection<ExamProjection> exams = this.examRepository.findAllCustom();
        Link listSelfLink = links.linkFor(Exam.class).slash("/dto").withSelfRel();

        List<?> resources = exams.stream().map(this::toResource).collect(toList());

        return ResponseEntity.ok(new Resources<>(resources, listSelfLink));
    }

    @GetMapping("/search/findByTitle")
    public ResponseEntity<?> getPageExams(@Param("title") String title) {
        return ResponseEntity.ok(examRepository.findByTitle(title));
    }

    @PostMapping("/cst")
    public ResponseEntity<?> newExam(@DTO(ExamCreationDTO.class) Exam exam) {
        return ResponseEntity.ok(examRepository.save(exam));
    }

    @PutMapping("/cst")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> editExam(@DTO(ExamUpdateDTO.class) Exam exam) {
        return ResponseEntity.ok(examRepository.save(exam));
    }

    private ResourceSupport toResource(ExamProjection examProjection) {

        ExamDTO examDTO = new ExamDTO(examProjection);
        Link examLink = links.linkForSingleResource(examDTO.getExam()).withRel("exam");
        Link selfLink = links.linkForSingleResource(examDTO.getExam()).slash("/dto").withSelfRel();

        return new Resource<>(examDTO, examLink, selfLink);
    }
}
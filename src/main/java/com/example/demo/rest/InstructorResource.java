package com.example.demo.rest;

import com.example.demo.domain.Instructor;
import com.example.demo.service.InstructorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(
        origins = {"*"},
        allowedHeaders = {"*"},
        exposedHeaders =  {"X-demo-alert", "X-demo-params", "X-demo-error"},
        maxAge = 3600)
public class InstructorResource {

    private final Logger log = LoggerFactory.getLogger(InstructorResource.class);

    private static final String ENTITY_NAME = "instructor";

    @Autowired
    private InstructorService instructorService;

    /**
     * GET  /instructors : get all the instructors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of instructors in body
     */
    @GetMapping("/instructors")
    public List<Instructor> getAllInstructors() {
        log.debug("REST request to get all Instructors");
        return instructorService.findAll();
    }

    /**
     * GET  /instructors/:id : get the "id" instructor.
     *
     * @param id the id of the instructor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the instructor, or with status 404 (Not Found)
     */
    @GetMapping("/instructors/{id}")
    public ResponseEntity<Instructor> getInstructor(@PathVariable Long id) {
        log.debug("REST request to get Instructor : {}", id);
        Optional<Instructor> instructor = instructorService.findOne(id);
        return instructor
                .map(i -> ResponseEntity.ok().body(i))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST  /instructors : Create a new instructor.
     *
     * @param instructor the instructor to create
     * @return the ResponseEntity with status 201 (Created) and with body the new instructor, or with status 400 (Bad Request) if the instructor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/instructors")
    public ResponseEntity<Instructor> createInstructor(@Valid @RequestBody Instructor instructor) throws URISyntaxException {
        System.out.println("InstructorResource.createInstructor");
        System.out.println("instructor = [" + instructor + "]");
        log.debug("REST request to save Instructor : {}", instructor);
        if (instructor.getId() != null) {
            return ResponseEntity.badRequest().body(instructor);
        }
        Instructor result = instructorService.save(instructor);
        return ResponseEntity.created(new URI("/api/instructors/" + result.getId())).body(instructor);
    }

    /**
     * PUT  /instructor : Updates an existing instructor.
     *
     * @param instructor the instructor to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated instructor,
     * or with status 400 (Bad Request) if the instructor is not valid,
     * or with status 500 (Internal Server Error) if the instructor couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/instructors")
    public ResponseEntity<Instructor> updateInstructor(@Valid @RequestBody Instructor instructor) throws URISyntaxException {
        log.debug("REST request to update Instructor : {}", instructor);
        if (instructor.getId() == null) {
            return createInstructor(instructor);
        }
        Instructor result = instructorService.save(instructor);
        return ResponseEntity.ok().body(result);
    }

    /**
     * DELETE  /instructors/:id : delete the "id" instructor.
     *
     * @param id the id of the instructor to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/instructors/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        log.debug("REST request to delete Instructor : {}", id);
        instructorService.delete(id);
        return ResponseEntity.ok().build();
    }

}

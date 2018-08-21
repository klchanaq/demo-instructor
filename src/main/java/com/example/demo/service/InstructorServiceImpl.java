package com.example.demo.service;

import com.example.demo.domain.Instructor;
import com.example.demo.repository.InstructorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class InstructorServiceImpl implements InstructorService {

    private final Logger log = LoggerFactory.getLogger(InstructorServiceImpl.class);

    @Autowired
    private InstructorRepository instructorRepository;

    public InstructorServiceImpl() {
    }

    /*

    private final InstructorRepository instructorRepository;

    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }
    */

    /**
     * Save a instructor.
     *
     * @param instructor the entity to save
     * @return the persisted entity
     */
    @Override
    public Instructor save(Instructor instructor) {
        log.debug("Request to save Instructor : {}", instructor);
        return instructorRepository.save(instructor);
    }

    /**
     * Get all the instructors.
     *
     * @return the collection of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Instructor> findAll() {
        log.debug("Request to get all Instructors");
        return instructorRepository.findAll();
    }

    /**
     * Get the "id" instructor.
     *
     * @param id the id of the entity
     * @return the optional entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Instructor> findOne(Long id) {
        log.debug("Request to get find an Instructor id: {}", id);
        return instructorRepository.findById(id);
    }

    /**
     * Delete the "id" instructor.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to get delete an Instructor : {}", id);
        instructorRepository.deleteById(id);
    }


}

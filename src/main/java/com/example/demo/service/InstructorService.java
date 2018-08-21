package com.example.demo.service;

import com.example.demo.domain.Instructor;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing com.example.demo.domain.Instructor.
 */
public interface InstructorService {

    /**
     * Save a instructor.
     *
     * @param instructor the entity to save
     * @return the persisted entity
     */
    Instructor save(Instructor instructor);

    /**
     * Get all the instructors.
     *
     * @return the collection of entities
     */
    List<Instructor> findAll();

    /**
     * Get the "id" instructor.
     *
     * @param id the id of the entity
     * @return the optional entity
     */
    Optional<Instructor> findOne(Long id);

    /**
     * Delete the "id" instructor.
     *
     * @param id the entity id to delete
     */
    void delete(Long id);

}
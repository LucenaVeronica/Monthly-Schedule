package com.monthlySchedule.repositories;

import com.monthlySchedule.models.Instructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository
 * @author Veronica Lucena
 */
@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Integer> {
    
}

package com.monthlySchedule.repositories;

import com.monthlySchedule.models.Event;
import com.monthlySchedule.models.Instructor;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository
 * @author Veronica Lucena
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
    
    public abstract ArrayList<Event> findByInstructor_Id(Integer id);
}

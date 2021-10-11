package com.monthlyschedule.services;

import com.monthlySchedule.models.Event;
import com.monthlySchedule.models.Instructor;
import com.monthlySchedule.repositories.InstructorRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic
 * @author Veronica Lucena
 */
@Service
public class InstructorService {
    
    @Autowired
    InstructorRepository instructorRepository;
    
    /**
     * Method to consult the instructors of the organization.
     * @return A list of instructors.
    */
    public ArrayList<Instructor> getInstructors(){
        ArrayList<Instructor> instructors = new ArrayList<>();
        
        instructors = (ArrayList<Instructor>)instructorRepository.findAll();
        if(instructors.isEmpty()){
            instructors = createInstructors();
        }
        return (ArrayList<Instructor>)instructorRepository.findAll();
    }
    
    /**
     * Method to consult a instructor.
     * @param id Instructor identifier.
     * @return A instructor.
    */
    public Optional<Instructor> getInstructorById(Integer id){
        return instructorRepository.findById(id);
    }

    /**
     * Method to save a instructor.
     * @param instructor Instructor data to be stored.
     * @return The data that were saved.
    */
    public Instructor saveInstructor(Instructor instructor){
        return instructorRepository.save(instructor);
    }
    
    /**
     * Method to delete a instructor.
     * @param id Instructor identifier.
     * @return boolean value.
    */
   public boolean deleteInstructor(Integer id) {
        try{
            instructorRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
   
    /**
     * Method to create default instructors.
     * @return A list of instructors.
    */
    private ArrayList<Instructor> createInstructors(){
        
        ArrayList<Instructor> instructors = new ArrayList<>();
        instructors.add(saveInstructor(new Instructor("Natalia","G\u00f3mez","04/04/1993")));
        instructors.add(saveInstructor(new Instructor("Andrea","Morales","02/07/1991")));
        instructors.add(saveInstructor(new Instructor("Karina","Sequeda","05/06/1997")));
        instructors.add(saveInstructor(new Instructor("Esteban","Blanco","11/05/1994")));
        instructors.add(saveInstructor(new Instructor("Carlos","Luna","10/03/1990")));
        
        return instructors;
    }
    
    /**
     * Method to calculate the overall duration of events of an instructor.
     * @param id Instructor identifier.
     * @return overall duration.
    */
    public Long getDurationEvents(Integer id){
        Optional<Instructor> instructor = instructorRepository.findById(id);
        Long overall = 0L;  
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if(instructor.isPresent()){
            if(instructor.get().getEvents() != null){
                for(Event event: instructor.get().getEvents()){                    
                    overall +=  Math.abs(DAYS.between(LocalDate.parse(event.getEndDate(), formatter).atStartOfDay(), LocalDate.parse(event.getStartDate(), formatter).atStartOfDay()));
                }
            }   
        }
        return overall;
    }
}

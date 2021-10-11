package com.monthlyschedule.services;

import com.monthlySchedule.models.Event;
import com.monthlySchedule.models.Instructor;
import com.monthlySchedule.repositories.EventRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic
 * @author Veronica Lucena
 */
@Service
public class EventService {
    
    @Autowired
    EventRepository eventRepository;
    
    @Autowired
    private InstructorService instructorService;
    
    /**
     * Method to consult the events of the organization.
     * @return A list of events.
    */
    public ArrayList<Event> getEvents(){
        return (ArrayList<Event>)eventRepository.findAll();
    }

    /**
     * Method to consult events of a instructor.
     * @return A list of events.
    */
    public ArrayList<Event> getEventByInstructor(Integer id){
        ArrayList<Event> events = new ArrayList<>();
        events = (ArrayList<Event>)eventRepository.findByInstructor_Id(id);
        if(events.isEmpty()){
            events = createEvents(instructorService.getInstructorById(id).get());
        }
        return events;
    }
    
    /**
     * Method to consult a event.
     * @param id event identifier.
     * @return A event.
    */
    public Optional<Event> getEventById(Integer id){
        return eventRepository.findById(id);
    }
    
    /**
     * Method to save a event.
     * @param event Event data to be stored.
     * @return The data that were saved.
    */
    public Event saveEvent(Event event){
        return eventRepository.save(event);
    }
    
    
    /**
     * Method to delete a event.
     * @param id Event identifier.
     * @return boolean value.
    */
    public boolean deleteEvent(Integer id) {
        try{
            eventRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    /**
     * Method to create random events.
     * @param instructor. 
     * @return A list of events.
    */
    public ArrayList<Event> createEvents(Instructor  instructor){
        ArrayList<Event> events = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");       
        LocalDate today = LocalDate.now();
        Integer lastDay =  Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH); 
        LocalDate randomDate1 = getRadomDate(today, today.plusDays(lastDay-today.getDayOfMonth()));
        LocalDate randomDate2 = getRadomDate(today, today.plusDays(lastDay-today.getDayOfMonth()));
        LocalDate randomDate3 = getRadomDate(today, today.plusDays(lastDay-today.getDayOfMonth()));
        LocalDate randomDate4 = getRadomDate(today, today.plusDays(lastDay-today.getDayOfMonth()));        
        events.add(saveEvent(new Event( "Week off", "Holiday", formatter.format(randomDate1), formatter.format(randomDate1.plusDays(8)), instructor)));
        events.add(saveEvent(new Event( "Seminar", "Data analysis", formatter.format(randomDate2), formatter.format(randomDate2.plusDays(8)), instructor)));
        events.add(saveEvent(new Event( "Seminar", "Social and political networks", formatter.format(randomDate3), formatter.format(randomDate3.plusDays(8)), instructor)));
        events.add(saveEvent(new Event( "Project", "Project management", formatter.format(randomDate4), formatter.format(randomDate4.plusDays(8)), instructor)));
        return events;  
    }
    
    /**
     * Method to generate random dates.
     * @return A random LocalDate.
    */
    private LocalDate getRadomDate(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }
}

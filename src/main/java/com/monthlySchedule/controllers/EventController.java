package com.monthlySchedule.controllers;

import com.monthlySchedule.models.Event;
import com.monthlySchedule.models.Instructor;
import com.monthlyschedule.services.EventService;
import com.monthlyschedule.services.InstructorService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest API
 * @author Veronica Lucena
 */
@Controller
@RequestMapping
public class EventController {
    
    @Autowired
    private EventService eventService;  
    
    @Autowired
    private InstructorService instructorService;
        
    @GetMapping("/list/{id}")
    public String getEvents(@PathVariable("id") Integer id, Model model){
        ArrayList<Event> events =  eventService.getEventByInstructor(id);
        model.addAttribute("events", events);
        model.addAttribute("overall", instructorService.getDurationEvents(id));
        return "events";
    }

    @GetMapping("/create/{id}")
    public String createEvent(@PathVariable("id") Integer id, Model model){
        Event event = new Event();
        event.setInstructor(new Instructor());
        event.getInstructor().setId(id);
        model.addAttribute("event",event );
        return "create";
    }
    
    @PostMapping("/save")
    public String seveEvent(Event event, Model model){
        Integer id = event.getInstructor().getId();
        event.setInstructor(instructorService.getInstructorById(id).get());
        eventService.saveEvent(event);
        return "redirect:/instructor";
    }
    
    @GetMapping("/update/{id}")
    public String updateEvent(@PathVariable("id") Integer id, Model model){
        Optional<Event> event =  eventService.getEventById(id);
        model.addAttribute("event",event.get() );
        return "create";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable("id") Integer id, Model model){
        eventService.deleteEvent(id);
        return "redirect:/instructor";
    }
    
}

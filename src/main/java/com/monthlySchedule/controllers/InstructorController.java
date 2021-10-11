package com.monthlySchedule.controllers;

import com.monthlySchedule.models.Instructor;
import com.monthlyschedule.services.InstructorService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Rest API
 * @author Veronica Lucena
 */
@Controller
@RequestMapping
public class InstructorController {
    
    @Autowired
    private InstructorService service;  
    
    
    @GetMapping("/instructor")
    public String getInstructors(Model model){
        ArrayList<Instructor> instructors =  service.getInstructors();
        model.addAttribute("instructors", instructors);
        return "index";
    }
}

package com.monthlySchedule.models;

import java.util.Objects;
import javax.persistence.*;

/**
 * Represents an event
 * @author Veronica Lucena
 */
@Entity
@Table(name = "event")
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name="id_event")
    //Primary key
    private Integer id;
    
    private String type;
    
    private String description;
    
    private String startDate;
    
    private String endDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_instructor")
    private Instructor instructor;

    public Event() {
    }

    public Event(String type, String description, String startDate, String endDate, Instructor instructor) {
        this.type = type;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructor = instructor;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    } 

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", type=" + type + ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", instructor=" + instructor + '}';
    }
    
    
}
